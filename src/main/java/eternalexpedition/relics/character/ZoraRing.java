package eternalexpedition.relics.character;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import eternalexpedition.character.Paladin;
import eternalexpedition.powers.DeterminationPower;
import eternalexpedition.relics.BaseRelic;

import static eternalexpedition.EEMod.makeID;

public class ZoraRing extends BaseRelic {
    private static final String NAME = "ZoraRing";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public ZoraRing() {
        super(ID, NAME, Paladin.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        AbstractDungeon.player,
                        AbstractDungeon.player,
                        new DeterminationPower(AbstractDungeon.player, 1),
                        1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
