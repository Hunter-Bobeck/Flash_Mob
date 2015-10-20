package acts;

import utilities.*;
import players.*;
import scenarios.*;

public class Drink
{
	// verbs and objects – used in subsafe triggers permutations //
	
	
	
	
	private static final String[]
	
	
	
	
	// also used in triggering //
    triggers_subsafe_verbs_colloquial = {"quench", "wet", "allay", "resolve"},
	triggers_subsafe_objects_colloquial = {"thirst", "thirstiness", "dryness", "parched throat", "dry throat"},
	
	triggers_subsafe_verbs_uncolloquial = {"assuage", "cease", "end", "lessen", "quell", "soak", "water", "irrigate"},
	triggers_subsafe_objects_uncolloquial = Array.combine(triggers_subsafe_objects_colloquial, new String[] {"aridity", "drought", "keenness", "roughness", "scratchiness", "tickliness"}),
	
	
	triggers_subsafe_uncolloquial_specific = {"waterskin", "canteen", "waterbottle", "pack", "inventory", "contents", "carrying", "items", "carry"},
	
	
	triggers_subsafe_colloquial_guzzles = {"drink", "guzzle", "chug", "sip", "swallow", "swig"},
	
	
	triggers_subsafe_uncolloquial_resupplies = {"fill", "load", "store", "supply", "get more", "pack more", "acquire", "take", "procure", "bring", "amass"},
	triggers_subsafe_colloquial_resupplies = {"refill my food and water", "supply myself with more water and rations", "refill my waterskin and pack more food", "resupply"},
	
	
	ordering_statements = Array.permute
	(
		new String[] {"have", "grab", "order"},
		new String[] {" "},
		new String[] {"an ale", "a beer", "a brew", "a beverage", "a drink"}
	),
	triggers_subsafe_uncolloquial_ordering = Array.combine
	(
		ordering_statements,
		new String[] {"ale", "beer", "brew", "alcohol", "liquor"}
	),
	triggers_nonsubsafe_uncolloquial_ordering = {"have", "grab", "order", "ale"};
	
	
	
	public static final String[] items_plural = {"water", "sips of water"};
	
	
	
	
	
	
	
	
	// triggers //
	
	
	
	
	public static final String[]
	
	
	
	
	triggers_subsafe_uncolloquial = Array.combine
	(
		new String[] {"drain", "gulp", "guzzle", "quaff", "chug", "slurp", "suck", "waterfall", "absorb", "imbibe", "indulge", "irrigate", "swallog", "swig", "swill", "partake of some water"},
		triggers_subsafe_uncolloquial_specific,
		Array.permute
		(
			triggers_subsafe_verbs_uncolloquial,
			new String[] {" ", " my "},
			triggers_subsafe_objects_uncolloquial
		),
		Array.permute
		(
			triggers_subsafe_verbs_colloquial,
			new String[] {" ", " my "},
			triggers_subsafe_objects_uncolloquial
		),
		triggers_subsafe_uncolloquial_resupplies,
		triggers_subsafe_uncolloquial_ordering
	),
	
	triggers_subsafe_colloquial = Array.combine
	(
		new String[] {"drink"},
		Array.permute
		(
			triggers_subsafe_colloquial_guzzles,
			Array.permute(new String[] {" "}, new String[] {"some water", "from my waterskin", "some water from my waterskin"})
		),
		Array.permute
		(
			triggers_subsafe_verbs_colloquial,
			new String[] {" my "},
			triggers_subsafe_objects_colloquial
		),
		triggers_subsafe_colloquial_resupplies
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),

	
	
	triggers_nonsubsafe_uncolloquial = Array.combine(triggers_nonsubsafe_uncolloquial_ordering, new String[] {"d"}),
	
	triggers_nonsubsafe_colloquial = {"sip"},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	public static void extra(Player player, int[] possibilities, boolean tavern)
	{
		if (possibilities.length > 0)
		{
			int extra = Random.integer_of(possibilities);
			if (player.water + extra  >  player.water_max)
			{
				extra = player.water_max - player.water;
				if (extra == 0)		Type.delay_line("💧: "+Random.text_of(new String[] {"Your waterskin is already full of water, so you add no more.", "There is no room in your waterskin to carry any more water."}));
			}
			else if (extra == 0  &&  !tavern)		Type.delay_line("💧: "+Random.text_of(new String[] {"You acquire no extra water."}));
			player.water += extra;
			if (extra > 0)		Type.delay_line("💧: "+Random.text_of(new String[] {"You fill up your waterskin, increasing your amount of water by "+extra+" to now be "+player.water, "You add "+extra+" water to your waterskin"})+".");
		}
	}
	
	private static void gas(double chance)
	{
		if (Random.whole() <= chance)
		{
			Wait.milliseconds(322);
			System.out.print("\n\n");
			
			Burp burp = new Burp();
			if (Random.whole() <= .12)		gas(1);
		}
	}
	
	private static void drink(Player player, int amount_min, int amount_max, int[] extra_possibilities, boolean tavern, double gas_chance)
	{
		int amount = Random.integer_from(amount_min, amount_max);
		for (int i = 0; i < amount; i++)
		{
			Type.delay(".", 1100);
		}		System.out.println();
		boolean overdrinking_weaken = false;
		Type.delay("💧: ");
		if (player.wetness + amount  <  player.quenched)
			Type.delay_line(Random.text_of(new String[] {"You could drink some more.", "You are still thirsty.", "Your throat is still somewhat dry."}));
		else if (player.wetness + amount  ==  player.quenched)
			Type.delay_line(Random.text_of(new String[] {"You have drunk enough.", "You have quenched your thirst.", "You are no longer thirsty."}));
		else
		{
			if (player.wetness == player.quenched)
			{
				Type.delay_line(Random.text_of(new String[] {"You have overdrunk.", "You feel waterlogged."}));
				overdrinking_weaken = true;
				if (gas_chance + .65  <=  .9)		gas_chance += .65;
				else		gas_chance = .9;
			}
			else		Type.delay_line(Random.text_of(new String[] {"You have drunk enough.", "You have quenched your thirst.", "You are no longer thirsty."}));
			amount = player.quenched - player.wetness;
		}
		player.wetness += amount;
		player.display_wetnessbar();
		if (overdrinking_weaken)		player.weaken(1);
		if (player.wetness  >=  player.quenched - 1)		extra(player, extra_possibilities, tavern);
		gas(gas_chance);
	}
	
	public Drink(Player player, Scenario scenario, boolean World, boolean from_Ahead)
	{
		boolean
		tavern = scenario.environment.tavern,
		waterskin_specific = Array.subcontainment_case_ignored(Act.input_backup, Array.combine(triggers_subsafe_uncolloquial_specific, new String[] {"inv"})),
		resupplying = Array.subcontainment_case_ignored(Act.input_backup, triggers_subsafe_uncolloquial_resupplies),	
		resupplying_order = Random.binary();
		if (resupplying && resupplying_order)
		{
			Eat eat = new Eat(player, scenario, true, World, from_Ahead);
		}
		String quench_your_thirst_phrase = Random.text_of(triggers_subsafe_verbs_colloquial)+" your "+Random.text_of(triggers_subsafe_objects_colloquial);
		if (World || from_Ahead)
		{
			if (tavern && (!waterskin_specific || resupplying))
			{
				if (resupplying)		extra(player, new int[] {player.water_max - player.water}, tavern);
				else
				{
					Type.delay(Random.text_of(new String[] {"🍶", "🍷"})+": You "+Random.text_of(ordering_statements)+Random.text_of(new String[] {"", " at the bar"})+".");
					drink(player, 1, player.quenched, new int[] {0, 1, 1, 2, 2, 3}, tavern, .2);
				}
			}
			else if (Array.subcontainment_case_ignored(Act.input_backup, triggers_subsafe_uncolloquial_ordering) || Array.containment_case_ignored(triggers_nonsubsafe_uncolloquial_ordering, Act.input_backup))
			{
				Type.delay_line(Random.text_of(new String[] {"You are not in a tavern.", "There is no bartender here!"}));
			}
			else if ((!waterskin_specific  &&  scenario.environment.water  &&  Random.whole() <= .95) || (scenario.environment.water && resupplying))
			{
				Wait.milliseconds(400);
				Type.delay("💧: "+Random.text_of(new String[] {"You find some water available here", "You access the water source here"})+".");
				if (resupplying)		extra(player, new int[] {player.water_max - player.water}, tavern);
				else
				{
					if (scenario.environment.snowy)		Type.delay_line("❄: "+Random.text_of(new String[] {"You feel cold.", "That was chilling.", "You shiver.", "That was cold water!"}));
					drink(player, 1, Random.integer_of(new int[] {player.quenched - 1, player.quenched}), new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3}, tavern, .1);
				}
			}
			else if (player.water >= 1  &&  !resupplying)
			{
				int drunk = 1;
				if (Random.whole() <= .8)
				{
					drunk = player.quenched - player.wetness;
					while (drunk > player.water)		drunk--;
					if (drunk == 0)		drunk = 1;
				}
				else		drunk = Random.integer_of(new int[] {1, 1, 1, 1, 1, 2, 2, 2, 3});
				player.water -= drunk;
				Type.delay("💧: You "+Random.text_of(new String[]
				{
					"drink"+Random.text_of(new String[]
							{
								"",
								" "+Random.text_of(new String[] {"some water", "some of your water", "from your waterskin", "some water from your waterskin", "a few sips of water"+Random.text_of(new String[] {"", " from your waterskin"})}),
							}),
					"sip "+Random.text_of(new String[] {"some water", "on some water", "some of your water", "on some of your water", "from your waterskin", "on your waterskin", "some water from your waterskin"}),
					Random.text_of(triggers_subsafe_colloquial_guzzles)+" "+Random.text_of(new String[] {"some water", "some of your water", "some water from your waterskin"}),
					quench_your_thirst_phrase
				})+".");
				drink(player, drunk, drunk, new int[] {}, tavern, .1);
			}
			// type a statement explaining why the player cannot drink //
			else
			{
				if (resupplying)
					Type.delay_line(Random.text_of(new String[] {"There is no drinkable water nearby", "You have no clean water around to fill your waterskin with"})+".");
				else
					Type.delay_line("You have no water"+Random.text_of(new String[] {"", " "+Random.text_of(new String[] {"", "available", "left"})+Random.text_of(new String[] {"", " to "+quench_your_thirst_phrase+" with"}), "in your waterskin, nor is there any good water around", "on hand, and there is nothing here to "+quench_your_thirst_phrase, "with you, and there is no good drinking water here"})+".");
			}
		}
		else
		{
			if (resupplying)		Type.delay_line("You have no time to resupply during combat!");
			else
			{
				Type.delay_line("You have no time to "+Random.text_of(new String[]
				{
					"drink",
					Random.text_of(triggers_subsafe_colloquial_guzzles)+" "+Random.text_of(new String[] {"", "any "})+"water",
					"take a "+Random.text_of(triggers_subsafe_colloquial_guzzles)+Random.text_of(new String[] {"", " of water"}),
					quench_your_thirst_phrase,
				})+" during combat!");
			}
		}
		if (resupplying && !resupplying_order)
		{
			Eat eat = new Eat(player, scenario, true, World, from_Ahead);
		}
	}
}