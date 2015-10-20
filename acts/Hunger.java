package acts;

import utilities.*;
import acts_world.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Hunger
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"check hunger", "check eat", "test hunger"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = Array.permute
	(
		new String[] {"", "how ", "how is my ", "do I ", "do I have ", "what is my ", "describe my "},
		Array.combine
		(
			new String[] {"ravenous", "vorac", "starv", "belly", "tummy", "stomach", "satisfaction", "full", "peckish", "famish", "hungry", "hungriness", "nourishedness", "digestion", "ravenousness"},
			Array.permute
			(
				new String[] {"need to "},
				Array.combine(Eat.triggers_subsafe_uncolloquial, Eat.triggers_nonsubsafe_uncolloquial)
			)
		),
		new String[] {"", " doing", " right now", " at the moment", " level"}
	),
	
	triggers_nonsubsafe_colloquial = Array.permute
	(
		new String[] {"", "how ", "how is my ", "do I ", "do I have ", "what is my ", "describe my "},
		Array.combine
		(
			new String[] {"appetite", "hunger", "craving", "fullness"},
			Array.permute
			(
				new String[] {"need to "},
				Eat.triggers_colloquial
			)
		),
		new String[] {"", " doing", " right now", " at the moment", " level", " am I"}
	),
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Hunger(Player player, Scenario scenario, boolean entire_condition, boolean World, boolean Ahead)
	{
		player.describe_hunger();
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