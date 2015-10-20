package mobs;

import utilities.*;

public class Gorgon extends Mob
{
	public Gorgon()
	{
		large = true;
		
		health_min = 112;
		health_max = 116;
		health = Random.integer_from(health_min, health_max);
		
		fortitude = 18;
		
		agility = 11;
				
		block = fortitude + agility;
		
		strength = fortitude;
		
		damage_min = 5;
		damage_max = agility + strength;
	}
}