package mobs;

import utilities.*;

public class Quaggoth extends Mob
{
	public Quaggoth()
	{
		health_min = 40;
		health_max = 50;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(10, 14);
		
		if (agility > 13  &&  Random.whole() <= .6)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}