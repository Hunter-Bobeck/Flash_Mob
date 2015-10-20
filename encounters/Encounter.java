package encounters;

import utilities.*;
import players.*;
import scenarios.*;
import environments.*;
import mobs.*;

public class Encounter
{
	// mob to encounter //
	public Mob mob;
	// encounter randomization //
	public int randomization;
	// mob state booleans //
	public boolean
	mob_encountered = false,		// mob existence //
	mob_lootchecked = false;		// mob checked for loot yet //
	
	
	// mob randomizer (for potential mobs already instantiated anyway; else use the sole instantiation named object randomizer) //
	public static Mob random_mob_of(Mob[] exclusives)
	{
		return exclusives[Random.integer_until(exclusives.length)];
	}
	
	// sole instantiation mob randomizer (for potential mob for which instantiation is unecessary for all but the one randomized) //
	public static Mob solely_instantiated_named_mob_of(String[] classpaths)
	{
		try
		{
			return (Mob) Class.forName(Random.text_of(classpaths)).newInstance();
		}
		catch (ClassNotFoundException exception)
		{
   			System.err.println("ERROR:\nEncounter:\ncatch ('ClassNotFoundException' exception):\n"+exception.getMessage());
			return (Mob) new Object();
		}
		catch (InstantiationException exception)
		{
   			System.err.println("ERROR:\nEncounter:\ncatch ('InstantiationException' exception):\n"+exception.getMessage());
			return (Mob) new Object();
		}
		catch (IllegalAccessException exception)
		{
   			System.err.println("ERROR:\nEncounter:\ncatch ('IllegalAccessException' exception):\n"+exception.getMessage());
			return (Mob) new Object();
		}
	}
	
	// mob encountering //
	private void encounter_mob(Player player, Scenario scenario)
	{
		mob_encountered = true;
		scenario.environment.enter(player.initial_tavern, player, mob_encountered);		// environment entry - without setting (looking description) //
		if (Random.whole() <= .9645)
		{
			mob = solely_instantiated_named_mob_of(scenario.environment.moba);
			scenario.encounter = this;
			mob.spawn(player, scenario, false);
		}
		else
		{
			mob = solely_instantiated_named_mob_of(Environment.all_mobs);
			scenario.encounter = this;
			mob.spawn(player, scenario, true);
		}
	}
	
	
	// encounter generation //
	public Encounter(Player player, Scenario scenario)
	{
		Environment environment = scenario.environment;
		// moba setup //
		String[] moba = environment.moba;
		// cheat mob encountering //
		if ((player.Pwent && !player.Regis  ||  (player.Pwent  &&  player.Regis  &&  Random.whole() <= .5))  &&  moba.length > 0)				encounter_mob(player, scenario);
//////// noncheat encounter generation //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (!player.Regis)		// avoid encounters if Regis //
		{
			// randomization //
			randomization = Random.integer_from(0, 1);
			// if the randomization is 0, have no encounter //
			if (randomization == 0)		environment.enter(player.initial_tavern, player, false);		// environment entry - with setting (looking description) //
			// else if the randomization is 1 and the current environment has moba, encounter a mob //
			else if (randomization == 1  &&  moba.length > 0)		encounter_mob(player, scenario);
			// else (if the randomization's requirements could not be met), have no encounter //
			else		environment.enter(player.initial_tavern, player, false);		// environment entry - with setting (looking description) //
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Regis cheat encounter nongeneration (just enter the environment) //
		else		environment.enter(player.initial_tavern, player, false);		// environment entry - with setting (looking description) //
	}
}