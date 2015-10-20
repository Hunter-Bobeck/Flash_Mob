package environments_rares_exteriors_basics;

import utilities.*;

public class Floating_Island extends Rare_Exterior_Basic
{
    public Floating_Island()
	{
		super();

		water = true;
		context = "atop a floating island in the sky...";
		description = Random.text_of(new String[] {"The floating island is massive... it's only obviously floating when you stand at the edge or notice the many clouds drifting through at same level", "There are multiple boulder-sized slabs in the grass", "Another island is above you, from which you can see roots hanging down, sticking out of its inverted dirt pile form", "There are strange flowers on this landform, and a few dangerous-looking holes in the ground"})+".";
		large_character_placements = new String[] {"on a table-like slab of stone", "on a smaller, drifting island of hanging roots above", "among some strange flowers", "next to a gaping hole in the earth", "in a pool of rainwater"};
		noncharacter_placements = new String[] {"on the grass"};
		moba = moba_sky;
	}
}