package environments_commons_interiors_basics;

import utilities.*;

public class Underdark_Crawlspace extends Common_Interior_Basic
{
    public Underdark_Crawlspace()
	{
		super();

		context = "within a crawlspace of the Underdark.";
		description = Random.text_of(new String[] {"The crawlspace is very tight, affording you no room to turn around.", "There is no light in here, only uncomfortable rock to squeeze through."});
		nonlarge_character_placements = new String[] {"aside the rock wall", "aside the stone wall", "aside the stippled wall", "between two crevices", "between a rock and a hard place", "closer to the entrance", "outside the crawlspace opening", "squeezed into the space, blocking the way through", "near the back", "in the hole to another passage"};
		moba = moba_underground_nonlarge_with_gremlin_nonlarge;
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
	}
}