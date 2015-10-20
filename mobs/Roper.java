package mobs;

import utilities.*;

public class Roper extends Mob
{
	public Roper()
	{
		health_min = 87;
		health_max = 98;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 17, health);
		
		agility = Random.integer_from(7, 9);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}