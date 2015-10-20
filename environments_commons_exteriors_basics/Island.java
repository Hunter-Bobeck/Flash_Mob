package environments_commons_exteriors_basics;

import utilities.*;

public class Island extends Common_Exterior_Basic
{
    public Island()
	{
		super();

		context = "on an island.";
		description = Random.text_of(new String[] {"A crashed ship is washed up against the shore.", "A crab is carrying a dead fish into his hole in the sand.", "A pirate campfire is blazing behind some palm trees.", "There is a message in a bottle "+Random.text_of(new String[] {"beached next to some fronds", "lying in the sand", "floating in a puddle", "floating in the water"})+"."});
		large_character_placements = new String[] {"near the center of the island, by a palm tree", "next to a blazing campfire", "next to a few palms"};
		nonlarge_character_placements = new String[] {"in a shallow puddle of seawater in the soaked sand"};
		noncharacter_placements = new String[] {"near the open center of the island, in the sand", "on the sand of the beach", "in the stowed-away nest of some animal", "in the water, being slowly washed away by the tide", "beneath the splashing waves", "on the wet sand", "among scattered empty bottles"};
		moba = Array.combine(moba_sea_only_multiplied, mobs_permute(new String[] {"Water_Elemental", "Pirate", "Pirate", "Pirate", "Pirate", "Pirate"}));
	}
}