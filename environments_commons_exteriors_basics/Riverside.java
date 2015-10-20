package environments_commons_exteriors_basics;

import utilities.*;

public class Riverside extends Common_Exterior_Basic
{
    public Riverside()
	{
		super();

		water = true;
		context = "at a riverside.";
		description = Random.text_of(new String[] {"The river is very wide. The current isn't very strong.", "The river is thin and flowing very rapidly.", "Rocks stick out in the middle of the streaming river, allowing for a way across.", "The water is very clear, affording a view of some fish swimming below and several smooth pebbles underneath."});
		large_character_placements = new String[] {"at the edge of the water", "on a large rock in the middle of the river"};
		noncharacter_placements = new String[] {"in the shallow bank of clear water, nestled in some pebbles", "floating in the stream", "on the grass", "on the ground to the side"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Water_Elemental"}));
	}
}