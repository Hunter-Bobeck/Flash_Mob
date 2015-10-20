package mobs;

import utilities.*;

public class Chain_Devil extends Mob
{
	public Chain_Devil()
	{
		health_min = 83;
		health_max = 87;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 17, health_max, 19, health);
		
		agility = Random.integer_from(14, 16);
		
		if (agility > 15  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 20, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}