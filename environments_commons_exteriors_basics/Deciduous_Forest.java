package environments_commons_exteriors_basics;

import utilities.*;

public class Deciduous_Forest extends Common_Exterior_Basic
{
    public Deciduous_Forest()
	{
		super();

		water = true;
		context = "in a deciduous forest.";
		description = Random.text_of(new String[] {"Some trees here seem to have been cut down recently.", "The forest seems to be ancient.", "Birds are calling from tree to tree."});
		large_character_placements = new String[] {"beside a stump", "next to a fallen tree", "with a pile of sticks", "in some bushes", "next to a stream", "by a small boulder", "in a grassy clearing", "next to some flowering plants"};
		noncharacter_placements = new String[] {"under a pile of leaves", "inside of a hollow log", "on the leaf-covered ground", "in the dirt", "on the grass", "pressed into the ground of the forest", "next to a loose stick"};
		moba = moba_forest;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_stirge_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Stirge"}));
	}
}