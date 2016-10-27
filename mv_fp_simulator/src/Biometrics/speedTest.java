package Biometrics;

import com.iisc.simulator.AddressCalculator;
import com.iisc.simulator.Word;

public class speedTest {
	
	public static void speedTest(int loop)
	{
	    System.out.print("double: ");
	    for (int i = 0; i < loop; i++)
	    {
	        double a = 1000, b = 45, c = 12000, d = 2, e = 7, f = 1024;
	        a = Math.sin(a);
	        b = Math.asin(b);
	        c = Math.sqrt(c);
	        d = d + d - d + d;
	        e = e * e + e * e;
	        f = f / f / f / f / f;
	    }
	}

	public static void speedTest2(int loop)
	{
	    System.out.print("float: ");
	    for (int i = 0; i < loop; i++)
	    {
	        float a = 1000, b = 45, c = 12000, d = 2, e = 7, f = 1024;
	        a = (float) Math.sin(a);
	        b = (float) Math.asin(b);
	        c = (float) Math.sqrt(c);
	        d = d + d - d + d;
	        e = e * e + e * e;
	        f = f / f / f / f / f;
	    }
	}

	public static void main(String[] args)
	{
//	    long time = System.currentTimeMillis();
//	    speedTest(5 * 1000000);
//	    System.out.println("time needed: " + (System.currentTimeMillis() - time));
//
//	    time = System.currentTimeMillis();
//	    speedTest2(5 * 1000000);
//	    System.out.println("time needed: " + (System.currentTimeMillis() - time));
	    
		Word blk = new Word();
	    blk.setValue(-12345.0);
	    blk.convertToRawValue();
	    System.out.println("Blk value - "+blk.getValue()+" - raw - "+blk.rawValue);
	    blk.convertToValue();
	    System.out.println("Blk value - "+blk.getValue()+" - raw - "+blk.rawValue);
	    
	    System.out.println("Addr 100 : octal = "+AddressCalculator.getAddress(100)+" ; Binay = "+AddressCalculator.getAddress(100));
	    
	    
	    
	}
}
