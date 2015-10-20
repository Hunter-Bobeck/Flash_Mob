package environments_commons_specials_complexes_withins;

import utilities.*;

public class Abandoned_House extends Common_Special_Complex_Within
{
    public Abandoned_House()
	{
		super();

		context = "by an abandoned house";
		description = Random.text_of(new String[] {"The house looks new, but abandoned", "The house is old and dilapidated", "The roof is burnt and mostly nonexistent"})+".";
		large_character_placements = new String[] {"in the vacant doorway"};
		nonlarge_character_placements = new String[] {"under a table"};
		noncharacter_placements = new String[] {"on the hardwood floor", "in the used oven"};
		if (Random.whole() <= .889)		moba = Array.combine(moba_cultists, moba_roamers, moba_gremlin, mobs_permute(new String[] {"Shambling_Mound"}));
	}
}