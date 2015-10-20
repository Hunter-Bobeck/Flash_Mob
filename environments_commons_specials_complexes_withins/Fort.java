package environments_commons_specials_complexes_withins;

import utilities.*;

public class Fort extends Common_Special_Complex_Within
{
    public Fort()
	{
		super();

		context = Random.text_of(new String[] {"oustide of a fort", "outside of an old fort", "outside of a rundown fort"});
		description = Random.text_of(new String[] {"The fort is massive, and its crenellated battlements are equipped with catapults", "A large portcullis is raised, allowing entry into the fort", "The fort appears to have been assaulted recently; there are several fires still smoldering across the grounds and scorches mark the walls"})+".";
		large_character_placements = new String[] {"under the raised portcullis", "in between the upper crenellations", "at the forge", "on the battlements", "by the catapult station", "near the closed gate", "on the inner grounds", "on the hill outside the fort", "next to a collection of ingots by the forge", "by the grindstone of the forge", "by the weapon rack", "behind a reinforced wall", "in a trench lined with spears", "in the open gateway to the fort's interior"};
		nonlarge_character_placements = new String[] {"in an opened, hanging cage"};
		noncharacter_placements = new String[] {"on the footprinted ground inside the fort", "on the ground within the fort walls"};
		moba = Array.combine(moba_roamers, moba_roamers, moba_cultists, moba_fighters, moba_gremlin);
		if (Random.whole() <= moba_golem_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Clay_Golem", "Iron_Golem", "Stone_Golem", "Helmed_Horror", "Shield_Guardian"}));
	}
}