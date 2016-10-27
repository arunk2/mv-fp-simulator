package com.iisc.simulator;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;


/**
 * @author Arun kumar K
 */
public class SimulatorPanel extends JPanel {
	
	class BJPanel extends JPanel {
		public BufferedImage bi;

		public BJPanel() {
		}

		public BJPanel(BufferedImage bi) {
			this.bi = bi;
			setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
		}

		public void setBufferedImage(BufferedImage bi) {
			this.bi = bi;
			setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
			this.repaint();
		}

		public void paintComponent(Graphics g) {
			g.drawImage(bi, 0, 0, this);
		}
	}
	
	public SimulatorPanel(Container contentPane) {
		initComponents(contentPane);
	}
	
	private void initComponents(Container contentPane) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Arun kumar K
		panelInstruction = new JPanel();
		lblInstructions = new JLabel();
		scrollInstruction = new JScrollPane();
		txtInstructions = new JList(new DefaultListModel());
		textStatus = new JTextField();
		lblStatus = new JLabel();
		panelRegisters = new JPanel();
		panelR1 = new JPanel();
		lblRegister1 = new JLabel();
		scrollR1 = new JScrollPane();
		
		txtR1Value = new JTextField();
		panelR2 = new JPanel();
		lblRegister2 = new JLabel();
		scrollR2 = new JScrollPane();
		
		txtR2Value = new JTextField();
		panelInstruction2 = new JPanel();
		lblMemory = new JLabel();
		scrollInstruction2 = new JScrollPane();
		txtMemory = new JList(new DefaultListModel());
		lblCache = new JLabel();
		lblStatics = new JLabel();
		scrollInstruction3 = new JScrollPane();
		txtCache = new JList(new DefaultListModel());
		scrollInstruction4 = new JScrollPane();
		txtStatistics = new JList(new DefaultListModel());

		//======== this ========
		setName("Simulator-Multivalued FingerPrint unit");
//		Container contentPane = getContentPane();

		//======== panelInstruction ========
		{

			//---- lblInstructions ----
			lblInstructions.setText("Instructions");

			//======== scrollInstruction ========
			{

				//---- txtInstructions ----
//				txtInstructions.setEditable(false);
				scrollInstruction.setViewportView(txtInstructions);
			}

			//---- lblStatus ----
			lblStatus.setText("FP Unit - Status");

			GroupLayout panelInstructionLayout = new GroupLayout(panelInstruction);
			panelInstruction.setLayout(panelInstructionLayout);
			panelInstructionLayout.setHorizontalGroup(
				panelInstructionLayout.createParallelGroup()
					.addGroup(panelInstructionLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelInstructionLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(panelInstructionLayout.createSequentialGroup()
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textStatus, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollInstruction, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblInstructions, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, Short.MAX_VALUE))
			);
			panelInstructionLayout.setVerticalGroup(
				panelInstructionLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, panelInstructionLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblInstructions, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(scrollInstruction, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
						.addGap(9, 9, 9)
						.addGroup(panelInstructionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(textStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
		}

		//======== panelRegisters ========
		{
			panelRegisters.setLayout(new GridLayout(1, 2, 1, 1));

			//======== panelR1 ========
			{

				//---- lblRegister1 ----
				lblRegister1.setText("Register 1");

				//======== scrollR1 ========
				{
					scrollR1.setViewportView(r1Image);
				}

				GroupLayout panelR1Layout = new GroupLayout(panelR1);
				panelR1.setLayout(panelR1Layout);
				panelR1Layout.setHorizontalGroup(
					panelR1Layout.createParallelGroup()
						.addGroup(panelR1Layout.createSequentialGroup()
							.addGroup(panelR1Layout.createParallelGroup()
								.addComponent(lblRegister1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollR1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
								.addComponent(txtR1Value, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
							.addContainerGap())
				);
				panelR1Layout.setVerticalGroup(
					panelR1Layout.createParallelGroup()
						.addGroup(panelR1Layout.createSequentialGroup()
							.addComponent(lblRegister1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollR1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(txtR1Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			}
			panelRegisters.add(panelR1);

			//======== panelR2 ========
			{

				//---- lblRegister2 ----
				lblRegister2.setText("Register 2");

				//======== scrollR2 ========
				{
					scrollR2.setViewportView(r2Image);
				}

				GroupLayout panelR2Layout = new GroupLayout(panelR2);
				panelR2.setLayout(panelR2Layout);
				panelR2Layout.setHorizontalGroup(
					panelR2Layout.createParallelGroup()
						.addGroup(panelR2Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panelR2Layout.createParallelGroup()
								.addGroup(panelR2Layout.createSequentialGroup()
									.addComponent(lblRegister2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(49, Short.MAX_VALUE))
								.addComponent(scrollR2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
								.addComponent(txtR2Value, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
				);
				panelR2Layout.setVerticalGroup(
					panelR2Layout.createParallelGroup()
						.addGroup(panelR2Layout.createSequentialGroup()
							.addComponent(lblRegister2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollR2, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(txtR2Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			}
			panelRegisters.add(panelR2);
		}

		//======== panelInstruction2 ========
		{

			//---- lblMemory ----
			lblMemory.setText("Memory");

			//======== scrollInstruction2 ========
			{

				//---- txtMemory ----
//				txtMemory.setEditable(false);
				scrollInstruction2.setViewportView(txtMemory);
			}

			//---- lblCache ----
			lblCache.setText("Cache");

			//---- lblStatics ----
			lblStatics.setText("Statistics");

			//======== scrollInstruction3 ========
			{

				//---- txtMemory2 ----
//				txtMemory2.setEditable(false);
				scrollInstruction3.setViewportView(txtCache);
			}

			//======== scrollInstruction4 ========
			{

				//---- txtMemory3 ----
//				txtMemory3.setEditable(false);
				scrollInstruction4.setViewportView(txtStatistics);
			}

			GroupLayout panelInstruction2Layout = new GroupLayout(panelInstruction2);
			panelInstruction2.setLayout(panelInstruction2Layout);
			panelInstruction2Layout.setHorizontalGroup(
				panelInstruction2Layout.createParallelGroup()
					.addGroup(panelInstruction2Layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addGroup(panelInstruction2Layout.createParallelGroup()
							.addComponent(scrollInstruction2, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMemory, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addGap(30, 30, 30)
						.addGroup(panelInstruction2Layout.createParallelGroup()
							.addComponent(scrollInstruction3, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCache, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addGap(28, 28, 28)
						.addGroup(panelInstruction2Layout.createParallelGroup()
							.addComponent(scrollInstruction4, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStatics, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panelInstruction2Layout.setVerticalGroup(
				panelInstruction2Layout.createParallelGroup()
					.addGroup(panelInstruction2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelInstruction2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblMemory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCache, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStatics, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelInstruction2Layout.createParallelGroup()
							.addComponent(scrollInstruction2, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollInstruction3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollInstruction4, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(24, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(16, 16, 16)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(10, 10, 10)
							.addComponent(panelInstruction2, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
							.addGap(19, 19, 19)
							.addComponent(panelInstruction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(panelRegisters, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)))
					.addGap(88, 88, 88))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(32, 32, 32)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(panelInstruction, 0, 287, Short.MAX_VALUE)
						.addComponent(panelRegisters, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addGap(18, 18, 18)
					.addComponent(panelInstruction2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
//		pack();
//		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Arun kumar K
	private JPanel panelInstruction;
	private JLabel lblInstructions;
	private JScrollPane scrollInstruction;
	private JList txtInstructions;
	private JTextField textStatus;
	private JLabel lblStatus;
	private JPanel panelRegisters;
	private JPanel panelR1;
	private JLabel lblRegister1;
	private JScrollPane scrollR1;
	
	private JTextField txtR1Value;
	private JPanel panelR2;
	private JLabel lblRegister2;
	private JScrollPane scrollR2;
	
	private JTextField txtR2Value;
	private JPanel panelInstruction2;
	private JLabel lblMemory;
	private JScrollPane scrollInstruction2;
	private JList txtMemory;
	private JLabel lblCache;
	private JLabel lblStatics;
	private JScrollPane scrollInstruction3;
	private JList txtCache;
	private JScrollPane scrollInstruction4;
	private JList txtStatistics;
	private BJPanel r1Image = new BJPanel();
	private BJPanel r2Image = new BJPanel();
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public BJPanel getR1Image() {
		return r1Image;
	}

	public void setR1Image(BJPanel r1Image) {
		this.r1Image = r1Image;
	}

	public BJPanel getR2Image() {
		return r2Image;
	}

	public void setR2Image(BJPanel r2Image) {
		this.r2Image = r2Image;
	}

	public JTextField getTxtR1Value() {
		return txtR1Value;
	}

	public void setTxtR1Value(JTextField txtR1Value) {
		this.txtR1Value = txtR1Value;
	}

	public JTextField getTxtR2Value() {
		return txtR2Value;
	}

	public void setTxtR2Value(JTextField txtR2Value) {
		this.txtR2Value = txtR2Value;
	}

	public JList getTxtInstructions() {
		return txtInstructions;
	}

	public void setTxtInstructions(JList txtInstructions) {
		this.txtInstructions = txtInstructions;
	}

	public JList getTxtMemory() {
		return txtMemory;
	}

	public void setTxtMemory(JList txtMemory) {
		this.txtMemory = txtMemory;
	}

	public JTextField getTextStatus() {
		return textStatus;
	}

	public void setTextStatus(JTextField textStatus) {
		this.textStatus = textStatus;
	}

	public JList getTxtCache() {
		return txtCache;
	}

	public void setTxtCache(JList txtCache) {
		this.txtCache = txtCache;
	}

	public JList getTxtStatistics() {
		return txtStatistics;
	}

	public void setTxtStatistics(JList txtStatistics) {
		this.txtStatistics = txtStatistics;
	}
}
