package environments_rares_specials_basics;

import utilities.*;

public class Mountain_Monastery extends Rare_Special_Basic
{
    public Mountain_Monastery()
	{
		super();

		water = true;
		context = "inside a remote mountain monastery.";
		description = "This secluded courtyard of trees, pots, and brick paths is enclosed by a pillared wall. An adjoining room is full of torches. A hall of statues straightens out a segment of the regularly curving walls. A stream runs through the grounds. The place feels old, but well-kept. Only a lone, crumbling pillar in the courtyard is dilapidated. Outside the walls of the monastery are many mountains, some peaks level with this place and others towering above, while others poke the sky well below this remote ridge.";
		large_character_placements = new String[] {"in the inner courtyard", "in the incensed room of prayer", "next to some potted plants", "next to a statue of twig", "next to a statue of stone", "in a room full of torches", "on the bank of a stream running by the grounds", "in the water of the stream running by the grounds", "next to a bush garden", "beside a crumbling pillar in the courtyard"};
		noncharacter_placements = new String[] {"on the grass of the pavilion outside", "on the floor of the curving hallway", "in an old pot", "on a brick path"};
		moba = Array.combine(moba_cultists, mobs_permute(new String[] {"Water_Elemental", "Gargoyle", "Hill_Giant", "Stone_Giant", "Clay_Golem", "Stone_Golem", "Roc"}));
	}
}