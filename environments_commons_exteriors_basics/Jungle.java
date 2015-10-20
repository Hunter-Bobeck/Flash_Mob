package environments_commons_exteriors_basics;

import utilities.*;

public class Jungle extends Common_Exterior_Basic
{
    public Jungle()
	{
		super();

		water = true;
		context = Random.text_of(new String[] {"inside of a jungle.", "inside of a rainforest."});
		description = Random.text_of(new String[] {"Vines, moss, tall grass, and flowers are abundant..", "There is an awful stink in this area of the jungle.", "A nearby plant is snapping bugs out of the air.", "Mahogany trees tower above you, their canopies shading the entire jungle floor."});
		large_character_placements = new String[] {"beside a stump", "beside a fallen tree", "in some bushes", "next to a stream", "by a mossy boulder", "in a verdant bed of grasses", "next to some flowering plants", "under the huge leaves of a plant", "among some spiky plants", "next to a fruit-bearing growth", "in the thick jungle grasses"};
		nonlarge_character_placements = new String[] {"in an overgrown clump of plants", "in a muddy puddle"};
		noncharacter_placements = new String[] {"inside of a hollow log", "on the plant-covered ground", "in the dirt", "on the grass", "with a pile of roots", "pressed into the ground of the rainforest", "pressed into the ground of the jungle", "next to a loose stick"};
		moba = Array.combine(moba_forest, mobs_permute(new String[] {"Otyugh", "Mud_Mephit"}));
		if (Random.whole() <= moba_stirge_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Stirge"}));
	}
}