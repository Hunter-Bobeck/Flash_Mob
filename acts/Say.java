package acts;

import utilities.*;

public class Say
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"speak", "talk", "tell", "declare", "express", "yak", "verbalize", "lip", "converse", "blab", "voice", "utter", "chat", "yammer", "enunciate", "discuss", "mention", "state that", "communicate", "respond", "suggest", "put into words", "break the silence", "break silence", "wordify", "gossip", "murmur", "susurrate"},
	
	triggers_subsafe_colloquial_permutations_quiet = {" quietly", " softly"},
	triggers_subsafe_colloquial_permutations_loud_without_leading_out = {" loudly", " audibly", " obnoxiously"},
	triggers_subsafe_colloquial_permutations_loud = Array.combine(new String[] {" out loud"}, triggers_subsafe_colloquial_permutations_loud_without_leading_out),
	triggers_subsafe_colloquial_permutations_pronunciation = {"", " the word", " the phrase", " the saying", " the sentence", "the line", "the song", "the poem", "the epic", "the tune", "chant", "lyric", "lyrics", "verse", "piece"},
	/* not distributed properly for actual colloquial use (not used anyway though) */
	triggers_subsafe_colloquial = Array.combine
	(
		Array.permute
		(
			new String[] {"say", "call", "chant"},
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_quiet, triggers_subsafe_colloquial_permutations_loud),
			new String[] {"", " that"}
		),
		Array.permute
		(
			new String[] {"call out"},
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_quiet, triggers_subsafe_colloquial_permutations_loud_without_leading_out),
			new String[] {"", " that"}
		),
		Array.permute
		(
			new String[] {"whisper"},
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_quiet),
			new String[] {"", " that"}
		),
		Array.permute
		(
			new String[] {"pronounce"},
			triggers_subsafe_colloquial_permutations_pronunciation,
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_quiet, triggers_subsafe_colloquial_permutations_loud)
		),
		Array.permute
		(
			new String[] {"sing"},
			triggers_subsafe_colloquial_permutations_pronunciation,
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_quiet, triggers_subsafe_colloquial_permutations_loud),
			new String[] {"", " that"}
		),
		Array.permute
		(
			new String[] {"yell", "scream", "shout"},
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_loud),
			new String[] {"", " that"}
		),
		Array.permute
		(
			new String[] {"yell out"},
			Array.combine(new String[] {""}, triggers_subsafe_colloquial_permutations_loud_without_leading_out),
			new String[] {"", " that"}
		)
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
		
	public Say()
	{
		// setup input (if quotations were inputted, any postquote input may be manipulated later) //
		String input = Act.input_backup;
		// if an uncolloquial trigger was inputted, just describe the player speaking to themself //
		if (Array.subcontainment_case_ignored(input, triggers_subsafe_uncolloquial))
			Type.delay_wait_line("You "+Random.text_of(new String[] {"speak", "talk", "mumble something"})+" to yourself.", Random.integer_from(2, 3) * 400);
		// otherwise (if a colloquial trigger was inputted), describe the player saying something in particular based on what they inputted posttrigger  //
		else
		{
			String
			// setup the trigger as the one contained within the input //
			trigger = triggers_subsafe_colloquial[Array.subcontainment_case_ignored_index(input, triggers_subsafe_colloquial)],
			// declare the posttrigger //
			posttrigger = null;
			// if the player inputted something past the trigger, further initialize the posttrigger just as whatever was inputted past the trigger //
			if (!Array.containment(triggers_subsafe_colloquial, input))		posttrigger = input.substring(input.indexOf(trigger) + trigger.length());
			// if the player inputted nothing past the trigger, or at least two quotations but without any text between the first two of the at least two quotations, setup the posttrigger as a description as either 'something' or 'nothing' //
			if (Array.containment(triggers_subsafe_colloquial, input)  ||  (posttrigger.contains("\"")  &&  posttrigger.indexOf("\"") == posttrigger.indexOf("\"\"")))
			{
				if (trigger.contains("sing"))		posttrigger = Random.text_of(new String[] {"something"});
				else		posttrigger = Random.text_of(new String[] {"something", "nothing"});
			}
			// else if at least two quotations were inputted, with text between them, describe the player saying the quote (between the first two quotations of the at least two inputted) //
			else if (posttrigger.contains("\"")  &&  posttrigger.indexOf("\"", posttrigger.indexOf("\"") + "\"".length()) != -1  &&  posttrigger.indexOf("\"") != posttrigger.indexOf("\"\""))
			{
				// setup the quoted text as the text between the first two of the at least two quotations in the posttrigger //
				String quoted = posttrigger.substring(posttrigger.indexOf("\"") + "\"".length(), posttrigger.indexOf("\"", posttrigger.indexOf("\"") + "\"".length()));
				// setup the postquote as an empty string //
				String postquote = "";
				// if there is text postquote, further setup the postquote as the postquote text, and manipulate it to contain 'yourself's in place of any 'myself's, , ' you 's in place of any ' i 's, and ' you, 's in place of any ' i, 's  //
				if (posttrigger.indexOf(quoted) + quoted.length()  <=  posttrigger.length())
				{
					postquote = posttrigger.substring(posttrigger.indexOf("\"", posttrigger.indexOf("\"") + "\"".length()) + "\"".length());
					while (Text.lower(postquote).contains(" my "))
						postquote = postquote.substring(0, Text.lower(postquote).indexOf(" my "))+" your "+postquote.substring(Text.lower(postquote).indexOf(" my ") + " my ".length());
					while (Text.lower(postquote).contains("myself"))
						postquote = postquote.substring(0, Text.lower(postquote).indexOf("myself"))+"yourself"+postquote.substring(Text.lower(postquote).indexOf("myself") + "myself".length());
					while (Text.lower(postquote).contains(" i "))
						postquote = postquote.substring(0, Text.lower(postquote).indexOf(" i "))+" you "+postquote.substring(Text.lower(postquote).indexOf(" i ") + " i ".length());
					while (Text.lower(postquote).contains(" i, "))
						postquote = postquote.substring(0, Text.lower(postquote).indexOf(" i, "))+" you, "+postquote.substring(Text.lower(postquote).indexOf(" i, ") + " i, ".length());
				}
				// redefine the posttrigger as whatever was quoted within the posttrigger, then whatever was past that (effectively excluding any posttrigger but prequote text) //
				posttrigger = "\""+quoted+"\""+postquote;
			}
			// else (with posttrigger text but less than two quotations), redefine the whole posttrigger as a quote //
			else		posttrigger = "\""+input.substring(input.indexOf(trigger) + trigger.length() + " ".length())+"\"";
			Type.delay_wait_line("You "+trigger+" "+posttrigger+".", Random.integer_from(2, 3) * 400);
		}
	}
}