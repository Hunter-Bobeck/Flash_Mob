package environments_commons_exteriors_basics;

import utilities.*;

public class Birch_Forest extends Deciduous_Forest
{
    public Birch_Forest()
	{
		super();

		context = "in a birch forest.";
		description = Random.text_of(new String[] {"Autumnal leaves cover the ground.", "Bark peels off of several of the trees."});
	}
}