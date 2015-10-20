package environments_commons_exteriors_basics;

import utilities.*;

public class Grassy_Plains extends Common_Exterior_Basic
{
    public Grassy_Plains()
	{
		super();
		
		context = Random.text_of(new String[] {"on the grassy plains.", "on plains of grass."});
		description = Random.text_of(new String[] {"The clouds are really beautiful right now.", "The grass has been stamped on in some places.", "Several bunnies live here."});
		large_character_placements = new String[] {"within the grass", "in a grassless clearing", "on stamped grass"};
		noncharacter_placements = new String[] {"under a heap of dried grass", "nestled into the underbrush", "under a weaved grass pattern"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}