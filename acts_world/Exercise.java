package acts_world;

import utilities.*;
import players.*;

public class Exercise
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"work out", "exertion", "build muscles", "build my muscles", "work on my muscles", "practice my moves", "go through my routine"},
	
	triggers_subsafe_colloquial = {"exercise", "workout"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"exert", "lift", "routine", "exer"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Exercise(Player player)
	{
		if (player.wetness == 0  &&  player.fullness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself, drink some water, and rest before exercising.", "You can't exercise until you've satisfied your hunger and thirst and then rested."}));
		else if (player.wetness == 0  &&  player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself and drink some water before exercising.", "You can't exercise until you've satisfied your hunger and thirst."}));
		else if (player.wetness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to drink some water then rest before exercising.", "You can't exercise until you've quenched your thirst and rested."}));
		else if (player.fullness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself then rest before exercising.", "You can't exercise until you've satisfied your hunger and rested."}));
		else if (player.wetness == 0)		Type.delay(Random.text_of(new String[] {"You need to drink some water before exercising.", "You can't exercise until you've drank some water."}));
		else if (player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself before exercising.", "You can't exercise until you've settled your stomach."}));
		else if (player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to rest before exercising.", "You can't exercise until you've rested."}));
		else
		{
			Type.delay("You exercise.");
			int amount = Random.integer_from(1, 3);
			for (int i = 0; i < amount; i++)
			{
				Type.delay(".", 1300);
			}		System.out.println();
			System.out.println();
			
			player.exercise();
		}		
	}
}