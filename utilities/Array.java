package utilities;

import java.util.*;

public class Array
{
	public static String text(int[] integer_array)
	{
		return ""+Arrays.asList(integer_array);
	}
	
	public static String text(double[] decimal_array)
	{
		return ""+Arrays.asList(decimal_array);
	}
	
	public static String text(String[] text_array)
	{
		return ""+Arrays.asList(text_array);
	}
	
	public static String text(char[] character_array)
	{
		return ""+Arrays.asList(character_array);
	}
	
	public static String text(boolean[] binary_array)
	{
		return ""+Arrays.asList(binary_array);
	}
	
	
	
	
	public static int total(int[] integer_array)
	{
		int total = 0;
		for (int i = 0; i < integer_array.length; i++)
		{
			total += integer_array[i];
		}
		return total;
	}
	
	public static double total(double[] decimal_array)
	{
		double total = 0;
		for (int i = 0; i < decimal_array.length; i++)
		{
			total += decimal_array[i];
		}
		return total;
	}
	
	
	
	
	
	public static int[] copy(int[] integer_array)
	{
		if (integer_array == null)		return new int[] {};
		int[] copy = new int[integer_array.length];
		for (int i = 0; i < integer_array.length; i++)
		{
			copy[i] = integer_array[i];
		}
		return copy;
	}
	
	public static double[] copy(double[] decimal_array)
	{
		if (decimal_array == null)		return new double[] {};
		double[] copy = new double[decimal_array.length];
		for (int i = 0; i < decimal_array.length; i++)
		{
			copy[i] = decimal_array[i];
		}
		return copy;
	}
	
	public static String[] copy(String[] text_array)
	{
		if (text_array == null)		return new String[] {};
		String[] copy = new String[text_array.length];
		for (int i = 0; i < text_array.length; i++)
		{
			copy[i] = text_array[i];
		}
		return copy;
	}
	
	public static char[] copy(char[] character_array)
	{
		if (character_array == null)		return new char[] {};
		char[] copy = new char[character_array.length];
		for (int i = 0; i < character_array.length; i++)
		{
			copy[i] = character_array[i];
		}
		return copy;
	}
	
	
	public static String[][] copy(String[][] text_array_array)
	{
		if (text_array_array == null)		return new String[][] {};
		String[][] copy = new String[text_array_array.length][];
		for (int i = 0; i < text_array_array.length; i++)
		{
			copy[i] = text_array_array[i];
		}
		return copy;
	}
	
	
	
	
	public static int[] combine(int[] integer_array_1, int[] integer_array_2)
	{
		int integer_array_1_length, integer_array_2_length;
		if (integer_array_1 != null)	integer_array_1_length = integer_array_1.length;
		else	integer_array_1_length = 0;
		if (integer_array_2 != null)	integer_array_2_length = integer_array_2.length;
		else	integer_array_2_length = 0;
		int[] combined_integer_arrays = new int[integer_array_1_length + integer_array_2_length];
		if (integer_array_1 != null)
		{
			for (int i = 0; i < integer_array_1.length; i++)
			{
				combined_integer_arrays[i] = integer_array_1[i];
			}
		}
		if (integer_array_2 != null)
		{
			for (int i = 0; i < integer_array_2.length; i++)
			{
				combined_integer_arrays[integer_array_1_length + i] = integer_array_2[i];
			}
		}
		return combined_integer_arrays;
	}
	
	public static double[] combine(double[] decimal_array_1, double[] decimal_array_2)
	{
		int decimal_array_1_length, decimal_array_2_length;
		if (decimal_array_1 != null)	decimal_array_1_length = decimal_array_1.length;
		else	decimal_array_1_length = 0;
		if (decimal_array_2 != null)	decimal_array_2_length = decimal_array_2.length;
		else	decimal_array_2_length = 0;
		double[] combined_decimal_arrays = new double[decimal_array_1_length + decimal_array_2_length];
		if (decimal_array_1 != null)
		{
			for (int i = 0; i < decimal_array_1.length; i++)
			{
				combined_decimal_arrays[i] = decimal_array_1[i];
			}
		}
		if (decimal_array_2 != null)
		{
			for (int i = 0; i < decimal_array_2.length; i++)
			{
				combined_decimal_arrays[decimal_array_1_length + i] = decimal_array_2[i];
			}
		}
		return combined_decimal_arrays;
	}
	
	public static String[] combine(String[] text_array_1, String[] text_array_2)
	{
		int text_array_1_length, text_array_2_length;
		if (text_array_1 != null)		text_array_1_length = text_array_1.length;
		else	text_array_1_length = 0;
		if (text_array_2 != null)	text_array_2_length = text_array_2.length;
		else	text_array_2_length = 0;
		String[] combined_text_arrays = new String[text_array_1_length + text_array_2_length];
		if (text_array_1 != null)
		{
			for (int i = 0; i < text_array_1.length; i++)
			{
				combined_text_arrays[i] = text_array_1[i];
			}
		}
		if (text_array_2 != null)
		{
			for (int i = 0; i < text_array_2.length; i++)
			{
				combined_text_arrays[text_array_1_length + i] = text_array_2[i];
			}
		}
		return combined_text_arrays;
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3)
	{
		return combine(combine(text_array_1, text_array_2), text_array_3);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3), text_array_4);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4), text_array_5);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5), text_array_6);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6), text_array_7);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7, String[] text_array_8)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6, text_array_7), text_array_8);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7, String[] text_array_8, String[] text_array_9)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6, text_array_7, text_array_8), text_array_9);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7, String[] text_array_8, String[] text_array_9, String[] text_array_10)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6, text_array_7, text_array_8, text_array_9), text_array_10);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7, String[] text_array_8, String[] text_array_9, String[] text_array_10, String[] text_array_11)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6, text_array_7, text_array_8, text_array_9, text_array_10), text_array_11);
	}
	public static String[] combine(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5, String[] text_array_6, String[] text_array_7, String[] text_array_8, String[] text_array_9, String[] text_array_10, String[] text_array_11, String[] text_array_12)
	{
		return combine(combine(text_array_1, text_array_2, text_array_3, text_array_4, text_array_5, text_array_6, text_array_7, text_array_8, text_array_9, text_array_10, text_array_11), text_array_12);
	}
	
	public static char[] combine(char[] character_array_1, char[] character_array_2)
	{
		int character_array_1_length, character_array_2_length;
		if (character_array_1 != null)		character_array_1_length = character_array_1.length;
		else	character_array_1_length = 0;
		if (character_array_2 != null)		character_array_2_length = character_array_2.length;
		else	character_array_2_length = 0;
		char[] combined_character_arrays = new char[character_array_1_length + character_array_2_length];
		if (character_array_1 != null)
		{
			for (int i = 0; i < character_array_1.length; i++)
			{
				combined_character_arrays[i] = character_array_1[i];
			}
		}
		if (character_array_2 != null)
		{
			for (int i = 0; i < character_array_2.length; i++)
			{
				combined_character_arrays[character_array_1_length + i] = character_array_2[i];
			}
		}
		return combined_character_arrays;
	}
	
	public static Object[] combine(Object[] object_array_1, Object[] object_array_2)
	{
		int object_array_1_length, object_array_2_length;
		if (object_array_1 != null)		object_array_1_length = object_array_1.length;
		else	object_array_1_length = 0;
		if (object_array_2 != null)		object_array_2_length = object_array_2.length;
		else	object_array_2_length = 0;
		Object[] combined_object_arrays = new Object[object_array_1_length + object_array_2_length];
		if (object_array_1 != null)
		{
			for (int i = 0; i < object_array_1.length; i++)
			{
				combined_object_arrays[i] = object_array_1[i];
			}
		}
		if (object_array_2 != null)
		{
			for (int i = 0; i < object_array_2.length; i++)
			{
				combined_object_arrays[object_array_1_length + i] = object_array_2[i];
			}
		}
		return combined_object_arrays;
	}
	
	
	public static int[][] combine(int[][] integer_array_array_1, int[][] integer_array_array_2)
	{
		int integer_array_array_1_length, integer_array_array_2_length;
		if (integer_array_array_1 != null)	integer_array_array_1_length = integer_array_array_1.length;
		else	integer_array_array_1_length = 0;
		if (integer_array_array_2 != null)	integer_array_array_2_length = integer_array_array_2.length;
		else	integer_array_array_2_length = 0;
		int[][] combined_integer_array_arrays = new int[integer_array_array_1_length + integer_array_array_2_length][];
		if (integer_array_array_1 != null)
		{
			for (int i = 0; i < integer_array_array_1.length; i++)
			{
				combined_integer_array_arrays[i] = integer_array_array_1[i];
			}
		}
		if (integer_array_array_2 != null)
		{
			for (int i = 0; i < integer_array_array_2.length; i++)
			{
				combined_integer_array_arrays[integer_array_array_1_length + i] = integer_array_array_2[i];
			}
		}
		return combined_integer_array_arrays;
	}
	
	public static double[][] combine(double[][] decimal_array_array_1, double[][] decimal_array_array_2)
	{
		int decimal_array_array_1_length, decimal_array_array_2_length;
		if (decimal_array_array_1 != null)	decimal_array_array_1_length = decimal_array_array_1.length;
		else	decimal_array_array_1_length = 0;
		if (decimal_array_array_2 != null)	decimal_array_array_2_length = decimal_array_array_2.length;
		else	decimal_array_array_2_length = 0;
		double[][] combined_decimal_array_arrays = new double[decimal_array_array_1_length + decimal_array_array_2_length][];
		if (decimal_array_array_1 != null)
		{
			for (int  i = 0; i < decimal_array_array_1.length; i++)
			{
				combined_decimal_array_arrays[i] = decimal_array_array_1[i];
			}
		}
		if (decimal_array_array_2 != null)
		{
			for (int i = 0; i < decimal_array_array_2.length; i++)
			{
				combined_decimal_array_arrays[decimal_array_array_1_length + i] = decimal_array_array_2[i];
			}
		}
		return combined_decimal_array_arrays;
	}
	
	public static String[][] combine(String[][] text_array_array_1, String[][] text_array_array_2)
	{
		int text_array_array_1_length, text_array_array_2_length;
		if (text_array_array_1 != null)	text_array_array_1_length = text_array_array_1.length;
		else	text_array_array_1_length = 0;
		if (text_array_array_2 != null)	text_array_array_2_length = text_array_array_2.length;
		else	text_array_array_2_length = 0;
		String[][] combined_text_array_arrays = new String[text_array_array_1_length + text_array_array_2_length][];
		if (text_array_array_1 != null)
		{
			for (int  i = 0; i < text_array_array_1.length; i++)
			{
				combined_text_array_arrays[i] = text_array_array_1[i];
			}
		}
		if (text_array_array_2 != null)
		{
			for (int i = 0; i < text_array_array_2.length; i++)
			{
				combined_text_array_arrays[text_array_array_1_length + i] = text_array_array_2[i];
			}
		}
		return combined_text_array_arrays;
	}
	
	public static char[][] combine(char[][] character_array_array_1, char[][] character_array_array_2)
	{
		int character_array_array_1_length, character_array_array_2_length;
		if (character_array_array_1 != null)	character_array_array_1_length = character_array_array_1.length;
		else	character_array_array_1_length = 0;
		if (character_array_array_2 != null)	character_array_array_2_length = character_array_array_2.length;
		else	character_array_array_2_length = 0;
		char[][] combined_character_array_arrays = new char[character_array_array_1_length + character_array_array_2_length][];
		if (character_array_array_1 != null)
		{
			for (int  i = 0; i < character_array_array_1.length; i++)
			{
				combined_character_array_arrays[i] = character_array_array_1[i];
			}
		}
		if (character_array_array_2 != null)
		{
			for (int i = 0; i < character_array_array_2.length; i++)
			{
				combined_character_array_arrays[character_array_array_1_length + i] = character_array_array_2[i];
			}
		}
		return combined_character_array_arrays;
	}
	
	
	
	
	public static String[] permute(String[] text_array_1, String[] text_array_2)
	{
		if (text_array_1 == null  ||  text_array_1.length == 0  || text_array_2 == null  ||  text_array_2.length == 0)		return null;
		String[] permuted_text_arrays = new String[text_array_1.length * text_array_2.length];
		for (int text_array_1_index = 0; text_array_1_index < text_array_1.length; text_array_1_index++)
		{
			for (int text_array_2_index = 0; text_array_2_index < text_array_2.length; text_array_2_index++)
			{
				permuted_text_arrays[text_array_1_index * text_array_2.length  +  text_array_2_index] = text_array_1[text_array_1_index]+text_array_2[text_array_2_index];
			}
		}
		return permuted_text_arrays;
	}
	public static String[] permute(String[] text_array_1, String[] text_array_2, String[] text_array_3)
	{
		return permute(permute(text_array_1, text_array_2), text_array_3);
	}
	public static String[] permute(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4)
	{
		return permute(permute(text_array_1, text_array_2, text_array_3), text_array_4);
	}
	public static String[] permute(String[] text_array_1, String[] text_array_2, String[] text_array_3, String[] text_array_4, String[] text_array_5)
	{
		return permute(permute(text_array_1, text_array_2, text_array_3, text_array_4), text_array_5);
	}
	
	
	
	
	public static boolean containment(int[] integer_array, int integer) 
	{
		for (int element : integer_array)
		{
			if (element == integer)		return true;
		}
		return false;
	}
	
	public static boolean containment(double[] decimal_array, double decimal) 
	{
		for (double element : decimal_array)
		{
			if (element == decimal)		return true;
		}
		return false;
	}
	
	public static boolean containment(String[] text_array, String text)
	{
		for (String element : text_array)
		{
			if (element.equals(text))		return true;
		}
		return false;
	}
	public static boolean containment_case_ignored(String[] text_array, String text)
	{
		return containment(Text.upper(text_array), Text.upper(text));
	}
	
	public static boolean containment(char[] character_array, char character)
	{
		for (char element : character_array)
		{
			if (element == character)		return true;
		}
		return false;
	}
	
	
	public static boolean containment(int[][] integer_array_array, int integer) 
	{
		for (int[] element : integer_array_array)
		{
			if (containment(element, integer))		return true;
		}
		return false;
	}
	
	public static boolean containment(double[][] decimal_array_array, double decimal) 
	{
		for (double[] element : decimal_array_array)
		{
			if (containment(element, decimal))		return true;
		}
		return false;
	}
	
	public static boolean containment(String[][] text_array_array, String text)
	{
		for (String[] element : text_array_array)
		{
			if (containment(element, text))		return true;
		}
		return false;
	}
	public static boolean containment_case_ignored(String[][] text_array_array, String text)
	{
		return containment(Text.upper(text_array_array), Text.upper(text));
	}
	
	public static boolean containment(char[][] character_array_array, char character)
	{
		for (char[] element : character_array_array)
		{
			if (containment(element, character))	return true;
		}
		return false;
	}
	
	
	
	public static boolean subcontainment(String text, String[] text_array)
	{
		for (String element : text_array)
		{
			if (text.contains(element))		return true;
		}
		return false;
	}
	public static boolean subcontainment_case_ignored(String text, String[] text_array)
	{
		return subcontainment(Text.upper(text), Text.upper(text_array));
	}
	public static boolean subcontainment_ignoring(String text, String[] text_array, String[] ignoreds)
	{
		Text.remove(ignoreds, text);
		Text.remove(ignoreds, text_array);
		return subcontainment(text, text_array);
	}
	
	
	public static boolean subcontainment(String text, String[][] text_array_array)
	{
		for (String[] array_element : text_array_array)
		{
			for (String element : array_element)
			{
				if (text.contains(element))		return true;
			}
		}
		return false;
	}
	public static boolean subcontainment_case_ignored(String text, String[][] text_array_array)
	{
		return subcontainment(Text.upper(text), Text.upper(text_array_array));
	}
	
	
	public static int subcontainment_index(String text, String[] text_array)
	{
		for (int i = 0; i < text_array.length; i++)
		{
			if (text.contains(text_array[i]))	return i;
		}
		return -1;
	}
	public static int subcontainment_case_ignored_index(String text, String[] text_array)
	{
		for (int i = 0; i < text_array.length; i++)
		{
			if (Text.upper(text).contains(Text.upper(text_array[i])))	return i;
		}
		return -1;
	}
	public static int subcontainment_index_ignoring(String text, String[] text_array, String[] ignoreds)
	{
		Text.remove(ignoreds, text);
		Text.remove(ignoreds, text_array);
		return subcontainment_index(text, text_array);
	}
	
	
	
	public static int index(int[] integer_array, int integer)
	{
		for (int i = 0; i < integer_array.length; i++)
		{
			if (integer_array[i] == integer)	return i;
		}
		return -1;
	}
	
	public static int index(double[] decimal_array, double decimal)
	{
		for (int i = 0; i < decimal_array.length; i++)
		{
			if (decimal_array[i] == decimal)	return i;
		}
		return -1;
	}
	
	public static int index(String[] text_array, String text)
	{
		for (int i = 0; i < text_array.length; i++)
		{
			if (text_array[i].equals(text))	return i;
		}
		return -1;
	}
	
	public static int index(char[] character_array, char character)
	{
		for (int i = 0; i < character_array.length; i++)
		{
			if (character_array[i] == character)	return i;
		}
		return -1;
	}
	
	
	public static int[] index(int[][] integer_array_array, int integer)
	{
		int[] indexes = new int[2];
		for (int i = 0; i < integer_array_array.length; i++)
		{
			if (containment(integer_array_array[i], integer))
			{
				indexes[0] = i;
				indexes[1] = index(integer_array_array[indexes[0]], integer);
				return indexes;
			}
		}
		indexes[0] = -1;
		indexes[1] = -1;
		return indexes;
	}
	
	public static int[] index(double[][] decimal_array_array, double decimal)
	{
		int[] indexes = new int[2];
		for (int i = 0; i < decimal_array_array.length; i++)
		{
			if (containment(decimal_array_array[i], decimal))
			{
				indexes[0] = i;
				indexes[1] = index(decimal_array_array[indexes[0]], decimal);
				return indexes;
			}
		}
		indexes[0] = -1;
		indexes[1] = -1;
		return indexes;
	}
	
	public static int[] index(String[][] text_array_array, String text)
	{
		int[] indexes = new int[2];
		for (int i = 0; i < text_array_array.length; i++)
		{
			if (containment(text_array_array[i], text))
			{
				indexes[0] = i;
				indexes[1] = index(text_array_array[indexes[0]], text);
				return indexes;
			}
		}
		indexes[0] = -1;
		indexes[1] = -1;
		return indexes;
	}
	
	public static int[] index(char[][] character_array_array, char character)
	{
		int[] indexes = new int[2];
		for (int i = 0; i < character_array_array.length; i++)
		{
			if (containment(character_array_array[i], character))
			{
				indexes[0] = i;
				indexes[1] = index(character_array_array[indexes[0]], character);
				return indexes;
			}
		}
		indexes[0] = -1;
		indexes[1] = -1;
		return indexes;
	}
	
	
	
	
    public static int minimum(int[] integer_array)
	{
		int minimum = integer_array[0];
		for (int element : integer_array)
		{
			if (element < integer_array[0])    minimum = element;
		}
		return minimum;
    }
	
    public static double minimum(double[] decimal_array)
	{
		double minimum = decimal_array[0];
		for (double element : decimal_array)
		{
			if (element < decimal_array[0])    minimum = element;
		}
		return minimum;
    }
	
	
	
	
	public static String[] index_removed(String[] text_array, int index)
	{
		if (text_array == null)		return new String[] {};
		else if (index >= text_array.length)		return copy(text_array);
		String[] text_array_index_removed = new String[text_array.length - 1];
		for (int i = 0; i < text_array.length; i++)
		{
			if (i < index)		text_array_index_removed[i] = text_array[i];
			else if (i > index)		text_array_index_removed[i - 1] = text_array[i];
		}
		return text_array_index_removed;
	}
	
	public static int[] index_removed(int[] integer_array, int index)
	{
		if (integer_array == null)		return new int[] {};
		else if (index >= integer_array.length)		return copy(integer_array);
		int[] integer_array_index_removed = new int[integer_array.length - 1];
		for (int i = 0; i < integer_array.length; i++)
		{
			if (i < index)		integer_array_index_removed[i] = integer_array[i];
			else if (i > index)		integer_array_index_removed[i - 1] = integer_array[i];
		}
		return integer_array_index_removed;
	}
	
	
	public static String[][] index_removed(String[][] text_array_array, int index)
	{
		if (text_array_array == null)		return new String[][] {};
		else if (index >= text_array_array.length)		return copy(text_array_array);
		String[][] text_array_array_index_removed = new String[text_array_array.length - 1][];
		for (int i = 0; i < text_array_array.length; i++)
		{
			if (i < index)		text_array_array_index_removed[i] = text_array_array[i];
			else if (i > index)		text_array_array_index_removed[i - 1] = text_array_array[i];
		}
		return text_array_array_index_removed;
	}
}