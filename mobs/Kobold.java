package mobs;

import utilities.*;

public class Kobold extends Mob
{
	public Kobold()
	{
		health_min = 3;
		health_max = 7;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 7, health_max, 11, health);
		
		agility = Random.integer_from(13, 17);
		
		if (agility > 15  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 6, health_max, 8, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}