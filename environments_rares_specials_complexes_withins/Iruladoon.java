package environments_rares_specials_complexes_withins;

import utilities.*;

public class Iruladoon extends Rare_Special_Complex_Within
{
    public Iruladoon()
	{
		super();

		water = true;
		context = "in Iruladoon, the moving forest of Mielikki,";
		description = "Ancient trees stand here, intermingled with sprouting saplings. Some plants grow right before your eyes at magical rates. There are verdant beds of grass, plenty of flowers, a gentle stream, and leaves flying to and fro on an unseen wind. Ridges in the terrain shimmer back and forth between being manifest ridges and flatter ground. Many of the plants have an unusual green glow. The forest is overly perfect, and at once changing, reforming.";
		large_character_placements = new String[] {"next to a sapling springing into existence", "within a swirl of glowing green leaves", "on a verdant bed of grass next to a glistening stream", "in a patch of healthy flowers", "in the gentle stream", "on a path sloping around a shimmering ridge", "at the top of a shimmering ridge", "under a spot where the air seems to be singing"};
		noncharacter_placements = new String[] {"bouncing along the current of a winding stream", "in a pile of bright leaves", "on the ground in several places almost simultaneously"};
		moba = Array.combine(moba_forest, mobs_permute(new String[] {"Earth_Elemental"}));
	}
}