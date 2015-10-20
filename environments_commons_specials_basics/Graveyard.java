package environments_commons_specials_basics;

import utilities.*;

public class Graveyard extends Common_Special_Basic
{
    public Graveyard()
	{
		super();

		context = "in a graveyard.";
		description = Random.text_of(new String[] {"The graveyard is small, but packed with tombstones. A mausoleum is erected in the center", "A fence lines the perimeter of the graveyard, an unsuccessful attempt to keep vandals away", "Most of the tombstones look to be undamaged, but one grave is opened and empty inside"})+".";
		large_character_placements = new String[] {"next to a tombstone", "at the foot of the mausoleum", "by a simple cairn", "on a decorative pedestal", "in front of the swinging gate", "beside the tall fence", "on the stone walkway"};
		nonlarge_character_placements = new String[] {"in an opened and otherwise empty grave"};
		noncharacter_placements = new String[] {"next to some flowers for a grave", "on the ground"};
		moba = Array.combine(moba_crypt, mobs_permute(new String[] {"Gargoyle", "Stone_Golem", "Otyugh"}));
		if (Random.whole() <= .6)		moba = Array.combine(moba, moba_cultists);
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}