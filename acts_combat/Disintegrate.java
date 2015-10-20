package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Disintegrate
{
	public static final int experience_cost = 550;
	
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"degenerat", "disintegrat"},
	
	triggers_subsafe_colloquial_uncastpermuted = {"disintegrate", "disintegration", "disintegration beam", "disintegrating beam"},
	triggers_subsafe_colloquial = Array.permute
	(
		new String[] {"", "cast "},
		triggers_subsafe_colloquial_uncastpermuted
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial),
	
	triggers = Array.combine(triggers_subsafe, triggers_nonsubsafe);
	
	
	
	
	
	
	
	
	public static boolean memorized = true;
	
	
	
	
	public static final String[] from_player_glancing_blow_statements = Array.permute
	(
		Array.combine
		(
			Array.permute
			(
				new String[] {"You "},
				new String[] {"fail to align with your target", "cast the spell too quickly"}
			),
			Array.permute
			(
				new String[] {"Your "},
				triggers_subsafe_colloquial_uncastpermuted,
				new String[] {" "},
				new String[] {"misses", "misses its target", "comes out at a wrong angle", "is too ethereal", "fails to cast"}
			)
		),
		new String[] {"."}
	);
	public Disintegrate(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "disintegrate") && memorized)
		{
			int damage_pure = player.roll_damage_pure_magical() + Random.integer_from(11, 21), damage;
			Wait.milliseconds(800);
			if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_of(new int[] {0, 0, 2, 3, 6});
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
								triggers_subsafe_colloquial_uncastpermuted
							)
						)
					),
					Array.permute
					(
						new String[] {"Your "},
						triggers_subsafe_colloquial_uncastpermuted,
						new String[] {" "},
						Array.combine
						(
							Array.permute
							(
								new String[] {"only slightly "},
								new String[] {"degenerates", "disintegrates"},
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
							new String[] {"reflects off of the "+mob.species}
						)
					)
				), new String[] {"."});
				// glancing blow hit statement (no damage statement) //
				if (damage == 0)		Type.delay_line("🎇: "+Random.text_of(Array.combine(from_player_glancing_blow_statements, on_mob_glancing_blow_statements)));
				// glancing blow damage statement (no hit statement) //
				else		Type.delay("🎇: "+damage+" damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				if (damage > 0  &&  Random.whole() <= .23)		// player's attack hits critically //
				{
					damage = damage_pure;		// critical damage hits unreduced by block //
					if (damage > mob.health)		damage = mob.health;		// damage is redeoverkilled //
					Type.delay("🎇: "+damage+" CRITICAL damage dealt!");
				}
				else if (damage > 0)		// player's attack hits normally //
					Type.delay("🎇: "+damage+" damage dealt!");
				else		// player's attack fully blocked by mob //
				{
					String attack_descriptor = Random.text_of(triggers_subsafe_colloquial_uncastpermuted);
					Type.delay("🎇: ");
					if (mob.fortitude > mob.agility  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
						Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"somehow manages to reflect your "+attack_descriptor+" to the side", "somehow nullifies the degenerative quality of your beam"})+Random.text_of(new String[] {".", ".", "!", "!", "!"}));
					else
						Type.delay_line("The "+mob.species+" dodges out of the way of your disintegration beam"+Random.text_of(new String[] {".", ".", ".", "!", "!"}));
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
				Type.delay_line(Random.text_of(new String[] {"You don't know the disintegration spell", "You haven't learned to cast a disintegration beam yet"})+". ", 25);
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}