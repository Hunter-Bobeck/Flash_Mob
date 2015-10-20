package environments_commons_specials_complexes_withins;

import utilities.*;

public class Camp extends Common_Special_Complex_Within
{
    public Camp()
	{
		super();

		context = Random.text_of(new String[] {"in a camp", "at a campsite"});
		description = Random.text_of(new String[] {"There are multiple tents, a barrel, some supplies, and a firepit", "The camp looks like it was setup by a group of hunters. Some loose animal skins are lying around, and there is a cooking spit over a fire"})+".";
		large_character_placements = new String[] {"in a tent", "on the outskirts of the campsite", "next to a barrel"};
		if (context.contains("bandit"))		large_character_placements = Array.combine(large_character_placements, new String[] {"by a collection of stolen goods"});
		nonlarge_character_placements = new String[] {"in a bush", "on a crate"};
		noncharacter_placements = new String[] {"on the extinguished firepit", "under a burnt cooking spit", "under some loose animal skins"};
		moba = Array.combine(moba_roamers, moba_cultists, moba_fighters, moba_gremlin);
	}
}