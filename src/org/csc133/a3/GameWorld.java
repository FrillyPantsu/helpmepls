package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import java.util.Arrays;
import java.util.Vector;

public class GameWorld {
	// Initialize the game clock and lives separate from the rest of the objects for scope purposes
	public int gameClock = 0;
	boolean gameOver = false;

	private final static int numOfHelicopters = 1;
	private final static int numOfRefuelingBlimps = 4;
	private final static int numOfBirds = 10;
	private final static int numOfSkyScrapers = 5;
	final static int numOfNonPlayerHelicopters = 2;

	Helicopter[] helicopters = new Helicopter[numOfHelicopters];
	NonPlayerHelicopter[] npcs = new NonPlayerHelicopter[numOfNonPlayerHelicopters];
	static Helicopter player;
	RefuelingBlimp[] blimps = new RefuelingBlimp[numOfRefuelingBlimps];
	Bird[] birds = new Bird[numOfBirds];
	SkyScraper[] skies = new SkyScraper[numOfSkyScrapers];

	GameObjectCollection objects; 

	public GameWorld()
	{
		objects = new GameObjectCollection();

		// TO DO: Put code to create objects and assign values
		for (int i = 0; i < numOfHelicopters; i++) {
			helicopters[i] = new Helicopter();
		}
		// IMPORTANT TO NOTE:
		// helicopters[0] will ALWAYS be the player controlled helicopter
		player = helicopters[0];

		for (int i = 0; i < numOfRefuelingBlimps; i++) {
			blimps[i] = new RefuelingBlimp(300*(i+1),300*(i+1), i);
			blimps[i].initPos();
			blimps[i].setObjIdentifier(i);
		}
		for (int i = 1; i < numOfSkyScrapers; i++) {
			skies[i] = new SkyScraper(200.0*(i+1),300.0*(i+1), i);
			skies[i].setSeq(i+1);
		}
		for (int i = 0; i < numOfBirds; i++) {
			birds[i] = new Bird();
		}
		for (int i = 0; i < numOfNonPlayerHelicopters; i++) {
			npcs[i] = new NonPlayerHelicopter(this);
			npcs[i].setLocationX(player.getLocationX()*10*(i+1));
			npcs[i].setLocationY(player.getLocationY()*10*(i+1));
		}

		// Separating the instantiation of the first skyscraper so that we spawn here
		skies[0] = new SkyScraper(player.getLocationX(),player.getLocationY(), 1);


		// Load all instances of objects into the GameObjectCollection
		for (int i = 0; i < numOfHelicopters; i++)
			objects.add(helicopters[i]);

		for (int i = 0; i < numOfNonPlayerHelicopters; i++)
			objects.add(npcs[i]);

		for (int i = 0; i < numOfRefuelingBlimps; i++)
			objects.add(blimps[i]);

		for (int i = 0; i < numOfBirds; i++)
			objects.add(birds[i]);

		for (int i = 0; i < numOfSkyScrapers; i++)
			objects.add(skies[i]);

		for (int i = 0; i < numOfNonPlayerHelicopters; i++)
			objects.add(npcs[i]);

	}

	public Helicopter getPlayer()
	{
		return player;
	}

	public GameObjectCollection getObjects()
	{
		return this.objects;
	}

	// Commands are outdated code from AP1, ignore their comments
	// Input A for accelerate
	void inputAcc()
	{
		this.player.accelerate(true);
	}

	// Input B for brake
	void inputBrake()
	{
		this.player.brake(true);
	}

	// Input L for left turn on StickAngle
	void inputStickL()
	{
		this.player.turnStickAngle(-5);
	}

	// Input R for right turn on StickAngle
	void inputStickR()
	{
		this.player.turnStickAngle(5);
	}

	// Input C for collision
	void inputColl()
	{
		this.player.damage(true);
	}

	// Input E for intersection with a refuelingblimp
	void inputRef(int i)
	{
		if (i <= numOfRefuelingBlimps)
			player.refuel(true, blimps[i]);
	}

	// Input G for bird collision
	void inputBirdCol()
	{
		player.birdCol(true);
	}

	// Input T for clock tick
	// Updates position, heading, fuel, game clock, collision detection etc.
	void inputTick(MapView mv)
	{
		// Player helicopter
		for (int i = 0; i < numOfHelicopters; i++) {
			player.turning(true);
			player.fuelConsume(true);
			player.move(mv);
			// Collision detection for objects
			for (int k = 0; k < numOfBirds; k++) {
				if (player.collidesWith(birds[k]))
					player.handleCollision(birds[k]);
			}
			for (int k = 0; k < numOfRefuelingBlimps; k++) {
				if (player.collidesWith(blimps[k]))
					player.handleCollision(blimps[k]);
			}
			for (int k = 0; k < numOfSkyScrapers; k++) {
				if (player.collidesWith(skies[k])) {
					player.handleCollision(skies[k]);
					new SoundInteractions("skyReached.wav");
				}
			}
			for (int k = 0; k < numOfNonPlayerHelicopters; k++) {
				if (player.collidesWith(npcs[k]))
					player.handleCollision(npcs[k]);
			}
		}
		// Birds
		for (int i = 0; i < numOfBirds; i++) {
			birds[i].alterDir(true);
			birds[i].move(mv);
			// Collision detection for objects
			for (int k = 0; k < numOfNonPlayerHelicopters; k++) {
				if (birds[i].collidesWith(npcs[k]))
					birds[i].handleCollision(npcs[k]);
			}
			for (int k = 0; k < numOfHelicopters; k++) {
				if (birds[i].collidesWith(player))
					birds[i].handleCollision(player);
			}
		}
		// NonPlayer Helicopters
		for (int i = 0; i < numOfNonPlayerHelicopters; i++) {
			npcs[i].turning(true);
			npcs[i].move(mv);
			npcs[i].invokeStrategy();
			// Collision detection for objects
			for (int k = 0; k < numOfBirds; k++) {
				if (npcs[i].collidesWith(birds[k]))
					npcs[i].handleCollision(birds[k]);
			}
			for (int k = 0; k < numOfRefuelingBlimps; k++) {
				if (npcs[i].collidesWith(blimps[k]))
					npcs[i].handleCollision(blimps[k]);
			}
			for (int k = 0; k < numOfSkyScrapers; k++) {
				if (npcs[i].collidesWith(skies[k]))
					npcs[i].handleCollision(skies[k]);
			}
			for (int k = 0; k < numOfHelicopters; k++) {
				if (npcs[i].collidesWith(player))
					npcs[i].handleCollision(player);
			}
		}

		// Player interactions
		if (!gameOver && (int)player.getDamage() >= 10)
			respawn();


		// Framerate interactions
		mv.update();
		++gameClock;
	}

	void respawn()
	{
		if (player.getLives() > 0) {
			player.setLocationX(skies[0].getLocationX());
			player.setLocationY(skies[0].getLocationY());
			player.setSpeed(0);
			player.setHeading(0);
			player.setFuelLevel(2000);
			player.setDamageLevel(0);
			player.maximumSpeed = 10;
			player.loseLife();
			player.dead = false;
		}
		else
			gameOver = true;
	}

	// Input D for display
	// Displays the game information in printed text
	void inputDisplay()
	{
		System.out.println("Lifecount: " + helicopters[0].getLives()
		+ ' ' + "Clock counter: " + gameClock
		+ ' ' + "Last SkyScraper Reached: " + helicopters[0].getLastSky()
		+ ' ' + "Fuel level: " + helicopters[0].getFuel()
		+ ' ' + "Damage level: " + helicopters[0].getDamage());
	}

	// Input M for map display generation in text
	// May use array structures in for loops for objects containing more than 1 instance
	void inputMap()
	{
		for (int i = 0; i < numOfHelicopters; i++)
			helicopters[i].printInfo();
		for (int i = 0; i < numOfBirds; i++)
			birds[i].printInfo();
		for (int i = 0; i < numOfRefuelingBlimps; i++)
			blimps[i].printInfo();
		for (int i = 0; i < numOfSkyScrapers; i++)
				skies[i].printInfo();

	}
	
}
