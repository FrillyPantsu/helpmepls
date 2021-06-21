package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandRightTurn extends Command {

    GameWorld g;

    public CommandRightTurn(GameWorld g) {
        super("Accelerate");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputStickR();
    }
}
