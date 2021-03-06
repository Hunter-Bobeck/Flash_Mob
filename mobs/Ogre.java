package mobs;

import utilities.*;

public class Ogre extends Mob
{
	public Ogre()
	{
		large = true;
		
		health_min = 54;
		health_max = 63;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 14, health_max, 18, health);
		
		agility = Random.integer_from(6, 8);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 21, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}