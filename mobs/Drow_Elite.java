package mobs;

import utilities.*;

public class Drow_Elite extends Drow
{
	public Drow_Elite()
	{		
		health_min = 16;
		health_max = 20;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(17, 19);
		
		if (agility > 18  &&  Random.whole() <= .45)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 12, health_max, 14, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}