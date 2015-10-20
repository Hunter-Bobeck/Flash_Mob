package mobs;

import utilities.*;

public class Drow extends Mob
{
	public Drow()
	{
		health_min = 10;
		health_max = 16;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 9, health_max, 11, health);
		
		agility = Random.integer_from(13, 16);
		
		if (agility > 14  &&  Random.whole() <= .5)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 12, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}