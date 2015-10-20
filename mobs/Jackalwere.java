package mobs;

import utilities.*;

public class Jackalwere extends Mob
{
	public Jackalwere()
	{
		health_min = 17;
		health_max = 19;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = Random.integer_from(10, 12);
		
		agility = 15;
		
		if (Random.whole() <= .5)		haste = true;
		
		block = fortitude + agility;
		
		strength = Function.line(health_min, 10, health_max, 12, health);
		
		damage_min = 1;
		damage_max = agility + strength;
	}
}