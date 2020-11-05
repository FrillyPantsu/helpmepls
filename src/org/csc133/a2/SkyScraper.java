package org.csc133.a2;

public class SkyScraper extends Fixed {
	// Each skyscraper acts as a waypoint. sequenceNumber orders each waypoint along the grid.
	// Skyscrapers can not change color and locations are set upon creation; are fixed.
	// Skyscraper locations are set by you, the user.
	// TO DO: Later the ability for helicopters to pick up and deliver mail as they pass skyscrapers will be added.
	private int sequenceNumber = 1;
	
	// SkyScraper Constructor
	SkyScraper()
	{
		setSeq(1);
		setColor(255,255,0);
		setSize(10);
		setLocationX(20.0);
		setLocationY(20.0);
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
}
