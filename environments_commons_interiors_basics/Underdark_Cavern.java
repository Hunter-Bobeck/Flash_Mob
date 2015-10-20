package environments_commons_interiors_basics;

import utilities.*;

public class Underdark_Cavern extends Common_Interior_Basic
{
	public Underdark_Cavern()
	{
		super();

		context = Random.text_of(new String[] {"inside", "within"})+" a "+Random.text_of(new String[] {"cavern of the Underdark", "dark cavern underground"})+".";
		description = Random.text_of(new String[] {"The air is stale.", "You notice water trickling next to your feet."});
		large_character_placements = new String[] {"aside the rock wall", "aside the stone wall", "aside the stippled wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "among a pile of bones", "in the hole to another passage", "closer to the entrance", "in the depth of the cavern", "near the back", "at the opening to the cavern", "on the edge of an acid lake", "by a pool of acid"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "in a puddle of stagnant water", "over a tiny stream of water going through the cracks of the cavern", "in some sticky goo"};
		moba = Array.combine(moba_underground_with_gremlin, mobs_permute(new String[] {"Grell", "Otyugh"}));
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
	}
}