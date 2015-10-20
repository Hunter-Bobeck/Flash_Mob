package mobs;

import utilities.*;

public class Drow_Duelist extends Drow
{
	public Drow_Duelist()
	{
		health_min = 16;
		health_max = 20;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(19, 20);
		
		if (agility > 19  &&  Random.whole() <= .45)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}