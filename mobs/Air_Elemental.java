package mobs;

import utilities.*;

public class Air_Elemental extends Mob
{
	public Air_Elemental()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 87;
		health_max = 93;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 14;
		
		agility = Random.integer_from(18, 22);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}