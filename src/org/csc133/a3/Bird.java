package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;
import java.util.Vector;

public class Bird extends Movable{
		Random rand = new Random();
		// Create random values within an acceptable range; speed: 5-10; heading 1-359
		private int speed = rand.nextInt(10) + 5;	
		private int heading = rand.nextInt(359) + 1;
		private boolean dead = false;
		Vector<GameObject> currentlyCollidingWith = new Vector<GameObject>();
		
		// Bird Constructor
		public Bird()
		{
			super(30.0, 30.0, 0);
			initPos();						// Set a random position
			setColor(0,0,255);		// Set color to blue
			setSize(30);					// Set size to 30
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
					this.heading = getHeading();
					this.heading += rand.nextInt(5);
					if (this.heading >= 360)					// Wrapper
						this.heading -= 360;
					setHeading(this.heading);
				}
				if (heading >= 359)
				{
					this.heading = getHeading();
					this.heading -= rand.nextInt(5);
					if (this.heading <= 0)					// Wrapper
						this.heading += 360;
					setHeading(this.heading);
				}
			}
			
		}

		void birdDeath()
		{
			setColor(255,0,0);
			setSpeed(0);
			dead = true;
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

	@Override
	public void draw(Graphics g, Point containerOrigin)
	{
		int x = containerOrigin.getX() + (int)getLocationX() + getSize()/2;
		int y = containerOrigin.getY() + (int)getLocationY() + getSize()/2;

		g.setColor(getColor());
		g.drawArc(containerOrigin.getX() + (int)getLocationX(), containerOrigin.getY() + (int)getLocationY(), getSize(), getSize(), 0, 360);

		g.setColor(ColorUtil.MAGENTA);
		g.drawLine(x, y, x + (int)(Math.cos(Math.toRadians(90-getHeading())) * getSize()), y + (int)(Math.sin(Math.toRadians(90-getHeading())) * getSize()));
	}

	@Override
	public boolean collidesWith(GameObject otherObject)
	{
		// Initial collision check
		if ((this.getLocationX() <= otherObject.getLocationX() + otherObject.getSize()
				&& this.getLocationY() <= otherObject.getLocationY() + otherObject.getSize()
				&& this.getLocationX() >= otherObject.getLocationX() - otherObject.getSize()
				&& this.getLocationY() >= otherObject.getLocationY() - otherObject.getSize())		// Coordination of the hitbox
				&& !dead																			// Check to see if the object is dead/valid
				&& currentlyCollidingWith.isEmpty()) {												// Check to see if there are no current collisions
			currentlyCollidingWith.add(otherObject);		// Add the object to the vector to know we're currently colliding with it
			return true;									// There was an initial collision, return true
		}
		else if ((this.getLocationX() <= otherObject.getLocationX() + otherObject.getSize()
				&& this.getLocationY() <= otherObject.getLocationY() + otherObject.getSize()
				&& this.getLocationX() >= otherObject.getLocationX() - otherObject.getSize()
				&& this.getLocationY() >= otherObject.getLocationY() - otherObject.getSize())		// Coordination of the hitbox
				&& currentlyCollidingWith.contains(otherObject)) {									// Check to see if we're currently colliding with the object
			return false;									// We're currently colliding, so return false
		}
		else {												// We're no longer colliding with the object?
			currentlyCollidingWith.remove(otherObject);		// Remove it from the vector
			return false;									// We're free of all collisions, return false
		}
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		if (otherObject instanceof Helicopter) {
			birdDeath();
			new SoundInteractions("birdDeath.wav");
		}
	}
}
