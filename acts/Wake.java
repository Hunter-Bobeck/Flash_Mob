package acts;

import utilities.*;
import acts_combat.*;
import players.*;
import scenarios.*;

public class Wake
{
	// subsafe triggers permutations //
	
	
	private static final String[]
	
	
	triggers_become = Array.permute(new String[] {"become "}, new String[] {"awake", "awakened", "conscious", "wakeful"}),
	
	trigger_suffixes_sleep = {"catching winks", "catnapping", "dozing", "napping", "nodding", "shuteye", "sleep", "sleeping", "snoozing"},
	trigger_suffixes_sleep_singular = {"catnap", "doze", "nap", "nod", "snooze", "winks"},
	trigger_suffixes_my = Array.permute
								(
									new String[] {"my "},
									Array.combine
									(
										trigger_suffixes_sleep,
										trigger_suffixes_sleep_singular
									),
									new String[] {"", " session"}
								),
	trigger_suffixes_sleep_my = Array.combine(trigger_suffixes_sleep, trigger_suffixes_my),
	triggers_sleep_my = Array.permute
								(
									new String[] {"cease ", "complete ", "conclude ", "discontinue ", "end ", "finish ", "forsake ", "halt ", "relinquish ", "stop "},
									trigger_suffixes_sleep_my
								),
	trigger_suffixes_from = Array.permute
									(
										new String[] {"from "},
										trigger_suffixes_sleep_my
									),
	triggers_from = Array.permute
							(
								new String[] {"break away ", "come away "},
								trigger_suffixes_from
							),
	trigger_suffixes_sleep_my_from = Array.combine
											(
												trigger_suffixes_sleep,
												trigger_suffixes_my,
												trigger_suffixes_from
											),
	triggers_sleep_my_from = Array.permute
									(
										new String[] {"cutoff ", "escape ", "exit ", "pull out ", "quit ", "sally ", "slip ", "withdraw "},
										trigger_suffixes_sleep_my_from
									),
	trigger_suffixes_sleep_my_from_none = Array.combine
												(
													trigger_suffixes_sleep_my_from,
													new String[] {""}
												),
	triggers_sleep_my_from_none = Array.permute
										(
											new String[] {"rouse"},
											trigger_suffixes_sleep_my_from_none
										),
	trigger_suffixes_with = Array.permute
									(
										new String[] {"with "},
										trigger_suffixes_sleep_my
									),
	trigger_suffixes_sleep_my_from_with = Array.combine
												(
													trigger_suffixes_sleep_my_from,
													trigger_suffixes_with
												),
	triggers_sleep_my_from_with = Array.permute
										(
											new String[] {"break ", "part ", "push off "},
											trigger_suffixes_sleep_my_from_with
										),
	
	triggers_gain = Array.permute
							(
								new String[] {"gain "},
								new String[] {"consciousness", "wakefulness"}
							),
	
	triggers_wake = Array.permute
							(
								new String[] {"wake"},
								new String[] {"", " myself", " up"}
							);
	
	
	
	
	
	
	
	
	// triggers //
	
	
	
	
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial = Array.combine(new String[] {"reality", "become aroused"}, triggers_sleep_my, triggers_from, triggers_sleep_my_from, triggers_sleep_my_from_none, triggers_sleep_my_from_with, triggers_gain),
	
	triggers_subsafe_colloquial = Array.combine(new String[] {"awaken", "open my eyes", "rise and shine", "rouse"}, triggers_become, triggers_wake),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String wakening_statement()
	{
		String wakening_statement = "You "+Random.text_of(triggers_colloquial)+".";
		while (wakening_statement.contains(" my "))
			wakening_statement = wakening_statement.substring(0, wakening_statement.indexOf(" my "))+" your "+wakening_statement.substring(wakening_statement.indexOf(" my ") + " my ".length());
		while (wakening_statement.contains("myself"))
			wakening_statement = wakening_statement.substring(0, wakening_statement.indexOf("myself"))+"yourself"+wakening_statement.substring(wakening_statement.indexOf("myself") + "myself".length());
		return wakening_statement;
	}
	public Wake(Player player, Scenario scenario, boolean from_Ahead)
	{
		Type.delay_line(Random.text_of(new String[] {"You are not even sleeping!", "This is not a dream.", "You must fall asleep before awakening.", "You are wakeful already."}));
		if (from_Ahead)		Ahead.reprompt(player, scenario, scenario.encounter.mob);
	}
}