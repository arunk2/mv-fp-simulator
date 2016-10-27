package com.iisc.simulator;

public class CPUClock {
	
	Long clock = 0L;
	
	public void reset() {
		clock = 0L;
	}
	
	public Long tick() {
		clock++;
		return clock;
	}

}
