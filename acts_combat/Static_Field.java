package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Static_Field
{
	public static final int experience_cost = 290;
	
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"aura", "charge", "electrical field"},
	
	triggers_subsafe_colloquial_descriptors = {"static field", "static"},
	triggers_subsafe_colloquial = Array.permute
	(
		new String[] {"", "cast "},
		triggers_subsafe_colloquial_descriptors
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"sf"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
	public static boolean memorized = true;
	
	
	
	
	public Static_Field(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "static field") && memorized)
		{
			// damage rolling //
			int damage_pure = player.roll_damage_pure_magical() + Random.integer_from(1, 6), damage;
			Wait.milliseconds(800);
			if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_from(1, 6);
				// glancing blow damage statement //
				Type.delay("⚡: "+damage+" damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				if (damage > 0  &&  Random.whole() <= .15)		// player's attack hits critically //
				{
					damage = damage_pure;		// critical damage hits unreduced by block //
					if (damage > mob.health)		damage = mob.health;		// damage is redeoverkilled //
					Type.delay("⚡: "+damage+" CRITICAL damage dealt!");
				}
				else if (damage > 0)		// player's attack hits normally //
					Type.delay("⚡: "+damage+" damage dealt!");
				else		// player's attack fully blocked by mob //
				{
					String attack_descriptor = Random.text_of(triggers_subsafe_colloquial_descriptors);
					Type.delay("⚡: ");
					if (mob.fortitude > mob.agility  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
						Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"resists your "+attack_descriptor, "nullifies the "+attack_descriptor})+Random.text_of(new String[] {".", ".", "!", "!", "!"}));
					else
						Type.delay_line("The "+mob.species+" avoids the "+attack_descriptor+Random.text_of(new String[] {".", ".", "!", "!", "!"}));
				}
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
				Type.delay_line(Random.text_of(new String[] {"You don't know the static field spell", "You haven't learned to cast static field yet"})+". ", 25);
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}