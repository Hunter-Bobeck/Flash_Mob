package acts;

import utilities.*;
import players.*;
import scenarios.*;
import encounters.*;
import mobs.*;
import acts_combat.*;

public class Eat
{
	// verbs and objects – used in subsafe triggers permutations //
	
	
	
	
	private static final String[]
	
	
	
	
	// also used in triggering //
    triggers_subsafe_verbs_colloquial = {"allay", "appease", "resolve", "sate", "satiate", "satisfy", "settle"},
	triggers_subsafe_objects_colloquial = {"appetite", "famishment", "hunger"},
	
	triggers_subsafe_verbs_uncolloquial = {"assuage", "cease", "end", "lessen", "quell"},
	triggers_subsafe_objects_uncolloquial = Array.combine(triggers_subsafe_objects_colloquial, new String[] {"munchies", "ravenousness", "voraciousness", "voracity", "starvation", "peckishness"}),
	
	
	triggers_subsafe_verbs_basic_uncolloquial = {"bite", "chew", "chow", "devour", "dine", "feast", "feed", "gobble", "gorge", "gormandize", "hunger", "ingest", "masticate", "munch", "nibble", "nosh", "nourish", "sate", "satiate", "satisfy", "swallow", "grub", "break my fast"},
	
	triggers_subsafe_verbs_basic_colloquial = {"eat, sup"},
	
	
	triggers_subsafe_uncolloquial_specific = {"forage", "hunt", "ration"};
	
	
	
	
	public static final String[]
	items_singular = {"an edible", "a food item", "a ration", "a snack"},
	items_plural = {"edibles", "food items", "rations", "snacks"};
	
	
	
	
	
	
	
	
	// triggers //
	
	
	
	
	public static final String[]
	
	
	
	
	
	triggers_subsafe_uncolloquial = Array.combine
	(
		Array.permute
		(
			Array.combine(triggers_subsafe_verbs_basic_uncolloquial, triggers_subsafe_verbs_basic_colloquial),
			new String[] {"", " some"},
			Array.combine(new String[] {"", "food", "grub"}, items_plural)
		),
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
		)
	),
	
	triggers_subsafe_colloquial = Array.combine
	(
		Array.permute
		(
			triggers_subsafe_verbs_basic_colloquial,
			new String[] {"", " some"},
			new String[] {"food", "grub"}
		),
		new String[] {"eat food", "eat some food", "have a bite", "settle my stomach"},
		Array.permute
		(
			triggers_subsafe_verbs_colloquial,
			new String[] {" my "},
			triggers_subsafe_objects_colloquial
		)
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),

	
	
	triggers_nonsubsafe_uncolloquial = {"ravenous", "digest", "unfamish", "e"},
	
	triggers_nonsubsafe_colloquial = {"eat", "sup"},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	String
	foraging_statement = Random.text_of(new String[] {"🍓", "🍂", "🍃", "🍅", "🍏", "🍊"})+": You forage and find some veggies and berries",
	hunting_statement = Random.text_of(new String[] {"🍖", "🍲"})+": You hunt some wild animals and cook their meat";
	
	public static void extra(Player player, int[] possibilities)
	{
		if (possibilities.length > 0)
		{
			int extra = Random.integer_of(possibilities);
			if (player.food + extra  >  player.food_max)
			{
				extra = player.food_max - player.food;
				if (extra == 0)		Type.delay_line("🍞: "+Random.text_of(new String[] {"Your pack is already full of food, so you add no more.", "There is no room in your pack to carry any more food."}));
			}
			else if (extra == 0)		Type.delay_line("🍞: "+Random.text_of(new String[] {"You acquire no extra food.", "No food is left to store in your pack."}));
			player.food += extra;
			if (extra > 0)
			{
				Type.delay("🍞: ");
				if (extra == 1)
					Type.delay_line(Random.text_of(new String[] {"You add "+Random.text_of(items_singular)+" to your pack, increasing your amount of food by "+extra+" to now be "+player.food, "You add "+Random.text_of(items_singular)+" to your pack"})+".");
				else
					Type.delay_line(Random.text_of(new String[] {"You add some "+Random.text_of(items_plural)+" to your pack, increasing your amount of food by "+extra+" to now be "+player.food, "You add "+extra+" "+Random.text_of(items_plural)+" to your pack"})+".");
			}
		}
	}
	
	private static void gas(double chance, boolean might_also_drink, Player player)
	{
		if (Random.whole() <= chance)
		{
			Wait.milliseconds(322);
			System.out.print("\n\n");
			
			if (Random.whole() <= .6)
			{
				Burp burp = new Burp();
			}
			else
			{
				Fart fart = new Fart();
			}
			if (Random.whole() <= .12)		gas(1, false, player);
			if (might_also_drink && (player.wetness < player.quenched))
			{
				Wait.milliseconds(322);
				System.out.print("\n\n");
				
			}
		}
	}
	
	private static void eat(Player player, int amount_min, int amount_max, int[] extra_possibilities, double gas_chance, boolean might_also_drink)
	{
		int amount = Random.integer_from(amount_min, amount_max);
		for (int i = 0; i < amount; i++)
		{
			Type.delay(".", 1250);
		}		System.out.println();
		boolean overeating_weaken = false;
		Type.delay("🍽: ");
		if (player.fullness + amount  <  player.full)
			Type.delay_line(Random.text_of(new String[] {"You could eat some more.", "You are still peckish.", "You are still hungry."}));
		else if (player.fullness + amount  ==  player.full)
			Type.delay_line(Random.text_of(new String[] {"You have eaten enough.", "You feel satisfed.", "You are no longer hungry."}));
		else
		{
			if (player.fullness == player.full)
			{
				Type.delay_line(Random.text_of(new String[] {"You are way too full.", "You are feeling sick."}));
				overeating_weaken = true;
				if (gas_chance + .65  <=  .9)		gas_chance += .65;
				else		gas_chance = .9;
			}
			else		Type.delay_line(Random.text_of(new String[] {"You have eaten enough.", "You feel satisfed.", "You are no longer hungry."}));
			amount = player.full - player.fullness;
		}
		player.fullness += amount;
		player.display_fullnessbar();
		if (overeating_weaken)		player.weaken(1);
		if (player.fullness  >=  player.full - 1)		extra(player, extra_possibilities);
		gas(gas_chance, might_also_drink, player);
	}
	
	public Eat(Player player, Scenario scenario, boolean resupplying, boolean World, boolean from_Ahead)
	{
		Encounter encounter = scenario.encounter;
		if (World || from_Ahead)
		{
			// determine an eating action //
			String action = Random.text_of(new String[]
			{
				"eat",
				"snack on",
				Random.text_of(new String[]
				{
					"satisfy yourself",
					Random.text_of(triggers_subsafe_verbs_colloquial)+" your "+Random.text_of(triggers_subsafe_objects_colloquial)
				})+" with"
			});
			String
			article_item = Random.text_of(new String[] {"an edible", "a bit"+Random.text_of(new String[] {"", "e"})+" of food", "a food item", "a ration", "a snack"}),
			item_plural = Random.text_of(Array.combine(items_plural, new String[] {"food"}));
			if (encounter.mob_encountered && Act.input_backup.contains(encounter.mob.species))
			{
				if (from_Ahead)
				{
					Type.delay_line("Try attacking with any of the following abilities:", 25);
					player.list_abilities();
				}
				else
				{
					Mob mob = encounter.mob;
					Type.delay(Random.text_of(new String[] {"You "+Random.text_of(new String[] {"try", "attempt"})+" to eat the "+mob.species+".", "You taste the dead "+mob.species+"."}));
					for (int i = 0; i < Random.integer_from(1, 2); i++)
					{
						Type.delay(".", 1250);
					}		System.out.println();
					Type.delay_line(Random.text_of(new String[] {"You are feeling sick.", "That was a bad idea."}));
					player.weaken(1);
					gas(.4, false, player);
				}
			}
			else if (scenario.environment.tavern && !Act.input_backup.contains("ration"))
			{
				if (resupplying)		extra(player, new int[] {player.food_max - player.food});
				else
				{
					Type.delay(Random.text_of(new String[] {"🍲", "🍲", "🍖", "🍞", "🍏"})+": "+Random.text_of(new String[] {"You order some "+Random.text_of(new String[] {"food", "grub"}), "You dine", "You "+Random.text_of(new String[] {"eat", "order", "have"})+" a "+Random.text_of(new String[] {"dinner", "meal"})})+".");
					eat(player, 1, player.full, new int[] {0, 1, 1, 2, 2, 3, 3, 4, 4}, .2, true);
					if (player.wetness < player.quenched)
					{
						Drink drink = new Drink(player, scenario, World, from_Ahead);
					}
				}
			}
			else if (scenario.environment.exterior && (Random.whole() <= .6  ||  (player.food <= 5  &&  Random.whole() <= .9)  ||  Act.input_backup.contains("forage")  ||  Act.input_backup.contains("hunt")) && !Act.input_backup.contains("ration"))
			{
				Wait.milliseconds(400);
				if (Act.input_backup.contains("forage"))		Type.delay(foraging_statement+".");
				else if (Act.input_backup.contains("hunt"))		Type.delay(hunting_statement+".");
				else		Type.delay(Random.text_of(new String[] {foraging_statement, hunting_statement})+".");
				if (resupplying)		extra(player, new int[] {player.food_max - player.food});
				else		eat(player, 1, player.full - Random.integer_from(0, 3), new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3}, .1, false);
			}
			else if (player.food >= 1  &&  !resupplying)
			{
				int eaten = 1;
				if (Random.whole() <= .8)
				{
					eaten = player.full - player.fullness;
					while (eaten > player.food)		eaten--;
					if (eaten == 0)		eaten = 1;
				}
				else		eaten = Random.integer_of(new int[] {1, 1, 1, 1, 1, 2, 2, 2, 3});
				player.food -= eaten;
				Type.delay("🍞: You "+action+" "+Random.text_of(new String[] {article_item, "some "+item_plural})+Random.text_of(new String[] {"", " "+Random.text_of(new String[] {"in", "from"})+" your pack", " that you have "+Random.text_of(new String[] {"been carrying", "brought with you"})})+".");
				eat(player, eaten, eaten, new int[] {}, .1, false);
			}
			// type a statement explaining why the player cannot eat //
			else
			{
				if (resupplying)		Type.delay_line(Random.text_of(new String[] {"There is no food source nearby", "You have no food available to resupply your pack with here"})+".");
				else
				{
					Type.delay_line(Random.text_of(new String[]
					{
						"You have nothing "+Random.text_of(new String[] {"available", "to "+action+Random.text_of(new String[] {"", " on hand, and there is nothing here"})}),
						"You have no "+item_plural+Random.text_of(new String[] {"", " to "+action, " on hand, and there is nothing here to "+action, " available"})
					})+Random.text_of(new String[] {".", "!"}));
				}
			}
		}
		else if (!resupplying)
		{
			if (encounter.mob_encountered && Act.input_backup.contains(encounter.mob.species))
			{
				Attack attack = new Attack(player, scenario);
			}
			else
				Type.delay_line("You have no time to "+Random.text_of(new String[] {"eat"+Random.text_of(new String[] {"", " food", " a snack"}), "have a bite"+Random.text_of(new String[] {"", " of food"}), "settle your stomach", "snack"+Random.text_of(new String[] {"", " around"}), Random.text_of(new String[] {"satisfy", "satiate"})+" "+Random.text_of(new String[] {"yourself", "your hunger"})})+" during combat!");
		}
	}
}