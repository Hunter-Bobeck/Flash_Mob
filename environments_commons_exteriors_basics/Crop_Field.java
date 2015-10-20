package environments_commons_exteriors_basics;

import utilities.*;

public class Crop_Field extends Common_Exterior_Basic
{
    public Crop_Field()
	{
		super();

		context = Random.text_of(new String[] {"in a crop field.", "in a field of crops.", "in a farmland."});
		description = Random.text_of(new String[] {"Countless rows of corn stand short, not yet fully grown.", "A farmer works the field in the distance."});
		large_character_placements = new String[] {"in a cropless clearing", "on stamped crops"};
		nonlarge_character_placements = new String[] {"under berry vines"};
		noncharacter_placements = new String[] {"covered in tilled dirt", "under a heap of corn husks", "nestled into the cornstalks"};
		moba = Array.combine(moba_gremlin, mobs_permute(new String[] {"Ankheg", "Otyugh", "Shambling_Mound"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}