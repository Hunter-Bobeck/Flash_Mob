package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Water
{
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial = {"how much water", "check water"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_prephrases = Array.combine
	(
		new String[] {""},
		Array.permute
		(
			new String[] {"how many", "how much", "do I have", "do I have any", "what's in my", "what is in my", "what are the contents of my", "what do I have in my", "what am I carrying in my", "what am I lugging in my", ""},
			new String[] {" "}
		)
	),
	triggers_nonsubsafe_postphrases = {"", " do I have", " do I have with me", " with me", " remaining", " left"},
	triggers_nonsubsafe_postprephrases = {" do I have remaining in my ", " remaining in my ", " do I have left in my ", " left in my ", " do I have in my ", " in my "},
	triggers_nonsubsafe_waterskins = {"waterskin", "canteen", "waterbottle"},
	
	
	triggers_nonsubsafe_uncolloquial = Array.permute
	(
		triggers_nonsubsafe_prephrases,
		new String[] {"water", "drops of water", "drinks", "chugs", "drinks of water", "chugs of water"},
		Array.combine
		(
			triggers_nonsubsafe_postphrases,
			Array.permute(triggers_nonsubsafe_postprephrases, triggers_nonsubsafe_waterskins)
		)
	),
	
	/* not totally colloquial due to the surrounding permutations, but with the irregular way these are used, it doesn't matter  */
	triggers_nonsubsafe_colloquial = Array.permute
	(
		triggers_nonsubsafe_prephrases,
		new String[] {"water", "sips of water", "canteen", "waterskin"},
		Array.combine
		(
			triggers_nonsubsafe_postphrases,
			Array.permute(triggers_nonsubsafe_postprephrases, triggers_nonsubsafe_waterskins)
		)
	),
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}