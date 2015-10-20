package mobs;

import utilities.*;

public class Gnoll extends Mob
{
	public Gnoll()
	{
		health_min = 16;
		health_max = 30;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 10, health_max, 12, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 12  &&  Random.whole() <= .69)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}