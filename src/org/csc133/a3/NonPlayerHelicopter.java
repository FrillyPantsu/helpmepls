package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerHelicopter extends Helicopter{

    GameWorld target;
    IStrategy strat;

    NonPlayerHelicopter(GameWorld target)
    {
        // Pass in the GameWorld so a target may be set
        this.target = target;

        // Default NPC characteristic settings
        this.setLocationX(100.0);
        this.setLocationY(100.0);
        setStrategy(new StrategyAggressive());
        // Setting the speed
        accelerate(true);
        accelerate(true);
        accelerate(true);
    }

    public void setStrategy(IStrategy strat) {
        this.strat = strat;
    }

    public void invokeStrategy()
    {
        strat.executeStrategy(target, this);
    }

    String getStringOfCurrentStrategy(){
        if (strat instanceof StrategyAggressive)
            return "Aggressive Strategy";
        if (strat instanceof StrategyCompetitive)
            return "Competitive Strategy";
        else
            return " ";
    }

    void printInfo()
    {
        System.out.println("Helicopter: " +
                "loc=" + Math.round(getLocationX()*10.0)/10.0 + ',' + Math.round(getLocationY()*10.0)/10.0 + ' ' +
                "color=[" + getColorR() + ','  + getColorG() + ',' + getColorB() + ']' + ' ' +
                "size=" + getSize() + ' ' +
                "heading=" + getHeading() + ' ' + "speed=" + getSpeed() + ' '
                + "maxSpeed=" + getMaximumSpeed() + ' ' +
                "fuelLevel="  + getFuelLevel() + ' ' +
                "stickAngle=" + getStickAngle() + ' ' +
                "damageLevel=" + getDamage() + ' ' +
                "maxDamage=" + getMaxDam() + ' ' +
                "tankCapacity=" + getTankCapacity() + ' ' +
                "fuelConsumptionRate=" + getFuelConsumptionRate() + ' ' +
                "currentStrategy=" + getStringOfCurrentStrategy());
    }

    // The draw method is overrided here to ensure that the NPH have infinite fuel and that NPH are a different color
    @Override
    public void draw(Graphics g, Point containerOrigin)
    {
        setFuelLevel(1000);

        int x = containerOrigin.getX() + (int)getLocationX() + getSize()/8;
        int y = containerOrigin.getY() + (int)getLocationY() + getSize()/8;

        g.setColor(ColorUtil.GRAY);
        g.fillArc(containerOrigin.getX() + (int)getLocationX() - getSize()/2, containerOrigin.getY() + (int)getLocationY() - getSize()/2, getSize(), getSize(), 0, 360);

        g.setColor(ColorUtil.MAGENTA);
        g.drawLine(x, y, x + (int)(Math.cos(Math.toRadians(90-getHeading())) * getSize()), y + (int)(Math.sin(Math.toRadians(90-getHeading())) * getSize()));
    }
}
