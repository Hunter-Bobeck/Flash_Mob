package environments_commons_interiors_complexes_undergrounds;

import utilities.*;

public class Tunnel extends Common_Interior_Complex_Underground
{
    public Tunnel()
	{
		super();

		context = "in a tunnel";
		description = Random.text_of(new String[] {"Stalagmites and stalactites are formed on the the ceiling and floor of the tunnel.", "There is a hole to another passage halfway down the tunnel"})+".";
		large_character_placements = new String[] {"aside the "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "with a pile of bones", "closer to the entrance", "in the depth of the tunnel", "at the opening to the tunnel", "in some sticky goo", "between two crevices", "between a rock and a hard place", "in the hole to another passage", "outside the opening of the tunnel", "in the middle of the tunnel"};
		nonlarge_character_placements = new String[] {"in a puddle of stagnant water"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "over a tiny stream of water going through subsurface cracks"};
		moba = Array.combine(moba, mobs_permute(new String[] {"Manticore"}));
	}
}