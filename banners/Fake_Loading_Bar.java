package banners;

import utilities.*;

public class Fake_Loading_Bar
{
    public Fake_Loading_Bar()
	{
		// 0% //
		System.out.print("▯▯▯▯▯▯▯▯▯▯ 0%    ");
		Wait.milliseconds(Random.integer_to(700));
		// 10% //
		System.out.print("\r");
		System.out.print("▮▯▯▯▯▯▯▯▯▯ 10%   ");
		Wait.milliseconds(Random.integer_to(500));
		// 20% //
		System.out.print("\r");
		System.out.print("▮▮▯▯▯▯▯▯▯▯ 20%   ");
		Wait.milliseconds(Random.integer_to(400));
		// 30% //
		System.out.print("\r");
		System.out.print("▮▮▮▯▯▯▯▯▯▯ 30%   ");
		Wait.milliseconds(Random.integer_to(300));
		// 40% //
		System.out.print("\r");
		System.out.print("▮▮▮▮▯▯▯▯▯▯ 40%   ");
		Wait.milliseconds(Random.integer_to(300));
		// 50% //
		System.out.print("\r");
		System.out.print("▮▮▮▮▮▯▯▯▯▯ 50%   ");
		Wait.milliseconds(Random.integer_to(600));
		// 60% //
		System.out.print("\r");
		System.out.print("▮▮▮▮▮▮▯▯▯▯ 60%   ");
		Wait.milliseconds(Random.integer_to(250));
		// 70% //
		System.out.print("\r");
		System.out.print("▮▮▮▮▮▮▮▯▯▯ 70%   ");
		Wait.milliseconds(Random.integer_to(350));
		// 80% //
		System.out.print("\r");
		System.out.print("▮▮▮▮▮▮▮▮▯▯ 80%   ");
		Wait.milliseconds(Random.integer_to(250));

		System.out.print("\r");
		System.out.print("▮▮▮▮▮▮▮▮▮▯ 90%   ");
		Wait.milliseconds(Random.integer_to(800));

		System.out.print("\r");
		System.out.print("▮▮▮▮▮▮▮▮▮▮ 100%  ");
		Wait.milliseconds(300);

		System.out.print("\r");
		System.out.print("                 ");
		
		System.out.println();
    }
}