package mobs;

import utilities.*;

public class Manes extends Mob
{
	public Manes()
	{
		health_min = 7;
		health_max = 11;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(8, 10);
		
		if (agility > 9  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(9, 11);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}