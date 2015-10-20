package mobs;

import utilities.*;

public class Piercer extends Mob
{
	public Piercer()
	{
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 16, health);
		
		agility = Random.integer_from(12, 14);
		
		if (agility > 13  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 11, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}