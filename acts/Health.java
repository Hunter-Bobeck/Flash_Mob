package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Health
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"shape"},
	
	triggers_subsafe_colloquial = {"health", "fortitude", "injury"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Health(Player player, Scenario scenario, boolean entire_condition, boolean World, boolean Ahead)
	{
		player.describe_health();
		if (!World && !entire_condition)
		{
			System.out.println();
			
			if (!Ahead)
			{
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Ahead ahead = new Ahead(player, scenario, scenario.encounter.mob);
			}
		}
	}
}