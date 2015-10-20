// Random: contains static methods for pseudorandom generation of data literals; they are generally ordered by restriction; a single parameter for one of these methods will be the lower bound //
 
package utilities;

import java.text.NumberFormat;

public class Random
{
	public static boolean binary()
	{
		return (new boolean[] {false, true})[Random.integer_from(0, 1)];
	}
	
	
	
	public static double whole()
	{
		return roundable(0);
	}
	public static double quarter_varied_whole()
	{
		return (double) Random.integer_from(3, 5)  /  4.0;
	}
	
	
	public static double roundable(int factor)
	{
		return Math.random() * (1 + Double.MIN_VALUE + factor);
	}
	
	public static double roundable()
	{
		return roundable(1);
	}
	
	
	
	public static int integer_of(int[] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	public static int integer_from(int lower, int upper)
	{
		if (lower > upper)
		{
			int temporary = lower;
			lower = upper;
			upper = temporary;
		}
		return (int) (roundable(upper - lower)) + lower;
	}
	
    public static int integer_from(int lower)
	{
		return integer_from(lower, Integer.MAX_VALUE / 2);
    }
	
	public static int integer_from()
	{
		return integer_from(Integer.MIN_VALUE / 2);
	}
	
	
	public static int integer_to(int upper)
	{
		return integer_from(0, upper);
	}
	
	public static int integer_to()
	{
		return integer_to(Integer.MAX_VALUE);
	}
	
	
	public static int integer_until()
	{
		return integer_to();
	}
	
	public static int integer_until(int upper)
	{
		return integer_to(upper - 1);
	}
	
	
	public static int integer_between(int lower, int upper)
	{
		if (lower + 1  >  upper - 1)
		{
			int temporary = lower;
			lower = upper;
			upper = temporary;
			if (lower + 1  >  upper - 1)
			{
				System.out.println("ERROR:\nRandom:\ninteger_between(int lower, int upper):\nif (lower + 1  >  upper - 1):\nif (lower + 1  >  upper - 1) returned true; return 0;");
				return 0;
			}
		}
		return integer_from(lower + 1, upper - 1);
	}
	
	public static int integer_between(int lower)
	{
		if (lower + 1  >=  Integer.MAX_VALUE)
		{
			System.out.println("ERROR:\nRandom:\ninteger_between(int lower):\nif (lower + 1  >=  Integer.MAX_VALUE) returned true; return 0;");
			return 0;
		}
		return integer_from(lower + 1, Integer.MAX_VALUE / 2);
	}
	
	public static int integer_between()
	{
		return integer_from();
	}
	
	
	public static int integer()
	{
		return integer_from();
	}
	
	
	
	public static double decimal_of(double[] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	public static String text_of(String... exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	public static char character_of(char[] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	// object randomizer (for potential mobs already instantiated anyway; else use a sole instantiation named object randomizer) //
	public static Object object_of(Object[] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	
	public static int[] integer_array_of(int[][] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	public static double[] decimal_array_of(double[][] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	public static String[] text_array_of(String[][] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	public static char[] character_array_of(char[][] exclusives)
	{
		return exclusives[integer_until(exclusives.length)];
	}
	
	
	
	
	public static int integer_of(int[][] exclusives)
	{
		return integer_of(exclusives[integer_until(exclusives.length)]);
	}
	
	
	
	public static double decimal_of(double[][] exclusives)
	{
		return decimal_of(exclusives[integer_until(exclusives.length)]);
	}
	
	
	
	public static String text_of(String[][] exclusives)
	{
		return text_of(exclusives[integer_until(exclusives.length)]);
	}
	
	
	
	public static char character_of(char[][] exclusives)
	{
		return character_of(exclusives[integer_until(exclusives.length)]);
	}
}