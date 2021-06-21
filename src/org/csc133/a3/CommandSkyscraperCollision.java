package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandSkyscraperCollision extends Command {

    // Note that code will need to be refactored later for actual collisions
    int i = 0;    // Only needed for testing purposes, remove later
    GameWorld g;

    public CommandSkyscraperCollision(GameWorld g) {
        super("Skyscraper Collision");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       // g.inputSky();
        //++i;
    }
}
