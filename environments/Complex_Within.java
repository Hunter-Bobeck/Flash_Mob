package environments;

import utilities.*;
import scenarios.*;
import environments_rares_exteriors_basics.*;
import mobs.*;

public abstract class Complex_Within extends Environment
{
    public Complex_Within()
	{
		basic = false;
    }

    public void complexify()
    {
		// surrounding environment //
		Environment without = Scenario.solely_instantiated_named_environment_of(Array.combine
		(
			Scenario.environments_commons_exteriors_basics,
			Array.permute(new String[] {"environments_rares_exteriors_basics."}, new String[] {"Floating_Island", "Jagged_Mountain_Passage", "Pit", "Plateau_within_River", "Ravine"})
		));		// (certain exterior basics) //
		// weather //
		snowy = without.snowy;
    	// context //
    	context += " "+without.context;
		if (!without.basic)		context += ".";
		// description //
		description += " "+without.description;
		// placements //
		large_character_placements = Array.combine(large_character_placements, without.large_character_placements);
		nonlarge_character_placements = Array.combine(nonlarge_character_placements, without.nonlarge_character_placements);
		noncharacter_placements = Array.combine(noncharacter_placements, without.noncharacter_placements);
		// moba //
		moba = Array.combine(moba, without.moba);
    }
}