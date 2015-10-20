package mobs;

import utilities.*;

public class Wraith extends Mob
{
	public Wraith()
	{
		nonliving = true;
		
		health_min = 44;
		health_max = 95;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, 14, health_min, 18, health);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 5, health_min, 7, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}