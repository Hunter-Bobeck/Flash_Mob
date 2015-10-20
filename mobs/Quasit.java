package mobs;

import utilities.*;

public class Quasit extends Mob
{
	public Quasit()
	{
		health_min = 6;
		health_max = 8;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 9, health_max, 11, health);
		
		agility = Random.integer_from(16, 18);
		
		if (agility > 17  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(4, 6);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}