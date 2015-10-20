package mobs;

import utilities.*;

public class Hell_Hound extends Mob
{
	public Hell_Hound()
	{
		health_min = 42;
		health_max = 48;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 13, health_max, 15, health);
		
		agility = Random.integer_from(11, 13);
		
		if (agility > 12  &&  Random.whole() <= .85)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 16, health_max, 18, health);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}