package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.utility.UpgradeAllCardsInHandAction;
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
            CardTarget.NONE,
            3
    );

    public Audience() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Reduce cost of all hand cards by 1 this turn
        for (AbstractCard card : p.hand.group) {
            card.modifyCostForTurn(-1);
        }
        // Upgrade all hand cards
        addToBot(new UpgradeAllCardsInHandAction(p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Audience();
    }
}
