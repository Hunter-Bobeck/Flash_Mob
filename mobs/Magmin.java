package mobs;

import utilities.*;

public class Magmin extends Mob
{
	public Magmin()
	{
		nonliving = true;
		
		health_min = 8;
		health_max = 10;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 12;
		
		if (Random.whole() <= .5)		haste = true;
		
		agility = 15;
				
		block = fortitude + agility;
		
		strength = 7;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}