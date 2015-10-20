package mobs;

import utilities.*;

public class Gladiator extends Mob
{
	public Gladiator()
	{
		health_min = 19;
		health_max = 20;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 17, health);
		
		agility = Random.integer_from(15, 17);
		
		if (Random.whole() <= .65)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}