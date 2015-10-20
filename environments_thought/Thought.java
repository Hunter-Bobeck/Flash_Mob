package environments_thought;

import utilities.*;
import players.*;
import environments.*;
import scenarios.*;

public abstract class Thought extends Environment
{
	// thought placement possibilities //
	private static int thought_placements_counter = 0;		// thought placements counter for control //
	private static final String[] large_character_placements_thought_default = {"among a phantasmagoria of shapes", "randomly in the midair", "in the back of your mind", "on the edge of your thoughts", "in recent memory", "within your imagination", "all around you", "everywhere at once", "in your present focus"};		// default thought placements //
	// thought placements //
	private static String[] large_character_placements_thought, nonlarge_character_placements_thought, noncharacter_placements_thought;
	// thought moba //
	private static String[] moba_thought;
	
	public Thought()
	{
		if (Random.whole() <= .3)		water = true;
		if (Random.whole() <= .3)		snowy = true;
		real = false;
		common = false;		// thought environments are considered rare just because it seems more applicable //
		special = true;
		basic = false;
		complexify();
	}
	
	// thought placements + moba memory updating //
	public static void update_memory(Player player)
	{
		// reset the thought placements + moba memory: default the thought placements + moba memory and combine it with a random environment's placements + moba //
		if (player.initial_tavern  ||  thought_placements_counter % 3  ==  0)
		{
			Environment random_environment = Scenario.solely_instantiated_named_environment_of(Scenario.environments);		// (all real environments (equal probability because likelihood isn't a factor for thought)) //
			large_character_placements_thought = Array.combine(large_character_placements_thought_default, random_environment.large_character_placements);
			nonlarge_character_placements_thought = random_environment.nonlarge_character_placements;
			noncharacter_placements_thought = random_environment.noncharacter_placements;
			moba_thought = random_environment.moba;
		}
		// evolve the thought placements + moba memory: combine the thought placements + moba memory with the previous environment's placements + moba //
		else
		{
			large_character_placements_thought = Array.combine(large_character_placements_thought, Scenario.previous_large_character_placements);
			nonlarge_character_placements_thought = Array.combine(nonlarge_character_placements_thought, Scenario.previous_nonlarge_character_placements);
			noncharacter_placements_thought = Array.combine(noncharacter_placements_thought, Scenario.previous_noncharacter_placements);
			moba_thought = Array.combine(moba_thought, Scenario.previous_moba);
		}
		thought_placements_counter++;
	}
	
	public void complexify()
	{
		large_character_placements = large_character_placements_thought;
		nonlarge_character_placements = nonlarge_character_placements_thought;
		noncharacter_placements = noncharacter_placements_thought;
		update_placement_totals();
		moba = moba_thought;
	}
}