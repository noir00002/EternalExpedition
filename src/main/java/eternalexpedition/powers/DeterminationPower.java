package eternalexpedition.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static eternalexpedition.EEMod.makeID;

public class DeterminationPower extends BasePower {
    public static final String ID = makeID("Determination");

    public DeterminationPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            CardCrawlGame.sound.play("HEAL_3");
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(this.owner, this.owner, this.amount));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
