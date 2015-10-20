package environments_commons_exteriors_basics;

import utilities.*;

public class Hilly_Desert extends Common_Exterior_Basic
{
    public Hilly_Desert()
	{
		super();

		context = "in a hilly desert.";
		description = Random.text_of(new String[] {"The sand is blowing off of the dunes, getting into your eyes.", "You notice a harpy flying in the sky a ways off."});
		large_character_placements = new String[] {"at the top of a slope", "on a sliding incline", "on a fine but footprinted slope", "on the pebbly bottom between two hills of sand", "upon a drift of the sandy dune", "next to a spiky cactus", "between a large and small pair of cacti"};
		noncharacter_placements = new String[] {"on the smoothly raised sand", "on the dusty dirt", "under some sand", "on the sand", "on the hard dirt", "on the dirty sand", "on the dry ground", "on the arid desert surface"};
		moba = Array.combine(moba_harpies_with_gremlin, moba_roamers, mobs_permute(new String[] {"Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit", "Dust_Mephit"}));
	}
}