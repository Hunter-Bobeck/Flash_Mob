package mobs;

import utilities.*;

public class Hobgoblin extends Mob
{
	public Hobgoblin()
	{
		health_min = 17;
		health_max = 28;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(11, 14);
		
		if (agility > 13  &&  Random.whole() <= .7)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 17, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}