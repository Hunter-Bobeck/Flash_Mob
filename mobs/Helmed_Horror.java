package mobs;

import utilities.*;

public class Helmed_Horror extends Mob
{
	public Helmed_Horror()
	{
		nonliving = true;
		unconscious = true;
		
		health_min = 56;
		health_max = 64;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 16;
		
		agility = 13;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}