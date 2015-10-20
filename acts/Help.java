package acts;

import utilities.*;

public class Help
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"input", "advice", "guidance"},
	
	triggers_subsafe_colloquial = {"help", "commands", "inputs", "controls", "instructions", "guide"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"aid", "aid me", "acts", "list acts", "list all acts", "list all the acts", "list all of acts", "list all of the acts"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}