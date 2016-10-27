package com.iisc.simulator;

import com.iisc.simulator.fpunit.FingerPrintUtil;

public class FPTemplate {
	
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
	// defined in FingerPrintUtil.FP_TEMPLATE_MAX_SIZE
	double[] value;  //1 header + Maximum of 99 Minutia details

}
