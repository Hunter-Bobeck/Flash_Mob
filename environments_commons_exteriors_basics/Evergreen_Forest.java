package environments_commons_exteriors_basics;

import utilities.*;

public class Evergreen_Forest extends Common_Exterior_Basic
{
    public Evergreen_Forest()
	{
		super();

		water = true;
		context = "in an evergreen forest.";
		description = Random.text_of(new String[] {"Pine needles cover the ground.", "The scent of pine is crisp and strong."});
		large_character_placements = new String[] {"beside a stump", "next to a tree", "behind a tree", "next to a fallen tree", "next to a pile of sticks", "next to a stream", "by a small boulder", "in a grassy clearing", "next to some flowering plants"};
		nonlarge_character_placements = new String[] {"in some bushes"};
		noncharacter_placements = new String[] {"inside of a hollow log", "on the needle-covered ground", "in the dirt", "on the grass", "pressed into the ground of the forest", "next to a loose stick"};
		moba = moba_forest;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}