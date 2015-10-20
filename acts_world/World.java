package acts_world;

import utilities.*;
import players.*;
import scenarios.*;
import acts.*;
import encounters.*;
import mobs.*;
import acts_combat.*;
import banners.*;

public class World		// accessed by calling #-act //
{
	// #random (generates weighted random input for #-prompt for #-act) //
	private static String random(Player player, Encounter encounter)
	{
		// randomization setup //
		double randomization = Random.whole();
		// input declaration //
		String input = null;
		// randomization determination //
		boolean enough_experience_to_learn = false;
		for (int i = 0; i < player.unlearned_abilities.length; i++)
		{
			if (player.unlearned_abilities_experience_costs[i] <= player.experience)		enough_experience_to_learn = true;
		}
		if (encounter.mob_encountered && !encounter.mob_lootchecked)
		{
			Mob mob = encounter.mob;
			input = Random.text_of(Array.combine(Loot.triggers_colloquial, new String[] {"loot the "+mob.species, "check the "+mob.species+" for loot", "search the "+mob.species}));
		}
		else if (player.wetness <= 1  &&  randomization <= .9)		input = Random.text_of(Drink.triggers_colloquial);
		else if (player.fullness <= 1  &&  randomization <= .9)		input = Random.text_of(Eat.triggers_colloquial);
		else if (player.rest <= 1  &&  randomization <= .9)		input = Random.text_of(Sleep.triggers_colloquial);
		else if (enough_experience_to_learn  &&  randomization <= .85)		input = Random.text_of(Train.triggers_colloquial)+" "+Random.text_of(player.unlearned_abilities);
		else if (randomization <= .61)			input = Random.text_of(Travel.triggers_colloquial);
		else if (randomization <= .72)		input = Random.text_of(Look.triggers_colloquial);
		else if (randomization <= .87)		input = Random.text_of(Rest.triggers_colloquial);
		else if (randomization <= .89)		input = Random.text_of(Eat.triggers_colloquial);
		else if (randomization <= .91)		input = Random.text_of(Pause.triggers_colloquial);
		else if (randomization <= .98)		input = Random.text_of(Exercise.triggers_colloquial);
		else if (randomization <= .9875)	input = Random.text_of(Halt.triggers_colloquial);
		else if (randomization <= .995)		input = Random.text_of(Hesitate.triggers_colloquial);
		else if (randomization <= .997)		input = Random.text_of(Freakout.triggers_colloquial);
		else								input = Random.text_of(Fart.triggers_colloquial);
		// return input //
		return input;
	}
	
	
	
	// action unknown description toggle //
	private static boolean action_known = true;
	// #prompt (gets input for #-act, calling #-random if applicable) //
	private static String prompt(Player player, Scenario scenario)
	{
		// #world_prompt_question //
		if (player.initial_tavern)
		{
			if (scenario.encounter.mob_encountered)		Type.delay_line("Time to find a different tavern...");
			else
			{
				Type.delay_line(Random.text_of(new String[] {"You have just finished a meal.", "Dinner was good.", "The food was mediocre.", "You have had your fill.", "You have finished your drink.", "Your ale has run out."}));
				Type.delay_line("You are ready to "+Random.text_of(new String[] {"adventure", "continue on", "depart", "explore the "+Random.text_of(new String[] {"world", "wilds", "wilderness"}), "head on out", "hit the path", "hit the trail", "hit the road", "journey "+Random.text_of(new String[] {"on", "onward"}), "leave", "proceed elsewhere", "progress onward", "travel"+Random.text_of(new String[] {"", " onward"}), "traverse the "+Random.text_of(new String[] {"world", "wilds", "wilderness"}), "keep trekking again", "venture forth", "wander onward", "go", "get out"+Random.text_of(new String[] {"", " of here"}), "get going", "take off"})+Random.text_of(new String[] {"!", "."}));
				player.initial_tavern = false;
			}			
		}
		else
		{
			String player_addressing_possibility = Random.text_of(new String[] {"", ", "+player.name});
			String next_or_now = Random.text_of(new String[] {"next", "now"});
			Type.delay_line(Random.text_of(new String[]
			{
				Random.text_of(new String[] {"It is t", "T"})+"ime to act"+player_addressing_possibility+Random.text_of(new String[] {"...", ".", "!"}),
				Random.text_of
				(new String[]
				{
					"What "+Random.text_of(new String[]
							{
								"is next",
								"now",
								"are you "+Random.text_of(new String[] 
											{
												"going to do"+Random.text_of(new String[] {"", " "+next_or_now}),
												"thinking"+Random.text_of(new String[] {"", " about doing "+next_or_now})
											}),
								"do you do "+next_or_now,
								"do you want to do"+Random.text_of(new String[] {"", " "+next_or_now}),
								"will you do "+next_or_now,
								"is "+Random.text_of(new String[]
										{
											"your plan"+Random.text_of(new String[] {"", " now"}),
											"next",
											"your "+Random.text_of(new String[] {"course of action", "next action"+Random.text_of(new String[] {"", " going to be"})})
										})
							}),
					"Now what "+Random.text_of(new String[] {"\b", "are you "+Random.text_of(new String[] {"going to do", "thinking about doing"}), "do you "+Random.text_of(new String[] {"", "want to "})+"do", "will you do", "is your "+Random.text_of(new String[] {"plan", "course of action"})})
				})+player_addressing_possibility+"?"
			}));		// these prompt question phrases are matched with the hesitation input phrases of the #-hesitation_triggers //
		}
		// input declaration //
		String input = null;
		// if prompts are globally defaulted, then randomize the input and plan to type it //
		if (player.prompts_global_default)
		{
			Act.input_backup = random(player, scenario.encounter);
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
					Act.input_backup = random(player, scenario.encounter);
					input = Act.input_backup;
					// plan to type the input //
					action_known = false;
				}
			}
			// otherwise, now safely unpunctuate the input to further refine it //
			else		input = Text.unpunctuate(input);
		}
		// if still playing, and typing the input is planned due to it having been randomized and thus it being unknown, do so, then unplan to type the input //
		if (player.playing && !action_known)
		{
			System.out.print("› ");
			Wait.milliseconds(1000);
			Type.human_line(input);
			action_known = true;
		}
		// return the input //
		return input;
	}
	
	
	
	// allowed trigger examples to be listed when acting Help in the world context //
	private static final String[] allowed_trigger_examples = {"look", "travel", "loot", "eat", "drink", "resupply", "rest", "sleep", "exercise", "train"};
	// traveled toggle (if it becomes true by triggering travel, then another world act won't occur) //
	private boolean traveled;
	// #act (retrieves input from #-prompt, then acts in the world accordingly) //
	public World(Player player, Scenario scenario)
	{
		// retrieve input from #-prompt //
		String input = prompt(player, scenario);
////// triggering (searched in the order of sensitivity then applicability then commonness) /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (player.playing)
		{
			Encounter encounter = scenario.encounter;
			Mob mob = encounter.mob;
			System.out.print("\n\n");
			
			
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
			// travel triggering //
			else if (Array.subcontainment_case_ignored(input, Travel.triggers_subsafe) || Array.containment_case_ignored(Travel.triggers_nonsubsafe, input))
			{
				Travel travel = new Travel(scenario, player, false);
				traveled = true;
			}
			// water triggering //
			else if (Array.subcontainment_case_ignored(input, Water.triggers_subsafe) || Array.containment_case_ignored(Water.triggers_nonsubsafe, input))
			{
				player.describe_water();
			}
			// food triggering //
			else if (Array.subcontainment_case_ignored(input, Food.triggers_subsafe) || Array.containment_case_ignored(Food.triggers_nonsubsafe, input))
			{
				player.describe_food();
			}
			// inventory triggering //
			else if (Array.subcontainment_case_ignored(input, Inventory.triggers_subsafe) || Array.containment_case_ignored(Inventory.triggers_nonsubsafe, input))
			{
				player.describe_inventory();
			}
			// loot triggering //
			else if (Array.subcontainment_case_ignored(input, Loot.triggers_subsafe) || Array.containment_case_ignored(Loot.triggers_nonsubsafe, input))
			{
				Loot loot = new Loot(scenario, player);
			}
			// look triggering //
			else if (Array.subcontainment_case_ignored(input, Look.triggers_subsafe) || Array.containment_case_ignored(Look.triggers_nonsubsafe, input))
			{
				Look look = new Look(scenario, player, false);
			}
			// thirst triggering //
			else if (Array.subcontainment_case_ignored(input, Thirst.triggers_subsafe) || Array.containment_case_ignored(Thirst.triggers_nonsubsafe, input))
			{
				Thirst thirst = new Thirst(player, scenario, true, false);
			}
			// rest triggering //
			else if (Array.subcontainment_case_ignored(input, Rest.triggers_subsafe) || Array.containment_case_ignored(Rest.triggers_nonsubsafe, input))
			{
				Rest rest = new Rest(player, scenario);
			}
			// drink triggering //
			else if (Array.subcontainment_case_ignored(input, Drink.triggers_subsafe) || Array.containment_case_ignored(Drink.triggers_nonsubsafe, input))
			{
				Drink drink = new Drink(player, scenario, true, false);
			}
			// hunger triggering //
			else if (Array.subcontainment_case_ignored(input, Hunger.triggers_subsafe) || Array.containment_case_ignored(Hunger.triggers_nonsubsafe, input))
			{
				Hunger hunger = new Hunger(player, scenario, false, true, false);
			}
			// eat triggering //
			else if (Array.subcontainment_case_ignored(input, Eat.triggers_subsafe) || Array.containment_case_ignored(Eat.triggers_nonsubsafe, input) || (encounter.mob_encountered && (input.contains("eat "+mob.species) || input.contains("eat the "+mob.species) || input.contains("digest "+mob.species) || input.contains("digest the "+mob.species))))
			{
				Eat eat = new Eat(player, scenario, false, true, false);
			}
			// pause triggering //
			else if (Array.subcontainment_case_ignored(input, Pause.triggers_subsafe) || Array.containment_case_ignored(Pause.triggers_nonsubsafe, input))
			{
				Pause pause = new Pause();
			}
			// sleep triggering //
			else if (Array.subcontainment_case_ignored(input, Sleep.triggers_subsafe) || Array.containment_case_ignored(Sleep.triggers_nonsubsafe, input))
			{
				Sleep sleep = new Sleep(player, scenario);
			}
			// exercise triggering //
			else if (Array.subcontainment_case_ignored(input, Exercise.triggers_subsafe) || Array.containment_case_ignored(Exercise.triggers_nonsubsafe, input))
			{
				Exercise exercise = new Exercise(player);
			}
			// mine triggering //
			else if (Array.subcontainment_case_ignored(input, Mine.triggers_subsafe) || Array.containment_case_ignored(Mine.triggers_nonsubsafe, input))
			{
				Mine mine = new Mine();
			}
			// halt triggering //
			else if (Array.subcontainment_case_ignored(input, Halt.triggers_subsafe) || Array.containment_case_ignored(Halt.triggers_nonsubsafe, input))
			{
				Halt halt = new Halt();
			}
			// hesitate triggering //
			else if (Array.subcontainment_case_ignored(input, Hesitate.triggers_subsafe) || Array.containment_case_ignored(Hesitate.triggers_nonsubsafe, input))
			{
				Hesitate hesitate = new Hesitate();
			}
			// freakout triggering //
			else if (Array.subcontainment_case_ignored(input, Freakout.triggers_subsafe) || Array.containment_case_ignored(Freakout.triggers_nonsubsafe, input))
			{
				Freakout freakout = new Freakout();
			}
			// experience triggering //
			else if (Array.subcontainment_case_ignored(input, Experience.triggers_subsafe) || Array.containment_case_ignored(Experience.triggers_nonsubsafe, input))
			{
				player.describe_experience();
			}
			// train triggering //
			else if (Array.subcontainment_case_ignored(input, Train.triggers_subsafe) || Array.containment_case_ignored(Train.triggers_nonsubsafe, input))
			{
				Train train = new Train(player);
			}
			// abilities triggering //
			else if (Array.subcontainment_case_ignored(input, Abilities.triggers_subsafe) || Array.containment_case_ignored(Abilities.triggers_nonsubsafe, input))
			{
				player.describe_class(false);
			}
			// gender triggering //
			else if (Array.subcontainment_case_ignored(input, Gender.triggers_subsafe) || Array.containment_case_ignored(Gender.triggers_nonsubsafe, input))
			{
				player.describe_gender();
			}
			// name triggering //
			else if (Array.subcontainment_case_ignored(input, Name.triggers_subsafe) || Array.containment_case_ignored(Name.triggers_nonsubsafe, input))
			{
				player.describe_name(false);
			}
			// health triggering //
			else if (Array.subcontainment_case_ignored(input, Health.triggers_subsafe) || Array.containment_case_ignored(Health.triggers_nonsubsafe, input))
			{
				Health health = new Health(player, scenario, false, true, false);
			}
			// strength triggering //
			else if (Array.subcontainment_case_ignored(input, Strength.triggers_subsafe) || Array.containment_case_ignored(Strength.triggers_nonsubsafe, input))
			{
				player.describe_strength();
			}
			// agility triggering //
			else if (Array.subcontainment_case_ignored(input, Agility.triggers_subsafe) || Array.containment_case_ignored(Agility.triggers_nonsubsafe, input))
			{
				player.describe_agility();
			}
//  //  //   unallowed triggering   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
			// slash triggering //
			else if (Array.subcontainment_case_ignored(input, Slash.triggers_subsafe) || Array.containment_case_ignored(Slash.triggers_nonsubsafe, input))
			{
				if (player.duelist)		Type.delay_line("You "+Random.text_of(new String[] {"slash", "swing at"})+" the air in front of you.");
				else		Type.delay_line("You don't have a sword, nor are you in combat anyway.", 40);
			}
			// cut triggering //
			else if (Array.subcontainment_case_ignored(input, Cut.triggers_subsafe) || Array.containment_case_ignored(Cut.triggers_nonsubsafe, input))
			{
				if (player.duelist)		Type.delay_line("You "+Random.text_of(new String[] {"cut", "slice"})+" the air in front of you.");
				else		Type.delay_line("You don't have a sword, nor are you in combat anyway.", 40);
			}
			// double thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Double_Thrust.triggers_subsafe) || Array.containment_case_ignored(Double_Thrust.triggers_nonsubsafe, input))
			{
				if (player.duelist)
				{
					if (Array.containment(player.abilities, "double thrust"))		Type.delay_line(Random.text_of(new String[] {"You lunge forward with both swords thrusting the air.", "You stab the air with both blades at once."}));
					else		Type.delay_line(Random.text_of(new String[] {"You haven't learned the double thrust ability yet, nor are you in combat anyway", "You need to train yourself to execute a double thrust first. Besides, you're not even in combat"})+".", 40);
				}
				else		Type.delay_line("You don't have a sword, don't know how to execute a double thrust, nor are you in combat.", 40);
			}
			// thrust triggering //
			else if (Array.subcontainment_case_ignored(input, Thrust.triggers_subsafe) || Array.containment_case_ignored(Thrust.triggers_nonsubsafe, input))
			{
				if (player.duelist)		Type.delay_line(Random.text_of(new String[] {"You lunge forward at nothing in particular", "You thrust a sword at the air"})+".");
				else		Type.delay_line("You don't have a sword, nor are you in combat anyway.", 40);
			}
			// twist triggering //
			else if (Array.subcontainment_case_ignored(input, Twist.triggers_subsafe) || Array.containment_case_ignored(Twist.triggers_nonsubsafe, input))
			{
				if (player.duelist)
				{
					if (Array.containment(player.abilities, "twist"))		Type.delay_line(Random.text_of(new String[] {"You arc your sword in front of you.", "You rotate your blade in the air."}));
					else		Type.delay_line(Random.text_of(new String[] {"You haven't learned the twist ability yet, nor are you in combat anyway", "You need to train yourself to execute a twist first. Besides, you're not even in combat"})+".", 40);
				}
				else		Type.delay_line("You don't have a sword, don't know how to execute a twist, nor are you in combat.", 40);
			}
			// spin triggering //
			else if (Array.subcontainment_case_ignored(input, Spin.triggers_subsafe) || Array.containment_case_ignored(Spin.triggers_nonsubsafe, input))
			{
				if (player.duelist)
				{
					if (Array.containment(player.abilities, "spin"))		Type.delay_line(Random.text_of(new String[] {"You perform a pirouette fancifully.", "You execute a spin with your blades."}));
					else		Type.delay_line(Random.text_of(new String[] {"You haven't learned the spin ability yet, nor are you in combat anyway", "You need to train yourself to execute a spin first. Besides, you're not even in combat"})+".", 40);
				}
				else		Type.delay_line("You don't have a sword, don't know how to execute a spin, nor are you in combat.", 40);
			}
			// overhead triggering //
			else if (Array.subcontainment_case_ignored(input, Overhead.triggers_subsafe) || Array.containment_case_ignored(Overhead.triggers_nonsubsafe, input))
			{
				if (player.duelist)
				{
					if (Array.containment(player.abilities, "overhead"))		Type.delay_line(Random.text_of(new String[] {"You split the air with an overhead strike.", "You execute a sweeping overhead, hitting nothing but air.", "You perform an overhead. A flying bug gets split in two."}));
					else		Type.delay_line(Random.text_of(new String[] {"You haven't learned the overhead ability yet, nor are you in combat anyway", "You need to train yourself to execute an overhead first. Besides, you're not even in combat"})+".", 40);
				}
				else		Type.delay_line("You don't have a sword, don't know how to execute an overhead, nor are you in combat.", 40);
			}
			// flurry triggering //
			else if (Array.subcontainment_case_ignored(input, Flurry.triggers_subsafe) || Array.containment_case_ignored(Flurry.triggers_nonsubsafe, input))
			{
				if (player.duelist)
				{
					if (Array.containment(player.abilities, "flurry"))		Type.delay_line(Random.text_of(new String[] {"You perform a few quick slashes through the air.", "You whip your swords around a couple times."}));
					else		Type.delay_line(Random.text_of(new String[] {"You haven't learned the flurry ability yet, nor are you in combat anyway", "You need to train yourself to execute a flurry first. Besides, you're not even in combat"})+".", 40);
				}
				else		Type.delay_line("You don't have a sword, don't know how to execute a flurry, nor are you in combat.", 40);
			}
			// whack triggering //
			else if (Array.subcontainment_case_ignored(input, Whack.triggers_subsafe) || Array.containment_case_ignored(Whack.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You whiff your hand across in front of you.", "You swat at the air in front of you.", "You smack in front of you, dropping a fly out of the air."}));
			}
			// punch triggering //
			else if (Array.subcontainment_case_ignored(input, Punch.triggers_subsafe) || Array.containment_case_ignored(Punch.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You punch a fist out at the air.", "You swing your arm forward at nothing in particular.", "A fly falls dead at your feet."}));
			}
			// roundhouse kick triggering //
			else if (Array.subcontainment_case_ignored(input, Roundhouse_Kick.triggers_subsafe) || Array.containment_case_ignored(Roundhouse_Kick.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "roundhouse kick"))		Type.delay_line(Random.text_of(new String[] {"You stretch your legs with a couple of sweeping roundhouses.", "You perform a roundhouse kick."}));
				else		Type.delay_line("You don't know how to do a roundhouse kick, nor are you in combat anyway.", 40);
			}
			// flying kick triggering //
			else if (Array.subcontainment_case_ignored(input, Flying_Kick.triggers_subsafe) || Array.containment_case_ignored(Flying_Kick.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "flying kick"))		Type.delay_line(Random.text_of(new String[] {"You leap into the air and kick out at nothing in particular.", "You perform a jump kick."}));
				else		Type.delay_line("You don't know how to execute a flying kick, nor are you in combat anyway.", 40);
			}
			// kick triggering //
			else if (Array.subcontainment_case_ignored(input, Kick.triggers_subsafe) || Array.containment_case_ignored(Kick.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You extend your foot outward, kicking the air.", "You kick at the air.", "A fly meets its end on contact with your boot."}));
			}
			// elbow strike triggering //
			else if (Array.subcontainment_case_ignored(input, Elbow_Strike.triggers_subsafe) || Array.containment_case_ignored(Elbow_Strike.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "elbow strike"))		Type.delay_line(Random.text_of(new String[] {"Your elbow reaches out to jab the air.", "You pretend to pummel a mob in front of you."}));
				else		Type.delay_line("You don't know how to strike with your elbow, nor are you in combat anyway.", 40);
			}
			// pressure poke triggering //
			else if (Array.subcontainment_case_ignored(input, Pressure_Poke.triggers_subsafe) || Array.containment_case_ignored(Pressure_Poke.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "pressure poke"))		Type.delay_line(Random.text_of(new String[] {"Your finger jabs at the air.", "You shove your finger out in front of you. You fail to find a pressure point."}));
				else		Type.delay_line("You don't know how to execute a pressure poke, nor are you in combat anyway.", 40);
			}
			// upward palm triggering //
			else if (Array.subcontainment_case_ignored(input, Upward_Palm.triggers_subsafe) || Array.containment_case_ignored(Upward_Palm.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "upward palm"))		Type.delay_line(Random.text_of(new String[] {"Your palm strikes upward, parting air.", "You shove your upward palm at the air."}));
				else		Type.delay_line("You don't know how to execute an upward palm, nor are you in combat anyway.", 40);
			}
			// magic missile triggering //
			else if (Array.subcontainment_case_ignored(input, Magic_Missile.triggers_subsafe) || Array.containment_case_ignored(Magic_Missile.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "magic missile"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the magic missile spell, nor are you in combat anyway.", 40);
			}
			// fireblast triggering //
			else if (Array.subcontainment_case_ignored(input, Fireblast.triggers_subsafe) || Array.containment_case_ignored(Fireblast.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "fireblast"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the fireblast spell, nor are you in combat anyway.", 40);
			}
			// zap triggering //
			else if (Array.subcontainment_case_ignored(input, Zap.triggers_subsafe) || Array.containment_case_ignored(Zap.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "zap"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the zap spell, nor are you in combat anyway.", 40);
			}
			// static field triggering //
			else if (Array.subcontainment_case_ignored(input, Static_Field.triggers_subsafe) || Array.containment_case_ignored(Static_Field.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "static field"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the static field spell, nor are you in combat anyway.", 40);
			}
			// lightning bolt triggering //
			else if (Array.subcontainment_case_ignored(input, Lightning_Bolt.triggers_subsafe) || Array.containment_case_ignored(Lightning_Bolt.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "lightning bolt"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the lightning bolt spell, nor are you in combat anyway.", 40);
			}
			// disintegrate triggering //
			else if (Array.subcontainment_case_ignored(input, Disintegrate.triggers_subsafe) || Array.containment_case_ignored(Disintegrate.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "disintegrate"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the disintegration spell, nor are you in combat anyway.", 40);
			}
			// meteor triggering //
			else if (Array.subcontainment_case_ignored(input, Meteor.triggers_subsafe) || Array.containment_case_ignored(Meteor.triggers_nonsubsafe, input))
			{
				if (Array.containment(player.abilities, "meteor"))		Type.delay_line("You are not in combat.", 40);
				else		Type.delay_line("You don't know the meteor spell, nor are you in combat anyway.", 40);
			}
			// attack triggering //
			else if (Array.subcontainment_case_ignored(input, Attack.triggers_subsafe) || Array.containment_case_ignored(Attack.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"There is no mob to attack right now", "No enemy is nearby to attack"})+".", 40);
			}
			// flee triggering //
			else if (Array.subcontainment_case_ignored(input, Flee.triggers_subsafe) || Array.containment_case_ignored(Flee.triggers_nonsubsafe, input))
			{
				Type.delay_line(Random.text_of(new String[] {"You are not even in combat", "There's no "+Random.text_of(new String[] {"enemy", "mob"})+" to flee from"})+"!", 40);
			}
			// wake triggering //
			else if (Array.subcontainment_case_ignored(input, Wake.triggers_subsafe) || Array.containment_case_ignored(Wake.triggers_nonsubsafe, input))
			{
				Wake wake = new Wake(player, scenario, false);
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
			}
//  //  //   unrecognized/nontrigger error   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  
			else		Act.unrecognized_nontrigger_error();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////// finish /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// act again based on the same scenario if a new scenario (that would itself lead to an act) wasn't created by traveling //
			if (!traveled)
			{
				Wait.milliseconds(322);
				System.out.print("\n\n");
			
			
				World world = new World(player, scenario);
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
    }
}