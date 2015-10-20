package environments_commons_interiors_basics;

import utilities.*;

public class Old_Church extends Common_Interior_Basic
{
    public Old_Church()
	{
		super();

		context = "inside an old church.";
		description = Random.text_of(new String[] {"There are some bones in here, piled into the corner", "Several pues line the aisles of this small church, and an altar stands at the end", "Some sort of cult sigil banners are hung on the walls in here"})+".";
		large_character_placements = new String[] {"at the base of the altar", "on the raised deck in the back of the hall", "in the aisle", "by the grand doorway", "in the entryway"};
		nonlarge_character_placements = new String[] {"on the seat of the pue"};
		noncharacter_placements = new String[] {"on the wooden floor", "next to some bones piled in the corner"};
		moba = new String[] {Random.text_of(moba_gremlin), Random.text_of(moba_crypt), Random.text_of(moba_cultists)};
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}