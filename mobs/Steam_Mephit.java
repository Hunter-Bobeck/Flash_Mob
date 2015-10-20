package mobs;

import utilities.*;

public class Steam_Mephit extends Mob
{
	public Steam_Mephit()
	{
		nonliving = true;
		
		health_min = 20;
		health_max = 22;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 10;
		
		agility = 14;
		
		if (Random.whole() <= .5)		haste = true;
				
		block = fortitude + agility;
		
		strength = 5;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}