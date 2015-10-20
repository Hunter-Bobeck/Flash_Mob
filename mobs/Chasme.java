package mobs;

import utilities.*;

public class Chasme extends Mob
{
	public Chasme()
	{
		health_min = 83;
		health_max = 85;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(13, 17);
		
		if (agility > 15  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 16, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}