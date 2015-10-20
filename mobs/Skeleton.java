package mobs;

import utilities.*;

public class Skeleton extends Mob
{
	public Skeleton()
	{
		nonliving = true;
		
		health_min = 10;
		health_max = 15;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, Random.integer_of(new int[] {8, 13}), health_max, Random.integer_of(new int[] {14, 17}), health);
		
		agility = 14;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(7, 12);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}