package mobs;

import utilities.*;

public class Umber_Hulk extends Mob
{
	public Umber_Hulk()
	{
		large = true;
		
		health_min = 86;
		health_max = 102;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(12, 14);
		
		if (agility > 13  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 19, health_max, 21, health);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}