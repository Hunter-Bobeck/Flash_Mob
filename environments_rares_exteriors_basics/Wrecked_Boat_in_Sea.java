package environments_rares_exteriors_basics;

import utilities.*;

public class Wrecked_Boat_in_Sea extends Rare_Exterior_Basic
{
    public Wrecked_Boat_in_Sea()
	{
		super();

		context = "amid the open sea, where you are steadying yourself between the remains of your wrecked boat and some driftwood!";
		description = Random.text_of(new String[] {"The waves are turbulent, trying to flip your ruined craft. A storm is receding", "A pirate ship is skirting the waves nearby", "The fins of several creatures poke out of the water, in close proximity to your position"})+".";
		large_character_placements = new String[] {"on the wrecked boat", "on another boat that is much less damaged", "on another boat which is just as damaged", "in the side of the broken boat"};
		nonlarge_character_placements = new String[] {"on the driftwood", "in the high waters", "on a spare wooden panel riding on a wave"};
		moba = Array.combine(moba_sea_only_multiplied, mobs_permute(new String[] {"Pirate", "Pirate", "Pirate", "Pirate", "Water_Elemental"}));
	}
}