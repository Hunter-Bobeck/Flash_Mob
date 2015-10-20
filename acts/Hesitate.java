package acts;

import utilities.*;

public class Hesitate
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"Idk", "Idnk", "I do not know", "I don't know", "I'm unsure"},
	
	triggers_subsafe_colloquial = Array.combine
	(
		new String[] {"hesitate", "who knows"},
		new String[] {"I am unsure"},
		Array.permute
		(
			new String[] {"I am unsure of "},
			new String[] {"my plan", "my course of action"},
			new String[] {"", " now"}
		),
		Array.permute
		(
			new String[] {"I am unsure", "I am unsure of", "I do not know"},
			Array.combine
			(
				Array.permute
				(
					new String[] {" what "},
					new String[] {"I am going to do", "to do", "I want to do", "I will do", "is next", "my plan is"},
					new String[] {"", " now", " next"}
				),
				new String[] {" what my course of action is", " what my next course of action is", " what my course of action is now"}
			)		// these phrases are matched with the act prompt question phrases //
		)
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Hesitate()
	{
		Type.delay(Random.text_of(new String[] {"Hmmm.", "Hmm... what to do.", "What to do.", "What to do... what to do.", ".", ".", "."}));
		for (int i = 0; i < Random.integer_from(2, 3); i++)		Type.delay(".", Random.integer_from(300, 700));
		System.out.println();
	}
}