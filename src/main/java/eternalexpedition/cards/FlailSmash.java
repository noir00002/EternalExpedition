package eternalexpedition.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.util.CardStats;

public class FlailSmash extends BaseCard {
    public static final String ID = makeID("FlailSmash");
    private static final CardStats INFO = new CardStats(
            Paladin.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public FlailSmash() {
        super(ID, INFO);
        setDamage(13, 4);
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
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SMASH));
        int det = getDeterminationAmount();
        if (det > 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, det), det));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlailSmash();
    }
}
