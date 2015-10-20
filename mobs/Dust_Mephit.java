package mobs;

import utilities.*;

public class Dust_Mephit extends Mob
{
	public Dust_Mephit()
	{
		nonliving = true;
		
		health_min = 14;
		health_max = 20;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 10;
		
		agility = 14;
		
		if (Random.whole() <= .25)		haste = true;
				
		block = fortitude + agility;
		
		strength = 5;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}