package mobs;

import utilities.*;

public class Revenant extends Mob
{
	public Revenant()
	{
		nonliving = true;
		
		health_min = 124;
		health_max = 148;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, 17, health_min, 19, health);
		
		agility = Random.integer_from(13, 15);
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 17, health_min, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}