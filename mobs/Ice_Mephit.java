package mobs;

import utilities.*;

public class Ice_Mephit extends Mob
{
	public Ice_Mephit()
	{
		nonliving = true;
		
		health_min = 18;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 10;
		
		agility = 13;
				
		block = fortitude + agility;
		
		strength = 7;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}