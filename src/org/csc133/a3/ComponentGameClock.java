package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ComponentGameClock extends Component
{
    Image[] digitImages = new Image[10];
    Image[] onesImages = new Image[10];
    Image colonImage;
    GameWorld gw;
    // scaleFactor used to resize LEDs
    static final float scaleFactor = (float)0.6;

    int numDigits = 5;
    private static int HS_COLON_IDX = 2;
    Image[] clockDigits = new Image[numDigits];                      // Note that 5th index holds ms digit
    int ledColor = ColorUtil.CYAN;
    int ledColorTenth = ColorUtil.BLUE;
    Image onesDisplay;
    boolean tenMinPast;
    long elapsedTime;
    long startTime;

    ComponentGameClock(GameWorld gw)
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

            onesImages[0] = Image.createImage("/LED_digit_0_with_dot.png");
            onesImages[1] = Image.createImage("/LED_digit_1_with_dot.png");
            onesImages[2] = Image.createImage("/LED_digit_2_with_dot.png");
            onesImages[3] = Image.createImage("/LED_digit_3_with_dot.png");
            onesImages[4] = Image.createImage("/LED_digit_4_with_dot.png");
            onesImages[5] = Image.createImage("/LED_digit_5_with_dot.png");
            onesImages[6] = Image.createImage("/LED_digit_6_with_dot.png");
            onesImages[7] = Image.createImage("/LED_digit_7_with_dot.png");
            onesImages[8] = Image.createImage("/LED_digit_8_with_dot.png");
            onesImages[9] = Image.createImage("/LED_digit_9_with_dot.png");

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {e.printStackTrace();}

        startTime = System.currentTimeMillis();

        for (int i = 0; i < numDigits; i++)							// Defaults all clock digits to 0 if the clock isn't running
        {
            clockDigits[i] = digitImages[0];
        }
        onesDisplay = onesImages[0];										// Default the tenth display to 0
        clockDigits[HS_COLON_IDX] = colonImage;								// Sets the dividing colon between minutes and seconds
    }

    // Sets game time, taking in parameters minutes, seconds and milliseconds
    public void setDisplayTime()
    {
        int m = (int)(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-startTime)%60);
        int s = (int)(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-startTime)%60);
        int ms = (int)(TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-startTime)/100);

        if (m == 10)                                                      // When 10 minutes pass, set the flag to the paint method to change color to red
        {
            tenMinPast = true;
            repaint();
        }
        clockDigits[0] = digitImages[m/10];
        clockDigits[1] = digitImages[m%10];
        clockDigits[3] = digitImages[s/10];
        onesDisplay = onesImages[s%10];
        clockDigits[4] = digitImages[ms%10];

    }

    public void resetElapsedTime()
    {
        clockDigits[0] = digitImages[0];
        clockDigits[1] = digitImages[0];
        clockDigits[3] = digitImages[0];
        onesDisplay = onesImages[0];
        clockDigits[4] = digitImages[0];
        elapsedTime = 0;
    }

    public void startElapsedTime()
    {
        start();
    }

    public void stopElapsedTime()
    {
        stop();
    }

    public float getElapsedTime()
    {
        return elapsedTime;
    }

    public void start()
    {
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    public void laidOut()
    {
        this.start();
    }

    @Override
    public boolean animate()
    {
        elapsedTime = System.currentTimeMillis() - startTime;
        setDisplayTime();

        return true;
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }

    @Override
    protected Dimension calcPreferredSize()
    {
        return new Dimension(colonImage.getWidth()*numDigits,colonImage.getHeight());
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigits;

        int displayX = getX();
        int displayY = getY();

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), displayClockWidth, displayDigitHeight);

        g.setColor(ledColor);
        g.fillRect(displayX, displayY, displayClockWidth, displayDigitHeight);

        if (tenMinPast)             // This if statement is used to change the color to red after 10 minutes
        {
            g.setColor(ColorUtil.rgb(255,0,0));
            g.fillRect(displayX, displayY, displayClockWidth, displayDigitHeight);
        }

        g.drawImage(clockDigits[0], displayX + 0 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(clockDigits[1], displayX + 1 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(clockDigits[2], displayX + 2 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(clockDigits[3], displayX + 3 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        // Below draws the clock piece for the holder of ones place and tenth digit proceeding it
        g.drawImage(onesDisplay, displayX + 4 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);

        g.setColor(ledColorTenth);

        if (tenMinPast)
        {
            g.setColor(ColorUtil.rgb(255,0,0));
            g.fillRect(displayX + 5 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        }

        g.fillRect(getX()+(displayDigitWidth*5), getY(), displayDigitWidth, displayDigitHeight);

        g.drawImage(clockDigits[4], displayX + 5 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);

    }


}
