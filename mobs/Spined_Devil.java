package mobs;

import utilities.*;

public class Spined_Devil extends Mob
{
	public Spined_Devil()
	{
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(14, 16);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 11, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}