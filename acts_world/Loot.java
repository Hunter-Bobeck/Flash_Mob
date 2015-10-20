package acts_world;

import utilities.*;
import scenarios.*;
import players.*;
import encounters.*;
import mobs.*;
import acts.*;

public class Loot
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"acquire", "steal", "booty", "spoil", "graft", "pickings", "pillage", "plunder", "prize", "seizure", "take", "search"},
	
	triggers_subsafe_colloquial = {"loot"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"haul", "lift", "lo", "loo"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Loot(Scenario scenario, Player player)
	{
		// encounter and potential mob setup //
		Encounter encounter = scenario.encounter;
		Mob mob = encounter.mob;
		// a mob was encountered in this scenario (so it would have to be dead and thus lootable, since Loot only happens in World) //
		if (encounter.mob_encountered)
		{
			// mob not looted yet – loot mob //
			if (!encounter.mob_lootchecked)
			{
				Type.delay("You check the "+mob.species+" for loot.");
				for (int i = 0; i < Random.integer_until(2); i++)
				{
					Type.delay(".", 1250);
				}		System.out.println();
				System.out.println();
				double loot_randomization = Random.whole();
				if (loot_randomization <= .7  ||  (loot_randomization <= .78) && !player.duelist)
					Type.delay_line(Random.text_of(new String[] {"You find nothing on the "+mob.species, "The "+mob.species+" has nothing of value"+Random.text_of(new String[] {"", " on "+mob.article_indirect}), "You find nothing of value", "You find nothing", "You "+Random.text_of(new String[] {"acquire", "find"})+" no spoils"})+".");
				else if (loot_randomization <= .78 || (mob.species.equals("drow duelist")  &&  Random.whole() <= .79  &&  player.duelist))
				{
					Type.delay_line(Random.text_of(new String[] {"You have found an improved sword to replace one of your own!", "You have found a sword of greater quality. You swap it for one of your own."}));
					player.weapon_upgrades += Random.integer_from(1, Random.integer_from(2, 3));
				}
				else if (loot_randomization <= .89)
				{
					String food_item = "food";
					/*String food_item = "meat";
					if (mob.species.contains("drow") && Random.whole() <= .75)
					{
						food_item = "mushrooms";
						Type.delay(": ");
					}
					else		Type.delay(": ");*/
					Type.delay(Random.text_of("🍞", "🍖", "🍏")+": ");
					Type.delay_line(Random.text_of(new String[] {"You find some "+food_item+Random.text_of(new String[] {"", "the "+mob.species+" was carrying"}), "The "+mob.species+" was carrying some "+food_item, "The "+mob.species+" has some "+food_item+" on "+mob.article_indirect})+".");
					Eat.extra(player, new int[] {1, 2, 3, 4, 5});
				}
				else
				{
					Type.delay_line("💧: "+Random.text_of(new String[] {"You find some water"+Random.text_of(new String[] {"", "on the "+mob.species}), "The "+mob.species+" has some water"})+".");
					Drink.extra(player, new int[] {1, 2, 3, 4, 5}, false);
				}
				encounter.mob_lootchecked = true;
			}
			// mob checked for loot already – state that the mob has already been checked for loot //
			else
			{
				String for_loot_or_unstated = Random.text_of(new String[] {"", " for loot"});
				Type.delay_line("You've already "+Random.text_of(new String[] {"checked the "+mob.species+for_loot_or_unstated, "picked the "+mob.species+" over", "searched the "+mob.species+for_loot_or_unstated})+".", 45);
			}
		}
		// no mob was encountered in this environment //
		else
		{
			Type.delay_line(Random.text_of(new String[] {"There is no mob to loot here.", "You don't see any mob to loot."}));
		}
	}
}