package mobs;

import utilities.*;

public class Dryad extends Mob
{
	public Dryad()
	{
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 10, health_max, 12, health);
		
		agility = Random.integer_from(10, 14);
		
		if (agility > 12  &&  Random.whole() <= .7)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 11, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}