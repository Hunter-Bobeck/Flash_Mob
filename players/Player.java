package players;

import utilities.*;
import acts_combat.*;
import acts.*;
import scenarios.*;

public class Player
{
	// control inputs //
	public static final String[] control_inputs = {"-1", "-2", "-3", "-4"};
	public static final int[] control_input_integers = {-1, -2, -3, -4};
	// control toggles //
	public boolean
	prompts_global_default,		// defaulting prompts globally //
	prompts_set_default,		// defaulting prompts in the set of player creation //
	playing = true,		// whether or not the player is playing still (if they aren't, then the game will restart) //
	initial_tavern = true;		// whether the environment is currently an initial tavern //
	
	
	
	
//// #class /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// feed options //
	private static final String[] classes = {"duelist", "monk", "mage"};
	// toggles //
	public boolean duelist, monk, mage;
	// specific abilities (for listing and unlockedness checking) and colloquial triggers of combat abilities (for combat act randomization) //
	public String[] abilities, class_combat_triggers_colloquial, unlearned_abilities;
	public String[][] unlearned_abilities_triggers_colloquial, unlearned_abilities_triggers_subsafe;
	public int[] unlearned_abilities_experience_costs;
	// combat abilities statement //
	public void list_abilities()
	{
		for (int i = 0; i < abilities.length; i++)
		{
			Type.delay("\t\t› "+abilities[i]);
		}		System.out.println();
	}
	// class, weapon, and combat abilities statement //
	public void describe_class(boolean setup)
	{
		Type.delay("You are a ", 40);
		if (duelist)		Type.delay("duelist equipped with a pair of swords", 40);
		else if (monk)		Type.delay("monk trained in unarmed combat", 40);
		else		Type.delay("mage capable of casting spells", 40);
		Type.delay(Random.text_of(new String[] {"; y", ". Y"})+"ou ", 40);
		if (setup)
		{
			if (duelist)		Type.delay("also ");
			Type.delay(Random.text_of(new String[] {"start", "start out", "begin"})+" with", 40);
		}
		else		Type.delay("have");
		if (duelist || monk)
		{
			if (setup)		Type.delay_line(" 3 more strength and agility in addition to the following combat abilities:", 40);
			else		Type.delay_line(" the following abilities:", 40);
		}
		else if (mage)
		{
			if (setup)		Type.delay_line(" the following basic combat spell..", 40);
			else		Type.delay_line(" the following spells:", 40);
		}
		list_abilities();
	}
	// class prompt //
	private void prompt_class()
	{
		Type.delay_line(Random.text_of(new String[] {"Are you a duelist, a monk, or a mage?", "Is your class mage, monk, or duelist?"}));
		String class_feed = Text.lower(Prompt.text_of_unexplanatory(Array.combine(classes, new String[] {"a duelist", "a monk", "a mage", "d", "du", "mo", "ma", "due", "mon", "mag", "duel", "dueli", "duelis", "martial arts", "unarmed", "magic"}, control_inputs)));
		if (Array.containment(control_inputs, class_feed))
		{
			if (class_feed.equals("-2"))		prompts_set_default = true;
			else if (class_feed.equals("-3"))		prompts_global_default = true;
			else if (class_feed.equals("-4"))		playing = false;
			if (playing)		class_feed = Random.text_of(classes);
		}
		if (playing)
		{
			// #duelist setup //
			if (class_feed.equals("duelist") || class_feed.equals("d") || class_feed.equals("du") || class_feed.equals("due") || class_feed.equals("duel") || class_feed.equals("dueli") || class_feed.equals("duelis"))
			{
				duelist = true;
				abilities = new String[] {"slash", "cut", "thrust"};
				class_combat_triggers_colloquial = Array.combine(Slash.triggers_colloquial, Cut.triggers_colloquial, Thrust.triggers_colloquial);
				unlearned_abilities = new String[] {"twist", "spin", "overhead", "double thrust", "flurry"};
				unlearned_abilities_triggers_colloquial = new String[][] {Twist.triggers_colloquial, Spin.triggers_colloquial, Overhead.triggers_colloquial, Double_Thrust.triggers_colloquial, Flurry.triggers_colloquial};
				unlearned_abilities_triggers_subsafe = new String[][] {Twist.triggers_subsafe, Spin.triggers_subsafe, Overhead.triggers_subsafe, Double_Thrust.triggers_subsafe, Flurry.triggers_subsafe};
				unlearned_abilities_experience_costs = new int[] {Twist.experience_cost, Spin.experience_cost, Overhead.experience_cost, Double_Thrust.experience_cost, Flurry.experience_cost};
			}
			// #monk setup //
			else if (class_feed.equals("monk") || class_feed.equals("mo") || class_feed.equals("mon") || class_feed.equals("martial arts") || class_feed.equals("unarmed"))
			{
				monk = true;
				abilities = new String[] {"whack", "punch", "kick"};
				class_combat_triggers_colloquial = Array.combine(Whack.triggers_colloquial, Punch.triggers_colloquial, Kick.triggers_colloquial);
				unlearned_abilities = new String[] {"elbow strike", "roundhouse kick", "pressure poke", "flying kick", "upward palm"};
				unlearned_abilities_triggers_colloquial = new String[][] {Elbow_Strike.triggers_colloquial, Roundhouse_Kick.triggers_colloquial, Pressure_Poke.triggers_colloquial, Flying_Kick.triggers_colloquial, Upward_Palm.triggers_colloquial};
				unlearned_abilities_triggers_subsafe = new String[][] {Elbow_Strike.triggers_subsafe, Roundhouse_Kick.triggers_subsafe, Pressure_Poke.triggers_subsafe, Flying_Kick.triggers_subsafe, Upward_Palm.triggers_subsafe};
				unlearned_abilities_experience_costs = new int[] {Elbow_Strike.experience_cost, Roundhouse_Kick.experience_cost, Pressure_Poke.experience_cost, Flying_Kick.experience_cost, Upward_Palm.experience_cost};
			}
			// #mage setup //
			else
			{
				mage = true;
				int basic_ability_randomization = Random.integer_from(1, 3);
				// #magic_missile setup //
				if (basic_ability_randomization == 1)
				{
					abilities = new String[] {"magic missile"};
					class_combat_triggers_colloquial = Array.copy(Magic_Missile.triggers_colloquial);
					unlearned_abilities = new String[] {"fireblast", "zap", "static field", "lightning bolt", "disintegrate", "meteor"};
					unlearned_abilities_triggers_colloquial = new String[][] {Fireblast.triggers_colloquial, Zap.triggers_colloquial, Static_Field.triggers_colloquial, Lightning_Bolt.triggers_colloquial, Disintegrate.triggers_colloquial, Meteor.triggers_colloquial};
					unlearned_abilities_triggers_subsafe = new String[][] {Fireblast.triggers_subsafe, Zap.triggers_subsafe, Static_Field.triggers_subsafe, Lightning_Bolt.triggers_subsafe, Disintegrate.triggers_subsafe, Meteor.triggers_subsafe};
					unlearned_abilities_experience_costs = new int[] {Fireblast.experience_cost, Zap.experience_cost, Static_Field.experience_cost, Lightning_Bolt.experience_cost, Disintegrate.experience_cost, Meteor.experience_cost};
				}
				// #zap setup //
				else if (basic_ability_randomization == 2)
				{
					abilities = new String[] {"zap"};
					class_combat_triggers_colloquial = Array.copy(Zap.triggers_colloquial);
					unlearned_abilities = new String[] {"magic missile", "fireblast", "static field", "lightning bolt", "disintegrate", "meteor"};
					unlearned_abilities_triggers_colloquial = new String[][] {Magic_Missile.triggers_colloquial, Fireblast.triggers_colloquial, Static_Field.triggers_colloquial, Lightning_Bolt.triggers_colloquial, Disintegrate.triggers_colloquial, Meteor.triggers_colloquial};
					unlearned_abilities_triggers_subsafe = new String[][] {Magic_Missile.triggers_subsafe, Fireblast.triggers_subsafe, Static_Field.triggers_subsafe, Lightning_Bolt.triggers_subsafe, Disintegrate.triggers_subsafe, Meteor.triggers_subsafe};
					unlearned_abilities_experience_costs = new int[] {Magic_Missile.experience_cost, Fireblast.experience_cost, Static_Field.experience_cost, Lightning_Bolt.experience_cost, Disintegrate.experience_cost, Meteor.experience_cost};
				}
				// #fireblast setup //
				else
				{
					abilities = new String[] {"fireblast"};
					class_combat_triggers_colloquial = Array.copy(Fireblast.triggers_colloquial);
					unlearned_abilities = new String[] {"magic missile", "zap", "static field", "lightning bolt", "disintegrate", "meteor"};
					unlearned_abilities_triggers_colloquial = new String[][] {Magic_Missile.triggers_colloquial, Zap.triggers_colloquial, Static_Field.triggers_colloquial, Lightning_Bolt.triggers_colloquial, Disintegrate.triggers_colloquial, Meteor.triggers_colloquial};
					unlearned_abilities_triggers_subsafe = new String[][] {Magic_Missile.triggers_subsafe, Zap.triggers_subsafe, Static_Field.triggers_subsafe, Lightning_Bolt.triggers_subsafe, Disintegrate.triggers_subsafe, Meteor.triggers_subsafe};
					unlearned_abilities_experience_costs = new int[] {Magic_Missile.experience_cost, Zap.experience_cost, Static_Field.experience_cost, Lightning_Bolt.experience_cost, Disintegrate.experience_cost, Meteor.experience_cost};
				}
			}
			if (duelist || monk)
			{
				strength_min += 3;
				strength_max += 3;
				agility_min += 3;
				agility_max += 3;
			}
			System.out.println();
			describe_class(true);
		}
		if (!prompts_global_default && !prompts_set_default)		System.out.println();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//// #gender ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final String[]
	// colloquial gender texts //
	m_genders_colloquial = {"♂", "male", "man", "lad", "feller", "bloke"},
	f_genders_colloquial = {"♀", "female", "miss", "lady"},
	both_genders_colloquial = Array.combine(m_genders_colloquial, f_genders_colloquial),
	gender_prompts = Array.permute(Array.combine
	(
		Array.permute(new String[] {"Are you "}, new String[] {"♂ or ♀", "male or female", "female or male", "a guy or a gal", "a lad or a lass", "a lad or a lady"}),
		Array.permute(new String[] {"What is your "}, new String[] {"gender", "sex", "gender", "sex", "gender", "sex"})
	), new String[] {"?"}),
	// all gender texts //
	m_genders = Array.combine
	(
		m_genders_colloquial,
		new String[] {"m", "mister", "guy", "boy", "b", "dude", "gentleman", "chap", "he", "his"}
	),
	f_genders = Array.combine
	(
		f_genders_colloquial,
		new String[] {"f", "woman", "gal", "girl", "g", "dudette", "lass", "babe", "she", "her", "hers"}
	),
	both_genders = Array.combine(m_genders, f_genders);
	// specific gender texts //
	public String[] genders, genders_colloquial;
	public String article, article_possessive;
	// gender description //
	public void describe_gender()
	{
		String gender_statement = Random.text_of(genders_colloquial);
		if (gender_statement.equals("♂")  ||  gender_statement.equals("♀")  ||  gender_statement.equals("male")  ||  gender_statement.equals("female")  &&  Random.whole() <= .85)
			Type.delay_wait_line("You are "+gender_statement+".", 322);
		else		Type.delay_wait_line(Random.text_of(new String[] {"You are a "+gender_statement+"."}), 322);
	}
	// gender prompt //
	private void prompt_gender()
	{
		String gender_feed;
		if (prompts_global_default || prompts_set_default)		gender_feed = Random.text_of(both_genders_colloquial);
		else
		{
			Type.delay_line(Random.text_of(gender_prompts));
			gender_feed = Text.lower(Prompt.text_of_unexplanatory(Array.combine(both_genders, control_inputs)));
			if (Array.containment(control_inputs, gender_feed))
			{
				if (gender_feed.equals("-2"))		prompts_set_default = true;
				else if (gender_feed.equals("-3"))		prompts_global_default = true;
				else if (gender_feed.equals("-4"))		playing = false;
				if (playing)		gender_feed = Random.text_of(both_genders_colloquial);
			}
		}
		if (playing)
		{
			if (Array.containment(m_genders, gender_feed.toLowerCase()))
			{
				genders = m_genders;
				genders_colloquial = m_genders_colloquial;
				article = "he";
				article_possessive = "his";
			}
			else if (Array.containment(f_genders, gender_feed.toLowerCase()))
			{
				genders = f_genders;
				genders_colloquial = f_genders_colloquial;
				article = "she";
				article_possessive = "her";
			}
			System.out.println();
			describe_gender();
		}
		System.out.println();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//// #name //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// specific name text //
	public String name;
	private static final String[]
	// random name components //
	generic_titles = {"Boss", "Chanter", "Prophet", "Slayer", "Warden", "Warlock"},
	female_first_names = {"Amber", "Belladonna", "Chastity", "Dawn", "Faint", "Ivy", "Jasmine", "Johanna", "Mistress", "Rain", "Ruby", "Sarah", "Sheol", "Valentine"},
	male_first_names = {"Albion", "Bedlam", "Brand", "Dain", "Edmund", "Griffon", "Hawke", "Jice", "Lance", "Leo", "Magus", "Master", "Maverick", "Quake"},
	last_names_prefix = {"Aeon", "Dark", "Demon", "Dread", "Eden", "Fate", "Fell", "Fire", "Honor", "King", "Lion", "Lord", "Mad", "Magic", "Peri", "Quasi", "Rune"},
	last_names_suffix = {"blood", "brew", "chanter", "chef", "claw", "dawn", "drinker", "fond", "grave", "guard", "hand", "heart", "hunter", "icon", "killer", "knight", "noble", "rouge", "seraph", "shadow", "soul", "spawn", "spirit", "steel", "tome", "twist"};
	// name randomizer //
	private String randomized_name()
	{
		if (Random.whole() <= .25)
			return Random.text_of(Array.permute(generic_titles, new String[] {" "}, last_names_prefix, last_names_suffix));
		else if (article.equals("he"))
			return Random.text_of(Array.permute(male_first_names, new String[] {" "}, last_names_prefix, last_names_suffix));
		else
			return Random.text_of(Array.permute(female_first_names, new String[] {" "}, last_names_prefix, last_names_suffix));
	}
//  //   #cheats   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  // 
	// trigger names //
	private static final String[] cheat_names = {"Regis", "Pwent", "Masoj", "Bruenor", "Brawnanvil", "Wulfgar", "Kipper", "Gromph", "Guenhwyvar", "Mooshie", "Drizzt", "Danica", "Entreri", "gladiator", "crawling claw", "thirsty", "hungry", "sleepy"};
	// toggles //
	public boolean Regis, Pwent, Masoj, Bruenor, Wulfgar, Guenhwyvar, Drizzt, Danica, Entreri, gladiator, crawling_claw;		// Brawnanvil, Kipper, Gromph, and Mooshie don't need one //
//  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
	// name description //
	public void describe_name(boolean during_creation)
	{
		String name_statement = null;
		if (Array.subcontainment_case_ignored(name, cheat_names))
		{
			name_statement = "YOU ARE "+Text.upper(name)+"!";
			if (name.contains("Regis") && name.contains("Pwent"))
			{
				if (during_creation)
				{
					Regis = true;		Pwent = true;
				}
				name_statement += " You only encounter mobs.";
			}
			else if (name.contains("Regis"))
			{
				if (during_creation)		Regis = true;
				name_statement += " You avoid all encounters.";
			}
			else if (name.contains("Pwent"))
			{
				if (during_creation)		Pwent = true;
				name_statement += " You always encounter mobs.";
			}
			if (name.contains("Masoj"))
			{
				if (during_creation)		Masoj = true;
				name_statement += " You have the worst values.";
			}
			if (name.contains("Bruenor"))
			{
				if (during_creation)		Bruenor = true;
				name_statement += " You have 1000 health.";
			}
			if (name.contains("Brawnanvil"))
			{
				if (during_creation)
				{
					health_exercise = health_exercise_frequency - 1;
					strength_exercise = strength_exercise_frequency - 1;
					agility_exercise = agility_exercise_frequency - 1;
				}
				name_statement += " You begin with almost enough exercise to upgrade your values.";
			}
			if (name.contains("Wulfgar"))
			{
				if (during_creation)		Wulfgar = true;
				name_statement += " You have 1000 strength.";
			}
			if (name.contains("Kipper"))
			{
				if (during_creation)
				{
					experience = 1000000;
					experience_total = 1000000;
				}
				name_statement += " You begin with 1000000 experience.";
			}
			if (name.contains("Gromph"))
			{
				if (during_creation)
				{
					if (duelist)
					{
						abilities = new String[] {"slash", "cut", "thrust", "twist", "spin", "overhead", "double thrust", "flurry"};
						class_combat_triggers_colloquial = Array.combine(Slash.triggers_colloquial, Cut.triggers_colloquial, Thrust.triggers_colloquial, Twist.triggers_colloquial, Spin.triggers_colloquial, Overhead.triggers_colloquial, Double_Thrust.triggers_colloquial, Flurry.triggers_colloquial);
						unlearned_abilities = new String[] {};
						unlearned_abilities_triggers_colloquial = new String[][] {};
						unlearned_abilities_triggers_subsafe = new String[][] {};
						unlearned_abilities_experience_costs = new int[] {};
					}
					else if (monk)
					{
						
						abilities = new String[] {"whack", "punch", "kick", "elbow strike", "roundhouse kick", "flying kick", "pressure poke", "upward palm"};
						class_combat_triggers_colloquial = Array.combine(Whack.triggers_colloquial, Punch.triggers_colloquial, Kick.triggers_colloquial, Elbow_Strike.triggers_colloquial, Roundhouse_Kick.triggers_colloquial, Flying_Kick.triggers_colloquial, Pressure_Poke.triggers_colloquial, Upward_Palm.triggers_colloquial);
						unlearned_abilities = new String[] {};
						unlearned_abilities_triggers_colloquial = new String[][] {};
						unlearned_abilities_triggers_subsafe = new String[][] {};
						unlearned_abilities_experience_costs = new int[] {};
					}
					else		// mage //
					{
						
						abilities = new String[] {"magic missile", "fireblast", "zap", "static field", "lightning bolt", "disintegrate", "meteor"};
						class_combat_triggers_colloquial = Array.combine(Magic_Missile.triggers_colloquial, Fireblast.triggers_colloquial, Zap.triggers_colloquial, Static_Field.triggers_colloquial, Lightning_Bolt.triggers_colloquial, Disintegrate.triggers_colloquial, Meteor.triggers_colloquial);
						unlearned_abilities = new String[] {};
						unlearned_abilities_triggers_colloquial = new String[][] {};
						unlearned_abilities_triggers_subsafe = new String[][] {};
						unlearned_abilities_experience_costs = new int[] {};
					}
				}
				name_statement += " You know all the abilities of your class.";
			}
			if (name.contains("Guenhwyvar"))
			{
				if (during_creation)		Guenhwyvar = true;
				name_statement += " All encountered mobs will already be dead.";
			}
			if (name.contains("Mooshie"))
			{
				if (during_creation)		initial_tavern = false;
				name_statement += " You begin in the "+Random.text_of(new String[] {"wild", "wilds", "wilderness"})+".";
			}
			if (name.contains("Drizzt"))
			{
				if (during_creation)		Drizzt = true;
				name_statement += " You always initiate combat and can always disengage successfully.";
			}
			if (name.contains("Danica"))
			{
				if (during_creation)		Danica = true;
				name_statement += " You have 1000 agility.";
			}
			if (name.contains("Entreri"))
			{
				if (during_creation)		Entreri = true;
				name_statement += " You always "+Random.text_of(new String[] {"have the initiative", "initiate combat", "strike first"})+".";
			}
			if (name.contains("gladiator") || name.contains("Gladiator"))
			{
				if (during_creation)
				{
					initial_tavern = false;
					gladiator = true;
				}
				name_statement += " You are always in the arena.";
			}
			if (name.contains("crawling claw") || name.contains("crawling Claw") || name.contains("Crawling claw") || name.contains("Crawling Claw"))
			{
				if (during_creation)
				{
					crawling_claw = true;
				}
				name_statement += " You begin with 1 health.";
			}
			if (name.contains("thirsty") || name.contains("Thirsty"))
			{
				if (during_creation)
				{
					wetness = 1;
				}
				name_statement += " You begin with 1 wetness.";
			}
			if (name.contains("hungry") || name.contains("Hungry"))
			{
				if (during_creation)
				{
					fullness = 1;
				}
				name_statement += " You begin with 1 fullness.";
			}
			if (name.contains("sleepy") || name.contains("Sleepy"))
			{
				if (during_creation)
				{
					rest = 1;
				}
				name_statement += " You begin with 1 rest.";
			}
		}
		else
		{
			String[] noncheat_name_statement_possibilities = Array.permute
			(
				new String[] {"You are "+name, "Your name is "+name, name+" is your name", "Your title is "+name, "You are named "+name, "You are known as "+name, "You go by "+name, "You call yourself "+name, "Others call you "+name},
				new String[] {".", "!"}
			);
			if (during_creation)
			{
				noncheat_name_statement_possibilities = Array.combine
				(
					noncheat_name_statement_possibilities,
					Array.permute
					(
						new String[] {"Greetings", "Welcome to my tavern", "Well met"},
						new String[] {", "+name+".", ", "+name+"!"}
					)
				);
			}
			name_statement = Random.text_of(noncheat_name_statement_possibilities);
		}
		Type.delay_wait_line(name_statement, 322);
	}
	// name prompt //
	private void prompt_name()
	{	
		if (prompts_global_default || prompts_set_default)		name = randomized_name();
		else
		{
			Type.delay_line(Random.text_of(new String[] {"Who are you?", "What is your name?", "What is your title?", "What are you named?", "Who are you known as?", "What name do you go by?", "What do you go by?", "What do you call yourself?", "What do others call you?"}));
			name = Text.capitalize(Prompt.text().trim());
			if (Array.containment(control_inputs, name))
			{
				if (name.equals("-2"))		prompts_set_default = true;
				else if (name.equals("-3"))		prompts_global_default = true;
				else if (name.equals("-4"))		playing = false;
				if (playing)		name = randomized_name();
			}
			if (playing)		System.out.println();
		}
		if (playing)		describe_name(true);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #health ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// health range //
	private static final int health_min = 10, health_max = 20;
	// specific healthy and current health values //
	public int healthy, health;
	// health unknown description control toggles //
	private boolean health_known, health_range_known = true;
	// health prompt //
	private void prompt_health()
	{	
		if (prompts_global_default || prompts_set_default)
		{
			healthy = Random.integer_from(health_min, health_max);
			health_range_known = false;
		}
		else
		{
			System.out.println();
			String random_health_word = Random.text_of(new String[] {"health", "constitution", "fortitude"});
			Type.delay("❤: "+Random.text_of(Array.permute(new String[] {"What is your "+random_health_word, "What is the measure of your "+random_health_word, "What does your "+random_health_word+" measure up to", "How much "+random_health_word+" do you have", "What is your "+random_health_word, "How much health do you bear", "How tough is your fortitude"}, new String[] {"?", ", "+name+"?"})));
			Type.delay_line("     ("+health_min+" ┄ "+health_max+")", 15);
			healthy = Prompt.integer_from_or_of(health_min, health_max, control_input_integers);
			if (healthy == -1  ||  healthy == -2  ||  healthy == -3  ||  healthy == -4)
			{
				if (healthy == -2)		prompts_set_default = true;
				else if (healthy == -3)		prompts_global_default = true;
				else if (healthy == -4)		playing = false;
				if (playing)		healthy = Random.integer_from(health_min, health_max);
			}
			else	health_known = true;
		}
		health = healthy;
	}
	// range-relative healthbar //
	public void display_relative_healthbar()
	{
		if (health <= health_max)
		{
			for (int bar_value = 2; bar_value <= health_max; bar_value += 2)
			{
				if (health  >=  bar_value)		Type.delay("▮", 2);
				else		Type.delay("▯", 2);
			}
			System.out.println();
		}
		else		Type.delay_line("▮...", 2);
	}
	// healthbar (healthy-relative) - unnewlined //
	public void display_healthbar_unnewlined()
	{
		if (health <= health_max)
		{
			for (int bar_value = 2; bar_value <= healthy; bar_value += 2)
			{
				if (health  >=  bar_value)		Type.delay("▮", 2);
				else		Type.delay("▯", 2);
			}
		}
		else		Type.delay("▮...", 2);
	}
	// healthbar (healthy-relative) //
	public void display_healthbar()
	{
		display_healthbar_unnewlined();
		System.out.println();
	}
	// health description (healthy-relative) //
	public void describe_health()
	{
		String[] health_statements;
		if ((double) health  /  (double) healthy   >=   3.0 / 4.0)
		{
			health_statements = new String[] {"You are feeling healthy! Your health is up at "+health+".", "You are in your prime at the moment, with "+health+" health.", "You are in your best shape! Your health is "+health+".", "Your health is up at "+health+".", "Your fortitude is at its max.", "You have well-being. Your health is "+health+"."};
		}
		else if ((double) health  /  (double) healthy   >=   2.0 / 3.0)
		{
			health_statements = new String[] {"You feel normal. Your health is "+health+".", "You are feeling capable. Your health is at a level of "+health+".", "You have medium fortitude remaining, with a health of "+health+".", "Your health sits at "+health+", a decent well-being."};
		}
		else if ((double) health  /  (double) healthy   >=   1.0 / 3.0)
		{
			health_statements = new String[] {"You are feeling weak. You have but "+health+" health.", "You feel worn out, with only "+health+" health remaining.", "You are exhausted. You have only "+health+" health.", "You are fatigued. You have just "+health+" health.", "Exhaustion is setting in. Your health is down to "+health+".", "Adventuring is getting the better of you... your health is at "+health+".", "You're in bad shape – your health is just "+health+".", "You cannot handle that much more exercise; you only have "+health+" health "+Random.text_of(new String[] {"left", "remaining"})+"."};
		}
		else
		{
			health_statements = new String[] {"Your health is critically low, at "+health+".", "You have nearly no fortitude, with your health at a level of just "+health+".", "Your well-being is dangerously lacking. You have barely "+health+" health!", "You are about to perish! Only "+health+" of your health is remaining!", "You feel as though you will not make it; only "+health+" of your health is left...", "You are having trouble staying conscious in your crippled condition of "+health+" health...", "You are not sure how much longer your "+health+" health will last you...", "You don't know how you survived the encounter you just came out of"+Random.text_of(new String[] {"!", ".", "..."})+" You have barely "+health+" health at this point"+Random.text_of(new String[] {"!", ".", "..."})};
		}
		Type.delay_line("❤: "+Random.text_of(health_statements));
		display_healthbar();
	}
	// weakening statement //
	public void weakening_statement(int weakening)
	{
		Type.delay_line("❤: "+Random.text_of(new String[] {"You have lost "+weakening+" health. Your health is now "+health+".", "You feel worse; your health has gone down by "+weakening+" to "+health+".", "Your well-being has gone down by "+weakening+", putting your health at "+health+".", "Your measure of health has decreased by "+weakening+" to "+health+".", "Your level of health has been lowered from "+(health + weakening)+" to "+health+".", "Your condition has deteriorated by "+weakening+". You are now at "+health+" health.", "Your well-being is weakened now to "+health+", instead of your previous "+(health + weakening)+"."}));
		display_healthbar();
	}
	// weakening //
	public void weaken(int weakening)
	{
		if (health - weakening  <  1)			weakening = health - 1;
		if (weakening > 0)
		{
			health -= weakening;
			weakening_statement(weakening);
		}
	}
	// trap weakening //
	public void trap_weaken(int weakening)
	{
		if (weakening > 0)
		{
			health -= weakening;
			Type.delay_line("❤: "+Random.text_of(new String[] {"Your injury has dropped your health by "+weakening+".", "Your health has gone down by "+weakening+" to "+health+".", "Your measure of health has decreased by "+weakening+" to "+health+".", "Your level of health has been lowered from "+(health + weakening)+" to "+health+".", "Your condition has deteriorated by "+weakening+". You now are at "+health+" health."}));
			display_healthbar();
		}
	}
	// recovery statement //
	public void recovery_statement(int recovery)
	{
		Type.delay_line("❤: "+Random.text_of(new String[] {"You have regained "+recovery+" health. Your health is now "+health+".", "You feel better; your health has gone up by "+recovery+" to "+health+".", "Your well-being has gone up by "+recovery+", putting your health at "+health+".", "Your measure of health has increased by "+recovery+" to "+health+".", "Your level of health has been raised from "+(health - recovery)+" to "+health+".", "Your condition has improved by "+recovery+". You are now at "+health+" health.", "Your well-being is better now, at "+health+" instead of your previous "+(health - recovery)+"."}));
		display_healthbar();
	}
	// recovery //
	public void recover(int recovery)
	{
		if (health + recovery  >  healthy)	recovery = healthy - health;
		if (recovery > 0)
		{
			health += recovery;
			recovery_statement(recovery);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #strength //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// strength range //
	private int strength_min = 8, strength_max = 16;
	// specific strength value //
	public int strength;
	// strength unknown description control toggles //
	private boolean goblin_strength, strength_known, strength_range_known = true;
	// strength prompt //
	private int prompt_strength()
	{
		String prompt = "💪: "+Random.text_of(Array.permute(new String[] {"How powerful is your strength", "How much strength are you capable of exerting", "How strong are your muscles", "How much strength can you muster", "What does your strength measure up to", "How much can you muscle", "What is the strongest you can muster", "What is your strength", "How many goblins can you lift"}, new String[] {"?", ", "+name+"?"}));
		if (prompt.contains("How many goblins can you lift"))
		{
			goblin_strength = true;
			int goblins_min = 1;
			int goblins_max = 7;
			int goblins_input;
			if (prompts_global_default || prompts_set_default)
			{
				goblins_input = Random.integer_from(goblins_min, goblins_max);
				strength_range_known = false;
			}
			else
			{
				System.out.println();
				Type.delay(prompt);
				Type.delay_line("     ("+goblins_min+" ┄ "+goblins_max+")", 15);
				goblins_input = Prompt.integer_from_or_of(goblins_min, goblins_max, control_input_integers);
				if (goblins_input == -1  ||  goblins_input == -2  ||  goblins_input == -3  ||  goblins_input == -4)
				{
					if (goblins_input == -2)		prompts_set_default = true;
					else if (goblins_input == -3)		prompts_global_default = true;
					else if (goblins_input == -4)		playing = false;
					if (playing)		goblins_input = Random.integer_from(goblins_min, goblins_max);
				}
			}
			return Function.line(goblins_min, strength_min, goblins_max, strength_max, goblins_input);
		}
		else
		{
			int strength_input;
			if (prompts_global_default || prompts_set_default)
			{
				strength_input = Function.line(health_min, strength_min, health_max, strength_max, health);
				strength_range_known = false;
			}
			else
			{
				System.out.println();
				Type.delay(prompt);
				Type.delay_line("     ("+strength_min+" ┄ "+strength_max+")", 15);
				strength_input = Prompt.integer_from_or_of(strength_min, strength_max, control_input_integers);
				if (strength_input == -1  ||  strength_input == -2  ||  strength_input == -3  ||  strength_input == -4)
				{
					if (strength_input == -2)		prompts_set_default = true;
					else if (strength_input == -3)		prompts_global_default = true;
					else if (strength_input == -4)		playing = false;
					if (playing)		strength_input = Function.line(health_min, strength_min, health_max, strength_max, health);
				}
				else	strength_known = true;
			}
			return strength_input;
		}
	}
	// range-relative strengthbar //
	private void display_relative_strengthbar()
	{
		if (strength <= strength_max)
		{
			for (int bar_value = 2; bar_value <= strength_max; bar_value += 2)
			{
				if (strength  >=  bar_value)		Type.delay("▮", 2);
				else		Type.delay("▯", 2);
			}
			System.out.println();
		}
		else		Type.delay_line("▮...", 2);
	}
	// strengthbar (nonrelative) //
	public void display_strengthbar()
	{
		if (strength >= 1000)
		{
			Type.delay_line("▮...", 2);
		}
		else
		{
			for (int bar_value = 2; bar_value <= strength; bar_value += 2)
			{
				Type.delay("▮", 2);
			}
			System.out.println();
		}
	}
	// strength description (nonrelative) //
	public void describe_strength()
	{
		Type.delay_line("💪: "+Random.text_of(new String[] {"You have "+strength+" strength", "Your strength measures up to "+strength})+".");
		display_strengthbar();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #fortitude /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// fortitude range //
	public static final int fortitude_min = 10, fortitude_max = 20;
	// specific fortitude value //
	public int fortitude;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #agility ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// agility range //
	public int agility_min = 10, agility_max = 20;
	// specific agility value //
	public int agility;
	// agility unknown description control toggles //
	private boolean agility_known, agility_range_known = true;
	// agility prompt //
	private int prompt_agility()
	{		
		int agility_input;
		if (prompts_global_default || prompts_set_default)
		{	
			agility_input = Random.integer_from(agility_min, agility_max);
			agility_range_known = false;
		}
		else
		{
			System.out.println();
			Type.delay("👣: "+Random.text_of(Array.permute(new String [] {"How much agility do you have", "How agile are you", "What is your agility", "What is your level of agility", "How quick are you", "How good are your reflexes", "How fast are your reactions", "How fast are you able to react", "How quickly can you react"}, new String[] {"?", ", "+name+"?"})));
			Type.delay_line("     ("+agility_min+" ┄ "+agility_max+")", 15);
			agility_input = Prompt.integer_from_or_of(agility_min, agility_max, control_input_integers);
			if (agility_input == -1  ||  agility_input == -2  ||  agility_input == -3  ||  agility_input == -4)
			{
				if (agility_input == -2)		prompts_set_default = true;
				else if (agility_input == -3)		prompts_global_default = true;
				else if (agility_input == -4)		playing = false;
				if (playing)		agility_input = Random.integer_from(agility_min, agility_max);
			}
			else	agility_known = true;
		}
		return agility_input;
	}
	// range-relative agilitybar //
	private void display_relative_agilitybar()
	{
		if (agility <= agility_max)
		{
			for (int bar_value = 2; bar_value <= agility_max; bar_value += 2)
			{
				if (agility  >=  bar_value)		Type.delay("▮", 2);
				else		Type.delay("▯", 2);
			}
			System.out.println();
		}
		else		Type.delay_line("▮...", 2);
	}
	// agilitybar (nonrelative) //
	public void display_agilitybar()
	{
		if (agility >= 1000)
		{
			Type.delay_line("▮...", 2);
		}
		else
		{
			for (int bar_value = 2; bar_value <= agility; bar_value += 2)
			{
				Type.delay("▮", 2);
			}
			System.out.println();
		}
	}
	// agility description (nonrelative) //
	public void describe_agility()
	{
		Type.delay_line("👣: "+Random.text_of(new String[] {"You have "+agility+" agility", "Your agility measures up to "+agility})+".");
		display_agilitybar();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #block /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// specific block value //
	public int block;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #creation //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Player()
	{
//////// player setup ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// class setup //
		prompt_class();
		// gender setup //
		if (playing)		prompt_gender();
		//////////////////
		// name input //
		if (playing)		prompt_name();
		////////////////
		// health setup //
		if (playing && !Bruenor && !Masoj &&!crawling_claw)		prompt_health();
		else if (playing && Bruenor)		health = 1000;
		else if (playing && crawling_claw)		health = 1;
		else if (playing && Masoj)		health = health_min;
		if (health_known)		display_relative_healthbar();
		//////////////////
//  //   damage setup   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
		// strength setup //
		if (playing && !Wulfgar && !Masoj)		strength = prompt_strength();
		else if (playing && Wulfgar)		strength = 1000;
		else if (playing && Masoj)		strength = strength_min;
		if (strength_known)		display_relative_strengthbar();
		////////////////////
//  \\   block setup   \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\
		// fortitude setup //
		fortitude = Function.line(health_min, fortitude_min, health_max, fortitude_max, health);
		/////////////////////
		// agility setup //
		if (playing && !Danica && !Masoj)		agility = prompt_agility();
		else if (playing && Masoj)		agility = agility_min;
		if (agility_known)		display_relative_agilitybar();
		///////////////////
		else if (playing && Danica)		agility = 1000;
//  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  /
		block = agility + fortitude;
//  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \\  \
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// potential range-relative descriptions //
		if (playing)
		{
			Wait.milliseconds(250);
			// potential range-relative health description //
			if (!health_known)
			{
				System.out.println();
				String[] health_statements;
				if (health - health_min   >=   (health_max - health_min)  *  3.0 / 4.0)
				{
					health_statements = new String[] {"Your constitution is considerable, a high "+health+".", "You look to be in excellent shape! Your health is "+health+".", "A strong "+health+" health lends capability to your character.", "You appear to be a work of art with a health of "+health+"!", "Your health is "+health+". You have great fortitude!", "You are formidable! You have "+health+" health.", "Your constitution of "+health+" is impressive.", "Your health is "+health+", making you quite tough."};
				}
				else if (health - health_min   >=   (health_max - health_min)  *  2.0 / 5.0)
				{
					health_statements = new String[] {"Your constitution of "+health+" is average.", "You are capable. Your fortitude is leveled at "+health+".", "You have medium constitution, with a health of "+health+".", "Your health sits at the regular value of "+health+"."};
				}
				else if (health - health_min   >=   (health_max - health_min)  *  1.0 / 8.0)
				{
					health_statements = new String[] {"Your constitution is not very capable; it is just "+health+".", "You get worn out easily with only "+health+" health.", "You have only "+health+" health.", "Your fortitude is a limited "+health+".", "Your "+health+" constitution is minor.", "You don't have much endurance... your health is "+health+".", "You only have a constitution of "+health+"."};
				}
				else
				{
					health_statements = new String[] {"Your fortitude matches that of a scrawny goblin.", "You have nearly no fortitude – merely "+health+".", "Your constitution measures at a mere "+health+".", "You are frail. Your endurance is "+health+".", "You are hardly capable with only "+health+" health.", "You seriously lack a constitution, having only "+health+" health.", "You have barely enough health ("+health+") to be adventuring!"};
				}
				Type.delay("❤: "+Random.text_of(health_statements));
				if (!health_range_known)		Type.delay_wait("     ("+health_min+" ┄ "+health_max+")", 15, 322);
				System.out.println();
				display_relative_healthbar();
			}
			// potential range-relative strength description //
			if (!strength_known)
			{
				System.out.println();
				String[] strength_statements;
				if (strength - strength_min   >=   (strength_max - strength_min)  *  13.0 / 16.0)
				{
					strength_statements = new String[] {"Your strength is powerful, at "+strength+"!", "You are capable of exerting a whopping "+strength+" strength!", "Your muscles are as strong as "+strength+".", "You can muster as much as "+strength+" strength."};
				}
				else if (strength - strength_min   >=   (strength_max - strength_min)  *  1.0 / 3.0)
				{
					strength_statements = new String[] {"Your strength measures up to "+strength+".", "You are capable of exerting a moderate "+strength+" strength.", "You can muscle a maximum of "+strength+" strength.", "The strongest you can muster is "+strength+" strength."};
				}
				else
				{
					strength_statements = new String[] {"Your strength is weak; it is just "+strength+".", "You are capable of exerting barely "+strength+" strength.", "You can muster just "+strength+" strength.", "The strongest you can muscle is only "+strength+" strength."};
				}
				Type.delay("💪: "+Random.text_of(strength_statements));
				if (goblin_strength || !strength_range_known)		Type.delay_wait("     ("+strength_min+" ┄ "+strength_max+")", 15, 322);
				System.out.println();
				display_relative_strengthbar();
			}
			// potential range-relative agility description //
			if (!agility_known)
			{
				System.out.println();
				String[] agility_statements;
				if (agility - agility_min   >=   (agility_max - agility_min)  *  2.0 / 3.0)
				{
					agility_statements = new String[] {"You are quite agile. Your agility is "+agility+".", "You are as agile as "+agility+"!", "You are really quick, with an agility of "+agility+".", "You have fast reactions. Your agility is "+agility+"!"};
				}
				else if (agility - agility_min   >=   (agility_max - agility_min)  *  1.0 / 3.0)
				{
					agility_statements = new String[] {"You have "+agility+" agility.", "Your agility is "+agility+".", "Your agility is leveled at "+agility+".", "Your reactions are average; you have "+agility+" agility."};
				}
				else
				{
					agility_statements = new String[] {"You are not very agile; you have just "+agility+" agility.", "Your agility is a slow "+agility+".", "You are not very quick; you have only "+agility+" agility.", "Your reactions are quite slow. You have but "+agility+" agility."};
				}
				Type.delay("👣: "+Random.text_of(agility_statements));
				if (!agility_range_known)		Type.delay_wait("     ("+agility_min+" ┄ "+agility_max+")", 15, 322);
				System.out.println();
				display_relative_agilitybar();
			}
		}
		System.out.println();
		// water + food containers and tent + bedroll statement //
		if (playing)
		{
			Type.delay("You have food rations, a waterskin, a tent, and a bedroll in your "+Random.text_of(new String[] {"pack", "inventory"}), 40);
			if (duelist)		Type.delay("; you are equipped with your pair of swords", 40);
			else if (mage)		Type.delay("; you also carry a book of combat spells", 40);
			Type.delay_line(".", 40);
		}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// #thought creation //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Player(Player player)
	{
		/*duelist = player.duelist;
		monk = player.monk;
		mage = player.mage;
		abilities = Array.copy(player.abilities);
		class_combat_triggers_colloquial = Array.copy(player.class_combat_triggers_colloquial);
		unlearned_abilities = Array.copy(player.unlearned_abilities);
		unlearned_abilities_triggers_colloquial = Array.copy(player.unlearned_abilities_triggers_colloquial);
		unlearned_abilities_triggers_subsafe = Array.copy(player.unlearned_abilities_triggers_subsafe);
		unlearned_abilities_experience_costs = Array.copy(player.unlearned_abilities_experience_costs);
		genders = Array.copy(player.genders);
		genders_colloquial = Array.copy(player.genders_colloquial);
		article = player.article;
		article_possessive = player.article_possessive;
		name = player.name;
		Regis = player.Regis;
		Pwent = player.Pwent;
		Masoj = player.Masoj;
		Bruenor = player.Bruenor;
		Wulfgar = player.Wulfgar;
		Guenhwyvar = player.Guenhwyvar;
		Drizzt = player.Drizzt;
		Entreri = player.Entreri;
		gladiator = player.gladiator;
		healthy = player.healthy;
		health = player.health;
		health_known = true;
		health_range_known = true;
		strength_min = player.strength_min;
		strength_max = player.strength_max;
		strength = player.strength;
		goblin_strength = player.goblin_strength;
		strength_known = true;
		strength_range_known = true;
		fortitude = player.fortitude;
		agility_min = player.agility_min;
		agility_max = player.agility_max;
		agility = player.agility;
		agility_known = true;
		agility_range_known = true;
		block = player.block;
		water = Random.integer_from(7, 11);
		wetness = Random.integer_from(quenched - 2, quenched);
		food = Random.integer_from(7, 11);
		fullness = Random.integer_from(full - 2, full);
		int random_extra_experience = Random.integer_from(0, 999);
		experience = player.experience + random_extra_experience;
		experience_total = player.experience_total + random_extra_experience + Random.integer_from(999, 9999);*/
	}
//// #inventory /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// inventory description //
	public void describe_inventory()
	{
		Type.delay("You have a tent and a bedroll in your "+Random.text_of(new String[] {"pack", "inventory"}));
		if (duelist)		Type.delay("; you are equipped with a pair of swords");
		else if (mage)		Type.delay("; you also carry a book of combat spells");
		Type.delay_line(".");
		describe_food();
		describe_water();
	}
	// inventory display //
	public void display_inventory()
	{
		/* cool gridded inventory */
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #water /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// water and wetness maxes //
	public static final int water_max = 15, quenched = 6;
	// specific food and fullness setup //
	public int water = Random.integer_from(7, 11), wetness = quenched;
	// max-relative waterbar //
	public void display_waterbar()
	{
		for (int bar_value = 1; bar_value <= water_max; bar_value += 1)
		{
			if (water  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
		System.out.println();
	}
	// water description //
	public void describe_water()
	{
		String item_plural = Random.text_of(Drink.items_plural);
		String placement = Random.text_of(new String[] {"with you", "remaining", "left", Random.text_of(new String[] {"", "remaining ", "remaining ", "left "})+"in your waterskin"});
		if (water == 0)		Type.delay_line(Random.text_of(new String[] {"You have no "+Random.text_of(new String[] {"", "more "})+item_plural+" "+placement, "You are out of water", "You have "+Random.text_of(new String[] {"drank all of the water in", "quaffed"+Random.text_of(new String[] {"", "", " the rest of the sips in"})})+" your waterskin"})+".");
		else
		{
			Type.delay("💧: ");
			if (water == 1)		Type.delay_line("You have only one "+Random.text_of(new String[] {"sip of water", "drink of water", "drop of water"})+" "+placement);
			else if (water < water_max)		Type.delay_line("You have "+water+" water "+placement+".");
			else		Type.delay_line(Random.text_of(new String[] {"You have as much water with you as your waterskin can hold", "Your waterskin is "+Random.text_of(new String[] {"full", "filled all the way", "filled to the brim"})})+".");
		}
		display_waterbar();
	}
	// quenched-relative wetnessbar //
	public void display_wetnessbar()
	{
		for (int bar_value = 1; bar_value <= quenched; bar_value += 1)
		{
			if (wetness  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
		System.out.println();
	}
	// dehyrdation statements //
	private static final String[] dehydration_statements = Array.permute(new String[] {"You are parched", "You are dehydrated", "You feel dizzy with dehydration", "Your throat is painfully dry", "You really need to drink", "You are very thirsty", "You need to chug some water right this instant"}, new String[] {"!", "."});
	// thirst description //
	public void describe_thirst()
	{
		String thirsty_or_dry = Random.text_of(new String[] {"thirsty", "thirsty", "dry"});
		Type.delay("💧: ");
		if (wetness == 0)		Type.delay_line(Random.text_of(dehydration_statements));
		else if ((double) wetness / (double) quenched  <=  2.0 / 5.0)		Type.delay_line(Random.text_of(new String[] {"You are quite "+thirsty_or_dry, "You are moderately "+thirsty_or_dry})+Random.text_of(new String[] {"", " right now"})+".");
		else if (wetness < quenched)		Type.delay_line(Random.text_of(new String[] {"You are a bit", "You are slightly", "You are a little bit"})+" "+thirsty_or_dry+".");
		else		Type.delay_line(Random.text_of(new String[] {"You are quenched", "You are not thirsty"})+Random.text_of(new String[] {"", " right now"})+".");
		display_wetnessbar();
	}
	// thirsting //
	public void thirst()
	{
		if (wetness > 0)
		{
			wetness -= 1;		// if the player's wetness is greater than 0, diminish it by 1 //
			if (wetness < 2  ||  (wetness == 2  &&  Random.whole() <= .8))
			{
				System.out.print("\n\n");
				
				
				if (wetness > 0)		Type.delay_line(Random.text_of(new String[] {"You feel a scratchiness in your throat.", "You cough", "Your throat is getting dry", "You are becoming a bit thirsty", "You will need to drink some water soon"})+".");
				else		Type.delay_line(Random.text_of(dehydration_statements));
			}
		}
		else		// otherwise, type a thirst statement and weaken the player by 1 //
		{
			System.out.print("\n\n");


			health -= 1;
			if (health > 0)
			{
				Type.delay_line(Random.text_of(dehydration_statements));
				weakening_statement(1);
			}
		}
		if (health == 0)		die_of_thirst();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #food //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// food and fullness maxes //
	public static final int food_max = 15, full = 7;
	// specific food and fullness setup //
	public int food = Random.integer_from(7, 11), fullness = full;
	// max-relative foodbar //
	public void display_foodbar()
	{
		for (int bar_value = 1; bar_value <= food_max; bar_value += 1)
		{
			if (food  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
		System.out.println();
	}
	// food description //
	public void describe_food()
	{
		String item_plural = Random.text_of(Eat.items_plural);
		String placement = Random.text_of(new String[] {"with you", "with you", "with you", "on hand", "on hand", "on hand", "remaining", "left", Random.text_of(new String[] {"", "remaining ", "left "})+"in your pack"});
		if (food == 0)
			Type.delay_line(Random.text_of(new String[] {"You have no "+Random.text_of(new String[] {"", "more "})+Random.text_of(new String[] {item_plural, "food"})+" "+placement, "You are out of "+item_plural, "You have "+Random.text_of(new String[] {"eaten", "devoured", "eaten up", "made disappear", "munched up", "finished"})+" all of the "+item_plural+" in your pack"})+".");
		else
		{
			Type.delay(Random.text_of(new String[] {"🍞", "🍖", "🍏"})+": ");
			if (food == 1)		Type.delay_line("You have only one "+Random.text_of(new String[] {"edible", "piece of food", "food item", "ration", "snack"})+" "+placement);
			else if (food < food_max)		Type.delay_line("You have "+food+" "+item_plural+" "+placement+".");
			else		Type.delay_line(Random.text_of(new String[] {"You have as many "+item_plural+" with you as you can carry", "Your pack is "+Random.text_of(new String[] {"full of", "stuffed with", "jammed with"})+" "+Random.text_of(new String[] {item_plural, "food"})})+".");
		}
		display_foodbar();
	}
	// full-relative fullnessbar //
	public void display_fullnessbar()
	{
		for (int bar_value = 1; bar_value <= full; bar_value += 1)
		{
			if (fullness  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
		System.out.println();
	}
	// starvation statements //
	private static final String[] starvation_statements = Array.permute(new String[] {"You are quite hungry", "You are starving", "You are ravenous", "You feel lightheaded with hunger", "You are famished", "You really need to eat", "You need to satisfy your hunger right now"}, new String[] {"!", "."});
	// hunger description //
	public void describe_hunger()
	{
		Type.delay("🍽: ");
		if (fullness == 0)		Type.delay_line(Random.text_of(starvation_statements));
		else if ((double) fullness / (double) full  <=  2.0 / 5.0)		Type.delay_line(Random.text_of(new String[] {"You are quite hungry", "You are moderately hungry"})+Random.text_of(new String[] {"", " right now"})+".");
		else if (fullness < full)		Type.delay_line(Random.text_of(new String[] {"You are a bit peckish", "You are slightly hungry", "You are a little bit hungry"})+".");
		else		Type.delay_line(Random.text_of(new String[] {"You are full", "You are not hungry"})+Random.text_of(new String[] {"", " right now"})+".");
		display_fullnessbar();
	}
	// hungering //
	public void hunger()
	{
		if (fullness > 0)
		{
			fullness -= 1;		// if the player's fullness is greater than 0, diminish it by 1 //
			if (fullness < 2  ||  (fullness == 2  &&  Random.whole() <= .75))
			{
				System.out.print("\n\n");


				if (fullness > 0)		Type.delay_line(Random.text_of(new String[] {"Your belly rumbles", "You will need to eat soon", "You are getting hungry"})+".");
				else		Type.delay_line(Random.text_of(starvation_statements));
			}
		}
		else		// otherwise, type a hunger statement and weaken the player by 1 //
		{
			System.out.print("\n\n");


			health -= 1;
			if (health > 0)
			{
				Type.delay_line(Random.text_of(starvation_statements));
				weakening_statement(1);
			}
		}
		if (health == 0)		die_of_hunger();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #rest /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// rest max //
	public static final int rested = 10;
	// specific rest setup //
	public int rest = rested;
	// rested-relative restbar //
	public void display_restbar()
	{
		for (int bar_value = 1; bar_value <= rested; bar_value += 1)
		{
			if (rest  >=  bar_value)		Type.delay("▮", 2);
			else		Type.delay("▯", 2);
		}
		System.out.println();
	}
	// rest description //
	public void describe_rest(boolean show_restbar)
	{
		if (rest == 0)
			Type.delay_line(Random.text_of(new String[] {"You feel like a zombie", "You really need to sleep", "You desperately need rest", "You can barely keep your eyes open", "Your head aches with sleeplessness", "You're going to fall over if you don't find some rest soon"})+".");
		else if (rest == 1)		Type.delay_line(Random.text_of(new String[] {"You really need some sleep", "You are very low on energy", "Your eyelids are getting droopy", "You really need some rest", "You feel the need to lie down and sleep"})+".");
		else if (rest == 2)		Type.delay_line(Random.text_of(new String[] {"You are starting to get sleepy", "You are fairly low on energy", "You could use some rest", "Rest appeals to your senses", "You're getting low on energy"})+".");
		else if (rest <= 5)		Type.delay_line(Random.text_of(new String[] {"You wouldn't mind taking a longer break", "You have a moderate amount of energy", "You don't feel the need to sleep quite yet", "Your energy reserve will last for some time", "You feel somewhat rested"})+".");
		else if (rest < rested - 1)		Type.delay_line(Random.text_of(new String[] {"You have a decent amount of energy", "You don't feel sleepy", "Your energy reserve will last for a while", "You feel decently rested"})+".");
		else if (rest == rested - 1)		Type.delay_line(Random.text_of(new String[] {"You don't really need any more rest", "You have plenty of energy", "You are pretty well rested", "You feel decently rested"})+".");
		else		Type.delay_line(Random.text_of(new String[] {"You are fully rested", "You are full of energy", "You have rested well", "You are feeling energetic", "You don't need any rest now"})+".");
		if (show_restbar)		display_restbar();
	}
	// tiring //
	public void tire()
	{
		if (rest > 0)
		{
			rest -= 1;		// if the player's rest is greater than 0, diminish it by 1 and describe their rest //
			if (rest < 2  ||  (rest == 2  &&  Random.whole() <= .825))
			{
				System.out.print("\n\n");
				
				
				describe_rest(false);
			}
		}
		else		// otherwise, describe the player's rest and weaken them by 1 //
		{
			System.out.print("\n\n");
			
			
			health -= 1;
			if (health > 0)
			{
				describe_rest(false);
				weakening_statement(1);
			}
		}
		if (health == 0)		die_of_tiredness();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//// #exercise //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// health exercise progress value //
	private int health_exercise = 0, health_exercise_frequency = 60;
	// health exercising //
	public void exercise_health()
	{
		if (health < health_max)		health_exercise += Random.integer_from(1, 5);
	}
	// health upgrading //
	public void upgrade_health()
	{
		if (health_exercise / health_exercise_frequency  ==  1)
		{
			health_exercise -= health_exercise_frequency;
			health++;
			fortitude = Function.line(health_min, fortitude_min, health_max, fortitude_max, health);
			block = agility + fortitude;
			Type.delay_line("Your health has improved to "+health+".");
		}
	}
	// strength exercise progress value //
	private int strength_exercise = 0, strength_exercise_frequency = 60;
	// strength exercising //
	public void exercise_strength(int possible)
	{
		if (strength  <  (strength_max * 2) + Random.integer_from(-2, 3))		strength_exercise += Random.integer_from(1, possible);
	}
	// strength upgrading //
	public void upgrade_strength()
	{
		if (strength_exercise / strength_exercise_frequency  ==  1)
		{
			strength_exercise -= strength_exercise_frequency;
			strength++;
			Type.delay_line("Your strength has improved to "+strength+".");
		}
	}
	// agility exercise progress value //
	private int agility_exercise = 0, agility_exercise_frequency = 60;
	// agility exercising //
	public void exercise_agility(int possible)
	{
		if (agility  <  (agility_max * 2) + Random.integer_from(-4, 3))				agility_exercise += Random.integer_from(1, possible);
	}
	// agility upgrading //
	public void upgrade_agility()
	{
		if (agility_exercise / agility_exercise_frequency  ==  1)
		{
			agility_exercise -= agility_exercise_frequency;
			agility++;
			block = agility + fortitude;
			Type.delay_line("Your agility has improved to "+agility+".");
		}
	}
	// duelist combat exercising //
	public void exercise_duelist()
	{
		exercise_strength(Random.integer_from(1, 5));
		exercise_agility(Random.integer_from(1, 5));
	}
	// monk combat exercising //
	public void exercise_monk()
	{
		exercise_strength(Random.integer_from(1, 5));
		exercise_agility(Random.integer_from(1, 5));
	}
	// mage combat exercising //
	public void exercise_mage()
	{
		exercise_agility(Random.integer_from(1, 3));
	}
	// (peaceful) exercising //
	public void exercise()
	{
		exercise_health();
		upgrade_health();
		exercise_strength(Random.integer_from(1, 5));
		upgrade_strength();
		exercise_agility(Random.integer_from(1, 5));
		upgrade_agility();
		tire();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #damage ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int weapon_upgrades = 0;		/* accounts for loot-found better swords swapped out for (a makeshift way of having different swords for now) */
	// pure damage rolling - sword //
	public int roll_damage_pure_weapon()		/* accounts for the sword damage modifier (have it that actually exist, accessed by calling whatever weapon is equipped, later) */
	{
		// if the player is a duelist, include the experience damage boost //
		if (duelist)		return Random.integer_to(10) + experience_damage_boost() + Random.integer_from(1, 16  +  (int) ((double) (strength + agility)  /  2.0)) + weapon_upgrades;
		// otherwise, don't include the experience damage boost //
		else		return Random.integer_to(10) + Random.integer_from(1, 16  +  (int) ((double) (strength + agility)  /  2.0));		/* relevant once weapons can be acquired, and thus used by nonduelists */
	}
	// pure damage rolling - unarmed //
	public int roll_damage_pure_unarmed()
	{
		if (monk)		return Random.integer_to(10) + experience_damage_boost() + Random.integer_from(1, strength + agility);
		else		return Random.integer_to(10) + Random.integer_from(1, (int) ((double) (strength + agility)  /  2.0));
	}
	// pure damage rolling - magical //
	public int roll_damage_pure_magical()
	{
		return Random.integer_to(10) + experience_damage_boost() + Random.integer_from(1, 32);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//// #experience ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// specific experience and total experience values //
	public int experience = 0, experience_total = 0;
	// experience description //
	public void describe_experience()
	{
		Type.delay_line(Random.text_of(new String[]
		{
			"You have "+experience+" points of experience to spend. Your total experience is "+experience_total,
			"You have "+experience+" points of recent experience out of your entire "+experience_total+" experience so far",
			"You have "+experience+" experience to train with, and "+experience_total+" overall",
			"You have "+experience_total+" total experience, "+experience+" of which you have not yet spent on training"
		})+".");
	}
	// experience damage boost //
	public int experience_damage_boost()
	{
		return (int) ((double) experience_total  /  400.0);
	}
	// experience gaining //
	public void gain_experience(int experience_gain)
	{
		experience += experience_gain;
		experience_total += experience_gain;
		String[] possible_experience_statements = Array.permute
		(
			new String[] {"You "},
			new String[] {"now have "+experience_gain+" more", "have gained "+experience_gain},
			new String[] {" experience"}
		);
		if (experience - experience_gain  >  0)
		{
			possible_experience_statements = Array.permute(possible_experience_statements, new String[] {" (to have "+experience_total+" total)", "."});
			possible_experience_statements = Array.combine(possible_experience_statements, new String[] {"Your experience has gone up by "+experience_gain+" to "+experience+".", "You now have "+experience_total+" total experience."});
		}
		else		possible_experience_statements = Array.permute(possible_experience_statements, new String[] {"."});
		Type.delay_line("⇪: "+Random.text_of(possible_experience_statements));
	}
	// ability learning //
	public void learn_ability(int unlearned_ability_index)
	{
		experience -= unlearned_abilities_experience_costs[unlearned_ability_index];
		abilities = Array.combine(abilities, new String[] {unlearned_abilities[unlearned_ability_index]});
		class_combat_triggers_colloquial = Array.combine(class_combat_triggers_colloquial, unlearned_abilities_triggers_colloquial[unlearned_ability_index]);
		unlearned_abilities = Array.index_removed(unlearned_abilities, unlearned_ability_index);
		unlearned_abilities_triggers_subsafe = Array.index_removed(unlearned_abilities_triggers_subsafe, unlearned_ability_index);
		unlearned_abilities_experience_costs = Array.index_removed(unlearned_abilities_experience_costs, unlearned_ability_index);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
//// #death /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void die_of_thirst()
	{
		playing = false;
		Type.delay_line("💧: "+Random.text_of(new String[] {"YOU ARE "+Random.text_of(new String[] {"", "NOW "})+"DEAD", "YOU HAVE DIED OF DEHYDRATION"}));
		System.out.println();
		
	}
	public void die_of_hunger()
	{
		playing = false;
		Type.delay_line("🍽: "+Random.text_of(new String[] {"YOU ARE "+Random.text_of(new String[] {"", "NOW "})+"DEAD", "YOU HAVE DIED OF STARVATION"}));
		System.out.println();
		
	}
	public void die_of_tiredness()
	{
		playing = false;
		Type.delay_line(Random.text_of(new String[] {"YOU ARE "+Random.text_of(new String[] {"", "NOW "})+"DEAD", "YOU HAVE DIED "+Random.text_of(new String[] {"OF TIREDNESS", "BY TIRING"})}));
		System.out.println();
		
	}
	public void die_to_mob(String species_uppercase)
	{
		playing = false;
		Type.delay_line("𒉑 "+Random.text_of(new String[] {"YOU ARE "+Random.text_of(new String[] {"", "NOW "})+"DEAD", "YOU HAVE BEEN SLAIN BY THE "+species_uppercase, "THE "+species_uppercase+" HAS KILLED YOU"})+" 𒈉");
		System.out.println();
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}