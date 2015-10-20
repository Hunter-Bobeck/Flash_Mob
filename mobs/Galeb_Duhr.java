package mobs;

import utilities.*;

public class Galeb_Duhr extends Mob
{
	public Galeb_Duhr()
	{
		nonliving = true;
		unconscious = true;
		
		large = true;
		
		health_min = 68;
		health_max = 102;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(19, 21);
		
		agility = 14;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}