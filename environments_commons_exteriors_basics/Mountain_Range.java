package environments_commons_exteriors_basics;

import utilities.*;

public class Mountain_Range extends Common_Exterior_Basic
{
    public Mountain_Range()
	{
		super();

		context = "in a mountain range.";
		description = Random.text_of(new String[] {"The other peaks you can see from this vantage point are snowy.", "A horde of goblins is marching out of a hole in a nearby mountain.", "A large bird is soaring from this mountain away to another.", "A giant is trudging down a steep path below you."});
		large_character_placements = new String[] {"halfway up a mountain, along a narrow rocky walkway", "next to some of the many boulders where the two nearest mountains meet", "just outside of a hole in the mountain", "near a steep peak", "on a narrow incline", "on a rocky ledge off of the mountain", "by the trees of the foothills", "at the top of a slope in the foothills", "among the debris of a settled avalanche", "in the crag between two broken rock faces", "inside a tunnel going through near the mountaintop", "against a sheer rock wall"};
		noncharacter_placements = new String[] {"among the grass and scattered rocks of the mountainside"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Hill_Giant", "Hill_Giant", "Hill_Giant", "Manticore", "Roc"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
		if (Random.whole() <= moba_giant_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Fire_Giant", "Frost_Giant", "Stone_Giant", "Yeti"}));
	}
}