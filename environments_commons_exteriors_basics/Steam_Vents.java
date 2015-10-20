package environments_commons_exteriors_basics;

import utilities.*;

public class Steam_Vents extends Common_Exterior_Basic
{
    public Steam_Vents()
	{
		super();

		water = true;
		context = Random.text_of(new String[] {"at a crag of steam vents.", "next to a geyser of steam coming out of a pore in rocky terrain.", "in a hot spring.", "in warm, bubbling steam pools."});
		description = Random.text_of(new String[] {"The steam from the geysers here go up to the height of two giants.", "A dragon can be seen flying through mountain peaks in the distance!", "There are geysers, bubbling pools, and steaming vents here. All of the water is hot or at least warm."});
		large_character_placements = new String[] {"in the nearby pool of hot water", "under the splashing water of a geyser", "next to a bubbling vent", "in an adjacent bubbling hot spring"};
		noncharacter_placements = new String[] {"in a shallow puddle of cooling water", "on the rocky jut", "on the warm ground"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Air_Elemental", "Water_Elemental", "Galeb_Duhr", "Steam_Mephit", "Steam_Mephit", "Steam_Mephit", "Harpy", "Harpy", "Harpy", "Harpy"}));
	}
}