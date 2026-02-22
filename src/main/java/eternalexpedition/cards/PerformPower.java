package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
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

    private int getDeterminationAmount() {
        if (AbstractDungeon.player != null) {
            AbstractPower power = AbstractDungeon.player.getPower(DeterminationPower.ID);
            if (power != null) return power.amount;
        }
        return 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int det = getDeterminationAmount();
        if (det > 0) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, det, false), det));
                    addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, det, false), det));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PerformPower();
    }
}
