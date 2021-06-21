package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import java.io.IOException;
import java.io.InputStream;

public class DirectionalButtons extends Container
{
    public DirectionalButtons(CommandAccelerate acc, CommandBrake brake, CommandLeftTurn left, CommandRightTurn right) {
        try {
            setLayout(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
            getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.WHITE));
            getAllStyles().setBgTransparency(255);
            getAllStyles().setBgColor(ColorUtil.WHITE);

            InputStream x = Display.getInstance().getResourceAsStream(getClass(), "/WestButton.png");
            Image w = Image.createImage(x);
            x = Display.getInstance().getResourceAsStream(getClass(), "/NorthButton.png");
            Image n = Image.createImage(x);
            x = Display.getInstance().getResourceAsStream(getClass(), "/SouthButton.png");
            Image s = Image.createImage(x);
            x = Display.getInstance().getResourceAsStream(getClass(), "/EastButton.png");
            Image e = Image.createImage(x);

            Button west = new Button(w);
            Button south = new Button(s);
            Button north = new Button(n);
            Button east = new Button(e);

            // TO DO: Need to add method implementations for the action listeners in the place of NULL
            west.addActionListener(left);
            south.addActionListener(brake);
            north.addActionListener(acc);
            east.addActionListener(right);

            add(west);
            add(south);
            add(north);
            add(east);
        } catch (IOException e){}
    }


}
