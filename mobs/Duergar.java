package mobs;

import utilities.*;

public class Duergar extends Mob
{
	public Duergar()
	{
		health_min = 20;
		health_max = 24;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 18, health);
		
		agility = Random.integer_from(10, 12);
		
		if (agility > 11  &&  Random.whole() <= .2)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 15, health_max, 19, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}