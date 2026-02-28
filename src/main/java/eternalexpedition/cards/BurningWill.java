package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.util.CardStats;

import java.util.Arrays;

public class BurningWill extends BaseCard {
    public static final String ID = makeID("BurningWill");
    private static final int COST = 2;
    private static final int MULTIPLIER = 4;
    private static final int MULTIPLIER_UPGRADE = 2; // upgraded: 6

    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            COST
    );

    public BurningWill() {
        super(ID, INFO);
        setMagic(MULTIPLIER, MULTIPLIER_UPGRADE);
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
        addToBot(new ExhaustAction(1, false, false));
        int count = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        int[] dmgs = new int[count];
        Arrays.fill(dmgs, this.damage);
        addToBot(new DamageAllEnemiesAction(p, dmgs, damageTypeForTurn,
                AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurningWill();
    }
}
