package acts;

import utilities.*;
import acts.*;

public class Halt
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"interim"},
	
	triggers_subsafe_colloquial = {"halt", "wait", "hold", "stay", "delay"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Halt()
	{
		if (Act.input_backup.contains("breath"))		Type.delay("You hold your breath.");
		else		Type.delay("You "+Random.text_of(triggers_subsafe_colloquial)+".");
		int amount = Random.integer_from(1, 4);
		for (int i = 0; i < amount; i++)
		{
			Type.delay(".", 1250);
		}		System.out.println();
	}
}