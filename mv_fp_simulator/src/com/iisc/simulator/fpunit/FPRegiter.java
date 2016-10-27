package com.iisc.simulator.fpunit;

import com.iisc.simulator.Word;

public class FPRegiter {
	
	/**
	*   The template will have to be formated according to the ISO standards as set out buy
	*   NIST , NIST also has a set of binary pictures to use for examples. This database is used for
	*   determaning the FAR(False Acceptance Rate) and FRR (False Rejection Rate)
	*
	*   First 7 are (elements in array , originx , originy , null , null , null ,null)
	*   The format is (x,y,r,degree ,number of ends,resultant degree) the 0 element in the array 
	*   is the size.
	*/

	// 601 cells Multi-Valued unit (Equivalent to a word in Regular processors)
	// There is a direct 1-1 mapping with this structure and Standard Minutia-Templates
	Word[] value = new Word[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];  //1 header + Maximum of 99 Minutia details
	
	public Long getWordUsageCount() {
		Long usage = 0l;
		for (int i = 0; i < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; i++) {
			usage += value[i].getAccessCount();
		}
		
		return usage;
	}
	
	public double[] getReisterContent() {
		double[] values = new double[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];
		for (int i = 0; i < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; i++) {
			values[i] = value[i].getValue();
		}
		return values;
	}

}
