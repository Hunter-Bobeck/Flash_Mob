package mobs;

import utilities.*;

public class Yeti extends Mob
{
	public Yeti()
	{
		large = true;
		
		health_min = 44;
		health_max = 58;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(12, 14);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}