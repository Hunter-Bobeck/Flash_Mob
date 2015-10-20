package environments_rares_specials_basics;

import utilities.*;

public class Darkness extends Rare_Special_Basic
{
    public Darkness()
	{
		super();

		context = "in sudden darkness!";
		description = Random.text_of(new String[] {"It's too dark to see anything whatsoever.", "Your vision picks up only pitch blackness."});
		large_character_placements = new String[] {"just a small distance away", "to your left", "to your right", "in front of you", "lying behind you", "seemingly above you"};
		moba = Array.combine(moba, moba_underground_with_gremlin, moba_drow, mobs_permute(new String[] {"Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider", "Smoke_Mephit", "Otyugh", "Giant_Spider"}));
	}
}