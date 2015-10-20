package mobs;

import utilities.*;

public class Hook_Horror extends Mob
{
	public Hook_Horror()
	{
		health_min = 70;
		health_max = 80;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 14, health_max, 16, health);
		
		agility = Random.integer_from(9, 11);
		
		if (agility > 10  &&  Random.whole() <= .43)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}