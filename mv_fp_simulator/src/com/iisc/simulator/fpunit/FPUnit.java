package com.iisc.simulator.fpunit;

import com.iisc.simulator.Word;

public class FPUnit {
	
	private Integer status = 0;	//Flag storing operation results
	
	private FPRegiter registers1;
	private FPRegiter registers2;
	private Segmentation segmentation = new Segmentation();
	//Enhancement Unit
	private Extractor extrator = new Extractor();
	private Comparator comparator = new Comparator();
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public FPRegiter getRegisters1() {
		return registers1;
	}
	public void setRegisters1(FPRegiter registers1) {
		this.registers1 = registers1;
	}
	public FPRegiter getRegisters2() {
		return registers2;
	}
	public void setRegisters2(FPRegiter registers2) {
		this.registers2 = registers2;
	}
	public void loadRegister1(Word[] words) {
		registers1 = new FPRegiter();
		registers1.value = words;
	}
	public void loadRegister2(Word[] words) {
		registers2 = new FPRegiter();
		registers2.value = words;
	}
	public int compare() {
		printStatistics();
		status = comparator.match(registers1.value, registers2.value,65,false);
		printStatistics();
		return status;
	}
	
	public int extract() {
		status = extrator.extract();
		return status;
	}
	
	public void printStatistics() {
		System.out.println("Register 1 usage - "+registers1.getWordUsageCount());
		System.out.println("Register 2 usage - "+registers2.getWordUsageCount());
	}
	
	
}
