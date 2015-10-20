package scenarios;

import utilities.*;
import environments.*;
import environments_commons_interiors_complexes_undergrounds.*;
import environments_commons_specials_complexes_withins.*;
import environments_commons_exteriors_basics.*;
import environments_commons_interiors_basics.*;
import environments_commons_specials_basics.*;
import environments_rares_exteriors_basics.*;
import environments_rares_exteriors_complexes_withins.*;
import environments_rares_interiors_basics.*;
import environments_rares_specials_basics.*;
import environments_rares_specials_complexes_withins.*;
import environments_thought.*;
import mobs.*;
import players.*;
import encounters.*;
import acts_world.*;

public class Scenario
{
	// environment tracked properties //
	public static String[] previous_large_character_placements, previous_nonlarge_character_placements, previous_noncharacter_placements, previous_moba;
	// environment to scenario //
	public Environment environment;
//// environment lists //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// common - exterior - basic //
	public static final String[]
	environments_commons_exteriors_basics = Array.permute(new String[] {"environments_commons_exteriors_basics."}, new String[]
	{
		"Beach", "Birch_Forest", "Boreal_Forest", "Cave_Opening", "Cliff", "Crop_Field", "Crossroads", "Deciduous_Forest", "Desert", "Evergreen_Forest", "Flowery_Field", "Frozen_River", "Grassy_Plains", "Heath", "Hills", "Hilly_Desert", "Hilly_Jungle", "Ice_Plains", "Island", "Jungle", "Mesa", "Mountain_Range", "Plateau", "Riverside", "Savanna", "Snowy_Beach", "Steam_Vents", "Sunflower_Field", "Swampland", "Wooded_Trail"
	}),
	// common - interior - complex - underground //
	environments_commons_interiors_complexes_undergrounds = Array.permute(new String[] {"environments_commons_interiors_complexes_undergrounds."}, new String[]
	{
		"Cavern",
		"Tunnel",
		"Underground"
	}),
	// common //
	environments_commons = Array.combine
	(
		environments_commons_exteriors_basics,		// common - exterior - basic //
		Array.permute(new String[] {"environments_commons_interiors_basics."}, new String[] {"Dark_Cave", "Old_Church", "Sewers", "Tavern", "Underdark_Cavern", "Underdark_Crawlspace"}),		// common - interior - basic //
		environments_commons_interiors_complexes_undergrounds,		// common - interior - complex - underground //
		new String[] {"environments_commons_specials_basics.Graveyard"},		// common - special - basic //
		Array.permute(new String[] {"environments_commons_specials_complexes_withins."}, new String[] {"Abandoned_House", "Camp", "Fort"})		// common - special - within //
	),
	// rare - exterior - basic [underground complexifiable] //
	environments_rares_exteriors_basics_underground_complexifiable = Array.permute(new String[] {"environments_rares_exteriors_basics."}, new String[] {"Webbed_Forest"}),
	// rare - exterior - basic //
	environments_rares_exteriors_basics = Array.combine
	(
		environments_rares_exteriors_basics_underground_complexifiable,		// rare - exterior - basic [underground complexifiable] //
		Array.permute(new String[] {"environments_rares_exteriors_basics."}, new String[] {"Floating_Ice", "Floating_Island", "Jagged_Mountain_Passage", "Pit", "Plateau_within_River", "Ravine", "Solid_Cloud", "Volcano", "Wrecked_Boat_in_Sea"})		// rare - exterior - basic [not underground complexifiable] //
	),
	// rare - exterior - complex - within //
	environments_rares_exteriors_complexes_withins = Array.permute(new String[] {"environments_rares_exteriors_complexes_withins."}, new String[] {"Quicksand"}),
	// rare - interior - basic //
	environments_rares_interiors_basics = Array.permute(new String[] {"environments_rares_interiors_basics."}, new String[] {"Drow_Complex_Cavern", "Reservoir_Cavern", "Wizard_Tower", "Wizard_Tower_Summoning_Room"}),
	// rare //
	environments_rares = Array.combine
	(
		environments_rares_exteriors_basics,		// rare - exterior - basic //
		environments_rares_exteriors_complexes_withins,		// rare - exterior - complex - within //
		environments_rares_interiors_basics,		// rare - interior - basic //
		Array.permute(new String[] {"environments_rares_specials_basics."}, new String[] {"Abyssal_Bridge", "Arena", "Darkness", "Deity_Realm", "Goblin_Zeppelin", "Hell", "Mountain_Monastery", "Pocket_Dimension", "Ruined_Temple"}),		// rare - special - basic //
		Array.permute(new String[] {"environments_rares_specials_complexes_withins."}, new String[] {"Iruladoon"})		// rare - special - complex - within //
	),
	// all //
	environments = Array.combine(environments_commons, environments_rares);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// environment randomizer (for potential environments already instantiated anyway; else use the sole instantiation named object randomizer) //
	public static Environment random_environment_of(Environment[] exclusives)
	{
		return exclusives[Random.integer_until(exclusives.length)];
	}
	// sole instantiation object randomizer (for potential environments for which instantiation is unecessary for all but the one randomized) //
	public static Environment solely_instantiated_named_environment_of(String[] classpaths)
	{
		try
		{
			return (Environment) Class.forName(Random.text_of(classpaths)).newInstance();
		}
		catch (ClassNotFoundException exception)
		{
   			System.err.println("ERROR:\nScenario:\ncatch ('ClassNotFoundException' exception):\n"+exception.getMessage());
			return (Environment) new Object();
		}
		catch (InstantiationException exception)
		{
   			System.err.println("ERROR:\nScenario:\ncatch ('InstantiationException' exception):\n"+exception.getMessage());
			return (Environment) new Object();
		}
		catch (IllegalAccessException exception)
		{
			System.err.println("ERROR:\nScenario:\ncatch ('IllegalAccessException' exception):\n"+exception.getMessage());
			return (Environment) new Object();
		}
	}
	// new environment //
	private Environment new_environment(Player player)
	{
		// declaration //
		Environment new_environment;
		// first time - tavern //
		if (player.initial_tavern)		new_environment = new Tavern();
		// not the first time - not necessarily tavern //
		else
		{
			// common (90% chance) //
			if (Random.whole() <= .9)
			{
				// tavern (10% chance) //
				if (Random.whole() <= .1)		new_environment = new Tavern();
				// certain complexes (10% chance) //
				else if (Random.whole() <= .2)
					new_environment = solely_instantiated_named_environment_of(Array.combine(environments_commons_interiors_complexes_undergrounds, new String[]
									{
										"environments_commons_specials_complexes_withins.Camp",
										"environments_commons_specials_complexes_withins.Fort"
									}));
				// else any (80% chance) //
				else		new_environment = solely_instantiated_named_environment_of(environments_commons);
			}
			// else rare (10% chance) //
			else		new_environment = solely_instantiated_named_environment_of(environments_rares);
		}
		// finalizations //
		new_environment.update_placement_totals();
		if (!new_environment.basic)		new_environment.complexify();
		if (new_environment.exterior  &&  Random.whole() <= .2)		new_environment.weathering = true;
		// return //
		return new_environment;
	}
//// environment scenarioing ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void scenario_environment(boolean imagination_enabled, boolean dream_enabled, Player player)
	{
		// thought environment scenarioing //
		if (imagination_enabled || dream_enabled)
		{
			if (imagination_enabled && dream_enabled)		environment = solely_instantiated_named_environment_of(new String[] {"environments_thought.Imagination", "environments_thought.Dream"});
			else if (imagination_enabled)		environment = new Imagination();
			else if (dream_enabled)				environment = new Dream();
		}
		// gladiator-cheated arena scenarioing //
		else if (player.gladiator)
		{
			environment = new Arena();
			// finalizations //
			environment.update_placement_totals();
			if (environment.exterior  &&  Random.whole() <= .1)		environment.weathering = true;
		}
		// real environment scenarioing //
		else		environment = new_environment(player);
		// nonthought environment properties tracking //
		if (!imagination_enabled && !dream_enabled)
		{
			previous_large_character_placements = Array.copy(environment.large_character_placements);
			previous_nonlarge_character_placements = Array.copy(environment.nonlarge_character_placements);
			previous_noncharacter_placements = Array.copy(environment.noncharacter_placements);
			previous_moba = Array.copy(environment.moba);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// encounter to scenario //
	public Encounter encounter;
	// scenario generation //
    public Scenario(Player player, boolean imagination_enabled, boolean dream_enabled)
	{
		// normal scenario generation //
		if (!imagination_enabled && !dream_enabled)
		{
			if (!player.initial_tavern)
			{
				// thirsting //
				if (player.playing)		player.thirst();
				// hungering //
				if (player.playing)		player.hunger();
				// tiring //
				if (player.playing)		player.tire();
			}
			if (player.playing)
			{
				// scenarioing //
				Wait.milliseconds(250);
				System.out.print("\n\n");
				scenario_environment(false, false, player);		// imagination and dream disabled //
				encounter = new Encounter(player, this);
			}
			// acting //
			if (player.playing)
			{
				Wait.milliseconds(1500);
				System.out.print("\n\n");
				World world = new World(player, this);
			}
		}
		// imagination scenario generation //
		else if (imagination_enabled)
		{
			/* imagination scenario generation */
		}
		// dream scenario generation //
		else
		{
			scenario_environment(false, true, player);		// imagination and dream disabled //
			encounter = new Encounter(player, this);
			System.out.println("\n\n");


			if (Random.whole() <= .4)
			{
				Scenario scenario = new Scenario(player, false, true);
			}
		}
    }
}