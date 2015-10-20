package environments;

import utilities.*;
import mobs.*;
import players.*;

public class Environment
{
//// moba utilities /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// mobs package prefixed mob name permutator //
	public static String[] mobs_permute(String[] names)
	{
		return Array.permute(new String[] {"mobs."}, names);
	}
	// default chances to include certain moba when considered more rare within an environment //
	public static final double
	moba_roamers_chance = .25,
	moba_elemental_chance = .29,
	moba_giant_chance = .4,
	moba_golem_chance = .14,
	moba_aberration_chance = .3,
	moba_abyss_chance = .2,
	moba_stirge_chance = .17;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// seasonal control //
	private static int season = 0;		// iterated through 0 (Spring), 1 (Summer), 2 (Fall), and 3 (Winter) //
	
//// properties /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean
	// tavern toggle //
	tavern = false,
	// climate toggles //
	snowy = false,		// any weather is either always snowy or usually rainy and seasonally snowy //
	weathering = false,		// either weathering (raining\snowing) or not //
	// (drinkable) water toggle //
	water = false,
	// categorization toggles //
	real = true,		// either real or thought //
	common = true,		// either common (more generic) or rare (more unique) //
	interior = false, exterior = false, special = false,		// interior\exterior (terior) or special (teriorly ambiguous) //
	basic = true;		// either basic (unlayered) or complex (layered with other environments) //
	
	// setting //
	public String context;		// the contextual phrase for the setting //
	public String description = "";		// a statement describing the setting //
	
	// placements //
	public String[]
	large_character_placements,		// possible placements within the environment that are realistic for only large characters //
	nonlarge_character_placements = {},		// possible placements within the environment that are realistic for only nonlarge characters //
	character_placements,		// possible placements within the environment that are realistic for any characters //
	noncharacter_placements = {},		// possible placements within the environment that are not realistic for characters (none by default) //
	placements;		// all possible placements //
	
	
	
	// moba (mobs that can be found in, and which are thus able to be encountered in, the environment) - each 'composition' (assumed; part of the direct makeup of all mobs), 'subcomposition' (component of a composition), or 'combination' (combination of other types) //
	public static final String[]
	
	moba_cultists = mobs_permute(new String[] {"Acolyte", "Cultist"}),		// (subcomposition:) cultists //
	moba_bandits = mobs_permute(new String[] {"Bandit", "Bandit_Leader"}),		// (subcomposition:) bandits //
	moba_mercenaries = mobs_permute(new String[] {"Assassin", "Thug"}),		// (subcomposition:) mercenaries //
	moba_fighters = mobs_permute(new String[] {"Berserker", "Gladiator"}),		// (subcomposition:) fighters //
	moba_roamers = mobs_permute(new String[] {"Bandit", "Bandit", "Bandit", "Bandit", "Bandit", "Bandit", "Bandit", "Bandit", "Bandit_Leader", "Bandit_Leader", "Bandit_Leader", "Assassin"}),		// (combination:) roamers //
	moba_folk = Array.combine(moba_cultists, moba_bandits, moba_mercenaries, moba_fighters, mobs_permute(new String[] {"Pirate"})),		// folk (mostly human people mobs) //
	
	moba_elementals = mobs_permute(new String[] {"Air_Elemental", "Earth_Elemental", "Water_Elemental", "Fire_Elemental", "Galeb_Duhr", "Gargoyle", "Magmin", "Dust_Mephit", "Ice_Mephit", "Magma_Mephit", "Mud_Mephit", "Smoke_Mephit", "Steam_Mephit"}),		// elementals moba //
	
	moba_giants = mobs_permute(new String[] {"Cloud_Giant", "Fire_Giant", "Frost_Giant", "Hill_Giant", "Stone_Giant"}),		// giants moba //
	
	moba_sky = mobs_permute(new String[] {"Air_Elemental", "Cloud_Giant", "Roc"}),		// (combination:) sky moba //
	
	moba_fire = mobs_permute(new String[] {"Flameskull", "Fire_Elemental", "Magmin", "Fire_Giant", "Magma_Mephit", "Smoke_Mephit"}),		// (combination:) fire moba //
	
	moba_golems = mobs_permute(new String[] {"Clay_Golem", "Flesh_Golem", "Iron_Golem", "Stone_Golem", "Gorgon", "Helmed_Horror", "Shield_Guardian"}),
	
	moba_gremlin_large = mobs_permute(new String[] {"Ogre", "Ogrillon", "Ettin"}),		// (subcomposition:) large gremlin moba //
	moba_gremlin_nonlarge_unmultiplied = mobs_permute(new String[] {"Bugbear", "Gnoll", "Goblin", "Orc", "Hobgoblin", "Kobold"}),		// (subcomposition:) unmultiplied nonlarge gremlin moba //
	moba_gremlin_nonlarge_multiplied = Array.combine(moba_gremlin_nonlarge_unmultiplied, mobs_permute(new String[] {"Goblin", "Orc", "Orc"})),		// (subcomposition:) multiplied nonlarge gremlin moba //
	moba_gremlin = Array.combine(moba_gremlin_large, moba_gremlin_nonlarge_multiplied),		// gremlin moba //
	
	moba_drow_large = mobs_permute(new String[] {"Drider"}),		// (subcomposition:) large drow moba //
	moba_drow_nonlarge = mobs_permute(new String[] {"Drow", "Drow_Duelist", "Drow_Elite", "Drow_Mage", "Drow_Priestess"}),		// (subcomposition:) nonlarge drow moba //
	moba_drow = Array.combine(moba_drow_large, moba_drow_nonlarge),		// (subcomposition:) drow moba //
	
	moba_underground_large = Array.combine(moba_drow_large, mobs_permute(new String[] {"Fomorian", "Hook_Horror", "Quaggoth", "Roper", "Umber_Hulk", "Xorn", "Giant_Spider"})),		// (subcomposition:) large underground moba //
	moba_underground_nonlarge = Array.combine(moba_drow_nonlarge, mobs_permute(new String[] {"Darkmantle", "Duergar", "Grick", "Piercer", "Troglodyte"})),		// (subcomposition:) nonlarge underground moba //
	moba_underground = Array.combine(moba_underground_large, moba_underground_nonlarge),		// underground moba //
	
	moba_underground_nonlarge_with_gremlin_nonlarge = Array.combine(moba_underground_nonlarge, moba_underground_nonlarge, moba_underground_nonlarge, moba_gremlin_nonlarge_multiplied),		// (combination:) mostly nonlarge underground with some nonlarge gremlin moba //
	moba_underground_with_gremlin = Array.combine(moba_underground, moba_underground, moba_underground, moba_gremlin),		// (combination:) mostly underground with some gremlin moba //
	moba_gremlin_with_underground = Array.combine(moba_gremlin, moba_gremlin, moba_gremlin, moba_underground),		// (combination:) mostly gremlin with some underground moba //
	
	moba_sea_only_unmultiplied = mobs_permute(new String[] {"Merrow", "Sahuagin"}),		// (subcomposition:) unmultiplied exclusively sea moba //
	moba_sea_only_multiplied = mobs_permute(new String[] {"Merrow", "Sahuagin", "Sahuagin", "Merrow", "Sahuagin", "Sahuagin"}),		// multiplied exclusively sea moba //
	
	moba_forest_only = mobs_permute(new String[] {"Ankheg", "Dryad", "Ent", "Shambling_Mound"}),		// exclusively forest moba //
	moba_forest = Array.combine(moba_forest_only, moba_gremlin, mobs_permute(new String[] {"Displacer_Beast", "Giant_Spider"})),		// (combination:) forest moba //
	
	moba_aberrations = mobs_permute(new String[] {"Grell", "Otyugh"}),
	
	moba_abyss = mobs_permute(new String[] {"Barlgura", "Chasme", "Manes", "Quasit", "Shadow_Demon", "Vrock", "Jackalwere"}),		// abyss moba (demons) //
	
	moba_hells_unmultiplied = mobs_permute(new String[] {"Barbed_Devil", "Bearded_Devil", "Chain_Devil", "Imp", "Lemure", "Spined_Devil", "Hell_Hound", "Rakshasa"}),		// (subcomposition:) unmultiplied hells moba //
	moba_hells_multiplied = Array.combine(moba_hells_unmultiplied, mobs_permute(new String[] {"Imp", "Imp", "Imp", "Imp", "Imp", "Lemure", "Hell_Hound"})),		// multiplied hells moba //
	
	moba_undead_unmultiplied = mobs_permute(new String[] {"Crawling_Claw", "Ghoul", "Carrion_Crawler", "Flameskull", "Ghost", "Ghast", "Mummy", "Skeleton", "Zombie", "Revenant", "Specter", "Wight", "Wraith"}),		// (subcomposition:) unmultiplied undead moba //
	moba_undead_multiplied = Array.combine(moba_undead_unmultiplied, mobs_permute(new String[] {"Crawling_Claw", "Ghoul", "Ghoul", "Ghost", "Ghost", "Skeleton", "Skeleton", "Zombie", "Zombie"})),		// multiplied undead moba //
	moba_crypt = Array.combine(moba_undead_multiplied, mobs_permute(new String[] {"Dust_Mephit", "Flesh_Golem"})),		// (combination:) crypt moba //
	
	
	unique_mobs = mobs_permute(new String[] {"Displacer_Beast", "Ettercap", "Harpy", "Kenku", "Manticore", "Naga", "Young_Remorhaz", "Remorhaz", "Roc", "Stirge"}),		// unique mob species //
	
	moba_harpies_with_gremlin = Array.combine(mobs_permute(new String[] {"Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy"}), moba_gremlin),
	moba_gremlin_with_harpies = Array.combine(moba_gremlin, mobs_permute(new String[] {"Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy"})),
	moba_harpies_and_manticores_with_gremlin = Array.combine(mobs_permute(new String[] {"Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Harpy", "Manticore", "Manticore", "Manticore", "Manticore", "Manticore", "Manticore", "Manticore", "Manticore", "Manticore"}), moba_gremlin),
	
	
	// all moba //
	all_mobs = Array.combine(moba_folk, moba_elementals, moba_giants, moba_gremlin, moba_underground, moba_sea_only_multiplied, moba_forest_only, moba_abyss, moba_hells_multiplied, moba_undead_multiplied, unique_mobs);
	
	// moba of the environment (all by default) //
	public String[] moba = all_mobs;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// placement totals updating //
	public void update_placement_totals()
	{
		character_placements = Array.combine(large_character_placements, nonlarge_character_placements);
		placements = Array.combine(character_placements, noncharacter_placements);
	}
	
	// complexification //
	public void complexify()
	{
		// (nothing by default) //
	}
	
	
	
	
	// if the current environment is exterior, mention the weather if there is any, and update the season //
	public void weather()
	{
		if (weathering)
		{
			if (snowy  ||  season == 3)
				Type.delay_line("❄: "+Random.text_of(new String[] {"It is "+Random.text_of(new String[] {"", "currently "})+"snowing.", "A freezing blizzard is pelting you with sleet."}));
			else
				Type.delay_line("⛈: "+Random.text_of(new String[] {"The weather is stormy.", "Lighting is flashing out of the clouds above you.", "Thunder rumbles and rain falls out of the sky.", "It is "+Random.text_of(new String[] {"", "currently "})+"raining."}));
			season = (season > 3) ? season + 1 : 0;
		}
		else if (exterior)
		{
			double nonweathering_randomization = Random.whole();
			if (nonweathering_randomization <= .15)		Type.delay_line("🌁: "+Random.text_of(new String[] {"It is fairly cloudy.", "Clouds fill the sky.", "It is foggy out.", "The clouds are low to the ground."}));
			else if (nonweathering_randomization <= .3)		Type.delay_line("⛅: "+Random.text_of(new String[] {"It is partly cloudy.", "The sun is peeking through the clouds."}));
		}
	}
	
	// event - perhaps enacting a spectacle //
	public void event(Player player)
	{
		// (nothing by default) //
	}
	
	// enact a spectacle unique to the environment //
	public void spectacle()
	{
		// (nothing by default) //
	}
	
	// entry //
	public void enter(boolean initial_tavern, Player player, boolean mob_encountered)
	{
/////// context statement ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// location symbol //
		Type.delay("🔰: ");
		// discovery statement //
		/**/if (initial_tavern)		Type.delay_line("You are residing "+context);
		else
			Type.delay_line(Random.text_of(new String[] {"Y", "After traveling, y"})+Random.text_of(new String[] {"ou find yourself", "ou have ended up", "ou are now", "ou are"})+" "+context);/**/
		// description statement if no encounter //
		if (!mob_encountered)		Type.delay_wait_line(description);
		/*if (initial_tavern)		Type.delay("You are residing "+context);
		else
			Type.delay(Random.text_of(new String[] {"Y", "After traveling, y"})+Random.text_of(new String[] {"ou find yourself", "ou have ended up", "ou are now", "ou are"})+" "+context);
		// description statement //
		Type.delay_wait_line(" "+description);*/
		// potential weather statement and season change //
		weather();
		// potential event (potentially including a spectacle) //
		event(player);
		// delay //
		Wait.milliseconds(460);
	}
	
	// setting //
	public void setting(Player player)
	{
		// setting //
		Type.delay_wait_line("🔰: You are "+context);		// context statement //
		Type.delay_wait_line(description);		// description statement //
		// potential weather statement and season change //
		weather();
		// potential spectacle //
		spectacle();
	}
}
