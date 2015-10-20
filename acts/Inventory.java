package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Inventory
{
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial = {"rucksack", "backpack", "stock", "garb", "contents", "carrying", "stockpile"},
	
	triggers_subsafe_colloquial = {"inventory", "items", "pack", "supplies"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"i", "inv", "carry", "hoard", "reserve"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}