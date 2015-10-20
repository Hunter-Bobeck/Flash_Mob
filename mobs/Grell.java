package mobs;

import utilities.*;

public class Grell extends Mob
{
	public Grell()
	{
		health_min = 50;
		health_max = 60;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(13, 15);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 16, health);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}