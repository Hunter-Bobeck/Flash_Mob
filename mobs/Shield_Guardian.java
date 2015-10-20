package mobs;

import utilities.*;

public class Shield_Guardian extends Mob
{
	public Shield_Guardian()
	{
		nonliving = true;
		unconscious = true;
		
		health_min = 138;
		health_max = 146;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 18;
		
		agility = 8;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}