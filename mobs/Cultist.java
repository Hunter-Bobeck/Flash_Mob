package mobs;

import utilities.*;

public class Cultist extends Mob
{
	public Cultist()
	{
		health_min = 11;
		health_max = 12;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 9, health_max, 11, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 12  &&  Random.whole() <= .4)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 10, health_max, 12, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}