package environments_commons_exteriors_basics;

import utilities.*;

public class Open_Fields_Dirt_Path extends Common_Exterior_Basic
{
    public Open_Fields_Dirt_Path()
	{
		super();

		context = "along a dirt path out in the open fields.";
		description = Random.text_of(new String[] {"The path is overgrown in some places.", "A shrouded figure is walking ahead of you.", "The setting sun is directly above the path you're heading down.", "A pile of horse droppings sits on the path."});
		large_character_placements = new String[] {"alongside the path", "near the edge of the path"};
		noncharacter_placements = new String[] {"in the dirt", "in heavily stamped-upon dirt", "in the dusty dirt", "in a split-apart opening of the fieldgrass", "on the dusty ground", "sticking out of the grasses", "next to a pile of horse droppings"};
		moba = Array.combine(moba_roamers, moba_gremlin, mobs_permute(new String[] {"Displacer_Beast"}));
	}
}