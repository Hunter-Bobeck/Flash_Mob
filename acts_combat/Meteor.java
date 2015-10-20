package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Meteor
{
	public static final int experience_cost = 780;
	
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"asteroid", "call down"},
	
	triggers_subsafe_colloquial_descriptors = {"meteor", "volcanic rock", "meteoroid", "comet", "bolide"},
	triggers_subsafe_colloquial = Array.permute
	(
		new String[] {"", "cast "},
		Array.combine(triggers_subsafe_colloquial_descriptors, new String[] {"call down a meteor", "call down bolide"})
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
	public static boolean memorized = true;
	
	
	
	
	public Meteor(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "meteor") && memorized)
		{
			int damage_pure = player.roll_damage_pure_magical() + Random.integer_from(34, 56), damage;
			Wait.milliseconds(800);
			if (damage_pure <= mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_of(new int[] {1, 2, 2, 5, 6});
				// glancing blow damage statement //
				if (Random.binary())		Type.delay_line(Random.text_of(new String[] {"Your meteor falls off to the side.", "The meteor impacts to the side of the "+mob.species}));
				Type.delay("🔥: "+damage+" damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				Type.delay("☄: "+damage+" damage dealt!");
			}
			mob.combat_weaken(damage);
			player.exercise_mage();
			memorized = false;
		}
		else
		{
			if (!memorized)
			{
				Type.delay_line(Random.text_of(new String[] {"You won't have "+Random.text_of(triggers_colloquial)+" memorized again until the next fight.", Text.capitalize(Random.text_of(triggers_colloquial))+" has already been cast."}));
		
				Combat combat = new Combat(player, scenario);
			}
			else if (player.mage)
			{
				Type.delay_line(Random.text_of(new String[] {"You don't know the meteor spell", "You haven't learned to cast meteor yet"})+". ", 25);
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}