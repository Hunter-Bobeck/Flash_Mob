package mobs;

import utilities.*;

public class Pirate extends Mob
{
	public Pirate()
	{
		health_min = 12;
		health_max = 16;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(10, 12);
		
		if (agility > 11  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 11, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}