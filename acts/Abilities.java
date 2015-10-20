package acts;

import utilities.*;

public class Abilities
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"talents", "options", "spell", "casts", "incantations", "invocations", "cantrips"},
	
	triggers_subsafe_colloquial = {"class", "abilities", "skills", "spells"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}