package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Pressure_Poke
{
	public static final int experience_cost = 390;
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"pressure", "point", "finger"},
	
	triggers_subsafe_colloquial = {"pressure poke", "poke", "poking finger", "deadly poke", "finger jab", "jab"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"pp"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
	public static boolean on_cooldown = false;
	
	
	
	
	public static final String[] from_player_glancing_blow_statements = Array.permute
	(
		Array.combine
		(
			Array.permute
			(
				new String[] {"You "},
				Array.combine
				(
					new String[] {"miss", "miss"},
					Array.permute
					(
						triggers_colloquial,
						new String[] {" only air"}
					)
				)
			),
			Array.permute
			(
				new String[] {"Your "},
				triggers_colloquial,
				new String[] {" "},
				new String[] {"misses", "misses", "jabs only air", "misses the pressure point", "pressures the wrong spot", "was directed poorly", "had no force", "was poorly executed", "touches too gently"}
			)
		),
		new String[] {"."}
	);
	public Pressure_Poke(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "pressure poke") && !on_cooldown)
		{
			// removing cooldowns //
			Elbow_Strike.on_cooldown = false;
			Roundhouse_Kick.on_cooldown = false;
			Flying_Kick.on_cooldown = false;
			Upward_Palm.on_cooldown = false;
			// damage rolling //
			int damage_pure = player.roll_damage_pure_unarmed() + Random.integer_from(1, 10), damage;
			Wait.milliseconds(800);
			if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_of(new int[] {0, 0, 0, 1, 3});
				// glancing blow on mob statements setup //
				String[] on_mob_glancing_blow_statements = Array.permute(Array.combine
				(
					Array.permute
					(
						new String[] {"The "+mob.species},
						Array.combine
						(
							new String[] {"", "pushes your poke to the side"},
							Array.permute
							(
								new String[] {"avoids", "blocks", "misdirects"},
								new String[] {"your"},
								new String[] {" "},
								triggers_colloquial
							)
						)
					),
					Array.permute
					(
						new String[] {"You "},
						Array.combine
						(
							Array.permute
							(
								new String[] {"land only a "},
								new String[] {"gentle", "light"},
								new String[] {" "},
								triggers_colloquial
							),
							Array.permute
							(
								new String[] {"hardly "},
								new String[] {"poke", "jab your finger"},
								new String[] {" with enough force"}
							)
						)
					)
				), new String[] {"."});
				// glancing blow hit statement (no damage statement) //
				if (damage == 0)		Type.delay_line("☚: "+Random.text_of(Array.combine(from_player_glancing_blow_statements, on_mob_glancing_blow_statements)));
				// glancing blow damage statement (no hit statement) //
				else		Type.delay("☚: "+damage+"  g l a n c i n g  damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				if (damage > 0  &&  Random.whole() <= .7)		// player's attack hits critically //
				{
					damage = damage_pure;		// critical damage hits unreduced by block //
					if (damage > mob.health)		damage = mob.health;		// damage is redeoverkilled //
					Type.delay("☚: "+damage+" CRITICAL damage dealt!");
				}
				else if (damage > 0)		// player's attack hits normally //
					Type.delay("☚: "+damage+" damage dealt!");
				else		// player's attack fully blocked by mob //
				{
					String attack_descriptor = Random.text_of(triggers_colloquial);
					Type.delay("☚: ");
					if (mob.fortitude > mob.agility  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
						Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"blocks your "+attack_descriptor+" perfectly", "nullifies the "+attack_descriptor})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
					else
						Type.delay_line("The "+mob.species+" dodges the "+attack_descriptor+Random.text_of(new String[] {"", " perfectly"})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
				}
			}
			mob.combat_weaken(damage);
			player.exercise_monk();
			on_cooldown = true;
		}
		else
		{
			if (on_cooldown)
			{
				Type.delay_line(Random.text_of(new String[] {"You aren't ready to execute another "+Random.text_of(triggers_colloquial)+" yet.", Text.capitalize(Random.text_of(triggers_colloquial))+" is on cooldown."}));

				Combat combat = new Combat(player, scenario);
			}
			else if (player.monk)
			{
				Type.delay_line(Random.text_of(new String[] {"You haven't learned to execute a pressure poke yet", "You need to train yourself to do deadly pokes first"})+".");

				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}