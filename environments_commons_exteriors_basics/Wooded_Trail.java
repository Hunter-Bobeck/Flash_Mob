package environments_commons_exteriors_basics;

import utilities.*;

public class Wooded_Trail extends Common_Exterior_Basic
{
    public Wooded_Trail()
	{
		super();

		context = "along a wooded trail.";
		description = Random.text_of(new String[] {"The trees provide some decent shade.", "Leaves are falling from the trees."});
		large_character_placements = new String[] {"on the trail", "on the path", "by a tree", "alongside the trail", "alongside the path"};
		noncharacter_placements = new String[] {"under a pile of leaves"};
		moba = Array.combine(moba_forest, moba_roamers);
		if (Random.whole() <= moba_abyss_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Jackalwere"}));
		if (Random.whole() <= .17)		moba = Array.combine(moba, mobs_permute(new String[] {"Kenku"}));
	}
}