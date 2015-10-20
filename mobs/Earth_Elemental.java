package mobs;

import utilities.*;

public class Earth_Elemental extends Mob
{
	public Earth_Elemental()
	{
		nonliving = true;
		unconscious = true;
		
		nonliving = true;
		
		large = true;
		
		health_min = 122;
		health_max = 130;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(19, 21);
		
		agility = 8;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = Random.integer_from(3, 4);
		damage_max = agility + strength;
	}
}