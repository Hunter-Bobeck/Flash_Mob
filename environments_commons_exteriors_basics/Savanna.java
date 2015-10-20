package environments_commons_exteriors_basics;

import utilities.*;

public class Savanna extends Common_Exterior_Basic
{
    public Savanna()
	{
		super();

		context = "in a "+Random.text_of(new String[] {"", "dry "})+"savanna.";
		description = Random.text_of(new String[] {"A manticore is taking down another animal in the distance.", "The heat is relentless in this region."});
		large_character_placements = new String[] {"next to a dusty bush", "around the rough plants"};
		nonlarge_character_placements = new String[] {"under a lone bush"};
		noncharacter_placements = new String[] {"on the arid ground", "among the brush", "on the hard earth", "on the dirty ground"};
		moba = Array.combine(moba_harpies_and_manticores_with_gremlin, mobs_permute(new String[] {"Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit"}));
	}
}