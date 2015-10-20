package mobs;

import utilities.*;

public class Kenku extends Mob
{
	public Kenku()
	{
		health_min = 12;
		health_max = 14;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 9;
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .6)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 9, health_max, 11, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}