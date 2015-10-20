package mobs;

import utilities.*;

public class Shambling_Mound extends Mob
{
	public Shambling_Mound()
	{
		large = true;
		
		health_min = 124;
		health_max = 148;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 14, health_max, 18, health);
		
		agility = 8;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}