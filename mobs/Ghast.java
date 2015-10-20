package mobs;

import utilities.*;

public class Ghast extends Mob
{
	public Ghast()
	{
		nonliving = true;
		
		health_min = Random.integer_of(new int[] {17, 23});
		health_max = 36;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, Random.integer_of(new int[] {7, 8}), health_min, Random.integer_of(new int[] {12, 13}), health);
		
		agility = Random.integer_from(15, 19);
		
		if (agility > 17  &&  Random.whole() <= .55)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 14, health_min, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}