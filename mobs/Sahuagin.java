package mobs;

import utilities.*;

public class Sahuagin extends Mob
{
	public Sahuagin()
	{
		health_min = 19;
		health_max = 25;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(10, 12);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 12, health_max, 14, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}