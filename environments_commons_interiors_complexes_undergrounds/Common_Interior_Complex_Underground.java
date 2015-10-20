package environments_commons_interiors_complexes_undergrounds;

import utilities.*;
import environments.*;
import scenarios.*;

public abstract class Common_Interior_Complex_Underground extends Environment
{
    public Common_Interior_Complex_Underground()
	{
		interior = true;
		basic = false;
		moba = Array.combine(moba_underground_with_gremlin, mobs_permute(new String[] {"Grell", "Otyugh"}));
		if (Random.whole() <= moba_elemental_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Earth_Elemental", "Galeb_Duhr", "Gargoyle"}));
    }

    public void complexify()
    {
		Environment aboveground = Scenario.solely_instantiated_named_environment_of(Array.combine(Scenario.environments_commons, Scenario.environments_rares_exteriors_basics_underground_complexifiable, Scenario.environments_rares_exteriors_complexes_withins, Scenario.environments_rares_interiors_basics));		// (not rare specials) //
    	// context //
		context += " "+Random.text_of(new String[] {"", "", "deep ", "far ", "directly "})+Random.text_of(new String[] {"beneath", "below", "under", "underneath"})+aboveground.context.substring(aboveground.context.indexOf(" "));
		if (!aboveground.basic)		context += ".";
    }
}