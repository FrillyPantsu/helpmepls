package org.csc133.a2;

public class RefuelingBlimp extends Fixed {
	// Initial capacity of of refueling blimp is proportional to its size. Size should be decided by you on initialization.
	private int capacity;
	
	// Color variables
	private int r = 0, g = 255, b = 0;
		
	// Constructor
	RefuelingBlimp()
	{
		capacity = getSize();					// Set capacity to the current size
		setColor(r,g,b);						// Blimp color should be green
		setSize(20);							// Size set to 20 by default
		initPos();								// Positions of refueling blimps will be random for now. Change for gameplay purposes later.
	}
	
	// If the helicopter overlaps with the location of the blimp, empty the capacity and return the amount to refuel with
	int refuel(boolean overlap)
	{
		if (overlap)
		{
			int capacity = this.capacity;		// Take the current capacity and store it into temp
			this.capacity = 0;					// Empty the capacity
			setColor(r,155,b);					// Change the color to light green
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
	
	
	
}


