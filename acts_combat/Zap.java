package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Zap
{
	public static final int experience_cost = 220;
	
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"electric"},
	
	triggers_subsafe_colloquial_descriptors = {"zap", "spark", "electrocution", "shock"},
	triggers_subsafe_colloquial = Array.permute
	(
		new String[] {"", "cast "},
		Array.combine(triggers_subsafe_colloquial_descriptors, new String[] {"electrocute", "electrify"})
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"z"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
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
				triggers_subsafe_colloquial_descriptors,
				new String[] {" "},
				new String[] {"misses", "misses", "misses its target", "misses", "misses", "misses its target", "leaps to the ground instead", "fizzles out upon casting", "fails to cast"}
			)
		),
		new String[] {"."}
	);
	public Zap(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "zap"))
		{
			int damage_pure = player.roll_damage_pure_magical(), damage;
			Wait.milliseconds(800);
			if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_of(new int[] {0, 1, 1, 2, 2});
				// glancing blow on mob statements setup //
				String[] on_mob_glancing_blow_statements = Array.permute(Array.combine
				(
					Array.permute
					(
						new String[] {"The "+mob.species+" "},
						Array.combine
						(
							new String[] {"is unharmed"},
							Array.permute
							(
								new String[] {"is unharmed by", "resists"},
								new String[] {" "},
								new String[] {"your"},
								new String[] {" "},
								triggers_subsafe_colloquial_descriptors
							)
						)
					),
					Array.permute
					(
						new String[] {"Your "},
						triggers_subsafe_colloquial_descriptors,
						new String[] {" "},
						Array.combine
						(
							Array.permute
							(
								new String[] {"only "},
								new String[] {"slightly charges"},
								new String[] {" the "+mob.species}
							),
							Array.permute
							(
								new String[] {"hardly "},
								Array.combine
								(
									new String[] {"causes any harm"},
									Array.permute
									(
										new String[] {"causes any harm to", "affects"},
										new String[] {"the "+mob.species}
									)
								)
							),
							new String[] {"is absorbed by the "+mob.species}
						)
					)
				), new String[] {"."});
				// glancing blow hit statement (no damage statement) //
				if (damage == 0)		Type.delay_line("⚡: "+Random.text_of(Array.combine(from_player_glancing_blow_statements, on_mob_glancing_blow_statements)));
				// glancing blow damage statement (no hit statement) //
				else		Type.delay("⚡: "+damage+" damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				if (damage > 0  &&  Random.whole() <= .3)		// player's attack hits critically //
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
						Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"blocks your "+attack_descriptor+" perfectly", "nullifies the "+attack_descriptor})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
					else
						Type.delay_line("The "+mob.species+" dodges the "+attack_descriptor+Random.text_of(new String[] {"", " perfectly"})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
				}
			}
			mob.combat_weaken(damage);
			player.exercise_mage();
		}
		else
		{
			if (player.mage)
			{
				Type.delay_line(Random.text_of(new String[] {"You don't know the zap spell", "You haven't learned to cast zap yet"})+". ", 25);
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}