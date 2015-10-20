package mobs;

import utilities.*;

public class Ogrillon extends Mob
{
	public Ogrillon()
	{
		large = true;
		
		health_min = 27;
		health_max = 33;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(8, 10);
		
		if (agility == 3  &&  Random.whole() <= .1)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}