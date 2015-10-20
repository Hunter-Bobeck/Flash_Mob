package environments_rares_interiors_basics;

import utilities.*;

public class Drow_Complex_Cavern extends Rare_Interior_Basic
{
    public Drow_Complex_Cavern()
	{
		super();

		context = Random.text_of(new String[] {"inside", "within"})+" a massive "+Random.text_of(new String[] {"Underdark ", ""})+"cavern housing a drow complex.";
		description = Random.text_of(new String[] {"The cavern is vast and mostly dark. Even with little light, it is obvious that this cavern is massive, and lived in. Huge stalactites hang down into the complex, sometimes serving as embedded drow structures. Luminescent organisms of some kind appear as starry specks on the distant ceiling. Elegant drow buildings can be seen marked by the purple glowing edges of faerie fire. Far-off torches are present at a market enclave, and closer ones burn in the outlying rothé farms managed by goblin slaves. From your position at this entrance, the awe of the place puts the cave systems behind you to shame"})+".";
		large_character_placements = new String[] {"aside the nearest "+Random.text_of(new String[] {"rock", "stone", "stippled"})+" wall", "on a curved shale table", "under some stalactites", "between some stalagmites", "next to some glowing mushrooms on the wall", "next to an ore vein", "with a pile of bones", "in the hole to another passage", "closer to the entrance", "at the opening to the cavern", "in some sticky goo", "in a field of rothé", "underneath the archway of a noble drow house", "at a stall in the busied marketplace", "in a goblin slave warren", "in the courtyard of a common drow house", "along a walkway between spiderlike shrines", "in the foyer to the priestess school", "below the tower of the wizard academy", "at the doors of the warrior academy"};
		nonlarge_character_placements = new String[] {"in a puddle of stagnant water"};
		noncharacter_placements = new String[] {"on the rock floor", "on the stone floor", "on the stippled ground", "over a tiny stream of water going through the cracks of the cavern"};
		moba = Array.combine(moba_drow, moba_underground, mobs_permute(new String[] {"Goblin", "Goblin", "Goblin", "Goblin", "Goblin", "Goblin", "Goblin", "Goblin", "Goblin"}), moba_gremlin);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
		if (Random.whole() <= moba_aberration_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Grell"}));
	}
}