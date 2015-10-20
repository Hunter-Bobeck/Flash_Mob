package acts_combat;

import utilities.*;
import scenarios.*;
import players.*;
import mobs.*;
import acts.*;

public class Flee
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"avoid", "come away", "forsake", "separate", "slip", "slip off", "split", "scurry", "crap", "shit", "yikes", "jeepers"},
	
	triggers_subsafe_colloquial = {"flee", "abandon", "break away", "cut and run", "ditch", "escape", "get away", "give the slip", "make a break", "pull out", "quit", "run away", "run off", "sally", "sally off", "take leave", "take off", "withdraw"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	triggers_nonsubsafe_uncolloquial = {"f"},
	
	triggers_nonsubsafe_colloquial = {"fly"},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
		
	public Flee(Scenario scenario, Player player)
	{
		if (player.Drizzt)
		{
			System.out.println();
			
			
			if (player.initial_tavern)		Type.delay_line("Time to find a different tavern!");
			Travel travel = new Travel(scenario, player, false);
		}
		else
		{
			if (player.initial_tavern)		Type.delay_line("Time to find a different tavern!");
			Mob mob = scenario.encounter.mob;
			String[] flee_statements = Array.permute
			(
				new String[] {"You try to "},
				Array.combine
				(
					new String[]
					{
						"abandon the fight",
						"cut and run",
						"give the "+mob.species+" the slip",
						"pull out from the fight",
						"quit the fight",
						"make a run for it",
						"split away",
						"make a split for it",
						"take your leave",
						"take off",
						"take off",
						"withdraw"
					},
					Array.permute(new String[] {"break away", "sally off"}, new String[] {" from the fight"}),
					Array.permute(new String[] {"ditch", "escape"}, new String[] {" the "}, new String[] {"fight", mob.species}),
					Array.permute(new String[] {"make a break"}, new String[] {"", " for it"}),
					Array.permute(new String[] {"run"}, new String[] {"", "", " away", " off"}, new String[] {" from the fight"})
				),
				new String[] {"!", "!", "."}
			);
			Wait.milliseconds(800);
			Type.delay_line(Random.text_of(flee_statements));
			double chance_mob_allows_escape = Random.whole();
			if (((double) player.agility  *  Random.quarter_varied_whole()   >   (double) mob.agility  *  Random.quarter_varied_whole())  ||  chance_mob_allows_escape <= .3)
			{
				if (chance_mob_allows_escape <= .3)
				{
					System.out.println();
					Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"doesn't bother to try and stop you", "lets you "+Random.text_of(new String[] {"go", "escape", "take your leave", "run off", "withdraw", "get away", "break away"})})+".");
				}
				else if ((double) mob.agility  *  1.25   >   (double) player.agility  *  .75)
				{
					String non_species_escape_statement = Random.text_of(flee_statements);
					while (non_species_escape_statement.contains(mob.species))		non_species_escape_statement = Random.text_of(flee_statements);
					non_species_escape_statement = Text.uncapitalize(Text.unpunctuate(non_species_escape_statement));
					System.out.println();
					Type.delay_line("The "+mob.species+" "+Random.text_of(new String[] {"attacks you as "+Text.uncapitalize(non_species_escape_statement), "gets another attack in"+Random.text_of(new String[] {"", " as "+Text.uncapitalize(non_species_escape_statement)})})+"!");
					mob.attack(player, false);
				}
				if (player.health > 0)
				{
					System.out.print("\n\n");


					Travel travel = new Travel(scenario, player, false);
				}
			}
			else
			{
				System.out.println();
				Type.delay_line(Random.text_of(new String[] {"You're not getting away this time", "You fail to "+Random.text_of(new String[] {"break away", "withdraw"})+" from the fight", "The "+mob.species+" doesn't "+Random.text_of(new String[] {"let you", "give you a chance to"})+" "+Random.text_of(new String[] {"break away", "escape", "get away", "run off", "withdraw"})})+"!");
			}
		}
	}
}