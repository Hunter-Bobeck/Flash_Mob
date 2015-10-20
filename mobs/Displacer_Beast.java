package mobs;

import utilities.*;

public class Displacer_Beast extends Mob
{
	public Displacer_Beast()
	{
		large = true;
		
		health_min = 83;
		health_max = 87;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Function.line(health_min, 15, health_max, 17, health);
		
		agility = Random.integer_from(14, 17);
		
		if (agility > 14  &&  Random.whole() <= .8)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 17, health_max, 19, health);
		
		damage_min = 3;
		damage_max = agility + strength;
	}
}