package mobs;

import utilities.*;

public class Troglodyte extends Mob
{
	public Troglodyte()
	{
		health_min = 11;
		health_max = 15;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(9, 11);
		
		if (agility > 10  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}