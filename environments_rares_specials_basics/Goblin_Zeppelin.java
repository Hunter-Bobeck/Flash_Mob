package environments_rares_specials_basics;

import utilities.*;
import mobs.*;

public class Goblin_Zeppelin extends Rare_Special_Basic
{
    public Goblin_Zeppelin()
	{
		super();
		
		water = true;
		context = "on a goblin zeppelin, sitting next to several mostly-friendly goblins.";
		description = "The zeppelin is rickety, but flying. It is being piloted by a goblin in an enclosed compartment at the fore of the craft. An enchantment seems to keep wind from blowing into the passenger compartment you are in. A table with dice rests in between two benches, allowing for entertainment along the way.";
		large_character_placements = new String[] {"next to the rickety wooden wheel", "by the aft propellers", "aside the rope netting"};
		nonlarge_character_placements = new String[] {"precariously on the railing"};
		noncharacter_placements = new String[] {"under a goblin game table", "on the floorboards"};
		moba = mobs_permute(new String[] {"Goblin", "Goblin", "Harpy", "Harpy", "Harpy"});
	}
}