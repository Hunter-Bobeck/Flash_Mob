package mobs;

import utilities.*;

public class Orog extends Mob
{
	public Orog()
	{
		health_min = 38;
		health_max = 46;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 20, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 12  &&  Random.whole() <= .29)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}