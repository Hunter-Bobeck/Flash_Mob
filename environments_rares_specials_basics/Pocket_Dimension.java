package environments_rares_specials_basics;

import utilities.*;

public class Pocket_Dimension extends Rare_Special_Basic
{
    public Pocket_Dimension()
	{
		super();

		water = true;
		context = "in a pocket dimension.";
		description = "This magical container of indiscrete size holds you, functioning as a room. There are no walls; there is only a spaceless space.";
		large_character_placements = new String[] {"just a small distance away", "to your left", "to your right", "in front of you", "lying behind you", "seemingly above you", "enclosed with the dpeth of the pocket dimension", "near the opening of the pocket dimension"};
		if (Random.whole() <= .67)		moba = new String[] {};
		else		moba = moba_elementals;
	}
}