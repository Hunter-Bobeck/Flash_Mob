package environments_commons_interiors_basics;

import utilities.*;
import scenarios.*;
import players.*;

public class Tavern extends Common_Interior_Basic
{
//////// name randomization sets ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
private static final String[]
// adjectives //
context_general_adjectives = {"Alabaster", "Ancient", "Beautiful", "Blue", "Boring", "Colorful", "Damned", "Divine", "Exotic", "Foul", "Great", "Green", "Impure", "Legendary", "Lucky", "Marked", "Modest", "New", "Old", "Purple", "Pure", "Queer", "Red", "Unique", "Vile", "Yellow"},
context_object_adjectives = Array.combine(new String[] {"Broken", "Cheap", "Common", "Duplicate", "Elegant", "Expensive", "Fine", "Inexpensive", "Magical", "New", "Rare", "Rusty", "Shattered", "Stolen", "Worn", "Wet"}, context_general_adjectives),
context_character_adjectives = Array.combine(new String[] {"Alert", "Artistic", "Bold", "Boring", "Charming", "Corrupt", "Creative", "Crying", "Dancing", "Dead", "Deadly", "Destitute", "Dying", "Elated", "Excited", "Foul", "Funny", "Glad", "Good", "Grinning", "Happy", "Howling", "Idiotic", "Illuminated", "Immoral", "Immortal", "Intelligent", "Jealous", "Joyous", "Keen", "Killing", "Kind", "Laughing", "Loving", "Mad", "Mean", "Morose", "Naked", "Needy", "Outraged", "Outrageous", "Prancing", "Quiet", "Rich", "Roaming", "Sad", "Screaming", "Smart", "Smiling", "Strong", "Swaggering", "Travelling", "Unquiet", "Violent", "Virtuous", "Vulgar", "Wandering", "Wealthy", "Weeping", "Wise", "Young", "Zestful"}, context_general_adjectives),
// subjects //
context_objects = {"Amulet", "Arm", "Barrel", "Battle", "Beak", "Blessing", "Book", "Booty", "Bottle", "Bow", "Casque", "Cauldron", "Chant", "Claw", "Claws", "Club", "Conflict", "Crime", "Crossbow", "Crown", "Cup", "Deception", "Decree", "Edict", "Elixir", "Fang", "Fangs", "Feather", "Fight", "Fist", "Flagon", "Flask", "Flight", "Fur", "Gallop", "Grimoire", "Hand", "Hanging", "Head", "Hoof", "Horn", "Horn-tip", "Law", "Lineage", "Mane", "Mug", "Pelt", "Potion", "Prayer", "Rule", "Scale", "Scales", "Scripture", "Scroll", "Sentence", "Slither", "Staff", "Strife", "Struggle", "Sword", "Tabard", "Tail", "Talisman", "Talisman", "Theft", "Throne", "Tooth", "Triumph", "Venom", "Victim", "Victory", "Wand", "War", "Web", "Wineskin", "Wing", "Wings"},
context_characters = {"Alchemist", "Alderman", "Angel", "Ape", "Artisan", "Bard", "Bird", "Boatman", "Bodyguard", "Bull", "Butcher", "Cat", "Centaur", "Cleric", "Coachman", "Conjurer", "Conjuress", "Courier", "Craftsman", "Crusader", "Demon", "Devil", "Dog", "Dragon", "Duke", "Dutchess", "Eagle", "Earl", "Empress", "Emperor", "Enchanter", "Enchantress", "Father", "Fish", "Fisherman", "Footpad", "Fox", "Giant", "Gnoll", "Goat", "Goblin", "Harpies", "Hawk", "Herdsman", "Highwayman", "Hobgoblin", "Horse", "Imp", "Jackal", "King", "Knight", "Laborer", "Ladies", "Lion", "Magi", "Marquessa", "Marquis", "Mercenaries", "Merchant", "Militaman", "Minister", "Minstrel", "Minotaur", "Monkey", "Naga", "Necromancer", "Ogre", "Owl", "Paladin", "Peasant", "Pedlar", "Phoenix", "Pony", "Priest", "Priestess'", "Prince", "Princess", "Quail", "Queen", "Raven", "Robber", "Rogue", "Seaman", "Serpent", "Servant", "Snake", "Soldier", "Sorceress", "Sorceror", "Spider", "Swindler", "Thief", "Tiger", "Toll-Keeper", "Trader", "Troll", "Unicorn", "Virgin", "Warrior", "Witch", "Wizard", "Wolf"},
context_characters_possessive = {"Alchemist's", "Alderman's", "Angel's", "Ape's", "Artisan's", "Bard's", "Bird's", "Boatman's", "Bodyguard's", "Bull's", "Butcher's", "Cat's", "Centaur's", "Cleric's", "Coachman's", "Conjurer's", "Conjuress'", "Courier's", "Craftsman's", "Crusader's", "Demon's", "Devil's", "Dog's", "Dragon's", "Duke's", "Dutchess'", "Eagle's", "Earl's", "Empress'", "Emperor's", "Enchanter's", "Enchantress'", "Father's", "Fish's", "Fisherman's", "Footpad's", "Fox's", "Giant's", "Gnoll's", "Goat's", "Goblin's", "Harpy's", "Hawk's", "Herdsman's", "Highwayman's", "Hobgoblin's", "Horse's", "Imp's", "Jackal's", "King's", "Knight's", "Laborer's", "Lady", "Lion's", "Magus'", "Marquessa's", "Marquis'", "Mercenary's", "Merchant's", "Militiaman's", "Minister's", "Minstrel's", "Minotaur's", "Monkey's", "Naga's", "Necromancer's", "Ogre's", "Owl's", "Paladin's", "Peasant's", "Pedlar's", "Phoenix's", "Pony's", "Priest's", "Priestess'", "Prince's", "Princess'", "Quail's", "Queen's", "Raven's", "Robber's", "Rogue's", "Seaman's", "Serpent's", "Servant's", "Snake's", "Soldier's", "Sorceress'", "Sorceror's", "Spider's", "Swindler's", "Thief's", "Tiger's", "Toll-Keeper's", "Troll's", "Trader's", "Unicorn's", "Virgin's", "Warrior's", "Witch's", "Wizard's", "Wolf's"},
context_characters_plural = {"Alchemists", "Aldermen", "Angels", "Apes", "Artisans", "Bards", "Birds", "Boatmen", "Bodyguards", "Bulls", "Butchers", "Cats", "Centaurs", "Clerics", "Coachmen", "Conjurers", "Conjuresses", "Couriers", "Craftsmen", "Crusaders", "Demons", "Devils", "Dogss", "Dragons", "Dukes", "Dutchesses", "Eagles", "Earls", "Empresses", "Emperors", "Enchanters", "Ennchantress'", "Fathers", "Fishermen", "Fishes", "Footpads", "Foxes", "Giants", "Gnolls", "Goats", "Goblins", "Guild", "Harpies", "Hawks", "Herdsmen", "Highwaymen", "Hobgoblins", "Horses", "Imps", "Jackals", "Kings", "Knights", "Laborers", "Ladies", "Lions", "Magus'", "Marquessas", "Marquis'", "Mercenaries", "Merchants", "Militiamen", "Ministers", "Minstrels", "Minotaurs", "Monkeys", "Nagas", "Necromancers", "Ogres", "Owls", "Paladins", "Peasants", "Pedlars", "Phoenixes", "Ponies", "Priestess", "Priests", "Princes", "Princesses", "Quails", "Queens", "Ravens", "Robbers", "Rogues", "Seamen", "Serpents", "Servants", "Snakes", "Soldiers", "Sorceresses", "Sorcerors", "Spiders", "Swindlers", "Thieves", "Tigers", "Toll-Keepers", "Traders", "Trolls", "Unicorns", "Virgins", "Warriors", "Witches", "Wizards", "Wolves"},
context_places_common = {"Tavern", "Tavern", "Inn", "Bar"},
context_places_rare = {"Alehouse", "Brewery", "Saloon", "Lodge", "Hall", "Stein"};
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// occupants randomization set //
public final String[] possible_occupants = {"a farmhand", "a pair of arguing drunks", "a couple of brawling men", "a wench", "a meditating monk", "a suspicious robed figure", "a ranger", "an elven lady", "a surly dwarf", "a gnome", "a fiddling gnome", "an engrossed bookreader", "an artisan", "a dapper halfling", "a waitress", "a hooded man", "a peasant lady", "a bearded man", "an old lady", "an old man", "a boy", "a girl", "a playing band", "a dwarven lass", "a well-dressed lady", "several rowdy dwarves", "a miner", "a thirsty laborer", "a swordarm", "an attractive woman", "a ragged drunkard", "a drunk farmer", "a farmer", "a salty pirate", "a crewman", "a deckhand", "the captain of the guard", "a seaman", "a colorful mystic", "a cloaked halfling", "a fat halfling", "a half-orc mercenary", "a nobleman", "a waiter", "a serving lady", "a serving boy", "a cook", "a brewer", "a sleeping adventurer", "a handsome lad", "a snoring man", "a snoring dwarf", "an elf maiden", "a male elf", "a well-equipped adventurer", "a traveling merchant", "a fat man", "a thug", "a bald mercenary", "a half-orc", "a peasant man", "a priest", "a priestess", "a guardsman", "a guardswoman", "a shifty-eyed rogue", "a rogue", "a cowled adventurer", "a noblewoman", "a "+Random.text_of(new String[] {"", "singing "})+Random.text_of(new String[] {"female bard", "male bard", "minstrel lady", "male minstrel"})+Random.text_of(new String[] {"", " playing a flute", " playing a lute"})};
// occupants properties //
String[] occupants;
String occupants_statement;
	
    public Tavern()
	{
		super();
		
		// tavern toggle //
		tavern = true;
		
		// context //
		String context_place;
		if (Random.whole() <= .7)		context_place = Random.text_of(context_places_common);
		else		context_place = Random.text_of(context_places_rare);
		context = Random.text_of(new String[] {"at", "inside", "in"})+" The ";
		switch (Random.integer_to(15))
		{
			case 0:
			{
				context += Random.text_of(context_object_adjectives)+" "+context_place;
				break;
			}
			case 1:
			{
				context += context_place+" of the "+Random.text_of(context_object_adjectives)+" "+Random.text_of(context_characters);
				break;
			}
			case 2:
			{
				context += Random.text_of(context_object_adjectives)+" "+Random.text_of(context_objects)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			case 3:
			{
				context += context_place+" of the "+Random.text_of(context_character_adjectives)+" "+Random.text_of(context_characters);
				break;
			}
			case 4:
			{
				context += Random.text_of(context_character_adjectives)+" "+Random.text_of(new String[] {Random.text_of(context_characters)+Random.text_of(new String[] {" "+context_place, ""}), Random.text_of(context_characters_possessive)+" "+context_place});
				break;
			}
			case 5:
			{
				context += context_place+" of the "+Random.text_of(context_characters_plural);
				break;
			}
			case 6:
			{
				context += context_place+" of "+Random.text_of(context_characters_plural);
				break;
			}
			case 7:
			{
				context += Random.text_of(context_objects)+" and the "+Random.text_of(context_objects)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			case 8:
			{
				context += Random.text_of(context_object_adjectives)+" "+Random.text_of(context_objects)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			case 9:
			{
				context += Random.text_of(context_characters_possessive)+" "+Random.text_of(context_objects)+" "+context_place;
				break;
			}
			case 10:
			{
				context += Random.text_of(context_character_adjectives)+" "+Random.text_of(context_characters_possessive)+" "+Random.text_of(context_objects)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			case 11:
			{
				context += Random.text_of(context_characters_possessive)+" "+context_place;
				break;
			}
			case 12:
			{
				context += Random.text_of(context_character_adjectives)+" "+Random.text_of(context_characters_possessive)+" "+context_place;
				break;
			}
			case 13:
			{
				context += Random.text_of(context_characters)+" and "+Random.text_of(context_characters)+" "+context_place;
				break;
			}
			case 14:
			{
				context += Random.text_of(context_characters)+" and the "+Random.text_of(context_objects)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			case 15:
			{
				context += Random.text_of(context_objects)+" and the "+Random.text_of(context_characters)+Random.text_of(new String[] {" "+context_place, ""});
				break;
			}
			// error //
			default:
			{
				System.out.println("ERROR:\nTavern:\nswitch chose default instead of a case");
			}
		}
		context += Random.text_of(new String[] {".", ".", ".", "..."});
		
		// description //
		description = Random.text_of(new String[] {"It's late, and most of the people here are tired. The inn's fireplace is crackling softly...", "The tavern is large, and largely undecorated, except for an extravagant rug at the entryway.", "The inn is warm and well lit inside. The lights flicker against various artworks hanging on the walls, depicting both adventurous and everyday scenes.", "The barkeep tends to the bar, wiping it clean. There is an impressive array of bottles and kegs behind him.", "The tavern is small, but well decorated. Adventuring paraphernalia are hung on the walls — items such as a crossbow, a bandolier, a helmet, and a broadsword.", "There are many barrels of mead stacked in the corner, enough to last the tavern for at least a year.", "Flickering candles are mounted on the walls, lending a midnight atmosphere. A large chandelier hangs from the center of the ceiling."});
		if (Random.whole() <= .35)
		{
			if (Random.whole() <= .3)		description += " Some open games of dice and cards rest on the tables for late night players.";
			else
			{
				if (Random.whole() <= .33)		description = "Card and dice games are being played tonight. "+description;
				else if (Random.whole() <= .66)		description += " There is a band in tonight, playing a jovial tune with lute, flute, and drums on a platform in the back of the tavern.";
				else		description = "Card and dice games are being played tonight. "+description+" There is a band of flute and lute players in the back of the tavern, providing a relaxing atmosphere.";
			}
		}
		
		// placements //
		large_character_placements = new String[] {"by the doorway", Random.text_of(new String[] {"at", "beside"})+" the bar", "on a table"};
		nonlarge_character_placements = new String[] {"seated on a stool at the bar", "in the seat at a table", "by the mead barrels", "behind the bar"};
		noncharacter_placements = new String[] {"on the wooden floor", "on the mantle of the fireplace", "in the fireplace", "in a crate", "on a shelf in the back", "mounted on the wall", "against the wall", "on the rug at the door", "in the lockbox under the counter of the bar", "by some ingredient pouches behind the bar", "under a wall-mounted candle"};
		// moba //
		if (Random.whole() <= .857)		moba = new String[] {};
		else		moba = mobs_permute(new String[] {"Thug"});
		
		// occupants //
		occupants = new String[] {Random.text_of(new String[] {"the barkeep", "the bartender", "the innkeeper"})};
		int more_occupants = Random.integer_from(2, 7);		// rough overcount because repeats will get discarded //
		for (int i = 0; i < more_occupants; i++)
		{
			String additional_occupant = Random.text_of(possible_occupants);
			if (!Array.containment(occupants, additional_occupant))		occupants = Array.combine(occupants, new String[] {additional_occupant});
		}
		// occupants statement //
		occupants_statement = Random.text_of(new String[] {"The", "Tonight's"})+" "+Random.text_of(new String[] {"occupants", "occupants", "residents"})+" include ";
		for (int i = 0; i < occupants.length; i++)
		{
			if (i < occupants.length - 2)
				occupants_statement += occupants[i]+", ";
			else if (i < occupants.length - 1)
				occupants_statement += occupants[i]+", and ";
			else
				occupants_statement += occupants[i]+".";
		}
	}
	
	public void spectacle()
	{
		Type.delay_line(occupants_statement);
	}
	
	public void event(Player player)
	{
		spectacle();
		if (player.health < player.healthy)
		{
			Type.delay("You find respite here.");
			int amount = Random.integer_from(1, 4);
			for (int i = 0; i < amount; i++)
			{
				Type.delay(".", 1250);
			}		System.out.println();
			player.recover(1 + amount);
		}
	}
}