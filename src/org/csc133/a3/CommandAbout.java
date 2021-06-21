package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {

    public CommandAbout() {
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Command cOk = new Command("Okay");
        Command c = Dialog.show("About", "Cody Sheets\nv0.2.1\nCSC 133", cOk);
        if (c == cOk)
            return;

    }
}
