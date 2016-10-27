package com.iisc.simulator;

public class Block {

	//8 Words per block
    public static final int BLK_SIZE = 8;
	public Word[] words = new Word[BLK_SIZE];
	
	@Override
	public String toString() {
		
		String temp = "";
		
		for (Word word : this.words) {
			if(word != null)
				temp = temp + word.rawValue + ";";
		}
		return temp;
	}
	
}
