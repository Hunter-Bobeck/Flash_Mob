package mobs;

import utilities.*;

public class Drow_Priestess extends Drow
{
	public Drow_Priestess()
	{
		health_min = 10;
		health_max = 16;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(17, 19);
		
		if (agility > 17  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 12, health_max, 14, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}