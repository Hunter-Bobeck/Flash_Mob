package acts_combat;

import utilities.*;
import players.*;
import mobs.*;
import scenarios.*;
import acts.*;
import encounters.*;
import acts_world.*;
import banners.*;

public class Ahead		// accessed by calling #-act //
{
	// #random (generates weighted random input for #-prompt for #-act) //
	private static String random(Player player, Mob mob)
	{
		// input declaration //
		String input = null;
		// randomization determination //
		if (player.health > mob.health  &&  Random.whole() <= .8)		input = Random.text_of(player.class_combat_triggers_colloquial);
		else if (player.health < mob.health  &&  Random.whole() <=.8)
		{
			if (Random.whole() <= .75)		input = Random.text_of(Flee.triggers_colloquial);
			else		input = Random.text_of(Travel.triggers_colloquial);
		}
		else if (player.wetness <= 1  &&  Random.whole() <= .8)		input = Random.text_of(Drink.triggers_colloquial);
		else if (player.fullness <= 1  &&  Random.whole() <= .8)		input = Random.text_of(Eat.triggers_colloquial);
		else
		{
			double randomization = Random.whole();
			if (randomization <= .61)			input = Random.text_of(Look.triggers_colloquial);		// 61% probability //
			else if (randomization <= .69)		input = Random.text_of(Hesitate.triggers_colloquial);	// 8% probability //
			else if (randomization <= .76)		input = Random.text_of(Halt.triggers_colloquial);		// 7% probability //
			else if (randomization <= .82)		input = Random.text_of(Pause.triggers_colloquial);		// 6% probability //
			else if (randomization <= .885)		input = Random.text_of(Fart.triggers_colloquial);		// 6.5% probability //
			else if (randomization <= .95)		input = Random.text_of(Burp.triggers_colloquial);		// 6.5% probability //
			else								input = Random.text_of(Freakout.triggers_colloquial);	// 5% probability //
		}
		// return input //
		return input;
	}
	
	
	
	// action unknown description toggle //
	private boolean action_known = true;
	// #prompt (gets input for #-act, calling #-random if applicable) //
	private String prompt(Player player, Mob mob)
	{
		Type.delay_line(Random.text_of
		(
			new String[]
			{
				"Do you "+Random.text_of
				(
					new String[]
					{
						"approach the "+mob.species+",",
						"confront the "+mob.species+",",
						"initiate combat,",
						"attack",
						"engage",
						"take advantage of the situation"
					}
				)+" or "+Random.text_of
				(
					new String[]
					{
						"continue on your way",
						"head somewhere else",
						"avoid confrontation",
						"pass on "+Random.text_of(new String[] {"by", "through"}),
						"leave the "+mob.species+" be",
						"leave the "+mob.species+" alone"
					}
				),
				Random.text_of(new String[]
				{
					"You're close to the "+mob.species+", but it doesn't know",
					"The "+mob.species+" "+Random.text_of
					(
						new String[]
						{
							"doesn't realize you're here",
							"thinks "+mob.article+" is alone"
						}
					)
				})+". "+Random.text_of(new String[]
				{
					"What do you do",
					"How do you act",
					"How do you react to the situation"
				})
			}
		)+"?");
		// input declaration //
		String input = null;
		// if prompts are setly or globally defaulted, then randomize the input and plan to type it //
		if (player.prompts_global_default)
		{
			Act.input_backup = random(player, mob);
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
				// for '-2', display a reinputting of '-1' to clarify that '-2' is the same as '-1' in this case //
				if (input.equals("-2"))
				{
					System.out.print("› ");
					Type.delay_line("-1");
				}
				// for '-3', set prompts to be defaulted globally //
				if (input.equals("-3"))		player.prompts_global_default = true;
				// for '-4', set the game to just be quit //
				else if (input.equals("-4"))		player.playing = false;
				// if still playing, now randomize the input and plan to type it //
				if (player.playing)
				{
					// randomize the input with #-random //
					Act.input_backup = random(player, mob);
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
	
	
	private static void separate()
	{
		Wait.milliseconds(322);
		System.out.println();
	}
	public static void reprompt(Player player, Scenario scenario, Mob mob)
	{
		separate();
		
		Ahead ahead = new Ahead(player, scenario, mob);
	}
	private static void reprompt_but_mob_might_notice(Player player, Scenario scenario, Mob mob)
	{
		if (Random.whole() <= .1)
		{
			System.out.print("\n\n");
			
			
			Type.delay_line(Random.text_of(new String[] {"The "+mob.species+" has noticed you. "+Text.capitalize(mob.article)+" is attacking!", "The "+mob.species+" is now aware of your presence; "+mob.article+"'s approaching!"}));
			if (Random.binary())		Combat.player_initiative(player, scenario, mob, false);
			else		Combat.mob_initiative(mob);
		}
		else
		{
			separate();
			
			Ahead ahead = new Ahead(player, scenario, mob);
		}
	}
	private static void reprompt_but_probably_alert_mob(Player player, Scenario scenario, Mob mob)
	{
		if (Random.whole() <= .9)
		{
			System.out.print("\n\n");
		
		
			Type.delay_line(Random.text_of(new String[] {"You have alerted the "+mob.species+" to your presence!", "The "+mob.species+" now knows that you're here!", "You have lost the initiative!"}));
			Combat.mob_initiative(mob);
		}
		else
		{
			separate();
			
			Ahead ahead = new Ahead(player, scenario, mob);
		}
	}
	// allowed trigger examples to be listed when acting Help in the ahead context //
	private static final String[] allowed_trigger_examples = {"attack", "continue", "look", "eat", "drink", "resupply"};
	// #act (retrieves input from #-prompt, then acts in the situation accordingly) //
	public Ahead(Player player, Scenario scenario, Mob mob)
	{
		Combat.first_attack = false;
		System.out.println();
		
		// retrieve input from #-prompt //
		String input = prompt(player, scenario.encounter.mob);
//////// triggering (searched in the order of sensitivity then applicability then commonness) ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (player.playing)
		{
			System.out.print("\n\n");
			
//  //  //   allowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  // 
			// experience triggering //
			if (Array.subcontainment_case_ignored(input, Experience.triggers_subsafe) || Array.containment_case_ignored(Experience.triggers_nonsubsafe, input))
			{
				player.describe_experience();
				reprompt(player, scenario, mob);
				
			}
//  //  //   unallowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
			// train triggering //
			else if (Array.subcontainment_case_ignored(input, Train.triggers_subsafe) || Array.containment_case_ignored(Train.triggers_nonsubsafe, input))
			{
				Type.delay_line("It would be too risky to train right now; there is "+mob.article_species+" nearby.", 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
//  //  //   allowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  // 
			// travel\ flee/travel triggering //
			else if (Array.subcontainment_case_ignored(input, Flee.triggers_subsafe) || Array.containment_case_ignored(Flee.triggers_nonsubsafe, input) || Array.subcontainment_case_ignored(input, Travel.triggers_subsafe) || Array.containment_case_ignored(Travel.triggers_nonsubsafe, input))
			{
				Travel travel = new Travel(scenario, player, true);
			}
			// attack triggering //
			else if (Array.subcontainment_case_ignored(input, Attack.triggers_subsafe) || Array.containment_case_ignored(Attack.triggers_nonsubsafe, input))
			{
				Type.delay_line("You "+Random.text_of(new String[] {Random.text_of(new String[] {"approach", "approach", "approach", "confront", "confront", "confront", "initiate combat with", "attack", "engage"})+" the "+mob.species, "initiate combat", "attack", "engage"})+".");
				Combat combat = new Combat(player, scenario);
				separate();
				
			}
			// slash triggering //
			else if (Array.subcontainment_case_ignored(input, Slash.triggers_subsafe) || Array.containment_case_ignored(Slash.triggers_nonsubsafe, input))
			{
				Slash slash = new Slash(scenario, player);
				separate();
				
			}
			// cut triggering //
			else if (Array.subcontainment_case_ignored(input, Cut.triggers_subsafe) || Array.containment_case_ignored(Cut.triggers_nonsubsafe, input))
			{
				Cut cut = new Cut(scenario, player);
				separate();
				
			}
			// double thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Double_Thrust.triggers_subsafe) || Array.containment_case_ignored(Double_Thrust.triggers_nonsubsafe, input))
			{
				Double_Thrust double_thrust = new Double_Thrust(scenario, player);
				separate();
				
			}
			// thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Thrust.triggers_subsafe) || Array.containment_case_ignored(Thrust.triggers_nonsubsafe, input))
			{
				Thrust thrust = new Thrust(scenario, player);
				separate();
				
			}
			// twist triggering //
			else if (Array.subcontainment_case_ignored(input, Twist.triggers_subsafe) || Array.containment_case_ignored(Twist.triggers_nonsubsafe, input))
			{
				Twist twist = new Twist(scenario, player);
				separate();
				
			}
			// spin triggering //
			else if (Array.subcontainment_case_ignored(input, Spin.triggers_subsafe) || Array.containment_case_ignored(Spin.triggers_nonsubsafe, input))
			{
				Spin spin = new Spin(scenario, player);
				separate();
				
			}
			// overhead triggering //
			else if (Array.subcontainment_case_ignored(input, Overhead.triggers_subsafe) || Array.containment_case_ignored(Overhead.triggers_nonsubsafe, input))
			{
				Overhead overhead = new Overhead(scenario, player);
				separate();
				
			}
			// flurry triggering //
			else if (Array.subcontainment_case_ignored(input, Flurry.triggers_subsafe) || Array.containment_case_ignored(Flurry.triggers_nonsubsafe, input))
			{
				Flurry flurry = new Flurry(scenario, player);
				separate();
				
			}
			// whack triggering //
			else if (Array.subcontainment_case_ignored(input, Whack.triggers_subsafe) || Array.containment_case_ignored(Whack.triggers_nonsubsafe, input))
			{
				Whack whack = new Whack(scenario, player);
				separate();
				
			}
			// punch triggering //
			else if (Array.subcontainment_case_ignored(input, Punch.triggers_subsafe) || Array.containment_case_ignored(Punch.triggers_nonsubsafe, input))
			{
				Punch punch = new Punch(scenario, player);
				separate();
				
			}
			// roundhouse kick triggering //
			else if (Array.subcontainment_case_ignored(input, Roundhouse_Kick.triggers_subsafe) || Array.containment_case_ignored(Roundhouse_Kick.triggers_nonsubsafe, input))
			{
				Roundhouse_Kick roundhouse_kick = new Roundhouse_Kick(scenario, player);
				separate();
				
			}
			// flying kick triggering //
			else if (Array.subcontainment_case_ignored(input, Flying_Kick.triggers_subsafe) || Array.containment_case_ignored(Flying_Kick.triggers_nonsubsafe, input))
			{
				Flying_Kick flying_kick = new Flying_Kick(scenario, player);
				separate();
				
			}
			// kick triggering //
			else if (Array.subcontainment_case_ignored(input, Kick.triggers_subsafe) || Array.containment_case_ignored(Kick.triggers_nonsubsafe, input))
			{
				Kick kick = new Kick(scenario, player);
				separate();
				
			}
			// elbow strike triggering //
			else if (Array.subcontainment_case_ignored(input, Elbow_Strike.triggers_subsafe) || Array.containment_case_ignored(Elbow_Strike.triggers_nonsubsafe, input))
			{
				Elbow_Strike elbow_strike = new Elbow_Strike(scenario, player);
				separate();
				
			}
			// pressure poke triggering //
			else if (Array.subcontainment_case_ignored(input, Pressure_Poke.triggers_subsafe) || Array.containment_case_ignored(Pressure_Poke.triggers_nonsubsafe, input))
			{
				Pressure_Poke pressure_poke = new Pressure_Poke(scenario, player);
				separate();
				
			}
			// upward palm triggering //
			else if (Array.subcontainment_case_ignored(input, Upward_Palm.triggers_subsafe) || Array.containment_case_ignored(Upward_Palm.triggers_nonsubsafe, input))
			{
				Upward_Palm upward_palm = new Upward_Palm(scenario, player);
				separate();
				
			}
			// magic missile triggering //
			else if (Array.subcontainment_case_ignored(input, Magic_Missile.triggers_subsafe) || Array.containment_case_ignored(Magic_Missile.triggers_nonsubsafe, input))
			{
				Magic_Missile magic_missile = new Magic_Missile(scenario, player);
				separate();
				
			}
			// fireblast triggering //
			else if (Array.subcontainment_case_ignored(input, Fireblast.triggers_subsafe) || Array.containment_case_ignored(Fireblast.triggers_nonsubsafe, input))
			{
				Fireblast fireblast = new Fireblast(scenario, player);
				separate();
				
			}
			// zap triggering //
			else if (Array.subcontainment_case_ignored(input, Zap.triggers_subsafe) || Array.containment_case_ignored(Zap.triggers_nonsubsafe, input))
			{
				Zap zap = new Zap(scenario, player);
				separate();
				
			}
			// static field triggering //
			else if (Array.subcontainment_case_ignored(input, Static_Field.triggers_subsafe) || Array.containment_case_ignored(Static_Field.triggers_nonsubsafe, input))
			{
				Static_Field static_field = new Static_Field(scenario, player);
				separate();
				
			}
			// lightning bolt triggering //
			else if (Array.subcontainment_case_ignored(input, Lightning_Bolt.triggers_subsafe) || Array.containment_case_ignored(Lightning_Bolt.triggers_nonsubsafe, input))
			{
				Lightning_Bolt lightning_bolt = new Lightning_Bolt(scenario, player);
				separate();
				
			}
			// disintegrate triggering //
			else if (Array.subcontainment_case_ignored(input, Disintegrate.triggers_subsafe) || Array.containment_case_ignored(Disintegrate.triggers_nonsubsafe, input))
			{
				Disintegrate disintegrate = new Disintegrate(scenario, player);
				separate();
				
			}
			// meteor triggering //
			else if (Array.subcontainment_case_ignored(input, Meteor.triggers_subsafe) || Array.containment_case_ignored(Meteor.triggers_nonsubsafe, input))
			{
				Meteor meteor = new Meteor(scenario, player);
				separate();
				
			}
			// abilities triggering //
			else if (Array.subcontainment_case_ignored(input, Abilities.triggers_subsafe) || Array.containment_case_ignored(Abilities.triggers_nonsubsafe, input))
			{
				player.describe_class(false);
				reprompt(player, scenario, mob);
				
			}
			// gender triggering //
			else if (Array.subcontainment_case_ignored(input, Gender.triggers_subsafe) || Array.containment_case_ignored(Gender.triggers_nonsubsafe, input))
			{
				player.describe_gender();
				reprompt(player, scenario, mob);
			}
			// name triggering //
			else if (Array.subcontainment_case_ignored(input, Name.triggers_subsafe) || Array.containment_case_ignored(Name.triggers_nonsubsafe, input))
			{
				player.describe_name(false);
				reprompt(player, scenario, mob);
			}
			// health triggering //
			else if (Array.subcontainment_case_ignored(input, Health.triggers_subsafe) || Array.containment_case_ignored(Health.triggers_nonsubsafe, input))
			{
				Health health = new Health(player, scenario, false, false, true);
			}
			// strength triggering //
			else if (Array.subcontainment_case_ignored(input, Strength.triggers_subsafe) || Array.containment_case_ignored(Strength.triggers_nonsubsafe, input))
			{
				player.describe_strength();
				reprompt(player, scenario, mob);
			}
			// agility triggering //
			else if (Array.subcontainment_case_ignored(input, Agility.triggers_subsafe) || Array.containment_case_ignored(Agility.triggers_nonsubsafe, input))
			{
				player.describe_agility();
				reprompt(player, scenario, mob);
			}
			// water triggering //
			else if (Array.subcontainment_case_ignored(input, Water.triggers_subsafe) || Array.containment_case_ignored(Water.triggers_nonsubsafe, input))
			{
				player.describe_water();
				reprompt(player, scenario, mob);
			}
			// thirst triggering //
			else if (Array.subcontainment_case_ignored(input, Thirst.triggers_subsafe) || Array.containment_case_ignored(Thirst.triggers_nonsubsafe, input))
			{
				Thirst thirst = new Thirst(player, scenario, false, true);
			}
			// food triggering //
			else if (Array.subcontainment_case_ignored(input, Food.triggers_subsafe) || Array.containment_case_ignored(Food.triggers_nonsubsafe, input))
			{
				player.describe_food();
				reprompt(player, scenario, mob);
			}
			// hunger triggering //
			else if (Array.subcontainment_case_ignored(input, Hunger.triggers_subsafe) || Array.containment_case_ignored(Hunger.triggers_nonsubsafe, input))
			{
				Hunger hunger = new Hunger(player, scenario, false, false, true);
			}
			// inventory triggering //
			else if (Array.subcontainment_case_ignored(input, Inventory.triggers_subsafe) || Array.containment_case_ignored(Inventory.triggers_nonsubsafe, input))
			{
				player.describe_inventory();
				reprompt(player, scenario, mob);
			}
			// drink triggering //
			else if (Array.subcontainment_case_ignored(input, Drink.triggers_subsafe) || Array.containment_case_ignored(Drink.triggers_nonsubsafe, input))
			{
				Drink drink = new Drink(player, scenario, false, true);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// eat triggering //
			else if (Array.subcontainment_case_ignored(input, Eat.triggers_subsafe) || Array.containment_case_ignored(Eat.triggers_nonsubsafe, input) || input.contains("eat "+mob.species) || input.contains("eat the "+mob.species) || input.contains("digest "+mob.species) || input.contains("digest the "+mob.species))
			{
				Eat eat = new Eat(player, scenario, false, false, true);
				if (Random.binary())		reprompt_but_probably_alert_mob(player, scenario, mob);
				else		reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// look triggering //
			else if (Array.subcontainment_case_ignored(input, Look.triggers_subsafe) || Array.containment_case_ignored(Look.triggers_nonsubsafe, input))
			{
				Look look = new Look(scenario, player, true);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// halt triggering //
			else if (Array.subcontainment_case_ignored(input, Halt.triggers_subsafe) || Array.containment_case_ignored(Halt.triggers_nonsubsafe, input))
			{
				Halt halt = new Halt();
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// pause triggering //
			else if (Array.subcontainment_case_ignored(input, Pause.triggers_subsafe) || Array.containment_case_ignored(Pause.triggers_nonsubsafe, input))
			{
				Pause pause = new Pause();
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// hesitate triggering //
			else if (Array.subcontainment_case_ignored(input, Hesitate.triggers_subsafe) || Array.containment_case_ignored(Hesitate.triggers_nonsubsafe, input))
			{
				Hesitate hesitate = new Hesitate();
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// freakout triggering //
			else if (Array.subcontainment_case_ignored(input, Freakout.triggers_subsafe) || Array.containment_case_ignored(Freakout.triggers_nonsubsafe, input))
			{
				Freakout freakout = new Freakout();
				if (Random.binary())		reprompt_but_probably_alert_mob(player, scenario, mob);
				else		reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// fart triggering //
			else if (Array.subcontainment_case_ignored(input, Fart.triggers_subsafe) || Array.containment_case_ignored(Fart.triggers_nonsubsafe, input))
			{
				Fart fart = new Fart();
				if (Random.whole() <= .9)
				{
					System.out.print("\n\n");


					Type.delay_line(Random.text_of(new String[] {"The "+mob.species+" has caught your stench!", "The "+mob.species+" now knows that you're here... and "+mob.article+" does not take kindly to farts!", "You have lost the initiative with your passing of the gas."}));
					Combat.mob_initiative(mob);
				}
				else
				{
					Ahead ahead = new Ahead(player, scenario, mob);
				}
			}
			// burp triggering //
			else if (Array.subcontainment_case_ignored(input, Burp.triggers_subsafe) || Array.containment_case_ignored(Burp.triggers_nonsubsafe, input))
			{
				Burp burp = new Burp();
				if (Random.whole() <= .9)
				{
					System.out.print("\n\n");


					Type.delay_line(Random.text_of(new String[] {"The "+mob.species+" has sniffed your smelly air!", "The "+mob.species+" now knows that you're here... and "+mob.article+" does not take kindly to your belching!", "Your burp has given away your position!"}));
					Combat.mob_initiative(mob);
				}
				else
				{
					Ahead ahead = new Ahead(player, scenario, mob);
				}
			}
			// say triggering //
			else if (Array.subcontainment_case_ignored(input, Say.triggers_subsafe) || Array.containment_case_ignored(Say.triggers_nonsubsafe, input))
			{
				Say say = new Say();
				reprompt_but_probably_alert_mob(player, scenario, mob);
			}
//  //  //   unallowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
			// loot triggering //
			else if (Array.subcontainment_case_ignored(input, Loot.triggers_subsafe) || Array.containment_case_ignored(Loot.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You must defeat the "+mob.species+" before looting "+mob.article_indirect+"!", "The "+mob.species+" is still alive – defeat "+mob.article_indirect+" first."}), 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// mine triggering //
			else if (Array.subcontainment_case_ignored(input, Mine.triggers_subsafe) || Array.containment_case_ignored(Mine.triggers_nonsubsafe, input))
			{
				Type.delay_line("Now is not the time to be mining; there is "+mob.article_species+" nearby.", 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// rest triggering //
			else if (Array.subcontainment_case_ignored(input, Rest.triggers_subsafe) || Array.containment_case_ignored(Rest.triggers_nonsubsafe, input))
			{
				Type.delay_line("You cannot safely rest when there is "+mob.article_species+" nearby.", 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// sleep triggering //
			else if (Array.subcontainment_case_ignored(input, Sleep.triggers_subsafe) || Array.containment_case_ignored(Sleep.triggers_nonsubsafe, input))
			{
				Type.delay_line("You cannot safely sleep when there is "+mob.article_species+" nearby.", 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// exercise triggering //
			else if (Array.subcontainment_case_ignored(input, Exercise.triggers_subsafe) || Array.containment_case_ignored(Exercise.triggers_nonsubsafe, input))
			{
				Type.delay_line("Now is not the time to "+Random.text_of(new String[] {"exercise", "workout"})+"; there is "+mob.article_species+" nearby.", 40);
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
			// wake triggering //
			else if (Array.subcontainment_case_ignored(input, Wake.triggers_subsafe) || Array.containment_case_ignored(Wake.triggers_nonsubsafe, input))
			{
				Wake wake = new Wake(player, scenario, true);
			}
//  //  //   help triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			else if (Array.subcontainment_case_ignored(input, Help.triggers_subsafe) || Array.containment_case_ignored(Help.triggers_nonsubsafe, input))
			{
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
				reprompt(player, scenario, mob);
			}
//  //  //   unrecognized/nontrigger error   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			else
			{
				Act.unrecognized_nontrigger_error();
				Act.unrecognized_nontrigger_errors--;
				reprompt_but_mob_might_notice(player, scenario, mob);
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
    }
}