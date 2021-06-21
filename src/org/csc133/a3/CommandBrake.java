package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandBrake extends Command {

    GameWorld g;

    public CommandBrake(GameWorld g) {
        super("Brake");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputBrake();
    }

}
