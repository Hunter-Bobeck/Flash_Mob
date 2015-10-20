package mobs;

import utilities.*;

public class Assassin extends Mob
{
	public Assassin()
	{
		health_min = 14;
		health_max = 17;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(17, 19);
		
		if (agility > 17  &&  Random.whole() <= .85)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 11, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}