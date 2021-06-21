package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.ArrayList;
import java.util.Vector;

public class Helicopter extends Movable implements ISteerable {
	// Helicopter can never exceed this speed
	int maximumSpeed = 10;
	
	// Indicates how much fuel is left. Helicopters with no fuel should have zero speed and cannot move.
	private int fuelLevel = 2000;
	//Indicates the max capacity of the fuel tank
	final private int tankCapacity = 2000;
	
	// Fuel consumption rate indicates how much fuel is spent each clock tick.
	final private int fuelConsumptionRate = 1;
	
	// Damage level increments everytime the helicopter collides with another object.
	// Default damage level is 0.
	private float damageLevel = 0;
	final private int maxDam = 10;
	boolean dead = false;

	// Number of lives
	private int lives = 3;
	
	// Indicates the sequence number of the last Skyscraper that the helicopter has reached in increasing order.
	// Initially set to 1 since the first Skyscraper is the starting point of the game.
	private int lastSkyScraperReached = 1;
	
	// "heading and stickAngle should be set to zero, and speed should be set to an appropriate positive (non-zero) value."
	// Stick angle is limited to maximum of + or - 40 degrees
	private int speed = 0, heading = 0, stickAngle = 0;

	Vector<GameObject> currentlyCollidingWith = new Vector<GameObject>();
	
	// Helicopter constructor
	public Helicopter()
	{
		super(20.0, 20.0, 0);
		setColor(0,0,0);				// Set color to black
		setSize(50);							// Set size to 50
		setLocationX(40.0);						// Position of helicopter should overlap with first SkyScraper
		setLocationY(40.0);
		setSpeed(this.speed);					// Set speed and heading to what's in this class
		setHeading(this.heading);
		
	}

	int getLives()
	{
		return lives;
	}

	void loseLife()
	{
		new SoundInteractions("playerDeath.wav");
		--lives;
	}

	void setDamageLevel(int damageLevel)
	{
		this.damageLevel = damageLevel;
	}

	void damage(boolean hit)
	{
		new SoundInteractions("nphCol.wav");

		if (hit && (damageLevel != maxDam))			// If the helicopter collides with a helicopter and isn't at maximum damage
		{
			damageLevel += 3;
			speed -= 3;
			maximumSpeed -= 3;
			if (speed <= 0 || damageLevel >= maxDam) {
				setSpeed(0);
				return;
			}
			setSpeed(speed);
		}
		else if (damageLevel >= maxDam)				// If at the maximum damage threshold
		{
			speed = 0;								// You don't get to move anymore

			setSpeed(speed);
			setColor(255,0,0);						// Set color to fully red, showing the helicopter destroyed
			dead = true;
		}
	}
	
	void birdCol(boolean hit)
	{
		if (hit && (damageLevel != maxDam))			// If the helicopter collides with a bird and isn't at maximum damage
		{
			damageLevel += 1;
			speed -= 1;
			maximumSpeed -= 1;
			if (speed <= 0 || damageLevel >= maxDam) {
				setSpeed(0);
				return;
			}
			setSpeed(speed);
			setColor(155,0,0);						// Set color to a light red
		}
		else if (damageLevel >= maxDam)				// If at the maximum damage threshold
		{
			speed = 0;								// You don't get to move anymore
			setSpeed(speed);
			setColor(255,0,0);						// Set color to fully red, showing the helicopter destroyed
			dead = true;
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
			if (stickAngle <= -5)					// Go left if it's negative
			{
				heading = getHeading();
				heading -= 5;						// Trajectory can only change by 5 degrees each tick
				if (heading <= 0)					// Wrapper
					heading += 360;
				setHeading(heading);
				stickAngle = stickAngle + 5;		// Push the stickAngle back towards equilibrium for each tick
				return;
			}
			else if (stickAngle >= 5)				// Go right if it's positive
			{
				heading = getHeading();
				heading += 5;						// Trajectory can only change by degrees each tick
				if (heading >= 360)					// Wrapper
					heading -= 360;
				setHeading(heading);
				stickAngle = stickAngle - 5;		// Push the stickAngle back towards equilibrium for each tick
				return;
			}

			// For stickAngles between -5 and 5 but not 0
			if (stickAngle > -5 && stickAngle < 0)	// Slight left trajectory shifts
			{
				heading = getHeading();
				heading -= stickAngle;				// Shift the heading to the left by the remaining amount of stickAngle
				setHeading(heading);
				stickAngle = 0;						// Reset stickAngle
				return;
			}
			else if (stickAngle < 5 && stickAngle > 0) // Slight right trajectory shifts
			{
				heading = getHeading();
				heading += stickAngle;				// Shift the heading to the right by the remaining amount of stickAngle
				setHeading(heading);
				stickAngle = 0;						// Rest stickAngle
				return;
			}


	}
	
	// Change the value to the last skyscraper reached if come into contact with the helicopter
	// Use the sequence number of the skyscraper for this
	void skyScraperReached(boolean reached, SkyScraper sky)
	{
		if (reached)
		{
			lastSkyScraperReached = sky.getSeq();
		}
	}

	// Accessors for helicopter information
	int getLastSky()
	{
		return lastSkyScraperReached;
	}
	
	float getDamage()
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
		if (acc && (speed != maximumSpeed) && (damageLevel < maxDam) && (fuelLevel > 0))			// If the user accelerates and hasn't reached the maximum speed yet
		{
			// Increment the current speed by 1, but also calculate the amount of damage that has accumulated with the speed
			speed = (speed + 1) - (((int)damageLevel/maxDam) * maximumSpeed);
			setSpeed(speed);
		}
	}
	
	void brake(boolean b)							// Slows the speed of the helicopter
	{
		if (b && (speed > 0))						// Is the helicopter stopped?
		{
			speed = (speed - 1);					// If not, decrease by 1
			setSpeed(speed);
			return;
		}
		else if (speed < 1 && speed != 0)			// Is the speed less than 1?
		{
			speed = 0;								// Set it to 0.
			setSpeed(speed);
			return;
		}
	}

	// Setters and getters that were created solely for the use of NPH functions
	int getMaximumSpeed()
	{
		return maximumSpeed;
	}

	int getFuelLevel()
	{
		return fuelLevel;
	}

	int getTankCapacity(){
		return tankCapacity;
	}

	int getFuelConsumptionRate(){
		return fuelConsumptionRate;
	}

	int getMaxDam(){
		return maxDam;
	}

	public void setLastSkyScraperReached(int lastSkyScraperReached) {
		this.lastSkyScraperReached = lastSkyScraperReached;
	}

	int getLastSkyScraperReached(){
		return lastSkyScraperReached;
	}

	public void setStickAngle(int stickAngle) {
		this.stickAngle = stickAngle;
	}

	int getStickAngle(){
		return stickAngle;
	}

	void setFuelLevel(int i)
	{
		fuelLevel = i;
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


	@Override
	public void draw(Graphics g, Point containerOrigin)
	{
		// Quick bugfix for negative speeds
		if (speed < 0)
			speed = 0;

		int x = containerOrigin.getX() + (int)getLocationX() + getSize()/8;
		int y = containerOrigin.getY() + (int)getLocationY() + getSize()/8;

		g.setColor(getColor());
		g.fillArc(containerOrigin.getX() + (int)getLocationX() - getSize()/2, containerOrigin.getY() + (int)getLocationY() - getSize()/2, getSize(), getSize(), 0, 360);

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
		if (otherObject instanceof RefuelingBlimp)
			refuel(true, (RefuelingBlimp)otherObject);
		if (otherObject instanceof SkyScraper)
			skyScraperReached(true, (SkyScraper)otherObject);
		if (otherObject instanceof Helicopter)
			damage(true);
		if (otherObject instanceof NonPlayerHelicopter)
			damage(true);
		if (otherObject instanceof Bird)
			birdCol(true);

	}
}
