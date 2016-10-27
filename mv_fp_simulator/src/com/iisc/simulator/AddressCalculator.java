package com.iisc.simulator;

import com.iisc.simulator.fpunit.FingerPrintUtil;

public class AddressCalculator {
	
	/**
	 * returns the Octal String used for reference
	 * @param address
	 * @return
	 */
	private static String getOctalAddress(Integer address) {
		return Integer.toOctalString( address );
	}
	
	/**
	 * returns the Octal String used for reference
	 * @param address
	 * @return
	 */
	private static String getBinaryAddress(Integer address) {
		return Integer.toBinaryString( address );
	}
	
	public static String getAddress(Integer address) {
		if(Block.BLK_SIZE == 2) {
			return getBinaryAddress(address);
		}
		else if (Block.BLK_SIZE == 8) {
			return getOctalAddress(address);
		}
		else if (Block.BLK_SIZE == 4) {
			return getBase4Address(address);
		}
		else {	//Only 2, 4 & 8 are supported
			return "0";
		}
	}

	private static String getBase4Address(Integer address) {
		String base4 = Long.toString(address, 4);
		return base4;
	}
}
