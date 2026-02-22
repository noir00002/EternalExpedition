package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.util.CardStats;

public class Crusader extends BaseCard {
    public static final String ID = makeID("Crusader");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Crusader() {
        super(ID, INFO);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int total = 0;

        if (p.hasPower(WeakPower.POWER_ID)) {
            total += p.getPower(WeakPower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
        }
        if (p.hasPower(VulnerablePower.POWER_ID)) {
            total += p.getPower(VulnerablePower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, VulnerablePower.POWER_ID));
        }
        if (p.hasPower(FrailPower.POWER_ID)) {
            total += p.getPower(FrailPower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        }

        if (total > 0) {
            addToBot(new ApplyPowerAction(p, p, new DeterminationPower(p, total), total));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Crusader();
    }
}
