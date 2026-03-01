package eternalexpedition.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eternalexpedition.actions.ExhaustAndDamageAction;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.util.CardStats;

public class InHisName extends BaseCard {
    public static final String ID = makeID("InHisName");
    private static final int BASE_COST = 30;

    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            BASE_COST
    );

    public InHisName() {
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
    public void applyPowers() {
        int det = getDeterminationAmount();
        if (det > 0) {
            this.costForTurn = Math.max(0, this.cost / det);
            this.isCostModified = true;
        } else {
            this.costForTurn = this.cost;
            this.isCostModified = false;
        }
        super.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAndDamageAction(p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InHisName();
    }
}
