package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Food
{
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial = {"how much food", "check food"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = Array.permute
	(
		new String[] {"", "how many ", "how much ", "do I have ", "do I have any "},
		Array.permute(new String[] {"edible", "breakfast", "lunch", "dinner", "supper", "meal", "ration", "snack", "piece of food", "bite of food"}, new String[] {"", "s"}),
		Array.combine
		(
			new String[] {"", " do I have", " do I have with me", " with me", " remaining", " left"},
			Array.permute(new String[] {" do I have remaining in my ", " remaining in my ", " do I have left in my ", " left in my ", " do I have in my ", " in my "}, new String[] {"rations"})
		)
	),
	
	/* not totally colloquial due to the surrounding permutations but it doesn't matter the way these are used */
	triggers_nonsubsafe_colloquial = Array.permute
	(
		new String[] {"", "how many ", "how much ", "do I have ", "do I have any "},
		new String[] {"edibles", "food", "food items", "grub", "rations", "snacks", "pieces of food", "bites of food"},
		Array.combine
		(
			new String[] {"", " do I have", " do I have with me", " with me", " remaining", " left"},
			Array.permute(new String[] {" do I have remaining in my ", " remaining in my ", " do I have left in my ", " left in my ", " do I have in my ", " in my "}, new String[] {"rations"})
		)
	),
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}