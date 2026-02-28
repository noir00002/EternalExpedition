package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.powers.FearPower;
import eternalexpedition.util.CardStats;

public class PerformPower extends BaseCard {
    public static final String ID = makeID("PerformPower");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public PerformPower() {
        super(ID, INFO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower det = p.getPower(DeterminationPower.ID);
        if (det != null && det.amount > 2) {
            addToBot(new ReducePowerAction(p, p, DeterminationPower.ID, 1));
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(monster, p, new FearPower(monster, 2), 2));
                }
            }
        }
    }

    @Override
    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PerformPower();
    }
}

