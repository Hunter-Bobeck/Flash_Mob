package mobs;

import utilities.*;

public class Mummy extends Mob
{
	public Mummy()
	{
		nonliving = true;
		
		health_min = 33;
		health_max = 67;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_max, Random.integer_of(new int[] {11, 3}), health_min, Random.integer_of(new int[] {15, 16}), health);
		
		agility = Random.integer_from(7, 8);
		
		block = fortitude + agility;
		
		strength = Function.line(health_max, 15, health_min, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}