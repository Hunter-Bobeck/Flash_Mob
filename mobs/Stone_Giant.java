package mobs;

import utilities.*;

public class Stone_Giant extends Mob
{
	public Stone_Giant()
	{
		large = true;
		
		health_min = 121;
		health_max = 131;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 20;
		
		agility = Random.integer_from(14, 16);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 22, health_max, 24, health);
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}