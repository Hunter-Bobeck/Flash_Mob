package mobs;

import utilities.*;

public class Darkmantle extends Mob
{
	public Darkmantle()
	{
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 11  &&  Random.whole() <= .65)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 15, health_max, 17, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}