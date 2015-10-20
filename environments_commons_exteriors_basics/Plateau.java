package environments_commons_exteriors_basics;

import utilities.*;

public class Plateau extends Common_Exterior_Basic
{
    public Plateau()
	{
		super();

		context = "on a plateau.";
		description = Random.text_of(new String[] {"There is a table-like rock on top of this table-like terrain. A few other boulders, apparently stools, have been placed next to it as if someone sits here and dines on occasion.", "The terrain is surprisingly flat, almost as if a god came along previously and cleaved it.", "An arrow is here, buried by its arrowhead in the ground."});
		large_character_placements = new String[] {"on the edge of the sloping side", "on a table-like rock at the center of the terrain"};
		noncharacter_placements = new String[] {"on the flat ground", "on a patch of sparse grass", "on the stone ground", "next to an arrow stuck in the ground"};
		moba = Array.combine(moba_harpies_with_gremlin, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle", "Hill_Giant", "Hill_Giant", "Hill_Giant"}));
	}
}