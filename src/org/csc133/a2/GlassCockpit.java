package org.csc133.a2;
import java.io.IOException;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;


public class GlassCockpit extends Component 
{
	Image[] digitImages = new Image[10];
	Image[] tenthImages = new Image[10];
	Image colonImage;
	Image tenthDisplay;
	private static int HM_COLON_IDX = 2;
	
	private static final int numDigitsShowing = 8;
	Image[] clockDigits = new Image[numDigitsShowing];
	Image[] fuelDigits = new Image[4];
	Image[] damageDigits = new Image[2];
	Image lifeDigit;
	Image lastDigit;
	Image[] headingDigits = new Image[3];
	
	private int ledColorClock, ledColorFuel, ledColorLife, ledColorLast, ledColorDamage, ledColorHeading;
	
	public GlassCockpit()
	{
		
		try
		{
			// Create instances for each LED number
			digitImages[0] = Image.createImage("/LED_digit_0.png");
			digitImages[1] = Image.createImage("/LED_digit_1.png");
			digitImages[2] = Image.createImage("/LED_digit_2.png");
			digitImages[3] = Image.createImage("/LED_digit_3.png");
			digitImages[4] = Image.createImage("/LED_digit_4.png");
			digitImages[5] = Image.createImage("/LED_digit_5.png");
			digitImages[6] = Image.createImage("/LED_digit_6.png");
			digitImages[7] = Image.createImage("/LED_digit_7.png");
			digitImages[8] = Image.createImage("/LED_digit_8.png");
			digitImages[9] = Image.createImage("/LED_digit_9.png");
			
			tenthImages[0] = Image.createImage("/LED_digit_0_with_dot.png");
			tenthImages[1] = Image.createImage("/LED_digit_1_with_dot.png");
			tenthImages[2] = Image.createImage("/LED_digit_2_with_dot.png");
			tenthImages[3] = Image.createImage("/LED_digit_3_with_dot.png");
			tenthImages[4] = Image.createImage("/LED_digit_4_with_dot.png");
			tenthImages[5] = Image.createImage("/LED_digit_5_with_dot.png");
			tenthImages[6] = Image.createImage("/LED_digit_6_with_dot.png");
			tenthImages[7] = Image.createImage("/LED_digit_7_with_dot.png");
			tenthImages[8] = Image.createImage("/LED_digit_8_with_dot.png");
			tenthImages[9] = Image.createImage("/LED_digit_9_with_dot.png");
			
			colonImage = Image.createImage("/LED_colon.png");
		} catch (IOException e) {e.printStackTrace();}
		
		for (int i = 0; i < numDigitsShowing; i++)							// Defaults all clock digits to 0 if the clock isn't running
		{
			clockDigits[i] = digitImages[0];
		}
		
		for (int i = 0; i < 4; i++)
		{
			fuelDigits[i] = digitImages[0];									// Defaults all fuel digits to 0
		}
		
		for (int i = 0; i < 2; i++)											// Defaults all damage digits to 0
			damageDigits[i] = digitImages[0];
		
		for (int i = 0; i < 3; i++)											// Defaults all heading digits to 0
			headingDigits[i] = digitImages[0];
		
		lifeDigit = digitImages[0];
		lastDigit = digitImages[0];
		
		clockDigits[HM_COLON_IDX] = colonImage;								// Sets the dividing colon between minutes and seconds
		tenthDisplay = tenthImages[0];										// Default the tenth display to 0
		
		ledColorClock = ColorUtil.CYAN;										// Sets the LED colors
		ledColorFuel = ColorUtil.GREEN;
		ledColorDamage = ColorUtil.red(255);
		ledColorLife = ColorUtil.CYAN;
		ledColorLast = ColorUtil.CYAN;
		ledColorHeading = ColorUtil.YELLOW;
		
	}
	
	// Sets game time, taking in parameters minutes, seconds and milliseconds
	public void setGameTime(int m, int s, int ms)
	{
		clockDigits[0] = digitImages[m/10];
		clockDigits[1] = digitImages[m%10];
		clockDigits[3] = digitImages[s/10];
		clockDigits[4] = digitImages[s%10];
		tenthDisplay = tenthImages[ms];
	}
	
	// Sets HUD digits
	public void setFuel(int fuel)
	{
		fuelDigits[0] = digitImages[fuel/1000];
		fuelDigits[1] = digitImages[(fuel%1000)/100];
		fuelDigits[2] = digitImages[(fuel%100)/10];
		fuelDigits[3] = digitImages[fuel%10];
	}
	
	public void setDamage(int dam)
	{
		damageDigits[0] = digitImages[dam/10];
		damageDigits[1] = digitImages[dam%10];
	}
	
	public void setLives(int life)
	{
		lifeDigit = digitImages[life];
	}
	
	public void setLast(int last)
	{
		lastDigit = digitImages[last];
	}
	
	public void setHeading(int h)
	{
		headingDigits[0] = digitImages[h/100];
		headingDigits[1] = digitImages[(h%100)/10];
		headingDigits[2] = digitImages[h%10];
	}
	
	public void start()
	{
		getComponentForm().registerAnimated(this);
	}
	
	public void stop()
	{
		getComponentForm().deregisterAnimated(this);
	}
	
	public void laidout()
	{
		this.start();
	}
	
	@Override
	protected Dimension calcPreferredSize()
	{
		return new Dimension(colonImage.getWidth()*numDigitsShowing,colonImage.getHeight());
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		final int COLOR_PAD = 1;
		
		int digitWidth = clockDigits[0].getWidth();
		int digitHeight = clockDigits[0].getHeight();
		int clockWidth = numDigitsShowing * digitWidth;
		
		float scaleFactor = Math.min(getInnerHeight()/(float) digitHeight, getInnerWidth()/(float)clockWidth);
		
		int displayDigitWidth = (int)(scaleFactor*digitWidth);
		int displayDigitHeight = (int)(scaleFactor*digitHeight);
		int displayClockWidth = displayDigitWidth*numDigitsShowing;
		
		int displayX = getX() + (getWidth() - displayClockWidth)/2;
		int displayY = getY() + (getHeight() - displayDigitHeight)/2;
		
		g.setColor(ColorUtil.BLACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		g.setColor(ledColorClock);
		g.fillRect(displayX+COLOR_PAD, displayY+COLOR_PAD, displayClockWidth - COLOR_PAD*2, displayDigitHeight - COLOR_PAD*2);
		
		for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++)
		{
			g.drawImage(clockDigits[digitIndex], displayX + digitIndex * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
		}
	}
	
}
