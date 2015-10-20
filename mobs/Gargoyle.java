package mobs;

import utilities.*;

public class Gargoyle extends Mob
{
	public Gargoyle()
	{		
		health_min = 36;
		health_max = 68;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 16;
		
		agility = 11;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}