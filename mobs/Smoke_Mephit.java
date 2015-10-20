package mobs;

import utilities.*;

public class Smoke_Mephit extends Mob
{
	public Smoke_Mephit()
	{
		nonliving = true;
		
		health_min = 21;
		health_max = 23;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 12;
		
		agility = 14;
		
		if (Random.whole() <= .45)		haste = true;
				
		block = fortitude + agility;
		
		strength = 6;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}