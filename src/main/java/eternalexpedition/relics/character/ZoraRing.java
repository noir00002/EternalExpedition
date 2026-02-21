package eternalexpedition.relics.character;

import eternalexpedition.character.Paladin;
import eternalexpedition.relics.BaseRelic;

import static eternalexpedition.EEMod.makeID;

public class ZoraRing extends BaseRelic {
    private static final String NAME = "ZoraRing"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //@SpireEnum(name = "GRAY") @SuppressWarnings("unused")
    //public static final RelicType customRelicType = GRAY;

    public ZoraRing() {super(ID, NAME, Paladin.Meta.CARD_COLOR, RARITY, SOUND);
    }
}
