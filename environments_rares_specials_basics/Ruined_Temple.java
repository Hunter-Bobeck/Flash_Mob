package environments_rares_specials_basics;

import utilities.*;

public class Ruined_Temple extends Rare_Special_Basic
{
    public Ruined_Temple()
	{
		super();

		context = Random.text_of(new String[] {"at a ruined ancient temple...", "at the ruins of an ancient temple..."});
		description = "Grand, rusting temple doors stand in front of you, marred with large dents. One is halfway opened. The dark antechamber within is supported my many large pillars that have glowing sigils inscribed upon them. There is a table covered in books and long-burnt candles. The floor is tiled, also going into some side rooms within.";
		large_character_placements = new String[] {"outside the dented temple doors", "in the dark antechamber lit only by glowing sigils on the walls", "by a table covered in books and long-burnt candles"};
		noncharacter_placements = new String[] {"on the floor of a small side room in the temple's interior", "on the tiled floor just inside", "on a table covered in books and long-burnt candles"};
		moba = Array.combine(moba_cultists, moba_cultists, mobs_permute(new String[] {"Gargoyle", "Clay_Golem", "Stone_Golem", "Naga"}));
		if (Random.whole() <= moba_roamers_chance)		moba = Array.combine(moba, moba_roamers);
		if (Random.whole() <= moba_golem_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Iron_Golem"}));
		if (Random.whole() <= moba_aberration_chance)		moba = Array.combine(moba, mobs_permute(new String[] {"Grell"}));
	}
}