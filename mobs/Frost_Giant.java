package mobs;

import utilities.*;

public class Frost_Giant extends Mob
{
	public Frost_Giant()
	{
		large = true;
		
		health_min = 128;
		health_max = 148;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 19, health_max, 22, health);
		
		agility = Random.integer_from(8, 9);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 21, health_max, 25, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}