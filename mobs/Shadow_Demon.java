package mobs;

import utilities.*;

public class Shadow_Demon extends Mob
{
	public Shadow_Demon()
	{
		health_min = 64;
		health_max = 68;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(15, 19);
		
		if (agility > 17  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = 1;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}