package mobs;

import utilities.*;

public class Stirge extends Mob
{
	public Stirge()
	{
		health_min = 1;
		health_max = 4;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 10, health_max, 12, health);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .55)		haste = true;
		
		block = fortitude + agility;
		
		strength = 4;
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}