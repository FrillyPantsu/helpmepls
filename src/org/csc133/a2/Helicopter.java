package org.csc133.a2;

public class Helicopter extends Movable implements ISteerable {
	// Helicopter can never exceed this speed
	final private int maximumSpeed = 10;
	
	// Indicates how much fuel is left. Helicopters with no fuel should have zero speed and cannot move.
	private int fuelLevel = 100;
	//Indicates the max capacity of the fuel tank
	final private int tankCapacity = 100;
	
	// Fuel consumption rate indicates how much fuel is spent each clock tick.
	final private int fuelConsumptionRate = 1;
	
	// Damage level increments everytime the helicopter collides with another object.
	// Default damage level is 0.
	private int damageLevel = 0;
	final private int maxDam = 10;
	
	// Indicates the sequence number of the last Skyscraper that the helicopter has reached in increasing order.
	// Initially set to 1 since the first Skyscraper is the starting point of the game.
	private int lastSkyScraperReached = 1;
	
	// "heading and stickAngle should be set to zero, and speed should be set to an appropriate positive (non-zero) value."
	// Stick angle is limited to maximum of + or - 40 degrees
	private int speed = 0, heading = 0, stickAngle = 0;
	
	// Helicopter constructor
	Helicopter()
	{
		setColor(255,255,255);						// Set color to black
		setSize(10);								// Set size to 10
		setLocationX(20.0);						// Position of helicopter should overlap with first SkyScraper
		setLocationY(20.0);
		setSpeed(this.speed);						// Set speed and heading to what's in this class
		setHeading(this.heading);
		
	}
	
	void damage(boolean hit)
	{
		if (hit && (damageLevel != maxDam))			// If the helicopter collides with a helicopter and isn't at maximum damage
		{
			++damageLevel;							// Increase the damage level by 1
			double tempDam = damageLevel/maxDam;	// Calculate percentage of how much speed should be reduced by
			speed = (int) (speed - (tempDam * maximumSpeed));// Apply the percentage to the current speed and subtract it from the total speed
			setSpeed(speed);
		}
		else if (damageLevel == maxDam)				// If at the maximum damage threshold
		{
			speed = 0;								// You don't get to move anymore
			setSpeed(speed);
			setColor(255,0,0);						// Set color to fully red, showing the helicopter destroyed
		}
	}
	
	void birdCol(boolean hit)
	{
		if (hit && (damageLevel != maxDam))			// If the helicopter collides with a bird and isn't at maximum damage
		{
			++damageLevel;							// Increase the damage level by 1
			double tempDam = (damageLevel/maxDam)/2;// Calculate percentage of how much speed should be reduced by
			speed = (int) (speed - (tempDam * maximumSpeed));// Apply the percentage to the current speed and subtract it from the total speed
			setSpeed(speed);
			setColor(155,0,0);						// Set color to a light red
		}
		else if (damageLevel == maxDam)				// If at the maximum damage threshold
		{
			speed = 0;								// You don't get to move anymore
			setSpeed(speed);
			setColor(255,0,0);						// Set color to fully red, showing the helicopter destroyed
		}
	}
	
	void refuel(boolean overlap, RefuelingBlimp temp)// Did the helicopter collide with a refueling blimp?
	{
		if (overlap)
		{
			fuelLevel += temp.refuel(overlap);		// If yes, refuel the helicopter
			if (fuelLevel > tankCapacity)			// If the amount of fuel received is greater than the capacity of the gas tank...
				fuelLevel = tankCapacity;			// Reset it to its max capacity
		}
	}
	
	void fuelConsume(boolean tick)
	{
		if (tick)									// If the clock ticks, reduce the fuel tank
			fuelLevel = fuelLevel - fuelConsumptionRate;
		if (fuelLevel == 0)							// Are you out of fuel?
		{
			speed = 0;								// You don't get to move anymore
			setSpeed(speed);
		}
	}
	
	// This represents the actually physical action of turning, not setting the stickAngle for a turn (see below for that method
	void turning(boolean tick)
	{
		if (tick && stickAngle != 0)				// If the clock ticks and the stickAngle isn't 0
		{			
			if (stickAngle <= -5)					// Go left if it's negative
			{
				heading = heading - 5;				// Trajectory can only change by 5 degrees each tick
				setHeading(heading);
				stickAngle = stickAngle + 5;		// Push the stickAngle back towards equilibrium for each tick
				return;
			}
			else if (stickAngle >= 5)				// Go right if it's positive
			{
				heading = heading + 5;				// Trajectory can only change by degrees each tick
				setHeading(heading);
				stickAngle = stickAngle - 5;		// Push the stickAngle back towards equilibrium for each tick
				return;
			}
			
			// For stickAngles between -5 and 5 but not 0
			if (stickAngle > -5 && stickAngle < 0)	// Slight left trajectory shifts
			{
				heading -= stickAngle;				// Shift the heading to the left by the remaining amount of stickAngle
				setHeading(heading);
				stickAngle = 0;						// Reset stickAngle
				return;
			}
			else if (stickAngle < 5 && stickAngle > 0) // Slight right trajectory shifts
			{
				heading += stickAngle;				// Shift the heading to the right by the remaining amount of stickAngle
				setHeading(heading);
				stickAngle = 0;						// Rest stickAngle
				return;
			}
			
		}
	}
	
	// Change the value to the last skyscraper reached if come into contact with the helicopter
	// Use the sequence number of the skyscraper for this
	void skyScraperReached(boolean reached, SkyScraper i)
	{
		if (reached)
		{
			int q = i.getSeq();
			q = lastSkyScraperReached;		
		}
	}
	
	// Accessors for helicopter information
	int getLastSky()
	{
		return lastSkyScraperReached;
	}
	
	int getDamage()
	{
		return damageLevel;
	}
	
	int getFuel()
	{
		return fuelLevel;
	}
	
	// Takes in the amount of stickAngle turn by the user
	// Negative values turn left, positive turn right
	// Can only accept values between -40 and +40; recalibrate user inputs if they go beyond this limitation
	void turnStickAngle(int turn)
	{
		if (turn > 40)
			stickAngle = 40;
		else if (turn < -40)
			stickAngle = -40;
		else
			stickAngle = turn;
	}
	
	void accelerate(boolean acc)
	{
		if (acc && (speed != maximumSpeed))			// If the user accelerates and hasn't reached the maximum speed yet
		{
			// Increment the current speed by 1, but also calculate the amount of damage that has accumulated with the speed
			speed = (speed + 1) - ((damageLevel/maxDam) * maximumSpeed);
			setSpeed(speed);
		}
	}
	
	void brake(boolean b)							// Slows the speed of the helicopter
	{
		if (b && (speed != 0))						// Is the helicopter stopped?
		{
			speed = (speed - 1);					// If not, decrease by 1
			return;
		}
		else if (speed < 1 && speed != 0)			// Is the speed less than 1?
		{
			speed = 0;								// Set it to 0.
			return;
		}
	}
	
	// Method to provide a print-out of current helicopter info
			void printInfo()
			{
				System.out.println("Helicopter: " +
								 "loc=" + Math.round(getLocationX()*10.0)/10.0 + ',' + Math.round(getLocationY()*10.0)/10.0 + ' ' + 
								 "color=[" + getColorR() + ','  + getColorG() + ',' + getColorB() + ']' + ' ' +
								 "size=" + getSize() + ' ' +
								 "heading=" + getHeading() + ' ' + "speed=" + getSpeed() + ' '
								 + "maxSpeed=" + maximumSpeed + ' ' +
								 "fuelLevel="  + fuelLevel + ' ' +
								 "stickAngle=" + stickAngle + ' ' + 
								 "damageLevel=" + damageLevel + ' ' +
								 "maxDamage=" + maxDam + ' ' + 
								 "tankCapacity=" + tankCapacity + ' ' +
								 "fuelConsumptionRate=" + fuelConsumptionRate + ' ');
			}
}
