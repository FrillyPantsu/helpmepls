package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandLeftTurn extends Command {
    GameWorld g;

    public CommandLeftTurn(GameWorld g) {
        super("Left Turn");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputStickL();
    }
}
