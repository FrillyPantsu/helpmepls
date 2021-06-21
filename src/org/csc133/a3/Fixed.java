package org.csc133.a3;
// Fixed objects are fixed in place. They can not change their location after they are initially set.
// Fixed objects currently include skyscrapers and refuelingblimps.
// TO DO: Add limited accessors to positions to positions for child classes so that their positions can't be accessed and changed
abstract public class Fixed extends GameObject {

    public Fixed(double x, double y, int objIdentifier)
    {
        super(x, y, objIdentifier);
    }

    @Override
    public void setLocationX(double x){}

    @Override
    public void setLocationY(double y){}
	
}
