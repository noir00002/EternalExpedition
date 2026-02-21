package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.util.CardStats;

public class BurningWill extends BaseCard {
    public static final String ID = makeID("BurningWill");
    private static final int COST = 2;
    private static final int DAMAGE_PER_STACK = 7;
    private static final int DAMAGE_PER_STACK_UPGRADE = 3; // upgraded: 10 per stack

    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            COST
    );

    public BurningWill() {
        super(ID, INFO);
        setMagic(DAMAGE_PER_STACK, DAMAGE_PER_STACK_UPGRADE);
        this.isMultiDamage = true;
        this.baseDamage = this.damage = 0;
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
        this.baseDamage = this.magicNumber * getDeterminationAmount();
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        this.baseDamage = this.magicNumber * getDeterminationAmount();
        super.calculateCardDamage(m);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn,
                AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p, p, new DeterminationPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurningWill();
    }
}
