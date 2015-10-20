package mobs;

import utilities.*;

public class Goblin extends Mob
{
	public Goblin()
	{
		health_min = 5;
		health_max = 9;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 8, health_max, 12, health);
		
		agility = Random.integer_from(12, 16);
		
		if (agility > 14  &&  Random.whole() <= .5)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 6, health_max, 10, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}