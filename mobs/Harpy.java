package mobs;

import utilities.*;

public class Harpy extends Mob
{
	public Harpy()
	{		
		health_min = 35;
		health_max = 41;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 12;
		
		agility = 13;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}