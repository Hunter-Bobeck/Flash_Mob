package mobs;

import utilities.*;

public class Imp extends Mob
{
	public Imp()
	{
		health_min = 9;
		health_max = 11;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(12, 14);
		
		agility = Random.integer_from(16, 18);
		
		if (agility > 17  &&  Random.whole() <= .45)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(5, 7);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}