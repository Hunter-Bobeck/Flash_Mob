package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Whack
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"swat", "spank"},
	
	triggers_subsafe_colloquial = {"whack", "smack", "slap"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {},
	
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
				triggers_colloquial,
				new String[] {" "},
				new String[] {"misses", "misses", "hits only air", "had too little weight", "comes up short", "was too slow"}
			)
		),
		new String[] {"."}
	);
	public Whack(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		// removing cooldowns //
		Elbow_Strike.on_cooldown = false;
		Roundhouse_Kick.on_cooldown = false;
		Pressure_Poke.on_cooldown = false;
		Flying_Kick.on_cooldown = false;
		Upward_Palm.on_cooldown = false;
		// damage rolling //
		int damage_pure = player.roll_damage_pure_unarmed() + Random.integer_from(1, 3), damage;
		Wait.milliseconds(800);
		if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
		{
			// glancing damage randomization //
			damage = Random.integer_of(new int[] {1, 1, 2, 2, 2});
			// glancing blow on mob statements setup //
			String[] on_mob_glancing_blow_statements = Array.permute(Array.combine
			(
				Array.permute
				(
					new String[] {"The "+mob.species+" blocks"},
					Array.combine
					(
						new String[] {""},
						Array.permute
						(
							new String[] {" "},
							new String[] {"your", "most of your", "the worst of your"},
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
							new String[] {"glancing", "gentle", "light"},
							new String[] {" "},
							triggers_colloquial
						),
						Array.permute
						(
							new String[] {"hardly "},
							new String[] {"whack with enough force", "bring enough weight to bear"}
						)
					)
				)
			), new String[] {"."});
			// glancing blow hit statement (no damage statement) //
			if (damage == 0)		Type.delay_line("✋: "+Random.text_of(Array.combine(from_player_glancing_blow_statements, on_mob_glancing_blow_statements)));
			// glancing blow damage statement (no hit statement) //
			else		Type.delay("✋: "+damage+"  g l a n c i n g  damage dealt!");
		}
		else	// mob blocks partially or exactly; player's attack hits //
		{
			// damage calculation //
			damage = damage_pure - mob.block;		// player damage is reduced by mob block //
			if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
			// hit statement //
			if (damage > 0  &&  Random.whole() <= .03)		// player's attack hits critically //
			{
				damage = damage_pure;		// critical damage hits unreduced by block //
				if (damage > mob.health)		damage = mob.health;		// damage is redeoverkilled //
				Type.delay("✋: "+damage+" CRITICAL damage dealt!");
			}
			else if (damage > 0)		// player's attack hits normally //
				Type.delay("✋: "+damage+" damage dealt!");
			else		// player's attack fully blocked by mob //
			{
				String attack_descriptor = Random.text_of(triggers_colloquial);
				Type.delay("✋: ");
				if (mob.fortitude > mob.agility  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
					Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"blocks your "+attack_descriptor+" perfectly", "nullifies the "+attack_descriptor})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
				else
					Type.delay_line("The "+mob.species+" dodges the "+attack_descriptor+Random.text_of(new String[] {"", " perfectly"})+Random.text_of(new String[] {".", ".", ".", ".", "!"}));
			}
		}
		mob.combat_weaken(damage);
		player.exercise_monk();
	}
}