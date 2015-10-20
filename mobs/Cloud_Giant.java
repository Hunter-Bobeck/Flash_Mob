package mobs;

import utilities.*;

public class Cloud_Giant extends Mob
{
	public Cloud_Giant()
	{
		large = true;
		
		health_min = 184;
		health_max = 216;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 20, health_max, 24, health);
		
		agility = Random.integer_from(9, 11);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 25, health_max, 29, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}