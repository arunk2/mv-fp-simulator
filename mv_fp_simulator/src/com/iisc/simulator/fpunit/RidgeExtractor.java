package com.iisc.simulator.fpunit;

import java.awt.Point;

public class RidgeExtractor {
	
	public int cycles = 1;
	
	public int getFingerPrintClassification(Point m_Point, byte [][] P)
	  {
//	      Point m_Point = this.getFingerPrintOrigin();
	      double gradcur=0;
	      
	      //stores total gradient of corners
	      double gradlt=0;
	      double gradrt=0;
	      double gradlb=0;
	      double gradrb=0;
	     
	      //counts total of each corner gradient
	      double cgradlt=0;
	      double cgradrt=0;
	      double cgradlb=0;
	      double cgradrb=0;
	     
	      for(int j =50;j<=FingerPrintUtil.FP_IMAGE_HEIGHT-50;j++)
	      { 
	        for(int i =50;i<=FingerPrintUtil.FP_IMAGE_WIDTH-50;i++)
	        {
	          if(P[i][j]==1)
	           {
	            //count surrounding pixels
	            int tc=0;
	            int x1=0;
	            int y1=0;
	            int x2=0;
	            int y2=0;
	            //find surrounding 1s
	            for (int m = -1*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;m<=FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;m++)
	                {
	                for (int n = -1*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;n<=FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;n++)
	                {
	                    if ((m==FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(m==(-1)*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(n==FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(n==(-1)*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS))
	                    {
	                        if(P[i+m][j+n] == 1 )
	                        {
	                        tc++;
	                            if (tc==1)
	                            {
	                            x1=i+m;
	                            y1=j+n;
	                            }
	                            if (tc==2)
	                            {
	                            x2=i+m;
	                            y2=j+n;
	                            }
	                        }//end if
	                    }//end if
	                }//end for n
	              } //end for m         
	              //does all the hard work of finding the greatest change in gradient
	              if(tc==2)
	              {
	               if ((x2-x1)>0)
	               { 
	                    gradcur  = (y2-y1)/(x2-x1);
	                    //check to see gradient change by at least 270 degrees
	                    if ((x2<m_Point.x)&&(y2>m_Point.y))
	                    {    
	                    gradlt=gradlt+gradcur;
	                    gradlt++;
	                    }    
	                    else if ((x2>m_Point.x)&&(y2>m_Point.y))
	                    {    
	                    gradrt=gradrt+gradcur;
	                    gradrt++;
	                    }    
	                    else if ((x2<m_Point.x)&&(y2<m_Point.y))
	                    {    
	                    gradlb=gradlb+gradcur;
	                    gradlb++;
	                    }    
	                    else if ((x2>m_Point.x)&&(y2<m_Point.y))
	                    {    
	                    gradrb=gradrb+gradcur;
	                    gradrb++;
	                    }    
	     
	                 }//(x2-x1)>0
	              }//end if tc==2
	           }//end if P[x][y]==1
	       }//end for i
	      }//end for j
	      //get average gradient for 4 corners
	      gradlb=gradlb/cgradlb;
	      gradrb=gradrb/cgradrb;
	      gradlt=gradlt/cgradlt;
	      gradrt=gradrt/cgradrt;
	      //determin classification according to gradient
	      //needs work
	      if ((gradlt>0)&&(gradrt>0)&&(gradlb>0)&&(gradrb>0))
	      {
	      return FingerPrintUtil.FP_CLASS_WHORL;
	      }
	      else if ((gradlt>0)&&(gradrt>0)&&(gradlb>0)&&(gradrb>0))
	      {
	      return FingerPrintUtil.FP_CLASS_LEFT_LOOP;
	      }
	      else if ((gradlt>0)&&(gradrt>0)&&(gradlb>0)&&(gradrb>0))
	      {
	      return FingerPrintUtil.FP_CLASS_RIGHT_LOOP;
	      }
	      else if ((gradlt>0)&&(gradrt>0)&&(gradlb>0)&&(gradrb>0))
	      {
	      return FingerPrintUtil.FP_CLASS_ARCH;
	      }
	      else if ((gradlt>0)&&(gradrt>0)&&(gradlb>0)&&(gradrb>0))
	      {
	      return FingerPrintUtil.FP_CLASS_ARCH_TENTED;
	      }
	      else
	      {
	        return 1;
	      }    
	  //  JOptionPane.showMessageDialog (null,Integer.toString(FP_ORIGIN_X)+";"+Integer.toString(FP_ORIGIN_Y),"getFingerPrintOrigin",JOptionPane.PLAIN_MESSAGE);
	 }
}
