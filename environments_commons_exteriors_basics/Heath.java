package environments_commons_exteriors_basics;

import utilities.*;

public class Heath extends Common_Exterior_Basic
{
    public Heath()
	{
		super();

		context = "in a heath.";
		description = Random.text_of(new String[] {"The heath is nothing more than a plain of weeds.", "The dirt is muddy in some places, retaining water from the recent rains."});
		large_character_placements = new String[] {"on stamped weeds", "in a patch of scraggly brush"};
		nonlarge_character_placements = new String[] {"under a lone bush", "in the underbrush", "in the weeds"};
		noncharacter_placements = new String[] {"next to a loose stick", "on the dirty ground"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}