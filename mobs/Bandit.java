package mobs;

import utilities.*;

public class Bandit extends Mob
{
	public Bandit()
	{
		health_min = 12;
		health_max = 16;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 11, health_max, 13, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 12  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 10, health_max, 12, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}