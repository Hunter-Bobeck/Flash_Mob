package utilities;

public class Wait
{
    public static void milliseconds(int milliseconds)
	{
		try{Thread.sleep(milliseconds);}	catch(InterruptedException e){}
    }
}