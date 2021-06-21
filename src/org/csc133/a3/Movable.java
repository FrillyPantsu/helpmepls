package org.csc133.a3;
// Movable objects can move. Their location is not fixed and can be moved after creation.
// Movable objects include birds and helicopters.
abstract public class Movable extends GameObject {
	private int heading, speed;
	
	public Movable(double x, double y, int identifier)
	{
		super(x,y,identifier);
	}

	void setHeading(int heading)
	{
		this.heading = heading;
	}

	// Accessors and mutators
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
	void move(MapView mv)
	{	
		double oldLocX = getLocationX();									// Store the (x,y) location into a buffer
		double oldLocY = getLocationY();
		
		double deltaX = Math.cos(Math.toRadians(90 - heading))*speed;		// Calculations for next position in movement
		double deltaY = Math.sin(Math.toRadians(90 - heading))*speed;
		
		double newLocX = oldLocX + deltaX;					
		double newLocY = oldLocY + deltaY;

		setLocationX(newLocX);
		setLocationY(newLocY);

		// Out of bounds reflections
		if (getLocationX() <= 0)					// Right
		{
			setLocationX(0);
			setHeading(90);
		}
		else if (getLocationX() >= mv.getWidth())	// Left
		{
			setLocationX(mv.getWidth());
			setHeading(270);
		}

		if (getLocationY() <= 0)					// Bottom
		{
			setLocationY(0);
			setHeading(0);
		}
		else if(getLocationY() >= mv.getHeight())	// Top
		{
			setLocationY(mv.getHeight());
			setHeading(180);
		}
	}
}
