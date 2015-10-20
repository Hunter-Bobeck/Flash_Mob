package acts;

import utilities.*;
import acts.*;

public class Pause
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"absorb", "moment"},
	
	triggers_subsafe_colloquial = {"admire", "pause", "view"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Pause()
	{
		if (Act.input_backup.contains("pause") && Random.binary())
		{
			Halt halt = new Halt();
		}
		else
		{
			Type.delay(Random.text_of(new String[] {"You pause for a moment.", "You pause for a moment, absorbing the view.", "You pause to admire your surroundings.", "You take a moment to admire your surroundings."}));
			for (int i = 0; i < Random.integer_from(1, 4); i++)
				Type.delay(".", 750);
			System.out.println();
		}
	}
}