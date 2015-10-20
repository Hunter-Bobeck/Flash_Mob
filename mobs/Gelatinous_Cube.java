package mobs;

import utilities.*;

public class Gelatinous_Cube extends Mob
{
	public Gelatinous_Cube()
	{
		large = true;
		
		unconscious = true;
		nonliving = true;
		
		health_min = 48;
		health_max = 112;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 18, health_max, 22, health);
		
		agility = Random.integer_from(2, 4);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}