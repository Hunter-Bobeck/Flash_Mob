package acts;

import utilities.*;

public class Experience
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"participation", "points"},
	
	triggers_subsafe_colloquial = {"experience", "training"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"x", "xp", "xperience"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}