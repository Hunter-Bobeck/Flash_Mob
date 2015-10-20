package mobs;

import utilities.*;
import players.*;
import scenarios.*;
import environments.*;
import acts_combat.*;

public class Mob
{
	// utilities //
	public static Mob[] copy_array(Mob[] mob_array)
	{
		Mob[] copy = new Mob[mob_array.length];
		for (int i = 0; i < mob_array.length; i++)
		{
			copy[i] = mob_array[i];
		}
		return copy;
	}
	public static Mob[] combine_arrays(Mob[] mob_array_1, Mob[] mob_array_2)
	{
		int mob_array_1_length, mob_array_2_length;
		if (mob_array_1 != null)		mob_array_1_length = mob_array_1.length;
		else	mob_array_1_length = 0;
		if (mob_array_2 != null)		mob_array_2_length = mob_array_2.length;
		else	mob_array_2_length = 0;
		Mob[] combined_mob_arrays = new Mob[mob_array_1_length + mob_array_2_length];
		if (mob_array_1 != null)
		{
			for (int i = 0; i < mob_array_1.length; i++)
			{
				combined_mob_arrays[i] = mob_array_1[i];
			}
		}
		if (mob_array_2 != null)
		{
			for (int i = 0; i < mob_array_2.length; i++)
			{
				combined_mob_arrays[mob_array_1_length + i] = mob_array_2[i];
			}
		}
		return combined_mob_arrays;
	}
	
	
	
	
//////// #values ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// characteristic toggles //
	public boolean nonliving, unconscious, large, haste;
	// health range //
	public int health_min, health_max;
	// specific healthy and current health values //
	public int healthy, health;
	// healthbar (healthy-relative) //
	public void display_healthbar()
	{
		display_healthbar_unnewlined();
		System.out.println();
	}
	// healthbar (healthy-relative) - unnewlined //
	public void display_healthbar_unnewlined()
	{
		for (int bar_value = 2; bar_value <= healthy; bar_value += 2)
		{
			if (health  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
	}
	// combat weakening //
	public void combat_weaken(int damage)
	{
		health -= damage;
		if (damage > 0  &&  health > 0)
		{
			Wait.milliseconds(400);
			System.out.println();
			Type.delay(species+" ❤: ");
			display_healthbar_unnewlined();
			Type.delay_line(" ("+health+")");
		}
	}
	// specific fortitude, agility, and strength values //
	public int fortitude, agility, strength;
	// specific block value //
	public int block;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//////// #descriptions //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// specific title texts: species, species code, article then species phrase, article (defaulted to neutral), indirect article (defaulted to neutral), possessive article (defaulted to neutral), environment placement, and environment character placement //
	public String species, species_code, article_species, article = "it", article_indirect = "it", article_possessive = "its", environment_placement, environment_character_placement;
	// descriptions setup (except for the environment character placement text, since it is setup in #-confrontation – only if the mob will be animated) //
	private void setup_descriptions(Environment environment)
	{
		// initialize the species text to the code name for the class //
		species = this.getClass().getSimpleName();
		// set the code address for the species to be that with 'mob.' prefixed //
		species_code = "mobs."+species;
		// lowercase the species text //
		species = Text.lower(species);
		// replace underscores in the species text with spaces //
		species = Text.spaceize(species);
		// setup the article then species phrase based on beginning vowelage of the species text //
		article_species = Text.article_then(species);
		// article and possessive article gender-applicable setup //
		if (Array.containment(Environment.moba_folk, species_code) || Array.containment(Environment.moba_drow, species_code) || species_code.equals("mobs.Dryad") || species_code.equals("mobs.Harpy"))
		{
			if (Random.whole() <= .5)
			{
				article = "he";
				article_indirect = "him";
				article_possessive = "his";
			}
			else
			{
				article = "she";
				article_indirect = "her";
				article_possessive = "her";
			}
			double rare_gender_possibility_randomization = Random.whole();
			if
			(
				(
					Array.containment(Environment.moba_bandits, species_code) ||
					Array.containment(Environment.moba_mercenaries, species_code) ||
					Array.containment(Environment.moba_fighters, species_code) ||
					species_code.equals("mobs.Pirate") ||
					species_code.equals("mobs.Drow_Duelist") ||
					species_code.equals("mobs.Drow_Mage")
				)  &&
				rare_gender_possibility_randomization <= .9
			)
			{
				article = "he";
				article_possessive = "his";
			}
			else if (
						(
							species_code.equals("mobs.Drow_Priestess") ||
							species_code.equals("mobs.Dryad") ||
							species_code.equals("mobs.Harpy")
						) ||
						rare_gender_possibility_randomization > .9
					)
			{
				article = "she";
				article_possessive = "her";
			}
		}
		// placement setup //
		environment_placement = Random.text_of(environment.placements);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #encountering /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// #spawning //
	public void spawn(Player player, Scenario scenario, boolean potentially_out_of_place)
	{
		// descriptions setup //
		setup_descriptions(scenario.environment);
		// confrontation //
		confront(player, scenario, potentially_out_of_place);
	}
	// situational initiative toggles //
	public boolean mob_ambush, player_surprise;
	// #confrontation //
	public void confront(Player player, Scenario scenario, boolean potentially_out_of_place)
	{
		// corpse\animation discovery //
		System.out.println();
		if (Random.whole() <= .08  ||  player.Guenhwyvar)		// mob corpse found //
		{
			// statement //
			String[] corpse_statements = new String[] {"The remains of "+article_species+" lie "+environment_placement+"."};
			if (!nonliving)
			{
				corpse_statements = Array.combine(corpse_statements, new String[]
				{
					"You have found a dead "+species+"! "+Text.capitalize(article_possessive)+" body is "+environment_placement+".",
					Text.capitalize(article_species)+" is dead here, resting "+environment_placement+".",
					"There is a dead "+species+" lying "+environment_placement+"...",
					Text.capitalize(article_species)+" is here, lying "+environment_placement+". "+Text.capitalize(article)+" is already dead!",
					"You have found "+article_species+" that seems to have recently died! "+Text.capitalize(article)+" is motionless "+environment_placement+".",
					"You have found "+article_species+" that seems to have been dead for a while; "+article+" is unmoving "+environment_placement+".",
					Text.capitalize(article_species)+" is lying dead "+environment_placement+"!"
				});
			}
			Type.delay_wait_line(Random.text_of(corpse_statements), 1230);
		}
		else		// animated mob confrontation //
		{
			System.out.println();
			// environment setup //
			Environment environment = scenario.environment;
			// character placement //
			if (large)		environment_character_placement = Random.text_of(environment.large_character_placements);
			else		environment_character_placement = Random.text_of(environment.character_placements);
			// statement //
			String[] confrontation_statements = {}, surprisable_confrontation_statements =
			{
				"You have found "+article_species+" "+environment_character_placement+"!",
				"You just noticed "+article_species+" "+environment_character_placement+"!",
				"You notice "+article_species+" "+environment_character_placement+"!",
				"There is "+article_species+" "+environment_character_placement+"!"
			};
			String confrontation_statement = "You have encountered "+article_species;		// just a backup case (present only since this has to be initialized to something) //
			if (player.Drizzt)		confrontation_statements = surprisable_confrontation_statements;
			else
			{
				confrontation_statements = Array.combine
				(
					surprisable_confrontation_statements,
					new String[]
					{
						"You have encountered "+article_species+" here!",
						"You have encountered "+article_species+" "+environment_character_placement+"!",
						"You are facing "+article_species+"!",
						"You are going up against "+article_species+"!",
						Text.capitalize(article_species)+" is going to challenge you!",
						"You have been surprised by "+article_species+" "+environment_character_placement+"!",
						"You have been confronted by "+article_species+"!"
					}
				);
			}
			if (!unconscious)		// potential confrontation statements that unconscious mobs shouldn't be described by //		// these are also surprisable //
			{
				confrontation_statements = Array.combine(confrontation_statements, new String[]
				{
					Text.capitalize(article_species)+" is ahead "+environment_character_placement+", not yet noticing you!",
					Text.capitalize(article_species)+" is ahead, not yet noticing you!",
				});
				if (!species_code.equals("mobs.Thug") && !Array.containment(Environment.moba_fighters, species_code))
					confrontation_statements = Array.combine(confrontation_statements, new String[] {"You have noticed "+article_species+" "+environment_character_placement+", hoping to be unseen!"});
				if (!nonliving && !Array.containment(Environment.moba_roamers, species_code))		// potential confrontation statements that nonliving mobs shouldn't be described by //
				{
					confrontation_statements = Array.combine(confrontation_statements, new String[]
					{
						Text.capitalize(article_species)+" is living here!",
						Text.capitalize(article_species)+" makes "+article_possessive+" home here...",
					});
				}
			}
			confrontation_statement = Random.text_of(confrontation_statements);
			// ambush or surprise setup, based on the confrontation statement //
			if (confrontation_statement.contains("surprised") || confrontation_statement.contains("confronted") || environment.context.contains("quicksand") || environment.context.contains("pit"))		mob_ambush = true;
			else if (confrontation_statement.contains("found") || confrontation_statement.contains("notic") || confrontation_statement.contains("There is"))		player_surprise = true;
			// potential confrontation statement addition mentioning how the mob is uncommon in the current environment //
			if (potentially_out_of_place)
			{
				if (!Array.containment(environment.moba, species_code) &&
					!Array.containment(Environment.moba_roamers, species_code) &&
					!Array.containment(Environment.moba_elementals, species_code) &&
					!Array.containment(Environment.moba_giants, species_code) &&
					!Array.containment(Environment.moba_golems, species_code) &&
					!Array.containment(Environment.moba_aberrations, species_code) &&
					!species.equals("stirge"))
				{
					confrontation_statement += " "+Random.text_of(new String[] {"You didn't expect to find "+article_species+" here.", "How odd – it's uncommon to encounter "+article_species+" in this environment.", "You wonder what the "+species+" is doing here.", "It's not often that "+article_species+" is seen in this setting."});
				}
			}
			Type.delay_line(confrontation_statement);
			System.out.println();
			
			// appearance and combat //
			appear();
			Wait.milliseconds(2500);
			System.out.println();
			
			Combat.combat_exchange(player, scenario, this);
		}
	}
	// #appearance //
	public void appear()
	{
		healthy = health;
		if (health > (health_max * 2/3))
			Type.delay_line(Random.text_of(new String[]
			{
				"The "+species+" looks tough"+Random.text_of(new String[] {"", " for "+article_possessive+" kind"})+"! "+Text.capitalize(article)+" has "+health+" health.",
				Text.capitalize(article)+" looks extra tough for "+article_species+". "+Text.capitalize(article)+" has "+health+" health."
			}));
		else if (health > (health_max * 1/2))
			Type.delay_line(Random.text_of(new String[]
			{
				"The "+species+" looks standard, but competent... "+article+" has "+health+" health.",
				"This one is sized averagely for "+article_species+". "+Text.capitalize(article_possessive)+" health is "+health+"."
			}));
		else
		{
			double weak_health_statement_randomization = Random.whole();
			Type.delay("❤: ");
			if (weak_health_statement_randomization <= .55)		Type.delay_line("The "+species+" looks weak; "+article+" has only "+health+" health.");
			else if (weak_health_statement_randomization <= .85)		Type.delay_line("This one is rather undersized for "+article_species+"... "+article_possessive+" health is only "+health+".");
			else		Type.delay_line("This one is downright scrawny for "+article_species+"! "+Text.capitalize(article)+" has just "+health+" health.");
		}
		display_healthbar();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//////// #attacking /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  //   #damage   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  // 
	// damage range //
	public int damage_min, damage_max;
	// pure damage rolling //
	private int roll_damage_pure()
	{
		return Random.integer_from(0, 30) + Random.integer_from(damage_min, damage_max);
	}
//  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
	// glancing blow statements //
	private static final String[] glancing_blow_statements_mob = Array.permute(Array.combine
	(
		Array.permute(new String[] {" but you block "}, new String[] {"most of it", "the worst of the hit"}),
		new String[]
		{
			" and you block most of the hit"
		},
		new String[]
		{
			", making only a "+Random.text_of(new String[] {"glancing", "grazing"})+" blow",
			", hardly making a dent",
			", "+Random.text_of(new String[] {"hardly", "nary"})+" causing any harm",
			", dealing only minimal damage",
			", landing only a scratch"
		},
		Array.permute(new String[] {", but the "}, new String[] {"hit ", "blow "}, new String[] {"is insignificant", "only grazes"})
	), new String[] {"."});
	// #pain statements //
	private static final String[] pain_statements = {"That kind of hurt.", "You feel pain.", "That's going to leave a bruise!", "Ow!!", "Damn, that blow really hurt.", "Your hand goes numb.", "A sharp pain spikes down your side.", "You have a painful new wound.", "The pain is intense."};
	// #attack //
	public void attack(Player player, boolean generic_attack_statement)
	{
		String[] attack_statements = {"The "+species+" attacks you", "Your opponent attacks you"};
		int damage_pure = roll_damage_pure(), damage;
		Wait.milliseconds(800);
		if (damage_pure < player.block)		// player overblocks; mob's attack glances //
		{
			// glancing damage randomization //
			damage = Random.integer_of(new int[] {0, 0, 0, 1, 1});
			// glancing blow statement //
			if (generic_attack_statement)		Type.delay_line("✊: "+Random.text_of(attack_statements)+Random.text_of(glancing_blow_statements_mob));
			// potentially applicable glancing blow damage statement //
			if (damage > 0)		Type.delay("✊: "+damage+"  g l a n c i n g  damage dealt!", 40);
			else if (!generic_attack_statement)
				Type.delay_line(Random.text_of(new String[] {"The "+species+" "+Random.text_of(new String[] {"makes only a "+Random.text_of(new String[] {"glancing", "grazing"})+" blow", "hardly makes a dent", Random.text_of(new String[] {"hardly", "nary"})+" causes any harm", "deals only minimal damage", "lands only a scratch"}), "The "+Random.text_of(new String[] {"hit", "blow"})+" "+Random.text_of(new String[] {"is insignificant", "only grazes"})})+".");
		}
		else	// player blocks partially or exactly; mob's attack hits //
		{
			// damage calculation //
			damage = damage_pure - player.block;		// mob damage is reduced by player block /
			// if mob's damage is greater than the 
			if (damage > player.health - 1  &&  Random.whole() <= .25)		// a finishing blow avoidance is randomized (unless the damage is perfect, it will be guaranteed) //
			{
				damage = 0;
				Type.delay_line(Random.text_of(new String[] {"You AVOID a "+Random.text_of(new String[] {"final", "finishing", "killing"})+" blow!"}));
			}
			else
			{
				if (damage > player.health - 1)		damage = player.health;		// damage is deoverkilled //
				// finishing blow statement //
				if (damage == player.health)		Type.delay_line(Random.text_of(new String[] {"The "+species+" takes the "+Random.text_of(new String[] {"FINAL", "FINISHING", "KILLING"})+" BLOW!"}));
				// hit statement //
				else if (damage > 0  &&  Random.whole() <= .05)		// mob's attack hits critically //
				{
					damage = damage_pure;	// critical damage hits unreduced by block //
					if (damage > (player.health - 1))		damage = player.health - 1;		// damage is redeoverkilled //
					Type.delay_line(Random.text_of(attack_statements)+Random.text_of(new String[] {" –"}));
					Type.delay("✊: "+damage+" CRITICAL damage dealt!");
				}
				else if (damage > 0)		// mob's attack hits normally //
				{
					if (generic_attack_statement)		Type.delay_line(Random.text_of(attack_statements)+Random.text_of(new String[] {"."}));
					Type.delay("✊: "+damage+" damage dealt!");
				}
				else		// mob's attack fully blocked by player //
				{
					if (Function.line(player.fortitude_min, 1, player.fortitude_max, 10, player.fortitude) > Function.line(player.agility_min, 1, player.agility_max, 10, player.agility)  &&  Random.whole() <= .75   ||   Random.whole() <= .5)
						Type.delay_line(Random.text_of(attack_statements)+Random.text_of(new String[] {" and you block the attack perfectly.", " and you nullify the hit."}));
					else
						Type.delay_line(Random.text_of(attack_statements)+Random.text_of(new String[] {" and you dodge the attack perfectly.", " and you dodge the attack."}));
				}
			}
		}
		player.health -= damage;
		player.exercise_health();
		if (damage > 0  &&  player.health > 0)
		{
			Wait.milliseconds(400);
			System.out.println();
			if (damage > 5  &&  Random.whole() <= .35)		Type.delay(pain_statements[Random.integer_from(0, pain_statements.length - 1)]+" ");
			if (player.health > 9)
			{
				Type.delay("❤: ");
				player.display_healthbar_unnewlined();
				Type.delay_line(" ("+player.health+")");
			}
			else if (player.health >= 6)
			{
				Type.delay_line("❤: "+Random.text_of(new String[] {"Your health is down to "+player.health+"!", "You have only "+player.health+" health left!"}));
				player.display_healthbar();
			}
			else if (player.health <= 5)
			{
				Type.delay_line("❤: "+Random.text_of(new String[] {"Your health is down to "+player.health+"!", "You have only "+player.health+" health left!", "You are hanging on with just "+player.health+" health!"}));
				player.display_healthbar();
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #death ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void die(Player player)
	{
		Type.delay("💀: ");
		if (nonliving && Array.containment(Environment.moba_undead_unmultiplied, species_code))
			Type.delay_line(Random.text_of(new String[] {"The "+species+" is really dead now.", "The "+species+" is no longer undead.", "The soul of the "+species+" may now rest in peace.", "The suffering of the "+species+" is now over.", "The wretch's suffering is now over."}));
		else if (nonliving)
			Type.delay_line(Random.text_of(new String[] {"The "+species+" is slain.", "The "+species+" would be dead now if it had been living.", "The "+species+" is effectively dead."}));
		else		Type.delay_line("The "+species+" is "+Random.text_of(new String[] {"", "", "now "})+Random.text_of(new String[] {"dead", "slain"})+".");
		player.gain_experience(healthy  +  block  +  (int) ((double) (damage_min + damage_max)  /  2.0));
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}