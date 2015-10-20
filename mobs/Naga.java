package mobs;

import utilities.*;

public class Naga extends Mob
{
	public Naga()
	{
		health_min = 56;
		health_max = 82;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(13, 15);
		
		agility = Random.integer_from(15, 19);
		
		if (agility > 17  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(16, 20);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}