package mobs;

import utilities.*;

public class Manticore extends Mob
{
	public Manticore()
	{
		health_min = 65;
		health_max = 71;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 16, health_max, 18, health);
		
		agility = Random.integer_from(15, 17);
		
		if (agility > 16  &&  Random.whole() <= .35)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 4;
		damage_max = agility + strength;
	}
}