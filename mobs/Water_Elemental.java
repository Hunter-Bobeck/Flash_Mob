package mobs;

import utilities.*;

public class Water_Elemental extends Mob
{
	public Water_Elemental()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 112;
		health_max = 116;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 18;
		
		agility = 14;
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 20, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}