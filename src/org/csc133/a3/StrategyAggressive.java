package org.csc133.a3;
import com.codename1.util.MathUtil;

public class StrategyAggressive implements IStrategy {

    // Strategy for attacking the player
    @Override
    public void executeStrategy(GameWorld target, NonPlayerHelicopter nph)
    {
        // Get location of the NPH
        double nphX = nph.getLocationX();
        double nphY = nph.getLocationY();

        // Get the location of the target (the player, in this case)
        double targetX = target.player.getLocationX();
        double targetY = target.player.getLocationY();

        double distX =  targetX - nphX;
        double distY = targetY - nphY;

        // Calculations to achieve the desired heading in degrees
        double betaAngle = 90 - Math.toDegrees(MathUtil.atan2(distY, distX));

        nph.setHeading((int)betaAngle);
    }
}
