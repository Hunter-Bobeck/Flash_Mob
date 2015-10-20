package environments_commons_exteriors_basics;

import utilities.*;

public class Swampland extends Common_Exterior_Basic
{
    public Swampland()
	{
		super();

		context = Random.text_of(new String[] {"in a swampland.", "in the marshes."});
		description = Random.text_of(new String[] {"The stench of rotting things is unbearable.", "Parasite bugs whiz around you.", "The swampwater has layers of algae on it."});
		large_character_placements = new String[] {"by the squishy edge of the muck", "next to some cattails", "on some drier ground", "at the edge of the swamp"};
		nonlarge_character_placements = new String[] {"in the soaked foliage"};
		noncharacter_placements = new String[] {"in some grassy strands", "in the squishy earth", "in a puddle of water in the mud"};
		moba = Array.combine(moba_gremlin_with_harpies, mobs_permute(new String[] {"Mud_Mephit", "Ankheg", "Otyugh", "Shambling_Mound", "Stirge", "Mud_Mephit", "Ankheg", "Otyugh", "Shambling_Mound", "Stirge", "Mud_Mephit", "Ankheg", "Otyugh", "Shambling_Mound", "Stirge", "Mud_Mephit", "Ankheg", "Otyugh", "Shambling_Mound"}));
	}
}