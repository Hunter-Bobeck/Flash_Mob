package mobs;

import utilities.*;

public class Otyugh extends Mob
{
	public Otyugh()
	{
		large = true;
		
		health_min = 109;
		health_max = 119;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 18, health_max, 20, health);
		
		agility = 11;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 15, health_max, 17, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}