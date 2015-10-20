package environments_rares_exteriors_basics;

import utilities.*;

public class Ravine extends Rare_Exterior_Basic
{
    public Ravine()
	{
		super();

		context = "at the bottom of a ravine.";
		description = Random.text_of(new String[] {"Plenty of ores are visible in the rocky walls to either side of you", "A trickle of water runs down the wall to your side, slipping away into a crack to continue underground", "The sky above looks especially distant, for you are deep below the level of the terrain above", "Few plants grow here, but those that do appear quite strong, grasping onto the sheer rock walls sideways by their roots"})+".";
		large_character_placements = new String[] {"among a cluster of rocks", "near a gaping crevice", "aside a sheer rock wall", "aside a sheer stone wall"};
		noncharacter_placements = new String[] {"on the debris-covered ground", "on the dusty surface", "on some crushed plants"};
		moba = Array.combine(moba_harpies_and_manticores_with_gremlin, mobs_permute(new String[] {"Grell"}));
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle", "Dust_Mephit"}));
		if (Random.whole() <= moba_giant_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Stone_Giant"}));
	}
}