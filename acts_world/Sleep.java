package acts_world;

import utilities.*;
import players.*;
import scenarios.*;
import environments.*;
import acts.*;

public class Sleep
{
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial_camping = {"setup camp", "setup a camp", "setup the camp", "setup my camp", "go camping", "tent"},
    triggers_subsafe_uncolloquial = Array.combine(new String[] {"bedroll"}, triggers_subsafe_uncolloquial_camping),
	
	triggers_subsafe_colloquial = {"sleep", "dream", "bed down", "bunk", "catnap", "crash", "doze", "fall asleep", "go to bed", "hibernate", "hit the sack", "nap", "nod", "nod off", "retire", "sack out", "saw wood", "slumber", "snore", "snooze", "take a nap", "try to dream", "zonk"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial_camping = {"camp"},
	triggers_nonsubsafe_uncolloquial = Array.combine(new String[] {"s", "bed"}, triggers_nonsubsafe_uncolloquial_camping),
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	private void sleep(Player player, Scenario scenario, boolean exterior)
	{
		System.out.println();
		
		Type.delay(Random.text_of(new String[] {"You drift off into sleep."}));
		int amount = Random.integer_from(1, 4);
		for (int i = 0; i < amount; i++)
		{
			Type.delay(".", 1250);
		}		System.out.println();
		System.out.print("\n\n");


		if (/**/false/**/)
		{
			Scenario sleep_scenario = new Scenario(new Player(player), false, true);


		}
		else		Wait.milliseconds(2000);
		Type.delay_line(Wake.wakening_statement());
		System.out.print("\n\n");


		int resteds = player.rested - player.rest;
		if (Random.binary())		player.rest += resteds;
		else
		{
			int sleeplessness = Random.integer_from(1, 2);
			if (resteds - sleeplessness > 0)		player.rest += resteds - sleeplessness;
		}
		player.describe_rest(true);
		player.recover(".".length() + amount);
		System.out.println();

		if (exterior)
		{
			System.out.println();
			Type.delay_wait(Random.text_of(new String[] {"You roll up your bed and fold up your tent", "You pack away your tent and bed"}), 400);
			Type.delay_line("..", 800);
		}
		else
		{
			System.out.println();
			Type.delay_wait(Random.text_of(new String[] {"You roll up your bed", "You pack up your bedroll"}), 400);
			Type.delay_line("..", 800);
		}
	}
	public Sleep(Player player, Scenario scenario)
	{
		if (player.wetness == 0  &&  player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself and drink some water before sleeping.", "You can't sleep until you've satisfied your hunger and thirst."}));
		else if (player.wetness == 0)		Type.delay(Random.text_of(new String[] {"You need to drink some water before sleeping.", "You can't sleep until you've drank some water."}));
		else if (player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself before sleeping.", "You can't sleep until you've settled your stomach."}));
		else
		{
			Environment environment = scenario.environment;
			boolean exterior = environment.exterior;
			if (exterior)
			{
				Type.delay_wait(Random.text_of(new String[] {"You unpack and setup your tent, then lay down your bedroll", "You pitch your tent and roll out your bedroll on the ground inside"})+".", 400);
				Type.delay_line("..", 800);
				System.out.println();
				
				System.out.println("             _______");
				System.out.println("            𝆱     𝆱𐩰");
				System.out.println("           /     /  \\");
				System.out.println("          /_____/----\\_");
				System.out.println("         \"     \"");				
				sleep(player, scenario, exterior);
			}
			else if (!Array.subcontainment(Act.input_backup, triggers_subsafe_uncolloquial_camping) && !Array.containment(triggers_nonsubsafe_uncolloquial_camping, Act.input_backup))
			{
				Type.delay_wait(Random.text_of(new String[] {"You lay out your bedroll and get inside", "You lay down your bedroll and squeeze into it", "You setup your bedroll and make yourself comfortable"})+".", 400);
				Type.delay_line("..", 800);				
				sleep(player, scenario, exterior);
			}
			else
			{
				if (environment.context.contains("camp"))		Type.delay_line("There's already a camp here. Just get out your bedroll.");
				else if (environment.context.contains("fort"))		Type.delay_line("The fort should provide enough shelter. You can just use your bedroll.");
				else		Type.delay_line(Random.text_of(new String[] {"You don't need to setup a tent to sleep here", "You should sleep with just your bedroll here", "This is no place to pitch a tent. Just sleep on your bedroll"})+".");
			}
		}
	}
}