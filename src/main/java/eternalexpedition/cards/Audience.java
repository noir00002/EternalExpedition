package eternalexpedition.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eternalexpedition.character.Paladin;
import eternalexpedition.util.CardStats;

public class Audience extends BaseCard {
    public static final String ID = makeID("Audience");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public Audience() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : p.hand.group) {
            card.modifyCostForTurn(-1);
            if (!card.upgraded) {
                card.upgrade();
                card.superFlash();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Audience();
    }
}
