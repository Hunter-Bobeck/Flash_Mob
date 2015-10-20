package acts_world;

import utilities.*;
import players.*;
import scenarios.*;
import acts.*;

public class Mine
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"excavate", "extract", "drill", "unearth", "quarry", "scoop", "mine ore", "pickaxe", "shovel"},
	
	triggers_subsafe_colloquial = {"mine"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"pick", "pan", "dig", "hew", "m"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Mine()
	{
		/* condition complaint statements like being thirsty */
		Type.delay_line("You don't have a pickaxe.");
		/* player.tire(); */
	}
}