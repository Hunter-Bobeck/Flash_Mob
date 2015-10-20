package mobs;

import utilities.*;

public class Remorhaz extends Mob
{
	public Remorhaz()
	{
		large = true;
		
		health_min = 184;
		health_max = 210;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 19, health_max, 22, health);
		
		agility = Random.integer_from(12, 14);
		
		if (agility > 13  &&  Random.whole() <= .66)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 23, health_max, 25, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}