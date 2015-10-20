package acts;

import utilities.*;
import scenarios.*;
import players.*;
import encounters.*;
import acts_combat.*;

public class Travel
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"about", "abroad", "ahead", "along", "area", "beyond", "biome", "domain", "elseplace", "elsewhere", "environment", "enter", "forth", "further", "go for", "go into", "go more", "go to", "go upon", "locale", "locality", "location", "off", "otherwhere", "pass on", "pass through", "place", "region", "scene", "set again", "set for", "set to", "set upon", "setting", "shove", "site", "someplace", "somewhere", "spot", "start", "stomp", "slip", "the unknown", "terrain", "territory", "toward", "turf", "venue", "vicinity", "whereabouts", "yonder", "walk", "hike", "jaunt", "stroll", "tour", "peregrin", "pace", "stride", "saunter", "traipse", "tread", "slide", "navigate", "wild"},
	
	triggers_subsafe_colloquial = {"advance", "adventure", "away", "continue", "depart", "exit", "explore", "forward", "get lost", "get back on track", "get on track", "go again", "go on", "head on", "head out", "hit the path", "hit the trail", "hit the road", "journey", "keep going", "keep on", "leave", "march", "migrate", "move", "onward", "persevere", "proceed", "progress", "quit", "run", "skedaddle", "travel", "traverse", "trek", "venture", "wander", "jog"},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"going", "head", "pass", "out of", "outta", "world", "t"},
	
	triggers_nonsubsafe_colloquial = {"go", "next", "out"},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	private boolean travel_recovery_yet, travel_weakening_yet;
	public Travel(Scenario scenario, Player player, boolean Ahead)
	{
		if (Ahead && player.initial_tavern)		Type.delay_line("Time to find a different tavern!");
		Type.delay("👣: You "+Random.text_of
		(
			Array.combine
			(
				new String[]
				{
					"journey"+Random.text_of(new String[] {"", "", " onward", " forward"}),
					"move "+Random.text_of(new String[]
							{
								"on"+Random.text_of(new String[] {"ward", ""}),
								"about the wilderness"
							})
				},
				Array.permute(new String[] {"are "}, new String[]
				{
					"traveling"+Random.text_of(new String[] {"", "", "", " elsewhere", " onward", " forward"}),
					"on the "+Random.text_of(new String[] {"move", "march"}),
					"on your way",
					"moving about the wilderness",
					"exploring"+Random.text_of(new String[] {"", " the world", " the wilds", " the wilderness", " new "+Random.text_of(new String[] {"terrain", "regions", "territory", "lands", "domains"})}),
					"wandering the "+Random.text_of(new String[] {"wilds", "wilderness"}),
					"traversing"+Random.text_of(new String[] {"", "", " onward", " forward"}),
					"trekking"+Random.text_of(new String[] {"", "", " onward", " forward"}),
					"venturing "+Random.text_of(new String[] {"further", "further", "onward", "forward"}),
					Random.text_of(Array.permute(new String[] {"progressing", "proceeding"}, new String[] {" onward"}))
				}))
			)
		);
		Type.delay("...", 800);
		for (int i = 0; i < Random.integer_from(0, 2); i++)		Type.delay(".", Random.integer_from(300, 700));
		System.out.println();
		if ((double) player.health  /  (double) player.healthy   >=   2.5 / 4.0    &&    Random.whole() <= .25)
		{
			int weakening = Random.integer_from(0, 5);
			if (player.health - weakening  <  1)		weakening = player.health - 1;
			if (weakening > 0)
			{
				System.out.println();
				String travel_weakening_explanation = null;
				do
					travel_weakening_explanation = Random.text_of(new String[] {"", "", "", "", "Your feet are sore and your legs are tired.", "Extensive trekking has exhausted you.", "You feel fatigued from your journey.", "Your legs ache from trekking.", "The terrain was treacherous.", "You tripped along the way."});
				while (!travel_weakening_yet && travel_weakening_explanation.equals(""));
				travel_weakening_yet = true;
				Type.delay(travel_weakening_explanation);
				if (!travel_weakening_explanation.equals(""))		System.out.println();
				player.health -= weakening;
				player.weakening_statement(weakening);
			}
		}
		else if (Random.whole() <= .45)
		{
			int recovery = Random.integer_from(0, 2);
			if (player.health + recovery  >  player.healthy)	recovery = player.healthy - player.health;
			if (recovery > 0)
			{
				System.out.println();
				String travel_recovery_explanation = null;
				do
					travel_recovery_explanation = Random.text_of(new String[] {"", "", "", "", "You took the time to rest along the way.", "The travel was easy.", "Your fitness has improved your condition.", "The time has allowed you to recover some health.", "You feel more capable after wandering on your own for a while.", "You found some medicinal plants along the way."});
				while (!travel_recovery_yet && travel_recovery_explanation.equals(""));
				travel_recovery_yet = true;
				Type.delay(travel_recovery_explanation);
				if (!travel_recovery_explanation.equals(""))		System.out.println();
				player.health += recovery;
				player.recovery_statement(recovery);
			}
		}
		scenario = new Scenario(player, false, false);
	}
}