package mobs;

import utilities.*;

public class Merrow extends Mob
{
	public Merrow()
	{
		large = true;
		
		health_min = 40;
		health_max = 50;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 16, health);
		
		agility = Random.integer_from(9, 11);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}