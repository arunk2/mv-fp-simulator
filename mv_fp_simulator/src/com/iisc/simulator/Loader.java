package com.iisc.simulator;

import com.iisc.simulator.fpunit.FPUnit;
import com.iisc.simulator.fpunit.FingerPrintUtil;

public class Loader<K, V> {

	private Cache<K, V> cache;
	private FPUnit fpUnit;
	
	public Loader(Cache<K, V> cache) {
		this.cache = cache; 
	}
	
	public Word load(String address) {
//		System.out.println("Loader: get addr - "+address);
		 String tag = address.substring(0, address.length()-1)+"0";
		 Block blk = (Block) cache.get((K)tag);
		 Integer word = new Integer(address.charAt(address.length()-1)-'0');
		 return blk.words[word];
		 
	}
	
	public double[] loadTemplate(Integer startAddress, String register) {
		Word[] words = new Word[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];
		double[] r1 = new double[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];
		for (int i = 0; i < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; i++) {
			words[i] = this.load(AddressCalculator.getAddress(startAddress));
			r1[i] = words[i].getValue();
			startAddress++;
		}
		
		if (register.equalsIgnoreCase("R1")) {
			fpUnit.loadRegister1(words);
		}
		else if (register.equalsIgnoreCase("R2")) {
			fpUnit.loadRegister2(words);
		}
		
		return r1;
	}

	public FPUnit getFpUnit() {
		return fpUnit;
	}

	public void setFpUnit(FPUnit fpUnit) {
		this.fpUnit = fpUnit;
	}
	
}
