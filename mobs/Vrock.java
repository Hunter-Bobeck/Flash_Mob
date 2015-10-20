package mobs;

import utilities.*;

public class Vrock extends Mob
{
	public Vrock()
	{
		large = true;
		
		health_min = 100;
		health_max = 108;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 20, health);
		
		agility = Random.integer_from(14, 16);
		
		if (agility > 15  &&  Random.whole() <= .3)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}