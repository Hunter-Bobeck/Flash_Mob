package mobs;

import utilities.*;

public class Hill_Giant extends Mob
{
	public Hill_Giant()
	{
		large = true;
		
		health_min = 97;
		health_max = 113;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 18, health_max, 21, health);
		
		agility = Random.integer_from(7, 9);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 20, health_max, 22, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}