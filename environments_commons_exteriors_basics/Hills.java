package environments_commons_exteriors_basics;

import utilities.*;

public class Hills extends Common_Exterior_Basic
{
    public Hills()
	{
		super();

		context = "in the hills.";
		description = Random.text_of(new String[] {"There seems to be movement ahead of you, but from each hilltop nothing can be seen.", "The rocky slopes are crumbling in many places.", "From the top of a hill, you can see some kind of structure in the distance."});
		large_character_placements = new String[] {"at the top of a slope", "on a crumbling incline", "on a rocky slope", "on the grassy bottom between two hills"};
		noncharacter_placements = new String[] {"on the bumpy ground", "on the grass"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr"}));
	}
}