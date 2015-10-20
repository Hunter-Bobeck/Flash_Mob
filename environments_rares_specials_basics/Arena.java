package environments_rares_specials_basics;

import utilities.*;
import players.*;

public class Arena extends Rare_Special_Basic
{
    public Arena()
	{
		super();

		context = "in the arena!";
		description = Random.text_of(new String[] {"You stand on worn and bloody sands, surrounded by traps, death machinery, monsters, and gladiators. Bodies lie here and there. Other combatants fight to the death. Crowded stands encircling the colosseum seat uncountable people, cheering on the madness from relative safety."});
		large_character_placements = new String[] {"in a pool of blood", "on the dusty and stamped ground", "adjacent to a spiky protrusion from an arena block", "on a tile away from most other contended zones", "against the wall of the stands", "on the metal grate trapdoor", "on a circular metal grate"};
		noncharacter_placements = new String[] {"among the debris of a tile ground up by action", "on the scarred arena surface", "on the floor of the main dueling dais"};
		moba = Array.combine(moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_fighters, moba_gremlin, mobs_permute(new String[] {"Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror", "Gorgon", "Helmed_Horror"}));
	}
	
	
	private String article_species_for_random_combatant_from_moba()
	{
		String combatant = Text.lower(Random.text_of(moba));
		combatant = combatant.substring(combatant.indexOf(".") + 1);
		for (int i = 0; i < combatant.length(); i++)
		{
			if (combatant.charAt(i) == '_')		combatant = combatant.substring(0, i)+" "+combatant.substring(i + 1);
		}
		if (Text.vowel(combatant))		return "an "+combatant;
		else		return "a "+combatant;
	}
	
	public void spectacle()
	{
		String
		first_combatant = article_species_for_random_combatant_from_moba(),
		second_combatant = article_species_for_random_combatant_from_moba();
	 	if (first_combatant.equals(second_combatant))		second_combatant = "another "+second_combatant.substring(second_combatant.indexOf(" ") + 1);
		Type.delay_line(Random.text_of(new String[] {"Off to your side", "Behind you", "In the distance"})+", "+first_combatant+" "+Random.text_of(new String[] {"fights", "skirmishes with", "is engaged in combat with", "is in a melee with", "is struggling against", "is getting slaughtered by"})+" "+second_combatant+".");
	}
	
	public void event(Player player)
	{
		spectacle();
		if (player.health > 8  &&  Random.whole() <= .767)
		{
			System.out.println();
			Type.delay_line(Random.text_of(new String[] {"You bump into a spike!", "You are standing in a pool of acid!", "Flames shoot out of a wall, singeing you!", "A poison dart shoots out of a wall, burying into you!", "You get zapped as you step onto a metal grate!"}));
			player.trap_weaken(Random.integer_from(5, 8));
		}
	}
}