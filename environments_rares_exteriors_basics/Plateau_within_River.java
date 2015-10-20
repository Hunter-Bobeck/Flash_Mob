package environments_rares_exteriors_basics;

import utilities.*;

public class Plateau_within_River extends Rare_Exterior_Basic
{
    public Plateau_within_River()
	{
		super();

		context = "on a plateau with a river around it.";
		description = Random.text_of(new String[] {"The ground is remarkably flat, and on the ground lie similarly flat rocks. A strong gale is coming up over the ridge to nearly knock you off your feet", "The ground you are on is very flat. A river lies glistening below, encircling the plateau then winding off in another direction"})+".";
		large_character_placements = new String[] {"on the edge of the sloping side", "on a table-like rock at the center of the terrain", "on a patch of sparse grass", "on a ledge overlooking the river below"};
		noncharacter_placements = new String[] {"on the flat ground", "on the stone ground"};
		moba = moba_gremlin;
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
		if (Random.whole() <= moba_giant_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Hill_Giant"}));
	}
}