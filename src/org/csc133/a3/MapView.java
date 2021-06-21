package org.csc133.a3;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Component
{
    GameWorld display;
    Point p;

    MapView(GameWorld o)
    {
        // Pass in a reference of the GameObjectCollection to be used for drawing the map
        display = o;
    }

    // Paint and animation methods
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        p = new Point(this.getX(), this.getY());
        for (int i = 0; i < display.getObjects().size(); i++)
        {
            display.getObjects().get(i).draw(g, p);
        }
    }

    public void update()
    {
        repaint();
    }


}
