package mobs;

import utilities.*;

public class Bearded_Devil extends Mob
{
	public Bearded_Devil()
	{
		health_min = 50;
		health_max = 54;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 14, health_max, 16, health);
		
		agility = Random.integer_from(13, 17);
		
		if (agility > 16  &&  Random.whole() <= .55)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(15, 17);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}