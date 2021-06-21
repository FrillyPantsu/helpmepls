package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import java.io.InputStream;

public class SoundBackground implements Runnable {
    private Media m;

    public SoundBackground (String fileName)
    {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);

            m = MediaManager.createMedia(is, "audio/wav");
        }
        catch (Exception e) { e.printStackTrace();}
        run();
    }

    public void pause() {
        m.pause();
    }

    public void play() {
        m.play();
    }

    public void run() {
        m.setVolume(50);
        m.setTime(0);
        m.play();
    }
}


