package mobs;

import utilities.*;

public class Flesh_Golem extends Mob
{
	public Flesh_Golem()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 86;
		health_max = 100;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 18;
		
		agility = 9;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}