package environments_rares_specials_basics;

import utilities.*;

public class Abyssal_Bridge extends Rare_Special_Basic
{
    public Abyssal_Bridge()
	{
		super();

		context = "on a faded bridge in the abyss!";
		description = Random.text_of(new String[] {"The abyssal walkway is ethereal, appearing transluscent and only somewhat tangible. It connects one hovering rock island to the next. Around you, an angry void seethes with billowing red nebulae of unmanifest demonic forms. It is neither dark nor light here in the abyss."});
		large_character_placements = new String[] {"on the edge of the ethereal bridge", "hovering in the middle of the bridge", "on the faded walkway", "on a rocky island on this end of the bridge", "on the rocky island across from the bridge"};
		noncharacter_placements = new String[] {"on the faded surface of the bridge", "on a the surface of a rocky island"};
		moba = moba_abyss;
	}
}