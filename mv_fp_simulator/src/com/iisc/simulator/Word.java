package com.iisc.simulator;

public class Word {
	private double value;
	public String rawValue;
	private Long accessCount = 0l;
	
	public void convertToRawValue() {
		rawValue = Double.toHexString(value);
	}
	
	public void convertToValue() {
		value = new Double(rawValue);
	}

	public double getValue() {
		accessCount++;
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Long getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Long accessCount) {
		this.accessCount = accessCount;
	}
}
