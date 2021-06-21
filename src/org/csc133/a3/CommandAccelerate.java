package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandAccelerate extends Command {

    GameWorld g;

    public CommandAccelerate(GameWorld g) {
        super("Accelerate");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        g.inputAcc();
    }
}
