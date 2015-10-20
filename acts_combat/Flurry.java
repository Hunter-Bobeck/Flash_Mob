package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Flurry
{
	public static final int experience_cost = 670;
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {},
	
	triggers_subsafe_colloquial = {"flurry"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
	public static boolean on_cooldown = false;
	
	
	
	
	public Flurry(Scenario scenario, Player player)
	{
		if (Array.containment(player.abilities, "flurry") && !on_cooldown)
		{
			// removing cooldowns //
			Twist.on_cooldown = false;
			Spin.on_cooldown = false;
			Overhead.on_cooldown = false;
			Double_Thrust.on_cooldown = false;
			// activating of either 2 or 3 random basic abilities //
			int[] randomizations = null;
			if (Random.binary())		randomizations = new int[] {Random.integer_from(1, 3), Random.integer_from(1, 3)};
			else		randomizations = new int[] {Random.integer_from(1, 3), Random.integer_from(1, 3), Random.integer_from(1, 3)};
			for (int i = 0; i < randomizations.length; i++)
			{
				if (scenario.encounter.mob.health > 0)
				{
					if (i > 0)
					{
						System.out.println();
						
					}
					if (randomizations[i] == 1)
					{
						Type.delay_line("You "+Random.text_of(Slash.triggers_colloquial)+Random.text_of(new String[] {".", "!"}));
						Slash slash = new Slash(scenario, player);
					}
					else if (randomizations[i] == 2)
					{
						Type.delay_line("You "+Random.text_of(Cut.triggers_colloquial)+Random.text_of(new String[] {".", "!"}));
						Cut cut = new Cut(scenario, player);
					}
					else
					{
						Type.delay_line("You "+Random.text_of(Thrust.triggers_colloquial)+Random.text_of(new String[] {".", "!"}));
						Thrust thrust = new Thrust(scenario, player);
					}
				}
			}
			on_cooldown = true;
		}
		else
		{
			if (on_cooldown)
			{
				Type.delay_line(Random.text_of(new String[] {"You aren't ready to execute another "+Random.text_of(triggers_colloquial)+" yet.", Text.capitalize(Random.text_of(triggers_colloquial))+" is on cooldown."}));
		
				Combat combat = new Combat(player, scenario);
			}
			else if (player.duelist)
			{
				Type.delay_line(Random.text_of(new String[] {"You haven't learned to flurry yet", "You need to train yourself to execute a flurry first"})+".");
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}