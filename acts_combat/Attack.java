package acts_combat;

import utilities.*;
import players.*;
import scenarios.*;

public class Attack
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"offens", "conflict", "altercate", "tussle", "scuffle", "melee", "against", "duel", "joust", "contest", "confront", "match", "struggle", "scrimmage", "strife", "feud", "ruckus", "rape", "assail", "assassinate", "destroy", "approach", "take advantage"},
	
	triggers_subsafe_colloquial = {"attack", "combat", "fight", "skirmish", "engage", "strike", "exchange", "kill"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"a", "war", "row", "riot"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	public Attack(Player player, Scenario scenario)
	{
		Type.delay_line("Try attacking with any of the following abilities:", 25);
		player.list_abilities();
		System.out.println();
		
		Combat combat = new Combat(player, scenario);
	}
}