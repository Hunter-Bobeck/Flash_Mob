package environments_commons_interiors_basics;

import utilities.*;

public class Sewers extends Common_Interior_Basic
{
    public Sewers()
	{
		super();

		context = "inside "+Random.text_of(new String[] {"the sewer system of a city", "a "+Random.text_of(new String[] {"passage in a sewer system", "sewer "+Random.text_of(new String[] {"passage", "system"})})})+".";
		description = Random.text_of(new String[] {"The air here is smelly, and the walls are grimy.", "The passage you are has a stream of sewage running through it, to a gate allowing the liquid, but not yourself, to get through.", "There is a walkway over the moving sludge, leading to a manhole.", "The floor is surprisingly clean in some places, as if something sticky has been run over the surface."});
		large_character_placements = new String[] {"on top of a large grate", "below a manhole", "in the slimy corridor", "in the dirty tunnel", "on a walkway over sludge", "next to a rusted gate", "in the moving stream of sewage", "in the depth of the sewer ahead"};
		noncharacter_placements = new String[] {"in a pipe sticking out of the wall", "in a puddle of ooze", "in a puddle of dirty water", "next to some crusted residue", "floating parallel to a log of poo in the passing sewage"};
		moba = mobs_permute(new String[] {"Kobold", "Kobold", "Kobold", "Gelatinous_Cube", "Gelatinous_Cube", "Gelatinous_Cube", "Otyugh", "Otyugh", "Otyugh", "Clay_Golem", "Grell", "Goblin", "Jackalwere", "Crawling_Claw", "Ghost", "Water_Elemental"});
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
	}
}