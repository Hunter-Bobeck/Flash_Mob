package acts_world;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Rest
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"downtime"},
	
	triggers_subsafe_colloquial = {"rest", "relax", "repose", "take a break", "take a breather", "recoup", "recharge", "rejuvenate"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"r", "heal"},
	
	triggers_nonsubsafe_colloquial = {"break", "breather", "charge up"},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Rest(Player player, Scenario scenario)
	{
		if (player.wetness == 0  &&  player.fullness == 0)		Type.delay_line(Random.text_of(new String[] {"You need to feed yourself and drink some water before resting.", "You can't rest until you've satisfied your hunger and thirst."}));
		else if (player.wetness == 0)		Type.delay_line(Random.text_of(new String[] {"You need to drink some water before resting.", "You can't rest until you've drank some water."}));
		else if (player.fullness == 0)		Type.delay_line(Random.text_of(new String[] {"You need to feed yourself before resting.", "You can't rest until you've settled your stomach."}));
		else
		{
			Type.delay(Random.text_of(Array.permute(new String[] {"You pause to ", "You take some time to "}, Array.combine(triggers_subsafe_colloquial, new String[] {"regain your strength"})))+".");
			int amount = Random.integer_from(1, 4);
			for (int i = 0; i < amount; i++)
			{
				Type.delay(".", 1250);
			}		System.out.println();
			player.rest += Random.integer_from(1, 3);
			player.recover(".".length() + amount);
			player.describe_rest(true);
		}
	}
}