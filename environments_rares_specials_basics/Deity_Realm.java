package environments_rares_specials_basics;

import utilities.*;
import players.*;

public class Deity_Realm extends Rare_Special_Basic
{
    public Deity_Realm()
	{
		super();

		context = "within the realm of a "+Random.text_of(new String[] {"deity", "god"})+"...";
		description = "This realms is twisting your mind. No matter how hard you focus on your senses, you can't bring your thoughts to reconcile your surroundings.";
		large_character_placements = new String[] {"on a fanciful altar", "under a detailed dome of the deity", "along a floating bridge to an enormous structure hanging in the void", "on a side pedestal"};
		noncharacter_placements = new String[] {"on the ethereal ground"};
		String[]
		moba_cultists_aspect_component = Array.combine(moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists, moba_cultists),
		moba_cultists_aspect = Array.combine(moba_cultists_aspect_component, moba_cultists_aspect_component, moba_cultists_aspect_component);
		moba = Array.combine(moba, moba_cultists_aspect, mobs_permute(new String[] {"Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider", "Giant_Spider"}));
	}
	
	public void spectacle()
	{
		Type.delay_line("You see things, but your mind hurts, terribly. You feel utterly confused.");
	}
	
	public void event(Player player)
	{
		spectacle();
	}
}