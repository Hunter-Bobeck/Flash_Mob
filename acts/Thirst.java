package acts;

import utilities.*;
import acts_world.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Thirst
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"check thirst", "check drink", "test thirst"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_subsafe_noncolloquial_condition_nonsubsafe = {"cond", "stat"},
	triggers_nonsubsafe_uncolloquial = Array.combine(Array.permute
	(
		new String[] {"", "how ", "how is my ", "do I ", "do I have ", "what is my ", "describe my "},
		Array.combine
		(
			new String[] {"dehydration", "wet", "parched", "throat", "body water"},
			Array.permute
			(
				new String[] {"need to "},
				Array.combine(Eat.triggers_subsafe_uncolloquial, Eat.triggers_nonsubsafe_uncolloquial)
			)
		),
		new String[] {"", " doing", " right now", " at the moment", " level"}
	), new String[] {"c", "s"}, triggers_subsafe_noncolloquial_condition_nonsubsafe),
	
	triggers_subsafe_colloquial_condition_nourishment_subsafe = {"nourishment", "sustenance"},
	triggers_subsafe_colloquial_condition_rest_subsafe = {"how rested am I", "what is my rest", "do I need rest", "do I need to rest", "do I need any rest", "how much rest do I have", "sleepiness", "tiredness"},
	triggers_subsafe_colloquial_condition_subsafe = Array.combine(new String[] {"condition", "well-being", "status", "state", "maintenance"}, triggers_subsafe_colloquial_condition_nourishment_subsafe, triggers_subsafe_colloquial_condition_rest_subsafe),
	triggers_nonsubsafe_colloquial = Array.permute
	(
		new String[] {"", "how ", "how is my ", "do I ", "do I have ", "what is my ", "describe my "},
		Array.combine
		(
			Array.combine(new String[] {"thirst", "hydration", "thirstiness"}, triggers_subsafe_colloquial_condition_subsafe),
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
	
	
	
	
	
	
	
	
	public Thirst(Player player, Scenario scenario, boolean World, boolean from_Ahead)
	{
		boolean entire_condition = (Array.subcontainment_case_ignored(Act.input_backup, triggers_subsafe_colloquial_condition_subsafe) || Array.containment_case_ignored(triggers_subsafe_noncolloquial_condition_nonsubsafe, Act.input_backup) || Act.input_backup.equals("c") || Act.input_backup.equals("s"));
		if (entire_condition)
		{
			if (!Array.subcontainment_case_ignored(Act.input_backup, triggers_subsafe_colloquial_condition_nourishment_subsafe))
			{
				Health health = new Health(player, scenario, entire_condition, World, from_Ahead);
				player.describe_rest(true);
			}
			Hunger hunger = new Hunger(player, scenario, entire_condition, World, from_Ahead);
		}
		player.describe_thirst();
		if (from_Ahead && !entire_condition)		Ahead.reprompt(player, scenario, scenario.encounter.mob);
	}
}