package environments_rares_interiors_basics;

import utilities.*;

public class Reservoir_Cavern extends Rare_Interior_Basic
{
    public Reservoir_Cavern()
	{
		super();

		context = Random.text_of(new String[] {"inside", "within"})+" "+Random.text_of(new String[] {"an Underdark", "an underground"})+" cavern holding a reservoir.";
		description = Random.text_of(new String[] {"Glowing mushrooms are your only light source, illuminating a sizeable pool of water in this cavern.", "There is some sticky goo near your feet."});
		large_character_placements = new String[] {"aside the nearest "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "with a pile of bones", "in the hole to another passage", "closer to the entrance", "in the depth of the cavern", "near the back", "at the opening to the cavern", "in some sticky goo", "in the dark waters", "on the edge of the underground lake", "on the edge of the reservoir", "on the edge of the repository of water"};
		nonlarge_character_placements = new String[] {"in a puddle of stagnant water"};
		noncharacter_placements = new String[] {"on the "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" floor", "over a tiny stream of water going through the cracks of the cavern"};
		moba = Array.combine(moba_underground_with_gremlin, mobs_permute(new String[] {"Grell", "Otyugh", "Otyugh"}));
		if (Random.whole() <= .6)		moba = Array.combine(moba, moba_cultists);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
	}
}