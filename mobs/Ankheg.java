package mobs;

import utilities.*;

public class Ankheg extends Mob
{
	public Ankheg()
	{
		large = true;
		
		health_min = 36;
		health_max = 42;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 12, health_max, 14, health);
		
		agility = Random.integer_from(9, 13);
		
		if (agility > 11  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 15, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}