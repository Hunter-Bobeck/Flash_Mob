package mobs;

import utilities.*;

public class Bandit_Leader extends Mob
{
	public Bandit_Leader()
	{
		health_min = 16;
		health_max = 20;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 16, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}