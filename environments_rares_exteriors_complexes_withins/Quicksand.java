package environments_rares_exteriors_complexes_withins;

import utilities.*;

public class Quicksand extends Rare_Exterior_Complex_Within
{
    public Quicksand()
	{
		super();

		context = "in a "+Random.text_of(new String[] {"bed", "pit"})+" of quicksand";
		description = Random.text_of(new String[] {"There's not much time to see anything other than the muddy sand rising up to engulf your legs!"});
		large_character_placements = new String[] {"at the edge of the quicksand", "right next to you", "above you to the side of the trap"};
		moba = mobs_permute(new String[] {"Earth_Elemental", "Harpy", "Dust_Mephit", "Mud_Mephit"});
	}
}