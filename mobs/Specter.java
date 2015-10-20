package mobs;

import utilities.*;

public class Specter extends Mob
{
	public Specter()
	{
		nonliving = true;
		
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, Random.integer_of(new int[] {9, 11}), health_min, Random.integer_of(new int[] {11, 13}), health);
		
		agility = Random.integer_from(12, 16);
		
		if (agility > 15  &&  Random.whole() <= .55)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 0, health_min, 2, health);
		
		damage_min = 0;
		damage_max = agility + strength;
	}
}