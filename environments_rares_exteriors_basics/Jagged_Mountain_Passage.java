package environments_rares_exteriors_basics;

import utilities.*;

public class Jagged_Mountain_Passage extends Rare_Exterior_Basic
{
    public Jagged_Mountain_Passage()
	{
		super();

		context = "in a jagged passage between steep mountains.";
		description = Random.text_of(new String[] {"Minor avalanches keep sliding down around you", "The way is nearly blocked, except for a crevice between piled stones", "The mountains' steep slopes spire above you on either side. You feel dizzy as your vision follows one slope up to the distant top"})+".";
		large_character_placements = new String[] {"next to some of the many boulders where the two nearest mountains meet", "among the scattered rocks of the mountainside", "in the crag between two broken rock faces", "aside a sheer rock wall", "between several sharp rocks"};
		noncharacter_placements = new String[] {"among the grass and scattered rocks of the mountainside", "with the debris of a settled avalanche"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Hill_Giant", "Stone_Giant", "Harpy", "Hill_Giant", "Stone_Giant", "Harpy", "Hill_Giant", "Stone_Giant", "Harpy"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
		if (Random.whole() <= moba_giant_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Fire_Giant"}));
	}
}