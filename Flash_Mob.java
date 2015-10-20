import banners.*;
import players.*;
import scenarios.*;
import acts.*;

public class Flash_Mob
{
	public static void main(String[] arguments)
	{
		while (true)
		{
			Intro intro = new Intro();
			
			Player player = new Player();
			if (player.playing)
			{	
				Scenario scenario = new Scenario(player, false, false);
			}
			Outro outro = new Outro();
		}
	}
}