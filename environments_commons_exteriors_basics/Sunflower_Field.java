package environments_commons_exteriors_basics;

import utilities.*;

public class Sunflower_Field extends Common_Exterior_Basic
{
    public Sunflower_Field()
	{
		super();
		
		context = Random.text_of(new String[] {"in a field of sunflowers.", "in a sunflower field."});
		description = Random.text_of(new String[] {"The sunflowers are tall and bristled.", "There is a large bag of sunflower seeds sitting here."});
		large_character_placements = new String[] {"among the sunflowers", "next to a thicker patch of the flowers", "in the middle of the sunflowers"};
		noncharacter_placements = new String[] {"on the grass", "next to a bag of sunflower seeds"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}