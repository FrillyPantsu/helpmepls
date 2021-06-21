package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandHelp extends Command {

    public CommandHelp() {
        super("Help");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Command cOk = new Command("Okay");
        Command c = Dialog.show("Help", "Key Binds\nAccelerate: a\nBrake: b\nLeft turn: l\nRight turn: r\nSkyScraper Collision: s\nBlimp refuel: e\nBird Collision: g", cOk);
    }

}
