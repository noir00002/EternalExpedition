package eternalexpedition.relics.character;

import eternalexpedition.character.MyCharacter;
import eternalexpedition.relics.BaseRelic;

import static eternalexpedition.BasicMod.makeID;

public class ZoraRing extends BaseRelic {
    private static final String NAME = "Zora Ring"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public ZoraRing() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }
}
