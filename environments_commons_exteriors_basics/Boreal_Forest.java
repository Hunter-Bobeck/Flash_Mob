package environments_commons_exteriors_basics;

import utilities.*;

public class Boreal_Forest extends Common_Exterior_Basic
{
    public Boreal_Forest()
	{
		super();

		water = true;
		snowy = true;
		context = Random.text_of(new String[] {"in a snowy "+Random.text_of(new String[] {"boreal", "evergreen"})+" forest.", "on a boreal taiga terrain."});
		description = Random.text_of(new String[] {"Drifts of snow bank the trees.", "The trees are the only plants standing out of the snow.", "The air is crisp, and scented of pine."});
		large_character_placements = new String[] {"between a couple of evergreens", "beside a snowdrift", "at the top of a slope", "on a crumbling incline", "on a rocky slope", "on the snowy dip between a few hills"};
		noncharacter_placements = new String[] {"in a snowdrift beside a pine", "on the cold earth", "on the snow", "on the bumpy ground", "on the frosty ground"};
		moba = Array.combine(moba_forest, moba_gremlin, mobs_permute(new String[] {"Ice_Mephit", "Frost_Giant", "Frost_Giant", "Frost_Giant", "Young_Remorhaz", "Remorhaz"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}