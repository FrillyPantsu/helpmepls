package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

public class ComponentFuel extends Component {
    Image[] digitImages = new Image[10];
    Image[] tenthImages = new Image[10];
    Image colonImage;
    GameWorld gw;
    // scaleFactor used to resize LEDs
    static final float scaleFactor = (float)0.6;

    Image[] fuelDigits = new Image[4];
    private int ledColor = ColorUtil.GREEN;
    private int numDigits = 4;

    ComponentFuel(GameWorld gw)
    {
        this.gw = gw;
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

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {e.printStackTrace();}

        for (int i = 0; i < 4; i++)
        {
            fuelDigits[i] = digitImages[0];									// Defaults all fuel digits to 0
        }
    }

    // Sets HUD digits
    private void setFuel()
    {
        int fuel = gw.player.getFuel();

        if (fuel > 0) {
            fuelDigits[0] = digitImages[fuel / 1000];
            fuelDigits[1] = digitImages[(fuel % 1000) / 100];
            fuelDigits[2] = digitImages[(fuel % 100) / 10];
            fuelDigits[3] = digitImages[fuel % 10];
        }
        else if (fuel < 0) {
            fuelDigits[0] = digitImages[0];
            fuelDigits[1] = digitImages[0];
            fuelDigits[2] = digitImages[0];
            fuelDigits[3] = digitImages[0];
        }
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
        setFuel();
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

        int digitWidth = digitImages[0].getWidth();
        int digitHeight = digitImages[0].getHeight();

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigits;

        int displayX = getX();
        int displayY = getY();

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), displayClockWidth, displayDigitHeight);

        g.setColor(ledColor);
        g.fillRect(displayX, displayY, displayClockWidth, displayDigitHeight);


        g.drawImage(fuelDigits[0], displayX + 0 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(fuelDigits[1], displayX + 1 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(fuelDigits[2], displayX + 2 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        g.drawImage(fuelDigits[3], displayX + 3 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);

    }
}
