package environments_commons_exteriors_basics;

import utilities.*;

public class Mesa extends Common_Exterior_Basic
{
    public Mesa()
	{
		super();

		context = Random.text_of(new String[] {"in a mesa.", "in an arched rock desert."});
		description = Random.text_of(new String[] {"The desert rocks are a deep, rich red.", "A grayish, winged form is perched on a rock pillar overhead."});
		large_character_placements = new String[] {"around the rough plants", "next to a dusty bush", "next to a large cactus", "under a singleton cactus of many flowers", "beside a flowering cactus without spikes", "near a painterly rock formation", "adjacent to a red rock spire", "on a very rocky swath of terrain", "inside the large hole of one rock formation", "inside some stony pillars covered in red sand", "next to a rock formation", "next to a large and colorful cliff"};
		nonlarge_character_placements = new String[] {"under a lone bush"};
		noncharacter_placements = new String[] {"on the arid ground", "among the brush", "on the hard earth", "on the dirty ground"};
		moba = Array.combine(moba_harpies_with_gremlin, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle", "Dust_Mephit"}));
	}
}