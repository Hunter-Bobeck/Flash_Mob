package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Name
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"who am I", "name", "title", "I go by", "call myself", "call me"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"n"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}