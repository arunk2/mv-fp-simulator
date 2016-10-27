package com.iisc.simulator;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.ListModel;

public class SimulatorForm extends JFrame {

	private SimulatorPanel simPanel;

	public SimulatorForm() {
				
		simPanel = new SimulatorPanel(this.getContentPane());
		this.setTitle("Multi Value - FingerPrint Unit - Simulator");
		this.setSize(new Dimension(900, 700));
		
	}

	public void updateR1Image(BufferedImage image) {
		simPanel.getR1Image().setBufferedImage(image);
	}

	public void updateR2Image(BufferedImage image) {
		simPanel.getR2Image().setBufferedImage(image);
	}
	
	public void updateR1Value(String value) {
		simPanel.getTxtR1Value().setText(value);
	}
	
	public void updateR2Value(String value) {
		simPanel.getTxtR2Value().setText(value);
	}
	
	public void addInstruction(String instruction) {
		
		DefaultListModel model = (DefaultListModel) simPanel.getTxtInstructions().getModel();
		model.addElement(instruction);
		
	}
	
	public void addMemoryValue(String blkAddress, String value) {

		DefaultListModel model = (DefaultListModel) simPanel.getTxtMemory().getModel();
		model.addElement(blkAddress+" - "+value);

	}
	
	public void addCacheValue(String blkAddress, String value) {

		DefaultListModel model = (DefaultListModel) simPanel.getTxtCache().getModel();
		model.addElement(blkAddress+" - "+value);

	}
	
	public void resetCache() {
		DefaultListModel model = (DefaultListModel) simPanel.getTxtCache().getModel();
		model.removeAllElements();
	}
	
	public void nextInstruction() {
		int curIndex = simPanel.getTxtInstructions().getSelectedIndex();
		simPanel.getTxtInstructions().setSelectedIndex(curIndex+1);
	}

	public void updateStatusValue(String value) {
		simPanel.getTextStatus().setText(value);
	}
	
	public void addStatisticsValue(String title, String value) {

		DefaultListModel model = (DefaultListModel) simPanel.getTxtStatistics().getModel();
		model.addElement(title+" - "+value);

	}

	public void resetStatistics() {
		DefaultListModel model = (DefaultListModel) simPanel.getTxtStatistics().getModel();
		model.removeAllElements();
	}

}