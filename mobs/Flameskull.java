package mobs;

import utilities.*;

public class Flameskull extends Mob
{
	public Flameskull()
	{
		nonliving = true;
		unconscious = true;
		
		health_min = Random.integer_of(new int[] {28, 32});
		health_max = 50;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(Random.integer_of(new int[] {11, 13}), Random.integer_of(new int[] {15, 17}));
		
		agility = Random.integer_from(16, 18);
				
		block = fortitude + agility;
		
		strength = 1;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}