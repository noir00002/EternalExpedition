package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eternalexpedition.character.Paladin;
import eternalexpedition.util.CardStats;

public class PaladinDefend extends BaseCard {
    public static final String ID = makeID("PaladinDefend");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    public PaladinDefend() {
        super(ID, INFO);
        setBlock(5, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PaladinDefend();
    }
}
