package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandNphStrategy extends Command {

    GameWorld gw;

    public CommandNphStrategy(GameWorld gw)
    {
        super("Strategies");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Command cAg = new Command("Aggressive");
        Command cCo = new Command("Competitive");
        Command c = Dialog.show("Strategies", "Select an NPH strategy", cAg, cCo);

        if (c == cAg) {
            for (int i = 0; i < gw.numOfNonPlayerHelicopters; i++)
                gw.npcs[i].setStrategy(new StrategyAggressive());
        }
        if (c == cCo) {
            for (int i = 0; i < gw.numOfNonPlayerHelicopters; i++)
                gw.npcs[i].setStrategy(new StrategyCompetitive());
        }


    }
}
