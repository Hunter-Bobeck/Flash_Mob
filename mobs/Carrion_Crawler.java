package mobs;

import utilities.*;

public class Carrion_Crawler extends Mob
{
	public Carrion_Crawler()
	{
		large = true;
		
		health_min = 47;
		health_max = 55;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(12, 14);
		
		if (agility > 16  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 13, health_max, 15, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}