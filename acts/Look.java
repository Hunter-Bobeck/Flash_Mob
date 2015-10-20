package acts;

import utilities.*;
import scenarios.*;
import players.*;
import encounters.*;
import mobs.*;
import environments.*;
import acts_combat.*;

public class Look
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"describe", "description", "investigate", "summary", "survey", "synopsis", "overview", "display", "explanation", "depict", "picture", "information", "narrative", "statement", "sketch", "room"},
	
	triggers_subsafe_colloquial = {"look"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"l", "tale"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	

	public Look(Scenario scenario, Player player, boolean alive_mob_nearby)
	{
		Encounter encounter = scenario.encounter;
		Mob mob = encounter.mob;
		Environment environment = scenario.environment;
		environment.setting(player);
		if (alive_mob_nearby || encounter.mob_encountered)
		{
			if (alive_mob_nearby)
				Type.delay_line(Random.text_of(new String[] {"The "+mob.species+" is here "+mob.environment_placement+"!", "You have noticed "+mob.article_species+" "+mob.environment_placement+".", "An unaware "+mob.species+" is "+mob.environment_placement+"..."}));
			else
				Type.delay_line(Random.text_of(new String[] {"A dead "+mob.species+" is "+mob.environment_placement, "The body of "+mob.article_species+" rests "+mob.environment_placement, Text.capitalize(mob.species)+" remains lie "+mob.environment_placement})+".");
		}
	}
}