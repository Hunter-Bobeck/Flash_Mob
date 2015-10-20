package environments_commons_exteriors_basics;

import utilities.*;

public class Frozen_River extends Common_Exterior_Basic
{
    public Frozen_River()
	{
		super();
		
		water = true;
		snowy = true;
		context = "beside a frozen river.";
		description = Random.text_of(new String[] {"Your breath mists into the air in front of you.", "Footprints are visible leading up to the bank."});
		large_character_placements = new String[] {"in the slushy waters", "on the side of frozen stream"};
		noncharacter_placements = new String[] {"on the frozen surface of the water", "on the snowy ground", "on the frozen ground", "on the iced-over ground"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Water_Elemental", "Ice_Mephit", "Frost_Giant", "Frost_Giant", "Frost_Giant", "Young_Remorhaz", "Remorhaz"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}