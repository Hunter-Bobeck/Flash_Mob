package environments_rares_exteriors_basics;

import utilities.*;

public class Solid_Cloud extends Rare_Exterior_Basic
{
    public Solid_Cloud()
	{
		super();

		water = true;
		context = "atop a solid cloud in "+Random.text_of(new String[] {"the sky", "the heights of the world"})+"...";
		description = Random.text_of(new String[] {"Looking down, you see only more clouds... you cannot be sure whether they are solid as well. The sky above you is a brilliantly clear starscape", "The fluffy surface of this cloud is somehow holding your weight, even as it drifts slowly through other, less substantial clouds in the sky"})+".";
		large_character_placements = new String[] {"on a misty puff", "on a moist gust", "on a pillow of air", "on the edge of a billow"};
		moba = moba_sky;
	}
}