package mobs;

import utilities.*;

public class Orc extends Mob
{
	public Orc()
	{
		health_min = 12;
		health_max = 18;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(9, 15);
		
		if (agility > 13  &&  Random.whole() <= .7)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}