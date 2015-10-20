package environments_rares_interiors_basics;

import utilities.*;

public class Wizard_Tower_Summoning_Room extends Rare_Interior_Basic
{
    public Wizard_Tower_Summoning_Room()
	{
		super();

		water = true;
		context = "inside the summoning room of a wizard tower!";
		description = Random.text_of(new String[] {"The summoning dais you are on is shimmering with a weaving magical energy... as you watch, still startled to be here, the mystical motion dies down.", "The room is dark, except for a few candles put up for this summoning."});
		large_character_placements = new String[] {"aside the decorated brick wall", "under an arched doorway", "at the other end of the summoning dais", "at the other end of the summoning circle", "aside a bookcase of many tomes and scrolls", "by an open portal", "next to a mystical window", "on the largest of the summoning runes designed on the circle"};
		noncharacter_placements = new String[] {"on a large shelf next to many ingredients"};
		moba = Array.combine(moba_elementals, moba_golems);
	}
}