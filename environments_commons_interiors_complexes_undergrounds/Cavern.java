package environments_commons_interiors_complexes_undergrounds;

import utilities.*;

public class Cavern extends Common_Interior_Complex_Underground
{
    public Cavern()
	{
		super();

		context = "in a cavern";
		description = Random.text_of(new String[] {"The glint of an ore vein can be seen in the cavern's wall", "Glowing mushrooms provide light for the cavern", "There is a lake of acid filling the majority of the huge cavern"})+".";
		large_character_placements = new String[] {"aside the "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "with a pile of bones", "in the hole to another passage", "closer to the entrance", "in the depth of the cavern", "near the back", "at the opening to the cavern", "in some sticky goo", "on the edge of an acid lake", "by a pool of acid"};
		nonlarge_character_placements = new String[] {"in a puddle of stagnant water"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "over a tiny stream of water going through the cracks of the cavern"};
		if (Random.whole() <= .6)		moba = Array.combine(moba, moba_cultists);
	}
}