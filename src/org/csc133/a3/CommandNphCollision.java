package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandNphCollision extends Command {

    GameWorld g;

    public CommandNphCollision(GameWorld g) {
        super("Collision");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputColl();
    }
}
