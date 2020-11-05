package org.csc133.a2;
import java.util.Random;

// Movable objects can move. Their location is not fixed and can be moved after creation.
// Movable objects include birds and helicopters.
public class Movable extends GameObject {
	private int heading, speed;
	
	
	// Accessors and mutators
	void setHeading(int heading)
	{
		this.heading = heading;
	}
	
	int getHeading()
	{
		return heading;
	}
	
	void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	int getSpeed()
	{
		return speed;
	}
	
	// Move method using formula provided from assignment specifications
	void move()
	{	
		double oldLocX = getLocationX();					// Store the (x,y) location into a buffer
		double oldLocY = getLocationY();
		
		double deltaX = Math.cos(Math.toRadians(90 - heading))*speed;		// Calculations for next position in movement
		double deltaY = Math.sin(Math.toRadians(90 - heading))*speed;
		
		double newLocX = oldLocX + deltaX;					
		double newLocY = oldLocY + deltaY;
		
		setLocationX(newLocX);
		setLocationY(newLocY);
		
		return;
	}
}
