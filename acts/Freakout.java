package acts;

import utilities.*;

public class Freakout
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"wtf", "fuck", "frick", "wth", "heck"},
	
	triggers_subsafe_colloquial = {"go crazy", "flip", "flip out", "freak", "freak out", "spaz", "spaz out"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"hell"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Freakout()
	{
		Type.delay_wait_line("You "+Random.text_of(triggers_subsafe_colloquial)+Random.text_of(new String[] {"!", "."}), Random.integer_from(2, 3) * 650);
	}
}