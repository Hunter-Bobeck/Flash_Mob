package mobs;

import utilities.*;

public class Ent extends Mob
{
	public Ent()
	{
		large = true;
		
		health_min = 84;
		health_max = 102;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 16, health);
		
		agility = Random.integer_from(5, 6);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 19, health_max, 20, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}