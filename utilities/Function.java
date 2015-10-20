package utilities;

public class Function
{
    public static int line(int input_1, int output_1, int input_2, int output_2, int input)
	{
		return (int) line((double) input_1, (double) output_1, (double) input_2, (double) output_2, (double) input);
	}
	
	public static double line(double input_1, double output_1, double input_2, double output_2, double input)
	{
		double slope = (output_2 - output_1)/(input_2 - input_1);	// both the numerator and the denominator are absolute difference, but here they are effectively since parities cancel out //
		return slope * input   +   output_1  -  slope * input_1;
	}
	
	
	
	
	public static double exponentiate(double exponent, double input)
	{
		return Math.pow(input, exponent);
	}
	public static double exponentiate(int exponent, double input)
	{
		return exponentiate((double) exponent, input);
	}
	
	
	public static double exponentiate(double exponent, int input)
	{
		return exponentiate(exponent, (double) input);
	}
	public static double exponentiate(int exponent, int input)
	{
		return exponentiate((double) exponent, input);
	}
}