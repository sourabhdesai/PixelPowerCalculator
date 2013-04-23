import java.awt.Dimension;
import java.awt.image.BufferedImage;


public class PixelPowerGrapher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			Zen.DEFAULT_SIZE=new Dimension(1000,1000); //Use this to change window size. Default is Width:640 Height:480
			Zen.setColor(0,0,0);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
			int[][]newPic=quadrant1Graph(); //Edit function of graph within methods themselves!
			BufferedImage img= Zen.toImage(toGrayScale(newPic));			 
			Zen.drawImage(img, 0, 0);
			TextIO.putln("DONE!");
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
	public static double map(int x, int in_min, int in_max, int out_min, int out_max)	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	//RECURSIVE MATH FORMULAS
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max)	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int fib(int n)	{
		if (n==0) return 0;
		if(n==1) return 1;
		return fib(n-1)+fib(n-2);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int fibFast(int n)	{
		int num1, num2, num3;
		num1 = -1; num2 = 1;	
		for (int i = 0; i <= n; i++)	{
		num3 = (num1 + num2);
		num1 = num2;
		num2 = num3;	
		}
		return num2;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int tri(int n)	{
		if(n==0) return 0;
		return n+tri(n-1);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int factorial(int n)	{
		if(n==1) return 1;
		int c=factorial(n-1);
		return c*n;
	}
	
	public static int catalanNums(int n)	{
		if(n==0) return 1;
		return (((4*(n-1))+2)*catalanNums(n-1))/(n+1);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int hanoi(int n)	{
		if(n<=1) return 1;
		return 2*hanoi(n-1)+1;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int euclidianGCD(int a, int b)	{
		int x=a;
		int y=b;
		while(y>0)	{
			int r=x%y;
			x=y;
			y=r;
		}
		return x;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
	public static int iterativeDecay(float decay,int iteration, int startValue)	{
		if(decay>1) return startValue;
		if(iteration==0) return startValue;
		return (int) decay*iterativeDecay(decay,iteration-1,startValue); //I know the closed form (decay^iteration)*startValue is faster but I think recursion is cooler
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int exponentialDecay(double decayRate, double decayConstant, int variable)	{
		// Larger decay constants make the quantity vanish much more rapidly.
		return (int) Math.floor(decayRate*Math.pow(Math.E,-1*decayConstant*variable));
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int mandleBrot(int x0,int y0)	{//This is straight from Pseudocode on Wikipedia
		  x0 = (int) map(x0,0,Zen.getZenWidth(),-2.5,1);//scaled x coordinate of pixel (must be scaled to lie somewhere in the mandelbrot X scale (-2.5, 1)
		  y0 = (int) map(y0,0,Zen.getZenHeight(),-1,1);//scaled y coordinate of pixel (must be scaled to lie somewhere in the mandelbrot Y scale (-1, 1)
		  int x = 0;
		  int y = 0;
		  int iteration = 0;
		  int max_iteration = 1000;
		  while ( x*x + y*y < 2*2  &&  iteration < max_iteration )	{
		    int xtemp = x*x - y*y + x0;
		    y = 2*x*y + y0;
		    x = xtemp;
		    iteration = iteration + 1;
		  }
		  int color = iteration;
		  return color;
	}
	
	public static int[][] coordinateGraph()	{
		int[][] newPic = new int[Zen.getZenWidth()][Zen.getZenHeight()];
		for(int X=Zen.getZenWidth()/2; X<Zen.getZenWidth(); X++)	{ //X: Starts from 0 coordinate, goes to MaxX coordinate
			for(int Y=Zen.getZenHeight()/2; Y<Zen.getZenHeight(); Y++)	{	//Y: Starts from 0 coordinate, goes to MaxY coordinate				
				for(int quadrant=1;quadrant<=4;quadrant++)	{
					int x=X-(Zen.getZenWidth()/2); //x: starts at 0, goes to half of width of dimension
					int y=Y-(Zen.getZenHeight()/2);//y: starts at 0, goes to half of height of dimension
					if(quadrant==2)	{
						x=-1*x;
					}
					if(quadrant==3)	{
						x=-1*x;
						y=-1*y;
					}
					if(quadrant==4)	{
						y=-1*y;
					}
				//ENTER MAIN FUNCTION BELOW
				int f_xy=(int)RGBUtilities.toBlue(mandleBrot(x,y)); //Try Messing around with Math.tan(x^2+y^2)  :)
				//ENTER MAIN FUNCTION ABOVE
//				int r=(int)Math.hypot(x^2, y^2);
//				int g=(int)r; 
				int r=(int) RGBUtilities.toRed(mandleBrot(x,y));
				int g=(int) RGBUtilities.toGreen(mandleBrot(x,y));
				int b=f_xy;
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;			
				int pixel=RGBUtilities.toRGB(r,g,b);
				if(quadrant==1)	{
					newPic[X][Zen.getZenHeight()-Y]=pixel;	
				}
				else if(quadrant==2)	{
					newPic[X-(Zen.getZenWidth()/2)][Zen.getZenHeight()-Y]=pixel;
				} else if(quadrant==3)	{
					newPic[X-(Zen.getZenWidth()/2)][Y]=pixel;
				} else if(quadrant==4)	{
					newPic[X][Y]=pixel;
				}
				}
			}
		}
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
	public static int[][] quadrant1Graph()	{
		int[][] newPic=new int[Zen.getZenWidth()][Zen.getZenHeight()];
		for(int x=0; x<Zen.getZenWidth(); x++)	{ //X: starts from 0, goes to MaxX coordinate
			for(int Y=Zen.getZenHeight()-1; Y>=0; Y--)	{	//Y: Starts from MaxY coordinate, goes to 0				
				int y=Zen.getZenHeight()-1-Y; //y: Starts from 0, goes to MaxY coordinate
				//ENTER MAIN FUNCTION BELOW
				int f_xy=(int) fibFast(x&y); //Try Messing around with Math.tan(x^2+y^2)  :)
				//ENTER MAIN FUNCTION ABOVE
				int r=(int)fibFast(x|y); //Sometimes does to Black and White
				int g=(int)fibFast(r&f_xy); //Sometime does to Black And White
//				int r=(int) Math.ceil(map(x,0,newPic.length,0,255));
//				int g=(int) Math.ceil(map(y,0,newPic[0].length,0,255));
				int b=f_xy;
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				
				int pixel=RGBUtilities.toRGB(r,g,b); 
				newPic[x][Y]=pixel;
			}
		}
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] quadrant1Graph2()	{
		int[][] newPic=new int[Zen.getZenWidth()][Zen.getZenHeight()];
		for(int x=0; x<Zen.getZenWidth(); x++)	{ //X: starts from 0, goes to MaxX coordinate
			for(int Y=Zen.getZenHeight()-1; Y>=0; Y--)	{	//Y: Starts from MaxY coordinate, goes to 0				
				int y=Zen.getZenHeight()-1-Y; //y: Starts from 0, goes to MaxY coordinate
				//ENTER MAIN FUNCTION BELOW
				int f_xy=(int)y*x; //Try Messing around with Math.tan(x^2+y^2)  :)
				//ENTER MAIN FUNCTION ABOVE
//				int r=(int)f_xy; //Sometimes does to Black and White
//				int g=(int)f_xy; //Sometime does to Black And White
				int r=(int) Math.ceil(map(x,0,newPic.length,0,255));
				int g=(int) Math.ceil(map(y,0,newPic[0].length,0,255));
				int b=f_xy;
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				
				int pixel=RGBUtilities.toRGB(r,g,b); 
				newPic[x][Y]=pixel;
			}
		}
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static boolean picsEqual(int[][] pic1, int[][] pic2)	{
		boolean isEqual=true;
		if(pic1.length != pic2.length || pic1[0].length != pic2[0].length)	return false;
		int W=pic1.length;
		int H=pic2[0].length;
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				if(pic1[x][y] != pic2[x][y]) isEqual=false;
			}
		}
		return isEqual;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	public static int[][] blur(int[][] sourceA, int blurLevel){//Can make this a lot more efficient															   
		if (sourceA==null) return null;						   //By making it color the edge and corner pixels first
		if(blurLevel==0) return sourceA;
		int[][] trgtPic= new int[sourceA.length][sourceA[0].length];
		int W= trgtPic.length;
		int H=trgtPic[0].length;
		int[][] source=sourceA;
		for(int level=0; level<blurLevel; level++)	{
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H;y++)	{
				int xLeft=x-1;
				int xRight=x+1;
				int yUp=y-1;
				int yDown=y+1;
				boolean xLeftCHK= xLeft>=0;
				boolean xRightCHK= xRight<W;
				boolean yUpCHK= yUp>=0;
				boolean yDownCHK= yDown<H;
				int sumR=0;
				int sumG=0;
				int sumB=0;
				int numPixels=0;
				if(xRightCHK)	{
					sumR+=RGBUtilities.toRed(source[xRight][y]);
					sumG+=RGBUtilities.toGreen(source[xRight][y]);
					sumB+=RGBUtilities.toBlue(source[xRight][y]);
					numPixels++;
					if(yUpCHK)	{
						sumR+=RGBUtilities.toRed(source[xRight][yUp]);
						sumG+=RGBUtilities.toGreen(source[xRight][yUp]);
						sumB+=RGBUtilities.toBlue(source[xRight][yUp]);
						numPixels++;
					}
					if(yDownCHK)	{
						sumR+=RGBUtilities.toRed(source[xRight][yDown]);
						sumG+=RGBUtilities.toGreen(source[xRight][yDown]);
						sumB+=RGBUtilities.toBlue(source[xRight][yDown]);
						numPixels++;
					}
				}
				if(xLeftCHK)	{
					sumR+=RGBUtilities.toRed(source[xLeft][y]);
					sumG+=RGBUtilities.toGreen(source[xLeft][y]);
					sumB+=RGBUtilities.toBlue(source[xLeft][y]);
					numPixels++;
					if(yUpCHK)	{
						sumR+=RGBUtilities.toRed(source[xLeft][yUp]);
						sumG+=RGBUtilities.toGreen(source[xLeft][yUp]);
						sumB+=RGBUtilities.toBlue(source[xLeft][yUp]);
						numPixels++;
					}
					if(yDownCHK)	{
						sumR+=RGBUtilities.toRed(source[xLeft][yDown]);
						sumG+=RGBUtilities.toGreen(source[xLeft][yDown]);
						sumB+=RGBUtilities.toBlue(source[xLeft][yDown]);
						numPixels++;
					}
				}
				if(yUpCHK)	{
					sumR+=RGBUtilities.toRed(source[x][yUp]);
					sumG+=RGBUtilities.toGreen(source[x][yUp]);
					sumB+=RGBUtilities.toBlue(source[x][yUp]);
					numPixels++;
				}
				if(yDownCHK)	{
					sumR+=RGBUtilities.toRed(source[x][yDown]);
					sumG+=RGBUtilities.toGreen(source[x][yDown]);
					sumB+=RGBUtilities.toBlue(source[x][yDown]);
					numPixels++;
				}
			int avgR=sumR/numPixels;
			int avgG=sumG/numPixels;
			int avgB=sumB/numPixels;
			trgtPic[x][y]=RGBUtilities.toRGB(avgR, avgG, avgB);
			}
		}
		source=PixelEffects.copy(trgtPic);
		}
	return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	public static int[][] toGrayScale(int[][] source)	{
		int[][] trgtPic=new int[source.length][source[0].length];
		int W=trgtPic.length;
		int H=trgtPic[0].length;
		for(int x=0;x<W; x++)	{
			for(int y=0; y<H; y++)	{
				int rVal=RGBUtilities.toRed(source[x][y]);
				int gVal=RGBUtilities.toGreen(source[x][y]);
				int bVal=RGBUtilities.toBlue(source[x][y]);
				int grayedPixel=(rVal+gVal+bVal)/3;
				trgtPic[x][y]=RGBUtilities.toRGB(grayedPixel, grayedPixel, grayedPixel);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] sortPic(int[][] src)	{ //Recursively Sorts each pixel according to value with topmost pixel having lowest rbg value
		int W=src.length;
		int H=src[0].length; 
		int[][] rPic=picToRed(src);
		int[][] gPic=picToGreen(src);
		int[][] bPic=picToBlue(src);
		
		for(int x=0; x<W;x++)	{
				SelectionSort.sort(rPic[x]);
				SelectionSort.sort(gPic[x]);
				SelectionSort.sort(bPic[x]);
		}
		return picsToRGB(rPic,gPic,bPic);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToRed(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toRed(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToGreen(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toGreen(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToBlue(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toBlue(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picsToRGB(int[][] rSrc,int[][] gSrc,int[][] bSrc)	{
		int W=rSrc.length;
		int H=rSrc[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toRGB(rSrc[x][y],gSrc[x][y],bSrc[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
}//END OF CLASS--------------------------------------------------------------------------------------------------------------------
/* LIST OF COOL FUNCTIONS TO TRY
 * 
 * Math.tan(tri(x)-tri(y))
 * Math.tan(x^2+y^2)
 * Math.tan(catalanNums(x)+catalanNums(y))
 * Math.pow(tri(x+y),n)
 * Math.tan(euclidianGCD(x,y))
 * exponentialDecay(255, 0.0008, y+x)
 * Math.tan(y & x)
 * Math.pow(x|y, x&y)          <--Sierpinski Triangles!!
 * 
 */

/* LIST OF COOL COMBO FUNCTIONS TO TRY
 * 					int f_xy=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, y+x))); //Try Messing around with Math.tan(x^2+y^2)  :)
					//ENTER MAIN FUNCTION ABOVE
					int r=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, y-x))); //Sometimes does to Black and White
					int g=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, x-y))); //Sometime does to Black And White
*
*
*					
*/
