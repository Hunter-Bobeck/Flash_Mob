package environments_rares_exteriors_basics;

import utilities.*;

public class Pit extends Rare_Exterior_Basic
{
    public Pit()
	{
		super();

		context = "at the bottom of a pit!";
		description = Random.text_of(new String[] {"Not much light makes its way into this pit. The sides are steep, and there is little room to maneuver around. Spikes out of the ground threaten to stick you if you get too close. There are a few bones lying about"})+".";
		large_character_placements = new String[] {"trapped behind several spikes", "against the steep side of the pit", "in the center of the pit", "among scattered stones", "next to a pile of bones"};
		if (Random.whole() <= .5)		moba = Array.combine(moba_roamers, moba_fighters, moba_fighters, mobs_permute(new String[] {"Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy"}));
	}
}