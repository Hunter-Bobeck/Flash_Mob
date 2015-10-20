package environments_rares_exteriors_basics;

import utilities.*;

public class Volcano extends Rare_Exterior_Basic
{
    public Volcano()
	{
		super();

		context = "on the "+Random.text_of(new String[] {"edge", "slope"})+" of a volcano.";
		description = Random.text_of(new String[] {"The heat here is intense, obscuring your vision with stinging beads of sweat, but it's quite apparent that the mountain's top has been blown away, opening the sky up to a spouts of lava and bursts of ashen smoke"})+".";
		large_character_placements = new String[] {"on the crumbling incline", "on the heated rock slope", "on the sharp jags of the precipice", "on a smooth boulder", "on an island of rock isolated from flowing lava", "next to a large stream of lava", "next to dripping streams of lava"};
		noncharacter_placements = new String[] {"on a shallow pool of lava", "on a hot, flat rock", "on the superheated slope", "on the bubbling ground"};
		moba = Array.combine(moba_fire, moba_cultists, moba_cultists);
	}
}