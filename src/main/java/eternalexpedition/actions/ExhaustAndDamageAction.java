package eternalexpedition.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eternalexpedition.powers.DeterminationPower;

import java.util.ArrayList;

public class ExhaustAndDamageAction extends AbstractGameAction {
    private final AbstractPlayer player;
    private boolean screenOpened = false;

    public ExhaustAndDamageAction(AbstractPlayer player) {
        this.player = player;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (player.hand.isEmpty()) {
            dealDamage(0);
            isDone = true;
            return;
        }

        if (!screenOpened) {
            AbstractDungeon.handCardSelectScreen.open(
                    "Choose cards to exhaust",
                    player.hand.size(),
                    true,   // isAllValid
                    false,  // isntCancellable — allow confirming with 0
                    true    // canPickZero
            );
            screenOpened = true;
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) return;

        // Exhaust selected cards and count them
        int count = AbstractDungeon.handCardSelectScreen.selectedCards.group.size();
        for (AbstractCard card : new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group)) {
            player.hand.moveToExhaustPile(card);
        }
        AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

        dealDamage(count);
        isDone = true;
    }

    private void dealDamage(int count) {
        int det = 0;
        AbstractPower power = player.getPower(DeterminationPower.ID);
        if (power != null) det = power.amount;

        // Formula: (count + 1) * 1.1 * (100 + det) / 100
        int dmg = (int) ((count + 1) * 1.1 * (100 + det) / 100.0);

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(
                        monster,
                        new DamageInfo(player, dmg, DamageInfo.DamageType.NORMAL),
                        AttackEffect.FIRE));
            }
        }
    }
}
