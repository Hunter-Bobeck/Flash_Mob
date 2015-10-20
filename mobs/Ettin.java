package mobs;

import utilities.*;

public class Ettin extends Mob
{
	public Ettin()
	{
		large = true;
		
		health_min = 80;
		health_max = 90;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 18, health);
		
		agility = Random.integer_from(7, 9);
				
		block = fortitude + agility;
		
		strength = Function.line(health_min, 20, health_max, 22, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}