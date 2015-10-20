package environments_commons_exteriors_basics;

import utilities.*;

public class Beach extends Common_Exterior_Basic
{
    public Beach()
	{
		super();

		context = Random.text_of(new String[] {"on a sandy beach.", "in the lapping waves of a beach.", "on a shell-covered beach.", "in the lapping waves of a shell-covered beach."});
		description = Random.text_of(new String[] {"The coast stretches as far as the eye can see to either side of you.", "There are many shells on this beach.", "The tide is high.", "The tide is low."});
		large_character_placements = new String[] {"on the sands", "in the low waves", "in the waters"};
		nonlarge_character_placements = new String[] {"in a shallow puddle of seawater in the soaked sand"};
		if (context.contains("shell"))		nonlarge_character_placements = Array.combine(nonlarge_character_placements, new String[] {"among the shells"});
		noncharacter_placements = new String[] {"in the water, being slowly washed away by the tide", "beneath the splashing of the waves", "on the wet sand"};
		moba = Array.combine(moba_gremlin, moba_sea_only_multiplied, moba_sea_only_multiplied, mobs_permute(new String[] {"Water_Elemental", "Water_Elemental", "Water_Elemental", "Pirate", "Pirate", "Pirate", "Pirate", "Pirate", "Pirate"}));
	}
}