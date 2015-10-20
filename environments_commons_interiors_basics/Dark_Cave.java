package environments_commons_interiors_basics;

import utilities.*;

public class Dark_Cave extends Common_Interior_Basic
{
    public Dark_Cave()
	{
		super();

		context = "within a dark cave.";
		description = Random.text_of(new String[] {"The darkness is overwhelming; the only light source is from several glowing mushrooms on the wall.", "The glow from several mushrooms is a soft, luminescent blue."});
		large_character_placements = new String[] {"aside the rock wall", "aside the stone wall", "aside the stippled wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "beside a pile of bones", "in the hole to another passage", "closer to the entrance", "in the depth of the cave", "near the back"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "in a puddle of stagnant water"};
		moba = Array.combine(moba_gremlin_with_underground, moba_cultists, mobs_permute(new String[] {"Manticore"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
		if (Random.whole() <= moba_stirge_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Stirge"}));
	}
}