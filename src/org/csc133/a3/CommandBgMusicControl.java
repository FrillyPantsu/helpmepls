package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandBgMusicControl extends Command {

    SoundBackground bg;

    public CommandBgMusicControl(SoundBackground bg)
    {
        super("Background Music");
        this.bg = bg;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Command cPa = new Command("Pause");
        Command cPl = new Command("Play");
        Command c = Dialog.show(" ", "Audio Control", cPa, cPl);

        if (c == cPa) {
            bg.pause();
        }
        if (c == cPl) {
            bg.play();
        }


    }
}
