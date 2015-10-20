package mobs;

import utilities.*;

public class Rakshasa extends Mob
{
	public Rakshasa()
	{
		health_min = 108;
		health_max = 112;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 17, health);
		
		agility = Random.integer_from(16, 19);
		
		if (agility > 17  &&  Random.whole() <= .45)		haste = true;
		
		block = fortitude + agility;
		
		strength = Random.integer_from(13, 15);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}