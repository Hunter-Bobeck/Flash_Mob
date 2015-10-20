package mobs;

import utilities.*;

public class Drider extends Mob
{
	public Drider()
	{
		large = true;
		
		health_min = 120;
		health_max = 126;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(17, 19);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 14, health_max, 18, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}