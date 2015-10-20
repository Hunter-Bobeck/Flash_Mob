package environments_commons_interiors_complexes_undergrounds;

import utilities.*;

public class Underground extends Common_Interior_Complex_Underground
{
    public Underground()
	{
		super();

		context = Random.text_of(new String[] {"underground", "in the Underdark"});
		description = Random.text_of(new String[] {"You can't even see your hand in front of your face", "The only light you see comes from glowing mushrooms on the wall"})+".";
		large_character_placements = new String[] {"aside the "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "with a pile of bones", "at the opening to a cavern", "in some sticky goo", "on the edge of an acid lake", "by a pool of acid", "between two crevices", "between a rock and a hard place", "in the hole to another passage", "outside the opening of a crawlspace", "squeezed into a crawlspace, blocking the way through", "near the back of a crawlspace"};
		nonlarge_character_placements = new String[] {"in a puddle of stagnant water"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "over a tiny stream of water going through subsurface cracks"};
	}
}