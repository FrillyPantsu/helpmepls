package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandBirdCollision extends Command {

    GameWorld g;

    public CommandBirdCollision(GameWorld g) {
        super("Bird gore");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputBirdCol();
    }
}
