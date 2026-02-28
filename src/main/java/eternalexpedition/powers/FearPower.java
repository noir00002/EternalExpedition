package eternalexpedition.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static eternalexpedition.EEMod.makeID;

public class FearPower extends BasePower {
    public static final String ID = makeID("Fear");

    public FearPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, owner, new VulnerablePower(owner, amount, false), amount));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, owner, new WeakPower(owner, amount, false), amount));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, owner, new FrailPower(owner, amount, false), amount));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(owner, owner, ID, 1));
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
