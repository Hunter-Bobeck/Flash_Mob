package mobs;

import utilities.*;

public class Fomorian extends Mob
{
	public Fomorian()
	{
		large = true;
		
		health_min = 139;
		health_max = 159;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 17, health_max, 23, health);
		
		agility = Random.integer_from(9, 11);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 21, health_max, 25, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}