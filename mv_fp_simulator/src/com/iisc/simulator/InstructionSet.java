package com.iisc.simulator;

import java.util.List;

public class InstructionSet {

	// Instructions like
	// Load Template (from multiValue Memory to Register)
	// Extract FullQualified-FilePath
	// Store Template (from Register to Memory)
	// Compare (Templates inside Registers) - it is programmers responsibility
	// to load templates for comparison

	private List<String> instructions;

	public InstructionSet(List<String> instructions) {
		this.instructions = instructions;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

}
