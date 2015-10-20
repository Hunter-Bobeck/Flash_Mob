package environments_commons_exteriors_basics;

import utilities.*;

public class Snowy_Beach extends Beach
{
    public Snowy_Beach()
	{
		super();
		
		water = true;
		snowy = true;
		context = "on a snowy beach.";
		description = Random.text_of(new String[] {"The waters are too frozen to move.", "A hole is visible in the ice, likely made by someone for fishing."});
		large_character_placements = new String[] {"in the slushy waters", "on the snowy sands"};
		noncharacter_placements = new String[] {"on the frozen surface of the water", "in a frozen pool of water in the snowy sand"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Water_Elemental", "Ice_Mephit", "Frost_Giant", "Frost_Giant", "Frost_Giant", "Young_Remorhaz", "Remorhaz", "Merrow"}));
	}
}