package mobs;

import utilities.*;

public class Roc extends Mob
{
	public Roc()
	{
		health_min = 218;
		health_max = 266;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 19, health_max, 21, health);
		
		agility = Random.integer_from(9, 11);
		
		if (agility > 10  &&  Random.whole() <= .45)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 27, health_max, 29, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}