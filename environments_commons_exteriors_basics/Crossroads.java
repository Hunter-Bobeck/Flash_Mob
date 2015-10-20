package environments_commons_exteriors_basics;

import utilities.*;
import mobs.*;

public class Crossroads extends Common_Exterior_Basic
{
    public Crossroads()
	{
		super();

		context = Random.text_of(new String[] {"in the middle of", "at"})+" a crossroads.";
		description = Random.text_of(new String[] {"Each road looks the same.", "You can see a light in the distance in one of the four directions.", "A signpost stands in the center, pointing in all four directions."});
		large_character_placements = new String[] {"in the center of the crossing", "at the edge of the intersecting road", "on the opposite road"};
		noncharacter_placements = new String[] {"in the dust", "off to the side"};
		moba = Array.combine(moba_roamers, moba_roamers, moba_gremlin, mobs_permute(new String[] {"Displacer_Beast"}));
		if (Random.whole() <= moba_abyss_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Jackalwere"}));
		if (Random.whole() <= .17)		moba = Array.combine(moba, mobs_permute(new String[] {"Kenku"}));
	}
}