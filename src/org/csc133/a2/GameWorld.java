package org.csc133.a2;

public class GameWorld {
	// Initialize the game clock and lives separate from the rest of the objects for scope purposes
	private int gameClock = 0;
	private int lives = 3;
	
	// SkyScraper objects instantiated in an array
	private SkyScraper[] skies = new SkyScraper[9];
	
	private Helicopter player = new Helicopter();
	private Bird bird = new Bird();
	private RefuelingBlimp blimp = new RefuelingBlimp();
	
	
	public void init()
	{
			
		// Setting up all the skyscraper objects
		skies[0] = new SkyScraper();
		skies[1] = new SkyScraper();
		skies[2] = new SkyScraper();
		skies[3] = new SkyScraper();
		skies[4] = new SkyScraper();
		skies[5] = new SkyScraper();
		skies[6] = new SkyScraper();
		skies[7] = new SkyScraper();
		skies[8] = new SkyScraper();
		
		skies[1].setLocationX(30.0);
		skies[1].setLocationY(30.0);
		skies[2].setLocationX(40.0);
		skies[2].setLocationY(40.0);
		skies[3].setLocationX(50.0);
		skies[3].setLocationY(50.0);
		skies[4].setLocationX(60.0);
		skies[4].setLocationY(60.0);
		skies[5].setLocationX(70.0);
		skies[5].setLocationY(70.0);
		skies[6].setLocationX(80.0);
		skies[6].setLocationY(80.0);
		skies[7].setLocationX(85.0);
		skies[7].setLocationY(85.0);
		skies[8].setLocationX(90.0);
		skies[8].setLocationY(90.0);
		
		skies[0].setSeq(1);
		skies[1].setSeq(2);
		skies[2].setSeq(3);
		skies[3].setSeq(4);
		skies[4].setSeq(5);
		skies[5].setSeq(6);
		skies[6].setSeq(7);
		skies[7].setSeq(8);
		skies[8].setSeq(9);
		
		
		
		
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
	
	// Input 1-9 for a collision with a SkyScraper representing the sequence number for that SkyScraper to the input
	void inputSky(int i)
	{
		this.player.skyScraperReached(true, this.skies[i]);
	}

	// Input E for intersection with a refuelingblimp
	void inputRef()
	{
		this.player.refuel(true, this.blimp);
	}
	
	// Input G for bird collision
	void inputBirdCol()
	{
		this.player.birdCol(true);
	}
	
	// Input T for clock tick
	// Updates position, heading, fuel, game clock etc.
	void inputTick()
	{
		this.player.turning(true);
		this.bird.alterDir(true);
		this.player.move();
		this.bird.move();
		this.player.fuelConsume(true);
		++gameClock;
	}
	
	// Input D for display
	// Displays the game information in printed text
	void inputDisplay()
	{
		System.out.println("Lifecount: " + lives
		+ ' ' + "Clock counter: " + gameClock
		+ ' ' + "Last SkyScraper Reached: " + this.player.getLastSky()
		+ ' ' + "Fuel level: " + this.player.getFuel()
		+ ' ' + "Damage level: " + this.player.getDamage());
	}
	
	// Input M for map display generation in text
	// May use array structures in for loops for objects containing more than 1 instance
	void inputMap()
	{
		this.player.printInfo();	
		this.bird.printInfo();
		this.blimp.printInfo();
		for (int i = 0; i < skies.length; i++)
			this.skies[i].printInfo();
		
	}
	
}
