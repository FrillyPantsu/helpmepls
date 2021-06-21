package org.csc133.a3;
public class PlayerHelicopter extends Helicopter{

    // Use the singleton design methodology
    private static PlayerHelicopter player = null;

    private PlayerHelicopter()
    {

    }

    public static PlayerHelicopter getInstance()
    {
        if (player == null)
            return new PlayerHelicopter();
        else
            return player;
    }
}
