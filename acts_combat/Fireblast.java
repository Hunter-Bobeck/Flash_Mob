package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;

public class Fireblast
{
	public static final int experience_cost = 175;
	
	
	
	
	
	
	
	
	
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {},
	
	triggers_subsafe_colloquial_uncastpermuted = {"blast", "fireblast", "fireball", "flameball", "ball of fire", "blast of fire", "ball of flame", "blast of flame"},
	triggers_subsafe_colloquial = Array.permute
	(
		new String[] {"", "cast "},
		triggers_subsafe_colloquial_uncastpermuted
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"fire"},
	
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
				new String[] {"miss", "miss", "blast only air"}
			),
			Array.permute
			(
				new String[] {"Your "},
				triggers_subsafe_colloquial_uncastpermuted, 
				new String[] {" "},
				new String[] {"misses", "singes only the air", "misses", "singes only the air", "misses", "singes only the air", "burns out too soon", "fails to cast"}
			)
		),
		new String[] {"."}
	);
	public Fireblast(Scenario scenario, Player player)
	{
		Mob mob = scenario.encounter.mob;
		if (Array.containment(player.abilities, "fireblast"))
		{
			int damage_pure = player.roll_damage_pure_magical(), damage;
			if (mob.species.equals("fire elemental") || mob.species.equals("magmin") || mob.species.equals("fire giant") || mob.species.equals("magma mephit"))		damage_pure = mob.block;
			Wait.milliseconds(800);
			if (damage_pure < mob.block)		// mob overblocks; player's attack glances //
			{
				// glancing damage randomization //
				damage = Random.integer_of(new int[] {0, 1, 1, 1, 2});
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
								new String[] {"only "},
								new String[] {"warms", "warms up", "slightly toasts"},
								new String[] {" the "+mob.species}
							),
							Array.permute
							(
								new String[] {"hardly "},
								new String[] {"causes any harm"},
								new String[] {"", " to the "+mob.species}
							),
							new String[] {"just licks the "+mob.species}
						)
					)
				), new String[] {"."});
				// glancing blow hit statement (no damage statement) //
				if (damage == 0)		Type.delay_line("🔥: "+Random.text_of(Array.combine(from_player_glancing_blow_statements, on_mob_glancing_blow_statements)));
				// glancing blow damage statement (no hit statement) //
				else		Type.delay("🔥: "+damage+" damage dealt!");
			}
			else	// mob blocks partially or exactly; player's attack hits //
			{
				// damage calculation //
				damage = damage_pure - mob.block;		// player damage is reduced by mob block //
				if (damage > mob.health)		damage = mob.health;		// damage is deoverkilled //
				// hit statement //
				if ((mob.species.equals("fire elemental") || mob.species.equals("magmin") || mob.species.equals("fire giant") || mob.species.equals("magma mephit")) && (damage > 0  &&  Random.whole() <= .1))		// player's attack hits critically //
				{
					damage = damage_pure;		// critical damage hits unreduced by block //
					if (damage > mob.health)		damage = mob.health;		// damage is redeoverkilled //
					Type.delay("🔥: "+damage+" CRITICAL damage dealt!");
				}
				else if (damage > 0)		// player's attack hits normally //
					Type.delay("🔥: "+damage+" damage dealt!");
				else		// player's attack fully blocked by mob //
				{
					String attack_descriptor = Random.text_of(triggers_subsafe_colloquial_uncastpermuted);
					Type.delay("🔥: ");
					if (mob.species.equals("fire elemental") || mob.species.equals("magmin") || mob.species.equals("fire giant") || mob.species.equals("magma mephit"))
						Type.delay(Random.text_of(new String[] {"The "+mob.species+" is resistant to fire; it is unharmed by your "+attack_descriptor, "Your "+attack_descriptor+" is absorbed by the "+mob.species+", a creature of fire", "Your "+attack_descriptor+" has no effect on the "+mob.species, "The "+mob.species+" absorbs your "+attack_descriptor}));
					else if (mob.fortitude > mob.agility  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
						Type.delay("The "+mob.species+" "+Random.text_of(new String[] {"blocks your "+attack_descriptor+" perfectly", "nullifies the "+attack_descriptor}));
					else
						Type.delay("The "+mob.species+" dodges the "+attack_descriptor+Random.text_of(new String[] {"", " perfectly"}));
					Type.delay_line(Random.text_of(new String[] {".", ".", ".", ".", "!"}));
				}
			}
			mob.combat_weaken(damage);
			player.exercise_mage();
		}
		else
		{
			if (player.mage)
			{
				Type.delay_line(Random.text_of(new String[] {"You don't know the fireblast spell", "You haven't learned to cast fireball yet"})+". ", 25);
		
				Combat combat = new Combat(player, scenario);
			}
			else
			{
				Attack attack = new Attack(player, scenario);
			}
		}
	}
}