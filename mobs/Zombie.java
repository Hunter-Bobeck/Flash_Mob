package mobs;

import utilities.*;

public class Zombie extends Mob
{
	public Zombie()
	{
		nonliving = true;
		
		health_min = 19;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, Random.integer_of(new int[] {10, 12}), health_max, Random.integer_of(new int[] {14, 17}), health);
		
		agility = Random.integer_from(5, 6);
		
		block = fortitude + agility;
		
		strength = Random.integer_from(10, 16);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}