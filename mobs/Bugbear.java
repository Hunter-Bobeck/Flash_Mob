package mobs;

import utilities.*;

public class Bugbear extends Mob
{
	public Bugbear()
	{
		health_min = 25;
		health_max = 29;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(12, 16);
		
		if (agility > 14  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 16, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}