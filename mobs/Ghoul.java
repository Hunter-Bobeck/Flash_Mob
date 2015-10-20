package mobs;

import utilities.*;

public class Ghoul extends Mob
{
	public Ghoul()
	{
		nonliving = true;
		
		health_min = Random.integer_of(new int[] {8, 18});
		health_max = 26;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, Random.integer_of(new int[] {5, 8}), health_max, Random.integer_of(new int[] {12, 15}), health);
		
		agility = Random.integer_from(11, 19);
		
		if (agility > 15  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(10, 16);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}