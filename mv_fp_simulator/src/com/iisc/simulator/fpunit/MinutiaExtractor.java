package com.iisc.simulator.fpunit;

import java.awt.Point;

import javax.swing.JOptionPane;

public class MinutiaExtractor {
	
	public int cycles = 1;
	
	  public double[] getFingerPrintTemplate(Point origin, byte [][] P)
	  {
	   // final int SEARCH_RADIUS = 1;   
	    double x=0;
	    double y=0;
	    double r=0;
	    double d=0;
	    double m_arr[] = new double[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];
	 
//	    this.ThinningHilditch();
//	    this.ThinningHitAndMiss();
//	    this.ThinningHilditch();
//	    this.ThinningHitAndMiss();
//	    
//	    Point origin = this.getFingerPrintOrigin();  
	    m_arr[1]=origin.x;
	    m_arr[2]=origin.y;   
	    
	    int c = 7 ;
	    int previ=0;
	    int prevj=0;
	    
	    boolean first = true;
	    
	    //start from 5 units in to avoid detection of edges of finger print and out of bound exceptions
	    for(int j = 5 ;j<= FingerPrintUtil.FP_IMAGE_HEIGHT - 6;j++)
	    {
	      first = true;  
	       for( int i =5 ;i<= FingerPrintUtil.FP_IMAGE_WIDTH - 6;i++)
	       {
	       if ((c<FingerPrintUtil.FP_TEMPLATE_MAX_SIZE)&&(P[i][j] == 1) && (i != FingerPrintUtil.FP_IMAGE_WIDTH - 1) && (i != 0) && (j != FingerPrintUtil.FP_IMAGE_HEIGHT - 1) && (j != 0) )
	        {
	         /*  
	         *   Must not capture first and last feature because those are the edges of the finger print
	         *   and will provide no value to the template.
	         */  
	         if(first == true)
	         {
	            first = false;
	            //cheak to see if previos item in array was aslo end
	            if( (c>7)&&((m_arr[c-6]+origin.x)==previ)&&((m_arr[c-5]+origin.y)==prevj) )
	            {
	              //delete previos featue
	                m_arr[c--]=0;
	                m_arr[c--]=0;
	                m_arr[c--]=0;
	                m_arr[c--]=0;
	                m_arr[c--]=0;
	                m_arr[c--]=0;         
	            }
	         }
	         else
	         {
	              int tc = 0;
	              for (int m = -1*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;m<=FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;m++)
	                {
	                for (int n = -1*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;n<=FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS;n++)
	                {
	                    if ((m==FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(m==(-1)*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(n==FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS)||(n==(-1)*FingerPrintUtil.FP_TEMPLATE_SEARCH_RADIUS))
	                    {
	                        if(P[i+m][j+n] == 1 )
	                        {
	                        tc++;
	                        }//end if
	                    }//end if
	                }//end for n
	              } //end for m         
	         
	                //calculate parameters necesary for template
	                if ((tc==1)||(tc==3))
	                {
	                  x = i- origin.x;
	                  y = j- origin.y;
	                  r = Math.hypot(x,y);
	                  if ((x>0)&&(y>0))
	                        {
	                        d =Math.atan(y/x);
	                        }
	                  else if ((x<0)&&(y>0))
	                        {
	                        d =Math.atan(y/x) - Math.PI ;
	                        }
	                  else if ((x<0)&&(y<0))
	                        {
	                        d =Math.PI + Math.atan(y/x);
	                        }
	                  else if ((x>0)&&(y<0))
	                        {
	                        d =2*Math.PI + Math.atan(y/x);
	                        }
	                }
	              
	                //check to see if point already been captured
	                boolean foundx = false;  
	                boolean foundy = false;  
	                  for (int m=7;m<=c;m=m+6)
	                    {
	                        if(m_arr[m+4]==3)
	                        {
	                            if(Math.abs(Math.abs((int)m_arr[m])-Math.abs(x))<4)
	                            {
	                            foundx=true;
	                            }
	                            if(Math.abs(Math.abs((int)m_arr[m+1])-Math.abs(y))<4)
	                            {
	                            foundy=true;
	                            }
	                        }//end if
	                    }//end for m
	                         
	            
	                //1 surrounding 1s
	                if ((tc==1) && (c <= FingerPrintUtil.FP_TEMPLATE_MAX_SIZE-6)  && (x!=0) && (y!=0) && ((foundx==false)||(foundy==false)) )
	                {
	              
	                    if (P[i-1][j+1] == 1)  
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] = 135 ;
	                    }
	                    else if (P[i][j+1] == 1)
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] =90  ;
	                    }
	                    else if (P[i+1][j+1] == 1)
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] =45  ;
	                    }
	                    else if (P[i+1][j] == 1) 
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] = 0  ;
	                    }
	                    else if (P[i+1][j-1] == 1)
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] =315  ;
	                    }
	                    else if (P[i][j-1] == 1)
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] = 270  ;
	                    }
	                    else if (P[i-1][j-1] == 1)
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] = 225 ;
	                    }
	                    else if (P[i-1][ j] == 1 ) 
	                    {
	                    m_arr[c++] = x;
	                    m_arr[c++] = y;
	                    m_arr[c++] = r;
	                    m_arr[c++] = d;
	                    m_arr[c++] = 1 ;
	                    m_arr[c++] = 180 ;
	                    }
	                }
	                else if ((tc>=3)&&(c <= FingerPrintUtil.FP_TEMPLATE_MAX_SIZE - 6)&&(x!=0)&&(y!=0)&&((foundx==false)||(foundy==false)) )
	                {          
	                       //3 surrounding 1s
	                        m_arr[c++] = x;
	                        m_arr[c++] = y;
	                        m_arr[c++] = r;
	                        m_arr[c++] = d;
	                        m_arr[c++] = 3;
	                        m_arr[c++] = 0;
	                }//if tc>=3 
	       }//end if first
	         previ=i;
	         prevj=j;
	         
	         //306;269
	         if (((i- origin.x)>=(306 - 4))&&((i- origin.y)>=(269-4)))
	         {
	           if (((i- origin.x)<=(306+4))&&((i- origin.y)<=(269+4)))
	           {
	          JOptionPane.showMessageDialog (null,Double.toString(c)+";"+Integer.toString(i)+";"+Integer.toString(j),"My Point",JOptionPane.PLAIN_MESSAGE);
	           }
	         }
	         
	      }//end if that checks for p[x,y]=1
	      }//end for
	    }//end for
	    //put total size of points collected at 0 in array
	    m_arr[0]=c;
	    return m_arr;
	  }//end getFingerPrintTemplate()
}
