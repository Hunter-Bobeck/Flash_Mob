package environments_commons_exteriors_basics;

import utilities.*;

public class Cliff extends Common_Exterior_Basic
{
    public Cliff()
	{
		super();

		context = Random.text_of(new String[] {"on the edge of a cliff!", "on a cliff."});
		description = Random.text_of(new String[] {"Fog can be seen below.", "The way down looks dangerously steep."});
		large_character_placements = new String[] {"at the precipice", "on the edge of the cliff", "on the stone", "away from the edge", "down below"};
		nonlarge_character_placements = new String[] {"under an overturned boulder"};
		noncharacter_placements = new String[] {"on the ground away from the edge"};
		moba = Array.combine(moba_harpies_with_gremlin, mobs_permute(new String[] {"Hill_Giant", "Hill_Giant", "Hill_Giant", "Hill_Giant", "Hill_Giant", "Hill_Giant", "Hill_Giant", "Hill_Giant", "Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
	}
}