package banners;

import utilities.*;

public class Intro
{
    public Intro()
	{
		System.out.print("\n\n\n");
		
		
		Fake_Loading_Bar fake_loading_bar = new Fake_Loading_Bar();		System.out.println();
		Title title = new Title();		System.out.println();
		Wait.milliseconds(250);
		System.out.print("\n\n");
		
    }
}