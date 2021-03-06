package acts;

import utilities.*;

public class Burp
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"eruct"},
	
	triggers_subsafe_colloquial = {"burp", "belch"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	private static final String[]
	burp_styles = {"loudly", "out loud", "noisily", "audibly", "obnoxiously", "robustly", "more subtlely than you intended", "a long one", "for an extended duration"},
	burp_aims = Array.permute(new String[] {" "}, new String[] {"on", "at", "toward", "in the direction of", "around", "near", "nearby"}, new String[] {" "});
	
	public Burp()
	{
		// setup potentially stylish (otherwise empty) descriptor text // 
		String descriptor = "";
		// setup a manipulated version of the input containing 'yourself's in place of any 'myself's, ' you 's in place of any ' i 's, and ' you, 's in place of any ' i, 's //
		String input = Act.input_backup;
		while (Text.lower(input).contains(" my "))		input = input.substring(0, Text.lower(input).indexOf(" my "))+" your "+input.substring(Text.lower(input).indexOf(" my ") + " my ".length());
		while (Text.lower(input).contains("myself"))		input = input.substring(0, Text.lower(input).indexOf("myself"))+"yourself"+input.substring(Text.lower(input).indexOf("myself") + "myself".length());
		while (Text.lower(input).contains(" i "))		input = input.substring(0, Text.lower(input).indexOf(" i "))+" you "+input.substring(Text.lower(input).indexOf(" i ") + " i ".length());
		while (Text.lower(input).contains(" i, "))		input = input.substring(0, Text.lower(input).indexOf(" i, "))+" you, "+input.substring(Text.lower(input).indexOf(" i, ") + " i, ".length());
		// potentially have the description be stylish; guaranteed if the input was stylish //
		if (Array.subcontainment_case_ignored(input, burp_styles) || Random.whole() <= .3)		descriptor = " "+Random.text_of(burp_styles);
		// describe the burping as happening aimed at a target the input described if applicable //
		if (Array.subcontainment_case_ignored(input, burp_aims))
		{
			String burp_aim = burp_aims[Array.subcontainment_case_ignored_index(input, burp_aims)];
			if (input.substring(Text.lower(input).indexOf(burp_aim) + burp_aim.length()).trim().equals(""))
				Type.delay_wait_line("You "+Random.text_of(triggers_subsafe_colloquial)+descriptor+Random.text_of(new String[] {"!", "."}), Random.integer_from(2, 3) * 400);
			else
			{
				if (Array.subcontainment_case_ignored(input.substring(Text.lower(input).indexOf(burp_aim) + burp_aim.length()), burp_styles))		descriptor = "";
			Type.delay_wait_line("You "+Random.text_of(triggers_subsafe_colloquial)+descriptor+burp_aim+input.substring(Text.lower(input).indexOf(burp_aim) + burp_aim.length())+Random.text_of(new String[] {"!", "."}));
			}
		}
		// otherwise, describe it as not happening on anything in particular //
		else		Type.delay_wait_line("You "+Random.text_of(triggers_subsafe_colloquial)+descriptor+Random.text_of(new String[] {"!", "."}), Random.integer_from(2, 3) * 400);
	}
}