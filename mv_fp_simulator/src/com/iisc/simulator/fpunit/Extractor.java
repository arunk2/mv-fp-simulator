package com.iisc.simulator.fpunit;

public class Extractor {
	
	public int cycles = 1;
	
	//Orientation field estimation
	private FingerPrintAreaLocator fingerPrintAreaLocator = new FingerPrintAreaLocator();
	private RidgeExtractor ridgeExtractor = new RidgeExtractor();
	private Thinner thinner = new Thinner();
	private MinutiaExtractor minutiaExtractor = new MinutiaExtractor();
	
	public Integer extract() {
		
		//Fingerprint Area Extraction
		fingerPrintAreaLocator.getFingerPrintOrigin(null);
		cycles += fingerPrintAreaLocator.cycles;
		
		ridgeExtractor.getFingerPrintClassification(null, null);
		cycles += ridgeExtractor.cycles;
		
		thinner.ThinningHilditch(null);
		cycles += thinner.cycles;
		
		minutiaExtractor.getFingerPrintTemplate(null, null);
		cycles += minutiaExtractor.cycles;
		
		return 1; //Success
		
	}
	
}
