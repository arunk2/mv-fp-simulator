package com.iisc.simulator.fpunit;

import java.awt.Point;

public class FingerPrintAreaLocator {
	
	public int cycles = 1;
	
	/*
	 * ################################ # Extract Origin #
	 * ################################
	 * 
	 * In future i want to use the gradients to classifie the finger print into
	 * the 5 different catagories which are marked in the FP_CLASS.
	 * 
	 * This function still needs to improved and somtimes dosen't find the
	 * center of the finger print.
	 * 
	 * The principle in finding the centre is simple , just find the greatest
	 * change in the gradient bettween two lines and you have your centre.
	 * 
	 * To find the classification you have to find the average changes in
	 * gradients in the different sectors (if you divided your picture in 4
	 * using the fingerprint centre as the centre).You should then classifie the
	 * fingerprint according to this.
	 */

	public Point getFingerPrintOrigin(byte[][] P) {
		Point m_Point = new Point();
		double gradcur = 0;
		double gradprev = 0;
		double gradchangebig = 0;
		double gradchange = 0;

		double graddistancebig = 0;
		double graddistance = 0;

		double prevx = 0;
		double prevy = 0;

		for (int j = 50; j <= FingerPrintUtil.FP_IMAGE_HEIGHT - 50; j++) {
			for (int i = 50; i <= FingerPrintUtil.FP_IMAGE_WIDTH - 50; i++) {

				if (P[i][j] == 1) {
					// count surrounding pixels
					int tc = 0;
					int x1 = 0;
					int y1 = 0;
					int x2 = 0;
					int y2 = 0;
					// find surrounding 1s
					for (int m = -1 * FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS; m <= FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS; m++) {
						for (int n = -1
								* FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS; n <= FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS; n++) {
							if ((m == FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)
									|| (m == (-1)
											* FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)
									|| (n == FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)
									|| (n == (-1)
											* FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)) {
								if (P[i + m][j + n] == 1) {
									tc++;
									if (tc == 1) {
										x1 = i + m;
										y1 = j + n;
									}
									if (tc == 2) {
										x2 = i + m;
										y2 = j + n;
									}
								}// end if
							}// end if
						}// end for n
					} // end for m
						// does all the hard work of finding the greatest change
						// in gradient
					if (tc == 2) {
						if ((x2 - x1) > 0) {
							gradcur = (y2 - y1) / (x2 - x1);
							// check to see gradient change by at least 270
							// degrees
							if ((gradcur > 0) && (gradprev < 0)) {
								gradchange = Math.abs(gradcur)
										+ Math.abs(gradprev);
								graddistance = Math.abs(i) - Math.abs(prevx);
								if (gradchangebig < gradchange) {
									if (graddistancebig < graddistance) {
										gradchangebig = gradchange;
										graddistancebig = graddistance;
										m_Point.x = i;// FP_ORIGIN_X =i;
										m_Point.y = j;// FP_ORIGIN_Y =j;
									}
								}
								break;
							}
							// reset varibles for new checks
							gradprev = gradcur;
							gradcur = 0;
							prevx = i;
							prevy = j;
						}// (x2-x1)>0
					}// end if tc==2
				}// end if P[x][y]==1
			}// end for i
		}// end for j
		// JOptionPane.showMessageDialog
		// (null,Integer.toString(FP_ORIGIN_X)+";"+Integer.toString(FP_ORIGIN_Y),"getFingerPrintOrigin",JOptionPane.PLAIN_MESSAGE);
		return m_Point;
	}

}
