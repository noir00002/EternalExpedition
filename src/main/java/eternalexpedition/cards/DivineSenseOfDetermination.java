package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DivineSensePower;
import eternalexpedition.util.CardStats;

public class DivineSenseOfDetermination extends BaseCard {
    public static final String ID = makeID("DivineSenseOfDetermination");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public DivineSenseOfDetermination() {
        super(ID, INFO);
        setMagic(1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DivineSensePower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DivineSenseOfDetermination();
    }
}
