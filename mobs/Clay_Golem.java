package mobs;

import utilities.*;

public class Clay_Golem extends Mob
{
	public Clay_Golem()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 122;
		health_max = 144;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 18;
		
		agility = 8;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}