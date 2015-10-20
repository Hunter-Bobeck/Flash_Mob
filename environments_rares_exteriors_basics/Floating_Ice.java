package environments_rares_exteriors_basics;

import utilities.*;

public class Floating_Ice extends Rare_Exterior_Basic
{
    public Floating_Ice()
	{
		super();
		
		snowy = true;
		context = "on a surface of floating ice, amid glaciers.";
		description = Random.text_of(new String[] {"The nearby glaciers are massive, and slowly drifting", "A colorful aurora lights up the sky above you", "The floes of ice are many, bumping into one another and breaking apart"})+".";
		large_character_placements = new String[] {"on the same floe as yourself", "on the nearest ice floe", "on a cracked ice floe", "on the bank of an enormous glacier", "on thick floating ice", "on a nearby glacier"};
		nonlarge_character_placements = new String[] {"in the frigid water"};
		noncharacter_placements = new String[] {"on the ice of the floe you are on", "on the surface of a nearby glacier"};
		moba = mobs_permute(new String[] {"Water_Elemental", "Ice_Mephit", "Frost_Giant"});
	}
}