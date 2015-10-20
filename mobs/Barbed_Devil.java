package mobs;

import utilities.*;

public class Barbed_Devil extends Mob
{
	public Barbed_Devil()
	{
		health_min = 107;
		health_max = 113;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 17, health_max, 19, health);
		
		agility = Random.integer_from(15, 19);
		
		if (agility > 17  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 15, health_max, 17, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}