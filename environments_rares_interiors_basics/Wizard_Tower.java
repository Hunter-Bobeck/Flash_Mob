package environments_rares_interiors_basics;

import utilities.*;

public class Wizard_Tower extends Rare_Interior_Basic
{
    public Wizard_Tower()
	{
		super();

		water = true;
		context = "inside a wizard tower.";
		description = Random.text_of(new String[] {"The interior of this tower is sculpted of luxurious marble. Through mystical-looking shimmering windows, ethereal light is pouring in. Tall statues of armor guard either side of the entryway. Various doors and portals open the way to other rooms, where wizardly devices such as an alchemy table, an enchanting pedestal, a summoning dais, and several bookcases holding tomes and scrolls are visible. A magnificent dining hall in one room of indeterminate length holds a dining table of changing size. Tapestries around you depict spectacular scenes of magical combat, which usually awe visitors standing in the foyer like this for some time."});
		large_character_placements = new String[] {"aside the decorated brick wall", "under an arched doorway", "on the summoning dais", "on the summoning circle", "aside a bookcase of many tomes and scrolls", "by an open portal", "next to a tea table in a tiny dining hall", "in front an endlessly long table in a dining hall", "next to a mystical window", "in a scroll study room", "in the middle of the entryway", "next to one of the pair of statues in the entryway", "at the alchemy station"};
		noncharacter_placements = new String[] {"on a large shelf next to many ingredients", "on an alchemy table", "on the enchanting pedestal", "on a bookshelf next to several aged scrolls", "on a platform by a doorway where a statue would usually stand", "on a very long dining table in a huge dining hall", "on a tea table in a tiny dining hall", "in an old urn"};
		moba = Array.combine(moba_elementals, moba_golems);
	}
}