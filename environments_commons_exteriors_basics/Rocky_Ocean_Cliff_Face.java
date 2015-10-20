package environments_commons_exteriors_basics;

import utilities.*;

public class Rocky_Ocean_Cliff_Face extends Common_Exterior_Basic
{
    public Rocky_Ocean_Cliff_Face()
	{
		super();

		context = "on a rocky cliff face by the ocean.";
		description = Random.text_of(new String[] {"The salty sea breeze whips into your face, especially at the edge of the cliff face.", "Many winged creatures fly throughout the skies here... some birds, other more humanoid in appearance.", "The waves are coming in at high tide.", "From even up here, you find the rhythmic washing of the waves to be relaxing.", "The wind is mild and refreshing."});
		large_character_placements = new String[] {"at the precipice", "on the edge of the cliff", "away from the edge"};
		nonlarge_character_placements = new String[] {"under an overturned boulder"};
		noncharacter_placements = new String[] {"on the stone"};
		moba = moba_harpies_with_gremlin;
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Earth_Elemental", "Galeb_Duhr", "Gargoyle", "Gargoyle", "Gargoyle", "Gargoyle"}));
		if (Random.whole() <= moba_giant_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Hill_Giant", "Stone_Giant", "Stone_Giant", "Hill_Giant", "Stone_Giant", "Stone_Giant"}));
	}
}