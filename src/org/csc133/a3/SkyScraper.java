package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Vector;

public class SkyScraper extends Fixed {
	// Each skyscraper acts as a waypoint. sequenceNumber orders each waypoint along the grid.
	// Skyscrapers can not change color and locations are set upon creation; are fixed.
	// Skyscraper locations are set by you, the user.
	// TO DO: Later the ability for helicopters to pick up and deliver mail as they pass skyscrapers will be added.
	private int sequenceNumber = 1;
	Vector<GameObject> currentlyCollidingWith = new Vector<GameObject>();
	
	// SkyScraper Constructor
	public SkyScraper(double x, double y, int identifier)
	{
		super(x, y, 0);
		setSeq(1);
		setColor(255,133,72);
		setSize(50);
	}
	
	// Accessors and mutators
	void setSeq(int seq)
	{
		this.sequenceNumber = seq;
	}
	
	int getSeq()
	{
		return this.sequenceNumber;
	}
	
	// Prints the information about the current object
	void printInfo()
	{
		System.out.println("SkyScraper: " +
						 "loc=" + Math.round(getLocationX()*10.0)/10.0 + ',' + Math.round(getLocationY()*10.0)/10.0 + ' ' + 
						 "color=[" + getColorR() + ','  + getColorG() + ',' + getColorB() + ']' + ' ' +
						 "size=" + getSize() + ' ' +
						 "seqNum=" + getSeq() + ' ');
	}

	@Override
	public void draw(Graphics g, Point containerOrigin)
	{
		g.setColor(getColor());
		g.fillRect((int)getLocationX() + containerOrigin.getX(), (int)getLocationY() + containerOrigin.getY(), (int)getSize(), (int)getSize());
		g.setColor(ColorUtil.BLACK);
		g.setFont(Font.createSystemFont((Font.FACE_SYSTEM), (Font.STYLE_ITALIC), (Font.SIZE_SMALL)));
		g.drawString(String.valueOf(getSeq()), (int)getLocationX() + containerOrigin.getX(), (int)getLocationY() + containerOrigin.getY());
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

	}
}
