package acts_combat;

import utilities.*;
import players.*;
import mobs.*;
import scenarios.*;
import acts.*;
import encounters.*;
import acts_world.*;
import banners.*;

public class Combat		// accessed by calling #-act //
{
	// #mob_initiative //
	public static void mob_initiative(Mob mob)
	{
		Type.delay("👣: ");
		if (mob.haste)		// mob haste statement //
		{
			Type.delay_line(Random.text_of(new String[] {"This "+mob.species+" is hasty", "The "+mob.species+" has haste"})+Random.text_of(new String[] {"!", " — "+mob.article+" is striking first!", "; "+mob.article+" has the advantage!"}));
		}
		else if (mob.mob_ambush)		// mob ambush statement //
		{
			Type.delay_line(Random.text_of(new String[] {"The "+mob.species+" has ambushed you", "The "+mob.species+" has the advantage", "The "+mob.species+" has the advantage of surprise"})+Random.text_of(new String[] {"!", " — "+mob.article+" is striking first!", "; "+mob.article+" is starting the combat!"}));
		}
		else		// mob agility statement //
		{
			Type.delay_line(Random.text_of(new String[] {"You demonstrate poor reflexes... the "+mob.species+" gets to strike first!", "This "+mob.species+" is unusually fast! "+Text.capitalize(mob.article)+" will strike first!", Text.capitalize(mob.article)+" isn't wasting any time!"}));
		}
		System.out.println();
		
	}
	// #player_initiative //
	public static void player_initiative(Player player, Scenario scenario, Mob mob, boolean controlled_initiation)
	{
		if (!controlled_initiation)
		{
			Type.delay("👣: ");
			if (player.Entreri)		Type.delay_line("Entreri always "+Random.text_of(new String[] {"has the initiative", "initiates combat", "strikes first"})+".");		// Entreri statement //
			else		// player agility statement //
				Type.delay_line(Random.text_of(new String[] {"You're quick enough to have the initiative this time.", "You have better reflexes, so you will strike first.", "This "+mob.species+" is extra slow. You get to strike first!", "You have the advantage of better reflexes!", "You get to make the first move."}));
			Wait.milliseconds(250);
		}
		System.out.println();
		
		// player attack //
		Combat combat = new Combat(player, scenario);
		if (player.playing)
		{
			Wait.milliseconds(322);
			System.out.println();
			
		}
	}
	// #combat_exchange //
	public static void combat_exchange(Player player, Scenario scenario, Mob mob)
	{
		prompts_set_default = false;
		first_attack = true;
		// initiative //
		if (mob.player_surprise || player.Drizzt || (player.agility > mob.agility  &&  Random.whole() <= .75  &&  !mob.haste  &&  !mob.mob_ambush) || player.Entreri)	// players must have > agility to get the initiative (and then still only sometimes get it), whereas mobs might not have > agility when getting the initiative, but when they do, they always get the initiative (this is because mobs stereotypically initiate scenarios); if the player is using the Entreri cheat then he has the initiative no matter what //
		{
			if (mob.player_surprise || player.Drizzt)
			{
				Ahead ahead = new Ahead(player, scenario, mob);
			}
			else		player_initiative(player, scenario, mob, false);
		}
		else		mob_initiative(mob);
		// continued fight //
		while (player.playing  &&  mob.health > 0  &&  player.health > 0)
		{
			scenario.encounter.mob.attack(player, true);
			if (mob.health > 0  &&  player.health > 0)
			{
				System.out.println();
				Combat combat = new Combat(player, scenario);
				if (player.playing)
				{
					Wait.milliseconds(322);
					System.out.println();

				}
			}
		}
		
		// removing cooldowns //
		if (player.duelist)
		{
			Twist.on_cooldown = false;
			Spin.on_cooldown = false;
			Overhead.on_cooldown = false;
			Double_Thrust.on_cooldown = false;
			Flurry.on_cooldown = false;
		}
		else if (player.monk)
		{
			Elbow_Strike.on_cooldown = false;
			Roundhouse_Kick.on_cooldown = false;
			Pressure_Poke.on_cooldown = false;
			Flying_Kick.on_cooldown = false;
			Upward_Palm.on_cooldown = false;
		}
		// rememorizing nonbasic spells //
		if (player.mage)
		{
			Static_Field.memorized = true;
			Lightning_Bolt.memorized = true;
			Disintegrate.memorized = true;
			Meteor.memorized = true;
		}
		// mob or player death //
		if (player.playing)
		{
			if (player.health > 0)
			{
				mob.die(player);
			}
			else if (player.playing)
			{
				System.out.println();

				player.die_to_mob(Text.upper(mob.species));
			}
			Wait.milliseconds(322);
		}
		// upgrading values //
		if (player.playing)
		{
			player.upgrade_health();
			player.upgrade_strength();
			player.upgrade_agility();
		}
	}
	
	
	
	
	
	
	
	
	
	// #random (generates weighted random input for #-prompt for #-act) //
	private static String random(Player player)
	{
		// randomization setup //
		double randomization = Random.whole();
		// input declaration //
		String input = null;
		// randomization determination //
		if (player.health < 10  &&  randomization <= .68)		input = Random.text_of(Flee.triggers_colloquial);
		else if (randomization <= .979)		input = Random.text_of(player.class_combat_triggers_colloquial);
		else if (randomization <= .9845)	input = Random.text_of(Halt.triggers_colloquial);
		else if (randomization <= .99)		input = Random.text_of(Hesitate.triggers_colloquial);
		else if (randomization <= .997)		input = Random.text_of(Freakout.triggers_colloquial);
		else								input = Random.text_of(Fart.triggers_colloquial);
		// return input //
		return input;
	}
	
	
	
	// toggle for defaulting prompts in the set of a combat exchange //
	public static boolean prompts_set_default;
	// first attack prompt toggle //
	public static boolean first_attack = false;
	// action unknown description toggle //
	private boolean action_known = true;
	// #prompt (gets input for #-act, calling #-random if applicable) //
	private String prompt(Player player, Mob mob)
	{
		if (first_attack)
		{
			String first_attack_question = "how do you "+Random.text_of(new String[] {"attack"+Random.text_of(new String[] {"", " the "+mob.species}), "react"+Random.text_of(new String[] {"", " to the "+mob.species})});
			Type.delay_line(Random.text_of(new String[]
			{
				"It's your move"+Random.text_of(new String[]
									{
										Random.text_of(new String[] {".", "..", "..."}),
										", "+player.name+"."+Random.text_of(new String[] {"", ".", ".."}),
										"... "+first_attack_question+Random.text_of(new String[] {"", ", "+player.name})+"?"
									}),
				Text.capitalize(first_attack_question)+"?"
			}));
			first_attack = false;
		}
		// input declaration //
		String input = null;
		// if prompts are setly or globally defaulted, then randomize the input and plan to type it //
		if (prompts_set_default || player.prompts_global_default)
		{
			Act.input_backup = random(player);
			input = Act.input_backup;
			action_known = false;
		}
		// otherwise, get and refine input, randomizing and planning further randomization as applicable //
		else
		{
			// get the input, made lowercase and without trailing spaces //
			Act.input_backup = Prompt.text().trim();		// backup the input in its unlowered form to Act so that acts can use the unlowered version of the input //
			input = Text.lower(Act.input_backup);
			// if applicable based on key '-' input, randomize and plan further randomization as applicable //
			if (Array.containment(player.control_inputs, input))
			{
				// for '-1', nothing needs to be done besides/before randomizing //
				// for '-2', set prompts to be defaulted setly //
				if (input.equals("-2"))		prompts_set_default = true;
				// for '-3', set prompts to be defaulted globally //
				if (input.equals("-3"))		player.prompts_global_default = true;
				// for '-4', set the game to just be quit //
				else if (input.equals("-4"))		player.playing = false;
				// if still playing, now randomize the input and plan to type it //
				if (player.playing)
				{
					// randomize the input with #-random //
					Act.input_backup = random(player);
					input = Act.input_backup;
					// plan to type the input //
					action_known = false;
				}
			}
			// otherwise, now safely unpunctuate the input to further refine it //
			else		input = Text.unpunctuate(input);
		}
		// if still playing, and typing the input is planned due to it having been randomized and thus it being unknown, do so //
		if (player.playing && !action_known)
		{
			System.out.print("› ");
			Wait.milliseconds(1000);
			Type.human_line(input);	
		}
		// return the input //
		return input;
	}
	
	
	// allowed trigger examples to be listed when acting Help in the combat context //
	private static final String[] allowed_trigger_examples = {"attack", "flee"};
	// #act (retrieves input from #-prompt, then acts in combat accordingly) //
	public Combat(Player player, Scenario scenario)
	{
		// setup mob //
		Mob mob = scenario.encounter.mob;
		// retrieve input from #-prompt //
		String input = prompt(player, mob);
//////// triggering (searched in the order of sensitivity then applicability then commonness) ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (player.playing)
		{
			System.out.println();
			
//  //  //   allowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			// say triggering //
			if (Array.subcontainment_case_ignored(input, Say.triggers_subsafe) || Array.containment_case_ignored(Say.triggers_nonsubsafe, input))
			{
				Say say = new Say();
			}
			// burp triggering //
			else if (Array.subcontainment_case_ignored(input, Burp.triggers_subsafe) || Array.containment_case_ignored(Burp.triggers_nonsubsafe, input))
			{
				Burp burp = new Burp();
			}
			// fart triggering //
			else if (Array.subcontainment_case_ignored(input, Fart.triggers_subsafe) || Array.containment_case_ignored(Fart.triggers_nonsubsafe, input))
			{
				Fart fart = new Fart();
			}
			// slash triggering //
			else if (Array.subcontainment_case_ignored(input, Slash.triggers_subsafe) || Array.containment_case_ignored(Slash.triggers_nonsubsafe, input))
			{
				Slash slash = new Slash(scenario, player);
			}
			// cut triggering //
			else if (Array.subcontainment_case_ignored(input, Cut.triggers_subsafe) || Array.containment_case_ignored(Cut.triggers_nonsubsafe, input))
			{
				Cut cut = new Cut(scenario, player);
			}
			// double thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Double_Thrust.triggers_subsafe) || Array.containment_case_ignored(Double_Thrust.triggers_nonsubsafe, input))
			{
				Double_Thrust double_thrust = new Double_Thrust(scenario, player);
			}
			// thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Thrust.triggers_subsafe) || Array.containment_case_ignored(Thrust.triggers_nonsubsafe, input))
			{
				Thrust thrust = new Thrust(scenario, player);
			}
			// twist triggering //
			else if (Array.subcontainment_case_ignored(input, Twist.triggers_subsafe) || Array.containment_case_ignored(Twist.triggers_nonsubsafe, input))
			{
				Twist twist = new Twist(scenario, player);
			}
			// spin triggering //
			else if (Array.subcontainment_case_ignored(input, Spin.triggers_subsafe) || Array.containment_case_ignored(Spin.triggers_nonsubsafe, input))
			{
				Spin spin = new Spin(scenario, player);
			}
			// overhead triggering //
			else if (Array.subcontainment_case_ignored(input, Overhead.triggers_subsafe) || Array.containment_case_ignored(Overhead.triggers_nonsubsafe, input))
			{
				Overhead overhead = new Overhead(scenario, player);
			}
			// flurry triggering //
			else if (Array.subcontainment_case_ignored(input, Flurry.triggers_subsafe) || Array.containment_case_ignored(Flurry.triggers_nonsubsafe, input))
			{
				Flurry flurry = new Flurry(scenario, player);
			}
			// whack triggering //
			else if (Array.subcontainment_case_ignored(input, Whack.triggers_subsafe) || Array.containment_case_ignored(Whack.triggers_nonsubsafe, input))
			{
				Whack whack = new Whack(scenario, player);
			}
			// punch triggering //
			else if (Array.subcontainment_case_ignored(input, Punch.triggers_subsafe) || Array.containment_case_ignored(Punch.triggers_nonsubsafe, input))
			{
				Punch punch = new Punch(scenario, player);
			}
			// roundhouse kick triggering //
			else if (Array.subcontainment_case_ignored(input, Roundhouse_Kick.triggers_subsafe) || Array.containment_case_ignored(Roundhouse_Kick.triggers_nonsubsafe, input))
			{
				Roundhouse_Kick roundhouse_kick = new Roundhouse_Kick(scenario, player);
			}
			// flying kick triggering //
			else if (Array.subcontainment_case_ignored(input, Flying_Kick.triggers_subsafe) || Array.containment_case_ignored(Flying_Kick.triggers_nonsubsafe, input))
			{
				Flying_Kick flying_kick = new Flying_Kick(scenario, player);
			}
			// kick triggering //
			else if (Array.subcontainment_case_ignored(input, Kick.triggers_subsafe) || Array.containment_case_ignored(Kick.triggers_nonsubsafe, input))
			{
				Kick kick = new Kick(scenario, player);
			}
			// elbow strike triggering //
			else if (Array.subcontainment_case_ignored(input, Elbow_Strike.triggers_subsafe) || Array.containment_case_ignored(Elbow_Strike.triggers_nonsubsafe, input))
			{
				Elbow_Strike elbow_strike = new Elbow_Strike(scenario, player);
			}
			// pressure poke triggering //
			else if (Array.subcontainment_case_ignored(input, Pressure_Poke.triggers_subsafe) || Array.containment_case_ignored(Pressure_Poke.triggers_nonsubsafe, input))
			{
				Pressure_Poke pressure_poke = new Pressure_Poke(scenario, player);
			}
			// upward palm triggering //
			else if (Array.subcontainment_case_ignored(input, Upward_Palm.triggers_subsafe) || Array.containment_case_ignored(Upward_Palm.triggers_nonsubsafe, input))
			{
				Upward_Palm upward_palm = new Upward_Palm(scenario, player);
			}
			// magic missile triggering //
			else if (Array.subcontainment_case_ignored(input, Magic_Missile.triggers_subsafe) || Array.containment_case_ignored(Magic_Missile.triggers_nonsubsafe, input))
			{
				Magic_Missile magic_missile = new Magic_Missile(scenario, player);
			}
			// fireblast triggering //
			else if (Array.subcontainment_case_ignored(input, Fireblast.triggers_subsafe) || Array.containment_case_ignored(Fireblast.triggers_nonsubsafe, input))
			{
				Fireblast fireblast = new Fireblast(scenario, player);
			}
			// zap triggering //
			else if (Array.subcontainment_case_ignored(input, Zap.triggers_subsafe) || Array.containment_case_ignored(Zap.triggers_nonsubsafe, input))
			{
				Zap zap = new Zap(scenario, player);
			}
			// static field triggering //
			else if (Array.subcontainment_case_ignored(input, Static_Field.triggers_subsafe) || Array.containment_case_ignored(Static_Field.triggers_nonsubsafe, input))
			{
				Static_Field static_field = new Static_Field(scenario, player);
			}
			// lightning bolt triggering //
			else if (Array.subcontainment_case_ignored(input, Lightning_Bolt.triggers_subsafe) || Array.containment_case_ignored(Lightning_Bolt.triggers_nonsubsafe, input))
			{
				Lightning_Bolt lightning_bolt = new Lightning_Bolt(scenario, player);
			}
			// disintegrate triggering //
			else if (Array.subcontainment_case_ignored(input, Disintegrate.triggers_subsafe) || Array.containment_case_ignored(Disintegrate.triggers_nonsubsafe, input))
			{
				Disintegrate disintegrate = new Disintegrate(scenario, player);
			}
			// meteor triggering //
			else if (Array.subcontainment_case_ignored(input, Meteor.triggers_subsafe) || Array.containment_case_ignored(Meteor.triggers_nonsubsafe, input))
			{
				Meteor meteor = new Meteor(scenario, player);
			}
			// attack triggering //
			else if (Array.subcontainment_case_ignored(input, Attack.triggers_subsafe) || Array.containment_case_ignored(Attack.triggers_nonsubsafe, input))
			{
				Attack attack = new Attack(player, scenario);
			}
			// abilities triggering //
			else if (Array.subcontainment_case_ignored(input, Abilities.triggers_subsafe) || Array.containment_case_ignored(Abilities.triggers_nonsubsafe, input))
			{
				player.describe_class(false);
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// experience triggering //
			else if (Array.subcontainment_case_ignored(input, Experience.triggers_subsafe) || Array.containment_case_ignored(Experience.triggers_nonsubsafe, input))
			{
				player.describe_experience();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// flee\ travel/flee triggering //
			else if (Array.subcontainment_case_ignored(input, Flee.triggers_subsafe) || Array.containment_case_ignored(Flee.triggers_nonsubsafe, input) || Array.subcontainment_case_ignored(input, Travel.triggers_subsafe) || Array.containment_case_ignored(Travel.triggers_nonsubsafe, input))
			{
				Flee flee = new Flee(scenario, player);
			}
			// freakout triggering //
			else if (Array.subcontainment_case_ignored(input, Freakout.triggers_subsafe) || Array.containment_case_ignored(Freakout.triggers_nonsubsafe, input))
			{
				Freakout freakout = new Freakout();
			}
			// halt triggering //
			else if (Array.subcontainment_case_ignored(input, Halt.triggers_subsafe) || Array.containment_case_ignored(Halt.triggers_nonsubsafe, input))
			{
				Halt halt = new Halt();
			}
			// pause triggering //
			else if (Array.subcontainment_case_ignored(input, Pause.triggers_subsafe) || Array.containment_case_ignored(Pause.triggers_nonsubsafe, input))
			{
				Pause pause = new Pause();
			}
			// hesitate triggering //
			else if (Array.subcontainment_case_ignored(input, Hesitate.triggers_subsafe) || Array.containment_case_ignored(Hesitate.triggers_nonsubsafe, input))
			{
				Hesitate hesitate = new Hesitate();
			}
			// gender triggering //
			else if (Array.subcontainment_case_ignored(input, Gender.triggers_subsafe) || Array.containment_case_ignored(Gender.triggers_nonsubsafe, input))
			{
				player.describe_gender();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// name triggering //
			else if (Array.subcontainment_case_ignored(input, Name.triggers_subsafe) || Array.containment_case_ignored(Name.triggers_nonsubsafe, input))
			{
				player.describe_name(false);
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// health triggering //
			else if (Array.subcontainment_case_ignored(input, Health.triggers_subsafe) || Array.containment_case_ignored(Health.triggers_nonsubsafe, input))
			{
				Health health = new Health(player, scenario, false, false, false);
			}
			// strength triggering //
			else if (Array.subcontainment_case_ignored(input, Strength.triggers_subsafe) || Array.containment_case_ignored(Strength.triggers_nonsubsafe, input))
			{
				player.describe_strength();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// agility triggering //
			else if (Array.subcontainment_case_ignored(input, Agility.triggers_subsafe) || Array.containment_case_ignored(Agility.triggers_nonsubsafe, input))
			{
				player.describe_agility();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// water triggering //
			else if (Array.subcontainment_case_ignored(input, Water.triggers_subsafe) || Array.containment_case_ignored(Water.triggers_nonsubsafe, input))
			{
				player.describe_water();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// thirst triggering //
			else if (Array.subcontainment_case_ignored(input, Thirst.triggers_subsafe) || Array.containment_case_ignored(Thirst.triggers_nonsubsafe, input))
			{
				Thirst thirst = new Thirst(player, scenario, false, false);
				System.out.println();
			
				Combat combat = new Combat(player, scenario);
			}
			// food triggering //
			else if (Array.subcontainment_case_ignored(input, Food.triggers_subsafe) || Array.containment_case_ignored(Food.triggers_nonsubsafe, input))
			{
				player.describe_food();
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
			// hunger triggering //
			else if (Array.subcontainment_case_ignored(input, Hunger.triggers_subsafe) || Array.containment_case_ignored(Hunger.triggers_nonsubsafe, input))
			{
				Hunger hunger = new Hunger(player, scenario, false, false, false);
			}
			// inventory triggering //
			else if (Array.subcontainment_case_ignored(input, Inventory.triggers_subsafe) || Array.containment_case_ignored(Inventory.triggers_nonsubsafe, input))
			{
				player.describe_inventory();
				Combat combat = new Combat(player, scenario);
			}
//  //  //   unallowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
			// loot triggering //
			else if (Array.subcontainment_case_ignored(input, Loot.triggers_subsafe) || Array.containment_case_ignored(Loot.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You must defeat the "+mob.species+" before looting "+mob.article_indirect+"!", "You're still in combat with the "+mob.species+"!"}), 40);
			}
			// train triggering //
			else if (Array.subcontainment_case_ignored(input, Train.triggers_subsafe) || Array.containment_case_ignored(Train.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You cannot take the time to practice when real combat is at hand!", "There is no time to train right now; you must fight with the skills you already have."}), 40);
			}
			// look triggering //
			else if (Array.subcontainment_case_ignored(input, Look.triggers_subsafe) || Array.containment_case_ignored(Look.triggers_nonsubsafe, input))
			{
				Type.delay_line("You have no time to look around during combat!", 40);
			}
			// drink triggering //
			else if (Array.subcontainment_case_ignored(input, Drink.triggers_subsafe) || Array.containment_case_ignored(Drink.triggers_nonsubsafe, input))
			{
				Drink drink = new Drink(player, scenario, false, false);
			}
			// eat triggering //
			else if (Array.subcontainment_case_ignored(input, Eat.triggers_subsafe) || Array.containment_case_ignored(Eat.triggers_nonsubsafe, input) || input.contains("eat "+mob.species) || input.contains("eat the "+mob.species) || input.contains("digest "+mob.species) || input.contains("digest the "+mob.species))
			{
				Eat eat = new Eat(player, scenario, false, false, false);
			}
			// rest triggering //
			else if (Array.subcontainment_case_ignored(input, Rest.triggers_subsafe) || Array.containment_case_ignored(Rest.triggers_nonsubsafe, input))
			{
				Type.delay_line("You cannot rest when you are in combat!", 40);
			}
			// sleep triggering //
			else if (Array.subcontainment_case_ignored(input, Sleep.triggers_subsafe) || Array.containment_case_ignored(Sleep.triggers_nonsubsafe, input))
			{
				Type.delay_line("You cannot sleep when you are in combat!", 40);
			}
			// wake triggering //
			else if (Array.subcontainment_case_ignored(input, Wake.triggers_subsafe) || Array.containment_case_ignored(Wake.triggers_nonsubsafe, input))
			{
				Wake wake = new Wake(player, scenario, false);
			}
			// exercise triggering //
			else if (Array.subcontainment_case_ignored(input, Exercise.triggers_subsafe) || Array.containment_case_ignored(Exercise.triggers_nonsubsafe, input))
			{
				Type.delay_line("Combat is the kind of exercise you should focus on right now!", 40);
			}
			// mine triggering //
			else if (Array.subcontainment_case_ignored(input, Mine.triggers_subsafe) || Array.containment_case_ignored(Mine.triggers_nonsubsafe, input))
			{
				Type.delay_line("You cannot mine when you are in combat!", 40);
			}
//  //  //   help triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			else if ((Array.subcontainment_case_ignored(input, Help.triggers_subsafe) || Array.containment_case_ignored(Help.triggers_nonsubsafe, input)) || (Act.unrecognized_nontrigger_errors > 0  &&  ((Act.unrecognized_nontrigger_errors + 1) % 3  ==  0)))
			{
				if (!(Array.subcontainment_case_ignored(input, Help.triggers_subsafe) || Array.containment_case_ignored(Help.triggers_nonsubsafe, input)))
				{
					Act.unrecognized_nontrigger_error();
					System.out.println();
					
				}
				Type.delay_line("Try inputting any of the following relevant commands:", 40);
				System.out.println("¤---------------------------------------------------¤");
				System.out.println();
				
				for (int i = 0; i < allowed_trigger_examples.length; i++)
				{
					Type.delay_line("\t\t› "+allowed_trigger_examples[i], 10);
				}
				System.out.println();
				
				Act.list_allowed_informational_trigger_examples();
				
				Prompting_Guide prompting_guide = new Prompting_Guide();
				Wait.milliseconds(250);
				System.out.println();

				Combat combat = new Combat(player, scenario);
			}
//  //  //   unrecognized/nontrigger error   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			else		Act.unrecognized_nontrigger_error();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
    }
}