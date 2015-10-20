package environments_commons_exteriors_basics;

import utilities.*;

public class Flowery_Field extends Common_Exterior_Basic
{
    public Flowery_Field()
	{
		super();

		context = Random.text_of(new String[] {"amid a flowery field.", "amid a field of flowers."});
		description = Random.text_of(new String[] {"The flowers are purple and orange.", "Bees are buzzing all about."});
		large_character_placements = new String[] {"among the flowers", "next to a patch of purple flowers", "next to several orange-petaled blooms", "on a patch of dandelions"};
		noncharacter_placements = new String[] {"in the grass"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}