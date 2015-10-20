package utilities;

public class Numbers
{	
    public static int greatest_common_factor_of(int first, int second)
	{
	   if (second == 0)		return first;
	   return greatest_common_factor_of(second, first % second);
    }
	
	public static int characters_in(int integer)
	{
		String temporary = ""+integer;
		return temporary.length();
	}
	
	// imperfect //
	public static int[] postdecimal_places_of_and_integer_from_shifted_decimal(double decimal)
	{
		int[] places_and_integer = new int[2];
		String decimal_as_text = ""+decimal;
		if (decimal_as_text.contains("0000"))
			decimal_as_text = decimal_as_text.substring(decimal_as_text.indexOf(".") + 1, decimal_as_text.indexOf("0000"));
		else if (decimal_as_text.contains("9999"))
			decimal_as_text = decimal_as_text.substring(decimal_as_text.indexOf(".") + 1, decimal_as_text.indexOf("9999"));
		places_and_integer[0] = decimal_as_text.length();
		places_and_integer[1] = (int) (decimal * Math.pow(10, places_and_integer[0]))  +  1;
		return places_and_integer;
	}
	
	private static char[] remainder_digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	// number conversion to decimal: returns the text for an inputted-base inputted-number text converted to decimal //
	public static int from_base_to_decimal(int base, String number_text)
	{
		int sum = 0;
		// iterate from the ones digit ascending //
		for (int i = number_text.length() - 1; i >= 0; i--)		sum += Array.index(remainder_digits, number_text.charAt(i)) * Math.pow(base, number_text.length() - 1 - i);
		return sum;
	}
	// recursive number conversion to base: returns the text for an inputted (decimal) number converted to the inputted base (that must be no more than 16, hexadecimal) //
	public static String to_base(int number, int base)
	{
		// relevant calculations //
		int quotient = number / base, remainder = number % base;
		// recursion end //
		if (quotient == 0)		return ""+remainder_digits[remainder];
		// recursion //
		return to_base(quotient, base)+remainder_digits[remainder];
	}
	// recursive number conversion from base to decimal: returns the text for an inputted-base inputted-number converted to the inputted base (that must be no more than 16, hexadecimal)
	public static String from_base_to_base(String number_text, int original_base, int target_base)
	{
		return to_base(from_base_to_decimal(original_base, number_text), target_base);
	}
}