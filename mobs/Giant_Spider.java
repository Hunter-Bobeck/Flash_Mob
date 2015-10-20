package mobs;

import utilities.*;

public class Giant_Spider extends Mob
{
	public Giant_Spider()
	{
		large = true;
		
		health_min = 24;
		health_max = 28;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .6)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}