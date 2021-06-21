package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Vector;

public class RefuelingBlimp extends Fixed {
	// Initial capacity of of refueling blimp is proportional to its size. Size should be decided by you on initialization.
	private int capacity;
	boolean drained;
	Vector<GameObject> currentlyCollidingWith = new Vector<GameObject>();
	
	// Color variables
	private int r = 0, g = 255, b = 0;
		
	// Constructor
	public RefuelingBlimp(double x, double y, int identifier)
	{
		super(x,y,identifier);
		setSize(100);
		capacity = getSize();					// Set capacity to the current size
		setColor(r,g,b);						// Blimp color should be green
		setSize(70);							// Size set to 70 by default
		initPos();								// Positions of refueling blimps will be random for now. Change for gameplay purposes later.
		drained = false;
	}
	
	// If the helicopter overlaps with the location of the blimp, empty the capacity and return the amount to refuel with
	int refuel(boolean overlap)
	{
		if (overlap && !drained)
		{
			new SoundInteractions("blimpRefuel.wav");
			int capacity = this.capacity;		// Take the current capacity and store it into temp
			this.capacity = 0;					// Empty the capacity
			setColor(r,155,b);				// Change the color to light green
			drained = true;						// Set flag for drained
			return capacity;					// Return the amount of fuel
		}
		return 0;
		
	}
	
	int getCapacity()
	{
		return this.capacity;
	}
	
	// Method to print out information of the current object
	void printInfo()
	{
		System.out.println("RefuelingBlimp: " +
						 "loc=" + Math.round(getLocationX()*10.0)/10.0 + ',' + Math.round(getLocationY()*10.0)/10.0 + ' ' + 
						 "color=[" + getColorR() + ','  + getColorG() + ',' + getColorB() + ']' + ' ' +
						 "size=" + getSize() + ' ' +
						 "capacity=" + getCapacity() + ' ');
	}

	@Override
	public void draw(Graphics g, Point containerOrigin)
	{
		g.setColor(getColor());
		g.fillArc(containerOrigin.getX() + (int)getLocationX(), containerOrigin.getY() + (int)getLocationY(), getSize() * 2, getSize(), 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.setFont(Font.createSystemFont((Font.FACE_SYSTEM), (Font.STYLE_ITALIC), (Font.SIZE_SMALL)));
		g.drawString(String.valueOf(getCapacity()), containerOrigin.getX() + (int)getLocationX() + 40, containerOrigin.getY() + (int)getLocationY() + 20);
	}

	@Override
	public boolean collidesWith(GameObject otherObject)
	{
		// Initial collision check
		if ((this.getLocationX() <= otherObject.getLocationX() + otherObject.getSize()
				&& this.getLocationY() <= otherObject.getLocationY() + otherObject.getSize()
				&& this.getLocationX() >= otherObject.getLocationX() - otherObject.getSize()
				&& this.getLocationY() >= otherObject.getLocationY() - otherObject.getSize())		// Coordination of the hitbox
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
		if (otherObject instanceof NonPlayerHelicopter)
			return;
		else if (otherObject instanceof Helicopter)
			refuel(true);
	}
}


