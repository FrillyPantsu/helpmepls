package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandBlimpCollision extends Command {

    // The code here will eventually need to be refactored
    GameWorld g;
    int i = 0;  // Only needed for testing purposes, remove later

    public CommandBlimpCollision(GameWorld g) {
        super("Refuel");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputRef(i);
        ++i;    // Only needed for testing purposes, remove later
    }
}
