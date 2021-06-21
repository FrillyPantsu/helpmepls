package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

public class ComponentLives extends Component {
    Image[] digitImages = new Image[10];
    Image[] tenthImages = new Image[10];
    Image colonImage;
    GameWorld gw;
    // scaleFactor used to resize LEDs
    static final float scaleFactor = (float)0.6;

    int ledColor = ColorUtil.CYAN;
    Image lifeDigit;
    int numDigits = 1;

    ComponentLives(GameWorld gw)
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

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {e.printStackTrace();}

        lifeDigit = digitImages[0];
        setLives();
    }

    private void setLives()
    {
        int life = gw.player.getLives();
        lifeDigit = digitImages[life];
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
        setLives();
        return true;
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
        final int COLOR_PAD = 1;

        int digitWidth = digitImages[0].getWidth();
        int digitHeight = digitImages[0].getHeight();
        int clockWidth = digitWidth;

        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigits;

        int displayX = getX();
        int displayY = getY();

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), displayClockWidth, displayDigitHeight);

        g.setColor(ledColor);
        g.fillRect(displayX, displayY, displayClockWidth, displayDigitHeight);

        g.drawImage(lifeDigit, displayX + 0 * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);


    }
}
