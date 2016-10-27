package com.iisc.simulator.fpunit;

import com.iisc.simulator.Main;
import com.iisc.simulator.Word;

public class Comparator {

	/*
	 * ################################ # Matching #
	 * ################################
	 * 
	 * Something to possably look at are
	 * 
	 * Distance = (X1 -X2)^2 + (Y1 - Y2)^2. The Error_Rating , if a image is is
	 * to the left or right or even at a angle the distance betwwen matched
	 * points will always be the same.
	 * 
	 * cross-corelation algorithm
	 * 
	 * The algotrithim could use classifications to speed it up and check that
	 * the feature direction to improve the faulse acceptance rate.
	 */

	public int match(Word[] finger1, Word[] finger2, int threshold,
			boolean fastmatch) {
		// compare matrix with all shifted matrixes
		// must do later. must get the size of the array
		// JOptionPane.showMessageDialog
		// (null,Double.toString(finger1[0])+";"+Double.toString(finger1[1])+";"+Double.toString(finger2[3]),"Match",JOptionPane.PLAIN_MESSAGE);
		double matchcount = 0;
		double matchcounttotal = (finger1[0].getValue() - 6) / 6;
		double bestmatch = 0;
		double radian = Math.PI / 180;
		boolean foundpoint;

		for (int k = -1 * FingerPrintUtil.FP_MATCH_POINT_ROTATION_MOVEMENT; k <= FingerPrintUtil.FP_MATCH_POINT_ROTATION_MOVEMENT; k++) {
			for (int i = 7; i <= finger1[0].getValue() - 5; i = i + 6) {
				foundpoint = false;
				for (int j = 7; j <= finger2[0].getValue() - 5; j = j + 6) {
					if (foundpoint == false) {
						// compare two points account for rotational , verticle
						// and horizontal shift
						int resx = 0;
						int resy = 0;
						double x1 = 0;
						double y1 = 0;
						double x2 = 0;
						double y2 = 0;
						double r = 0;
						double d = 0;
						// find nessasary parameters

						r = finger2[j + 2].getValue();
						d = finger2[j + 3].getValue();
						x2 = finger1[i].getValue();
						y2 = finger1[i + 1].getValue();
						// do angle shift for x
						x1 = r * Math.cos(d + (k * radian));
						resx = Math.abs((int) x2 + (int) (-1 * x1));
						// do angle shift for y
						y1 = r * Math.sin(d + (k * radian));
						resy = Math.abs((int) y2 + (int) (-1 * y1));
						
						Main.compareOperations++;

						// cheak shift matchs count as match
						if ((FingerPrintUtil.FP_MATCH_POINT_DISTANCE_MOVEMENT > resx)
								&& (FingerPrintUtil.FP_MATCH_POINT_DISTANCE_MOVEMENT > resy)) {

							// cheak if same kind of feature
							if (finger1[i + 4] == finger2[j + 4]) {
								// cheak if feature in same direction
								// if(((finger1[i+5]-finger2[j+5])<=46)||((finger1[i+5]==0)&&(finger2[j+5]==315))||((finger1[i+5]==0)&&(finger2[j+5]==45)))
								// {
								matchcount++;
								foundpoint = true;
								// break;
								// }//cheak if feature in same direction
							} // cheak if same kind of feature

						}// end if
					}// if found
				}// end for j
			}// end for i
				// see if we have a match
				// FP_MATCH_THRESHOLD
			if ((((matchcount / matchcounttotal) * 100) >= threshold)
					&& (fastmatch == true)) {
				// found match
				return (int) ((matchcount / matchcounttotal) * 100);
			} else {
				// not found match
				if (matchcount > bestmatch) {
					bestmatch = matchcount;
				}
				// reset match counter to 0
				matchcount = 0;
			} // end if

		}// end for k
		return (int) ((bestmatch / matchcounttotal) * 100);
	}// end Match

}
