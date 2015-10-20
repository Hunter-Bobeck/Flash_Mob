package mobs;

import utilities.*;

public class Young_Remorhaz extends Mob
{
	public Young_Remorhaz()
	{
		large = true;
		
		health_min = 70;
		health_max = 125;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 18, health);
		
		agility = Random.integer_from(13, 14);
		
		if (agility > 13  &&  Random.whole() <= .7)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}