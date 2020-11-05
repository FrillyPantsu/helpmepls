package org.csc133.a2;

import java.util.Random;

public class Bird extends Movable{
		Random rand = new Random();
		// Create random values within an acceptable range; speed: 5-10; heading 1-359
		private int speed = rand.nextInt(10) + 5;	
		private int heading = rand.nextInt(359) + 1;
		
		// Bird Constructor
		Bird()
		{
			initPos();						// Set a random position
			setColor(0,0,255);				// Set color to blue
			setSize(5);						// Set size to 5
			setSpeed(this.speed);			// Set the speed and heading to what's within this class
			setHeading(this.heading);
		}
		
		// Updates the heading of the birds every clock tick
		// TO DO: There's an issue with getting the bird's random function working properly. Birds fly off the map.
		void alterDir(boolean tick)
		{
			if (tick)
			{
				if (heading < 359)
				{
					this.heading += rand.nextInt(5);
					setHeading(this.heading);
				}
				else if (heading >= 359)
				{
					this.heading -= rand.nextInt(5);
					setHeading(this.heading);
				}
			}
			
		}
		
		// Out of bounds correction
		// If any of the birds come within 10 units of map boundary, change their heading to be in the direct opposite of the boundary
		void oobCorrection(boolean tick)
		{		
			// Range of acceptable locations:
			// X: 0.0-1024.0 Y: 0.0-768.0
			if (tick)
			{
				if (getLocationX() >= 1014.0)
				{
					this.heading = 270;
					setHeading(this.heading);
				}
				
				else if (getLocationY() >= 758.0)
				{
					this.heading = 180;
					setHeading(this.heading);
				}
				
				else if (getLocationX() <= 10.0)
				{
					this.heading = 90;
					setHeading(this.heading);
				}
				
				else if (getLocationY() <= 10.0)
				{
					this.heading = 0;
					setHeading(this.heading);
				}
			}
		}
		
		// Method to provide a print-out of current bird info
		void printInfo()
		{
			System.out.println("Bird: " +
							 "loc=" + Math.round(getLocationX()*10.0)/10.0 + ',' + Math.round(getLocationY()*10.0)/10.0 + ' ' + 
							 "color=[" + getColorR() + ','  + getColorG() + ',' + getColorB() + ']' + ' ' +
							 "size=" + getSize() + ' ' +
							 "heading=" + getHeading() + ' ' + "speed=" + getSpeed() + ' ');
		}
}
