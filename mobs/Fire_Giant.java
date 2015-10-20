package mobs;

import utilities.*;

public class Fire_Giant extends Mob
{
	public Fire_Giant()
	{
		large = true;
		
		health_min = 154;
		health_max = 170;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 22, health_max, 24, health);
		
		agility = Random.integer_from(8, 10);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 23, health_max, 27, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}