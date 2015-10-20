package environments_rares_specials_basics;

import utilities.*;

public class Hell extends Rare_Special_Basic
{
    public Hell()
	{
		super();

		context = "in a molten hell!";
		description = "There is an intense orange smoke around you, feeling like a thick and hot heat. Devilish creatures crawl and fly about. Magma pours over volcanic rock. The ground here is made of fused bones. You're ready to leave.";
		large_character_placements = new String[] {"on a rising volcanic rock dripping in magma", "on a hellish rock bridge surrounded by lava", "hovering ominously above a pool of lava", "on the dirty and scorched surface of the hell", "in a pool of hot rocks", "on the hellish expanse"};
		noncharacter_placements = new String[] {"on the bone-embedded ground", "fused into the ground where bones are melded together"};
		moba = Array.combine(moba_hells_multiplied, new String[] {"Fire_Elemental", "Magmin", "Magma_Mephit", "Smoke_Mephit", "Flameskull"});
	}
}