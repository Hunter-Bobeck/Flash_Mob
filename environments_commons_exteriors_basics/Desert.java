package environments_commons_exteriors_basics;

import utilities.*;

public class Desert extends Common_Exterior_Basic
{
    public Desert()
	{
		super();

		context = "in the desert.";
		description = Random.text_of(new String[] {"Dry air whistles in your ears.", "Spiders skid across the sands.", "There is no oasis in sight.", "Hot wind rearranges the sandy particles. The sand is soft and warm under your boots."});
		large_character_placements = new String[] {"upon a drift of the sandy dune", "next to a spiky cactus", "between a large and small pair of cacti"};
		noncharacter_placements = new String[] {"under some sand", "on the sand", "on the hard dirt", "on the dirty sand", "on the dry ground", "on the arid desert surface"};
		moba = Array.combine(moba_harpies_with_gremlin, moba_roamers, mobs_permute(new String[] {"Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit"}));
	}
}