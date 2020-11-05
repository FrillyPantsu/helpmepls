package org.csc133.a2;
import java.util.Random;


import com.codename1.charts.util.ColorUtil;

public class GameObject {
	
	
	
	// Size is bounded by a square; the int size refers to the length of one of its sides
	// Sizes must be constrained to something reasonable IE: 10-50
	private int size;
	// Range of acceptable locations:
	// X: 0.0-1024.0 Y: 0.0-768.0
	private double locationX;
	private double locationY;
	// Color uses static rgb() method of ColorUtil in CN1 to generate colors
	private int R, G, B;
	
	void setSize(int size)
	{
		this.size = size;
		return;
	}

	int getSize()
	{
		return size;
	}
	
	double getLocationX()
	{
		return locationX;
	}
	
	void setLocationX(double loc)
	{
		this.locationX = loc;
	}
	
	double getLocationY()
	{
		return locationY;
	}
	
	void setLocationY(double loc)
	{
		this.locationY = loc;
	}
	
	void setColor(int r, int g, int b)
	{
		this.R = r;
		this.G = g;
		this.B = b;
		
		ColorUtil.red(R);
		ColorUtil.green(G);
		ColorUtil.blue(B);
		return;
	}
	
	// Accessors for the color variables
	int getColorR()
	{
		return R;
	}
	
	int getColorG()
	{
		return G;
	}
	
	int getColorB()
	{
		return B;
	}
	
	// Initialize starting position for objects that need one:
	void initPos()
	{
		// Range of acceptable locations:
		// X: 0.0-1024.0 Y: 0.0-768.0
		Random rand1 = new Random();
		double maxX = 1024.0;
		double posX = (maxX) * rand1.nextDouble();				// Calculations for position X
		posX = Math.round(posX*10.0)/10.0;						// Rounds the result
		setLocationX(posX);
			
		Random rand2 = new Random();
		double maxY = 768.0;
		double posY = (maxY) * rand2.nextDouble();				// Calculations for position Y
		posY = Math.round(posY*10.0)/10.0;						// Rounds the result
		setLocationY(posY);
	}
	
}
