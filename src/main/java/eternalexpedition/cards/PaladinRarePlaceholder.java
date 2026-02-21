package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eternalexpedition.character.Paladin;
import eternalexpedition.util.CardStats;

public class PaladinRarePlaceholder extends BaseCard {
    public static final String ID = makeID("PaladinRarePlaceholder");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public PaladinRarePlaceholder() {
        super(ID, INFO);
        setMagic(3, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PaladinRarePlaceholder();
    }
}
