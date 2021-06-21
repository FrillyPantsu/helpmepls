package org.csc133.a3;
import com.codename1.util.MathUtil;

public class StrategyCompetitive implements IStrategy{

    // Strategy for pathing towards skyscrapers
    @Override
    public void executeStrategy(GameWorld target, NonPlayerHelicopter nph) {

        double targetY = 0;
        double targetX = 0;

        // Get location of the NPH
        double nphX = nph.getLocationX();
        double nphY = nph.getLocationY();

        // Get the location of the target (the skyscrapers, in this case)
        if (nph.getLastSkyScraperReached() < target.skies.length) {                 // if-else patch for out of bounds exception
            targetX = target.skies[nph.getLastSkyScraperReached()].getLocationX();
            targetY = target.skies[nph.getLastSkyScraperReached()].getLocationY();
        }
        else {
            targetX = target.skies[target.skies.length - 1].getLocationX();
            targetY = target.skies[target.skies.length - 1].getLocationY();
        }

        double distX = targetX - nphX;
        double distY = targetY - nphY;

        // Calculations to achieve the desired heading in degrees
        double betaAngle = 90 - Math.toDegrees(MathUtil.atan2(distY, distX));

        // Set the heading towards the target
        nph.setHeading((int) betaAngle);
    }
}
