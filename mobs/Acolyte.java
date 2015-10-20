package mobs;

import utilities.*;

public class Acolyte extends Mob
{
	public Acolyte()
	{
		health_min = 10;
		health_max = 13;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 9, health_max, 11, health);
		
		agility = Random.integer_from(9, 11);
		
		if (agility > 10  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 11, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}