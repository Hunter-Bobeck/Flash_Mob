package mobs;

import utilities.*;

public class Iron_Golem extends Mob
{
	public Iron_Golem()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 190;
		health_max = 230;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 20;
		
		agility = 9;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 5;
		damage_max = agility + strength;
	}
}