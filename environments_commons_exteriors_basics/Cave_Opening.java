package environments_commons_exteriors_basics;

import utilities.*;

public class Cave_Opening extends Common_Exterior_Basic
{
    public Cave_Opening()
	{
		super();

		context = "outside an opening to a cave.";
		description = Random.text_of(new String[] {"The opening is obscure, visible only from a certain angle.", "The opening gapes open widely, as if about to swallow you inside."});
		large_character_placements = new String[] {"just inside the mouth of the cave", "to the side of the cave entrance"};
		noncharacter_placements = new String[] {"amid some loose rocks", "next to a pile of bones", "next to several sticks"};
		moba = Array.combine(moba_gremlin, moba_roamers);
		if (Random.whole() <= .4)		moba = Array.combine(moba, moba_cultists);
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr"}));
		if (Random.whole() <= moba_stirge_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Stirge"}));
	}
}