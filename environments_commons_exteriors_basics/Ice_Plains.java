package environments_commons_exteriors_basics;

import utilities.*;

public class Ice_Plains extends Common_Exterior_Basic
{
    public Ice_Plains()
	{
		super();

		water = true;
		snowy = true;
		context = Random.text_of(new String[] {"on the ice plains.", "on plains of ice.", "on the tundra."});
		description = Random.text_of(new String[] {"The ice is so packed in some places that you slide as you move across.", "The icy expanse extends for a while in each direction.", "Several frost giants are trekking parallel to you, probably tracking you."});
		large_character_placements = new String[] {"on the ice", "out in the open", "on the open tundra", "next to some iced-over weeds"};
		noncharacter_placements = new String[] {"on the ground of the frozen tundra", "stuck in the freeze", "encrusted in ice", "on hard snow", "on thick ice", "on cracked ice"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Ice_Mephit", "Frost_Giant", "Frost_Giant", "Frost_Giant", "Young_Remorhaz", "Remorhaz"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}