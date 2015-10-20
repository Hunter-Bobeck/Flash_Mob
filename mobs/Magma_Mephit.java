package mobs;

import utilities.*;

public class Magma_Mephit extends Mob
{
	public Magma_Mephit()
	{
		nonliving = true;
		
		health_min = 19;
		health_max = 25;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 12;
		
		agility = 12;
				
		block = fortitude + agility;
		
		strength = 8;
		
		damage_min = 2;
		damage_max = agility + strength;
	}
}