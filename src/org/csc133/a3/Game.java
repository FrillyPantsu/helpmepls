package org.csc133.a3;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;


public class Game extends Form implements Runnable{

	GameWorld gw = new GameWorld();
	MapView mv = new MapView(gw);

	// TO DO: Pretty much everything
	public Game()
	{
		// Code to initialize the HUD

		// Commands
		CommandExit exit = new CommandExit();
		CommandAbout about = new CommandAbout();
		CommandHelp help = new CommandHelp();
		CommandNphStrategy str = new CommandNphStrategy(gw);
		CommandAccelerate acc = new CommandAccelerate(gw);
		CommandBrake brake = new CommandBrake(gw);
		CommandLeftTurn left = new CommandLeftTurn(gw);
		CommandRightTurn right = new CommandRightTurn(gw);
		CommandNphCollision nphcol = new CommandNphCollision(gw);
		CommandBlimpCollision blimpcol = new CommandBlimpCollision(gw);
		CommandBirdCollision birdcol = new CommandBirdCollision(gw);
		CommandSkyscraperCollision skycol = new CommandSkyscraperCollision(gw);
		SoundBackground bgm = new SoundBackground("backgroundMusic.wav");
		CommandBgMusicControl bgc = new CommandBgMusicControl(bgm);

		// Creating the containers and forms
		// Toolbar
		Toolbar sideToolbar = new Toolbar();
		setToolbar(sideToolbar);
		sideToolbar.setOnTopSideMenu(true);
		Label filler = new Label(" ");
		sideToolbar.addComponentToLeftSideMenu(filler);
		sideToolbar.addCommandToLeftSideMenu(bgc);
		sideToolbar.addCommandToLeftSideMenu(str);
		sideToolbar.addCommandToLeftSideMenu(help);
		sideToolbar.addCommandToLeftSideMenu(about);
		sideToolbar.addCommandToLeftSideMenu(exit);

		// Key listeners
		this.addKeyListener('a', acc);
		this.addKeyListener('b', brake);
		this.addKeyListener('l', left);
		this.addKeyListener('r', right);
		this.addKeyListener('s', skycol);
		this.addKeyListener('n', nphcol);
		this.addKeyListener('e', blimpcol);
		this.addKeyListener('g', birdcol);

		// Buttons
		DirectionalButtons sButtons = new DirectionalButtons(acc, brake, left, right);

		// Initialize the layout
		this.setLayout(new BorderLayout());
		this.setTitle("私は死にたい");

		// Loading forms into the containers
		this.add(BorderLayout.NORTH, new GlassCockpit(gw));
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.SOUTH, sButtons);
		this.show();

		// Background music
		bgm.play();

		run();
	}

	@Override
	public void run() {
		UITimer timer = new UITimer(this);
		timer.timer(20, true, this, () -> {gw.inputTick(mv);});
	}
}
