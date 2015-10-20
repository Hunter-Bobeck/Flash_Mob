package mobs;

import utilities.*;

public class Stone_Golem extends Mob
{
	public Stone_Golem()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 168;
		health_max = 188;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 20;
		
		agility = 9;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 5;
		damage_max = agility + strength;
	}
}