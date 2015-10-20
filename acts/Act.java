package acts;

import utilities.*;
import players.*;
import scenarios.*;

public class Act
{
	// input backup //
	public static String input_backup = "";
	
	
	
	
	// allowed informational trigger examples to be listed when acting Help in each context //
	public static final String[] allowed_informational_trigger_examples = {"inventory", "food", "water", "condition", "hunger", "thirst", "experience", "abilities", "gender", "name", "health", "strength", "agility"};
	
	// allowed informational trigger examples listing //
	public static void list_allowed_informational_trigger_examples()
	{
		for (int i = 0; i < allowed_informational_trigger_examples.length; i++)
		{
			Type.delay_line("\t\t› "+allowed_informational_trigger_examples[i], 10);
		}
		System.out.println();
		
	}
	
	
	// possible unrecognized/nontrigger error statements //
	public static final String[] error_statements = {"You are not sure what that would entail.", "You do not even know what you mean.", "You do not recognize such an intent.", "You feel confused."};
	
	// unrecognized/nontrigger errors count (for triggering Help every 3 times not including the first) //
	public static int unrecognized_nontrigger_errors = 0;
	
	// unrecognized/nontrigger error - statement + count incrementing //
	public static void unrecognized_nontrigger_error()
	{
		Type.delay_line("🅧: "+Random.text_of(error_statements), 10);
		unrecognized_nontrigger_errors++;
	}
}