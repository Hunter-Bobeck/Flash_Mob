package mobs;

import utilities.*;

public class Xorn extends Mob
{
	public Xorn()
	{
		large = true;
		
		health_min = 66;
		health_max = 80;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 20, health_max, 24, health);
		
		agility = Random.integer_from(9, 11);
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}