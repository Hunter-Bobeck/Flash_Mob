package mobs;

import utilities.*;

public class Ghost extends Mob
{
	public Ghost()
	{
		nonliving = true;
		
		health_min = Random.integer_of(new int[] {8, 15});
		health_max = 25;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, Random.integer_of(new int[] {6, 8}), health_min, Random.integer_of(new int[] {14, 16}), health);
		
		agility = Random.integer_from(10, 16);
		
		if (agility > 14  &&  Random.whole() <= .55)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 5, health_min, 8, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}