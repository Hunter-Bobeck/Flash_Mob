// Prompt: contains static methods for user input of data literals; they are generally ordered by freedom; a single parameter for one of these methods will be the lower bound //

package utilities;

import java.util.Scanner;
import java.util.ArrayList;

public class Prompt
{
	public static int integer()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("› ");
		while (!scanner.hasNextInt())
		{
			System.out.print("🅧: not an integer\n› ");
			scanner.next();
		}
		int integer_input = scanner.nextInt();
		return integer_input;
	}

	
	public static int integer_from()
	{
		return integer();
	}
	
	
    public static int integer_from(int lower)
	{
		return integer_from(lower, Integer.MAX_VALUE);
	}
	
	
    public static int integer_from(int lower, int upper)
	{
		Scanner scanner = new Scanner(System.in);
		
		if (lower > upper)
		{
			int temporary = lower;
			lower = upper;
			upper = temporary;
		}
		
		int integer_input = lower - 1;
		int error_count = 0;
		
		System.out.print("› ");
		while (integer_input < lower  ||  integer_input > upper)
		{
			if (error_count > 0)		System.out.print("🅧: not an integer from "+lower+" to "+upper+"\n› ");
			error_count++;
			while (!scanner.hasNextInt())
			{
				System.out.print("🅧: not an integer from "+lower+" to "+upper+"\n› ");
				scanner.next();
			}
			integer_input = scanner.nextInt();
		}
		return integer_input;
    }
	
	
	public static int integer_between()
	{
		return integer();
	}
	
	
	public static int integer_between(int lower)
	{
		if (lower + 1  >=  Integer.MAX_VALUE)
		{
			System.out.println("ERROR:\nPrompt:\ninteger_between(int lower):\nif (lower + 1  >=  Integer.MAX_VALUE) returned true; return 0;");
			return 0;
		}
		return integer_from(lower + 1, Integer.MAX_VALUE);
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
				System.out.println("ERROR:\nPrompt:\ninteger_between(int lower, int upper):\nif (lower + 1  >  upper - 1):\nif (lower + 1  >  upper - 1) returned true; return 0;");
				return 0;
			}
		}
		return integer_from(lower + 1, upper - 1);
	}
	
	
	public static int integer_of(int[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String error = "🅧: not an integer of ";
		for (int i = 0; i < exclusives.length; i++)
		{
			if (i == 0)		error += exclusives[i];
			else if (i  <  exclusives.length - 1)		error += ", "+exclusives[i];
			else if (i  ==  exclusives.length - 1   &&   exclusives.length == 2)		error += " and "+exclusives[i];
			else		error += ", and "+exclusives[i];
		}
		error += "\n› ";
		int integer_input;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print(error);
			error_count++;
			while (!scanner.hasNextInt())
			{
				System.out.print(error);
				scanner.next();
			}
			integer_input = scanner.nextInt();			
		}
		while (!Array.containment(exclusives, integer_input));
		return integer_input;
	}
	
	
	public static int integer_from_or_of(int lower, int upper, int[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		if (lower > upper)
		{
			int temporary = lower;
			lower = upper;
			upper = temporary;
		}
		
		String error = "🅧: not an integer from "+lower+" to "+upper;
		boolean any_exclusives_unfromed_yet = false;
		ArrayList<Integer> unique_exclusives = new ArrayList<Integer>();
		int integer_input = lower - 1;
		int last_unique_exclusive_index = exclusives.length - 1;
		for (int i = exclusives.length - 1; i >= 0; i--)
		{
			if (exclusives[i] < lower  ||  exclusives[i] > upper)
			{
				last_unique_exclusive_index = i;
				break;
			}
		}
		for (int i = 0; i  <  last_unique_exclusive_index + 1; i++)
		{
			if (exclusives[i] < lower  ||  exclusives[i] > upper)
			{
				if (i == last_unique_exclusive_index)		error += " nor "+exclusives[i];
				else if (!any_exclusives_unfromed_yet)
				{
					error += " nor of "+exclusives[i];
					any_exclusives_unfromed_yet = true;
				}
				else if (i < last_unique_exclusive_index)
				{
					error += ", "+exclusives[i];
					unique_exclusives.add(exclusives[i]);
				}
				else if (i == last_unique_exclusive_index  &&  unique_exclusives.size() == 1)
				{
					error += ", "+exclusives[i];
					unique_exclusives.add(exclusives[i]);
				}
				else		error += ", and "+exclusives[i];
				unique_exclusives.add(exclusives[i]);
				if (exclusives[i] <= integer_input)			integer_input = exclusives[i] - 1;
			}
		}
		error += "\n› ";
		int[] unique_exclusives_converted = new int[unique_exclusives.size()];
		for (int i = 0; i < unique_exclusives.size(); i++)		unique_exclusives_converted[i] = unique_exclusives.get(i);
		
		int error_count = 0;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print(error);
			error_count++;
			while (!scanner.hasNextInt())
			{
				System.out.print(error);
				scanner.next();
			}
			integer_input = scanner.nextInt();
		}
		while ((integer_input < lower  ||  integer_input > upper)  &&  !Array.containment(unique_exclusives_converted, integer_input));
		return integer_input;
	}
	
	
	
	public static char character()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("› ");
		return scanner.nextLine().charAt(0);
	}
	
	
	public static char character_of(char[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		char character_input = '\u0000';
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print("🅧: not an allowed character\n› ");
			error_count++;
			if (scanner.hasNextLine())		character_input = scanner.nextLine().charAt(0);
		}
		while (!Array.containment(exclusives, character_input));
		return character_input;
	}
	
	
	
	public static String text()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("› ");
		String text_input = scanner.nextLine();
		while (text_input.equals(""))
		{
			System.out.print("🅧: text required\n› ");
			text_input = scanner.nextLine();
		}
		return text_input;
	}
	
	
	public static String text_of(String[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String text_input = null;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print("🅧: not allowed text\n› ");
			error_count++;
			if (scanner.hasNextLine())		text_input = scanner.nextLine();
		}
		while (!Array.containment(exclusives, text_input));
		return text_input;
	}
	
	public static String text_of_explanatory(String[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String text_input = null;
		
		String allowance = "🅧: not allowed text: ";
		for (int i = 0; i < exclusives.length; i++)
		{
			if (i == 0)		allowance += exclusives[i];
			else if (i < exclusives.length - 1)		allowance += ", "+exclusives[i];
			else if (i  ==  exclusives.length - 1   &&   exclusives.length == 2)		allowance += " or "+exclusives[i];
			else		allowance += ", or "+exclusives[i];
		}
		allowance += "\n› ";
		System.out.print("› ");
		do
		{
			if (error_count > 0)		Type.delay(allowance, 20);
			error_count++;
			if (scanner.hasNextLine())		text_input = scanner.nextLine();
		}
		while (!Array.containment(exclusives, text_input));
		return text_input;
	}
	
	public static String text_of_unexplanatory(String[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String text_input = null;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print("🅧\n› ");
			error_count++;
			if (scanner.hasNextLine())		text_input = scanner.nextLine();
		}
		while (!Array.containment(exclusives, text_input));
		return text_input;
	}
	public static String text_of_unexplanatory_capitalization_ignored(String[] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String text_input = null;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print("🅧\n› ");
			error_count++;
			if (scanner.hasNextLine())		text_input = scanner.nextLine();
		}
		while (!Array.containment_case_ignored(exclusives, text_input));
		return text_input;
	}
	
	
	public static String text_inverse_containing_unexplanatory(String[][] exclusives)
	{
		Scanner scanner = new Scanner(System.in);
		
		int error_count = 0;
		String text_input = null;
		
		System.out.print("› ");
		do
		{
			if (error_count > 0)		System.out.print("🅧\n› ");
			error_count++;
			if (scanner.hasNextLine())		text_input = scanner.nextLine();
		}
		while (!Array.subcontainment(text_input, exclusives));
		return text_input;
	}
}