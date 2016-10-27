package com.iisc.simulator.fpunit;

public class Thinner {
	
	public int cycles = 1;
	
	  public void ThinningHilditch(byte [][] P)
	  {
	    int change  = 1;
	    boolean mbool  = true;
	    while (change != 0)
	    {
	      change = 0;
	      for(int i = 2; i <= FingerPrintUtil.FP_IMAGE_WIDTH - 2;i++)
	      {
	        for(int j = 2; j <= FingerPrintUtil.FP_IMAGE_HEIGHT - 2;j++)
	        {
	          if (P[i][j] == 1)
	          {
	          short c  = 0;
	          //count surrounding 1
	          //a) Make sure pixel 1, has 2 to 6 (inclusive) neighbors
	          if (P[i][j+1] == 1) { c++;}
	          if (P[i+1][j+1] == 1) { c++;}
	          if (P[i+1][j] == 1) { c++;}
	          if (P[i+1][j-1] == 1) { c++;}
	          if (P[i][j-1] == 1) { c++;}
	          if (P[i-1][j-1] == 1) { c++;}
	          if (P[i-1][j] == 1) { c++;}
	          if (P[i-1][j+1] == 1) { c++;}

	          if ((c >= 2) && (c <= 6 ))
	          {
	            c = 0;
	            //b) starting from 2, go clockwise until 9, and count the
	            //'   number of 0 to 1 transitions.  This should be equal to 1.
	            if ((P[i-1][j+1] == 0) && (P[i][j+1] == 1)) { c++;}
	            if ((P[i][j+1] == 0) && (P[i+1][j+1] == 1)) { c++;}
	            if ((P[i+1][j+1] == 0) && (P[i+1][j] == 1)) { c++;}
	            if ((P[i+1][j] == 0) && (P[i+1][j-1] == 1)) { c++;}
	            if ((P[i + 1][j-1] == 0) && (P[i][j-1] == 1)) { c++;}
	            if ((P[i][j-1] == 0) && (P[i-1][j-1] == 1)) { c++;}
	            if ((P[i-1][j-1] == 0) && (P[i-1][j] == 1)) { c++;}
	            if ((P[i - 1][j] == 0) && (P[i-1][j+1] == 1)) { c++;}

	            if (c == 1 )
	            {
	              c = 0;
	              if (mbool == true)
	              {
	                //c) 2*4*6=0  (ie either 2,4 ,or 6 is off)
	                if ((P[i][j+1] * P[i+1][j] * P[i+1][j-1]) == 0 )
	                {
	                  //d) 4*6*8=0
	                  if ((P[i+1][j] * P[i+1][j-1] * P[i-1][j]) == 0 )
	                  {
	                    P[i][j] = 0;
	                    change++;
	                  }
	                }
	                mbool = false;
	              }
	              else
	              {
	                //c) 2*6*8=0
	                if ((P[i][j+1] * P[i+1][j-1] * P[i-1][j]) == 0)
	                {
	                  //d) 2*4*8=0
	                  if ((P[i][j+1] * P[i+1][j] * P[i-1][j]) == 0)
	                  {
	                    P[i][j] = 0;
	                    change++;
	                  }
	                }
	                mbool = true;
	              }
	            }
	          }
	        }
	        }
	      }
	    }//End While
	  }//end ThinningHilditchAlgorithim

	  public void ThinningHitAndMiss(byte [][] P)
	  {
	/*
	*    basicly you take all patterns
	*    111    X1X
	*    X1X or x11 so on
	*    000    xxX
	*    if these conditions are true then set the middle 1 to 0
	*/
	    int c  = 1;
	    while (c != 0)
	    {
	      c = 0;
	      for(int i = 1;i<=FingerPrintUtil.FP_IMAGE_WIDTH - 1;i++)
	      {
	        for(int j = 1;j<=FingerPrintUtil.FP_IMAGE_HEIGHT - 1;j++)
	        {
	          if ((P[i][j] == 1) && (i != 0) && (j != FingerPrintUtil.FP_IMAGE_HEIGHT - 1) && (j != 0) && (i != FingerPrintUtil.FP_IMAGE_WIDTH - 1))
	          {
	            if ((P[i-1][j-1] == 1) &&( P[i][j-1] == 1) && (P[i+1][j-1] == 1) && (P[i-1][j+1] == 0) && (P[i][j+1] == 0) && (P[i+1][j+1] == 0))
	            {
	          P[i][j] = 0; //'1 on bottom
	          c++;
	        }
	        else if ((P[i-1][j+1] == 1) && (P[i][j+1] == 1) && (P[i+1][j+1] == 1) && (P[i-1][j-1] == 0) && (P[i][j-1] == 0) && (P[i+1][j-1] == 0))
	        {
	          P[i][j] = 0; //'1 on top
	          c++;
	        }
	        else if ((P[i-1][j] == 1) && (P[i-1][j - 1] == 1) && (P[i-1][j+1] == 1) && (P[i+1][j] == 0) && (P[i+1][j+1] == 0) && (P[i+1][j-1] == 0))
	        {
	          P[i][j] = 0; //'1 on left
	          c++;
	        }
	        else if ((P[i+1][j] == 1) && (P[i+1][j-1] == 1) && (P[i+1][j+1] == 1) && (P[i-1][j] == 0) && (P[i-1][j+1] == 0) && (P[i-1][j-1] == 0) )
	        {
	          P[i][j] = 0; //'1 on right
	          c++;
	        }
	        else if ((P[i-1][j] == 1) && (P[i][j-1] == 1) && (P[i][j+1] == 0) && (P[i+1][j+1] == 0) && (P[i + 1][j] == 0))
	        {
	                //x00
	                //110
	                //x1x
	          P[i][j] = 0; //'1 on Bottem Left
	          c++;
	        }
	        else if ((P[i-1][j] == 1) && (P[i][j+1] == 1) && (P[i][j-1] == 0) && (P[i+1][j-1] == 0) && (P[i+1][j] == 0))
	        {
	                //x1x
	                //110
	                //x00
	          P[i][j] = 0; //'1 on Top Left
	          c++;
	        }
	        else if ((P[i][j+1]== 1) && (P[i+1][j] == 1) && (P[i-1][j] == 0) && (P[i-1][j-1] == 0) && (P[i][j-1] == 0))
	        {
	                //x1x
	                //011
	                //00x
	          P[i][j] = 0; //'1 on Top Right
	          c++;
	        }
	        else if ((P[i][j-1] == 1) && (P[i+1][j] == 1) && (P[i-1][j] == 0) && (P[i-1][j+1] == 0) && (P[i][j+1] == 0) )
	        {
	                //00x
	                //011
	                //x1x
	          P[i][j] = 0; //'1 on Bottom Right
	          c++;
	        }
	        }
	        }//Next
	      }//Next
	    }//End While
	  }//end ThinningHitAndMiss
}
