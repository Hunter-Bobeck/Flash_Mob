package mobs;

import utilities.*;

public class Mud_Mephit extends Mob
{
	public Mud_Mephit()
	{
		nonliving = true;
		
		health_min = 25;
		health_max = 29;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 12;
		
		agility = 12;
				
		block = fortitude + agility;
		
		strength = 8;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}