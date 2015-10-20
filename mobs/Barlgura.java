package mobs;

import utilities.*;

public class Barlgura extends Mob
{
	public Barlgura()
	{
		large = true;
		
		health_min = 67;
		health_max = 69;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(14, 16);
		
		if (agility > 15  &&  Random.whole() <= .2)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}