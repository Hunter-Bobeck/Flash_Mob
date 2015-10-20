package mobs;

import utilities.*;

public class Crawling_Claw extends Mob
{
	public Crawling_Claw()
	{
		nonliving = true;
		unconscious = true;
		
		health_min = 1;
		health_max = 4;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 10, health_max, 12, health);
		
		agility = Random.integer_from(12, 16);
		
		if (agility > 13  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(11, 15);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}