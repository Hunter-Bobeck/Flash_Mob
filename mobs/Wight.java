package mobs;

import utilities.*;

public class Wight extends Mob
{
	public Wight()
	{
		nonliving = true;
		
		health_min = 35;
		health_max = 55;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 14, health_max, 18, health);
		
		agility = 14;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(13, 17);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}