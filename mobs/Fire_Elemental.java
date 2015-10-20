package mobs;

import utilities.*;

public class Fire_Elemental extends Mob
{
	public Fire_Elemental()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 100;
		health_max = 104;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 16;
		
		agility = Random.integer_from(16, 19);
				
		block = fortitude + agility;
		
		strength = 10;
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}