package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;


public class CommandExit extends Command{

    public CommandExit() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Command cOk = new Command("Yes");
        Command cCancel = new Command("Cancel");
        Command c = Dialog.show("Exit", "Are you sure you want to quit?", cOk, cCancel);

        if (c == cOk)
            System.exit(0);
        else
            return;

    }

}
