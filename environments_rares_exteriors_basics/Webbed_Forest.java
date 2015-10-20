package environments_rares_exteriors_basics;

import utilities.*;

public class Webbed_Forest extends Rare_Exterior_Basic
{
    public Webbed_Forest()
	{
		super();

		context = Random.text_of(new String[] {"in a webbed forest.", "in a forest sick with spiderwebs."});
		description = Random.text_of(new String[] {"This forest is sick with its infestation of spiders. Among the unnatural darkness, you make out shadowy forms clinging to the trees, be they strands of hanging webs or crawling creepers. Twisting roots from the trees trip up your steps"})+".";
		large_character_placements = new String[] {"beside a rotten stump", "next to a long-fallen tree", "with a pile of sticks", "in some web-covered bushes", "next to a dried stream", "by a small boulder", "in a dead grass clearing", "next to plants draped with spiderwebs", "under the boughs of a tree drooping with cobwebs", "under a spiderweb on a branch", "under the dense web and leaf ceiling"};
		noncharacter_placements = new String[] {"under a pile of leaves serving as a spider nest", "inside of a web-filled log", "on the leaf-covered ground", "in the dirt where spiders crawl", "on the dead grass", "weaved into the web covering the ground of the forest", "next to a loose stick with a spider on it"};
		moba = Array.combine(moba_forest, mobs_permute(new String[] {"Ettercap", "Ettercap", "Ettercap", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Otyugh"}));
		if (Random.whole() <= .65)		moba = Array.combine(moba, moba_cultists);
	}
}