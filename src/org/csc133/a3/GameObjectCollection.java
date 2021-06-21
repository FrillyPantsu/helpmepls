package org.csc133.a3;
import java.util.ArrayList;

public class GameObjectCollection extends ArrayList<GameObject>{

    GameObjectCollection()
    {

    }

    /*
    *   Methods to access components of the GameObjectCollection
    */

    public ArrayList<GameObject> getObjects()
    {
        return this;
    }

    public ArrayList<Bird> getBirds()
    {
        ArrayList<Bird> objArray = new ArrayList<Bird>();
        for (GameObject i : this)
        {
            if (i instanceof Bird)
                objArray.add((Bird)i);
        }
        return objArray;
    }

    public ArrayList<RefuelingBlimp> getRefuelingBlimps()
    {
        ArrayList<RefuelingBlimp> objArray = new ArrayList<RefuelingBlimp>();
        for (GameObject i : this)
        {
            if (i instanceof RefuelingBlimp)
                objArray.add((RefuelingBlimp) i);
        }
        return objArray;
    }

    public ArrayList<SkyScraper> getSkyScrapers()
    {
        ArrayList<SkyScraper> objArray = new ArrayList<SkyScraper>();
        for (GameObject i : this)
        {
            if (i instanceof SkyScraper)
                objArray.add((SkyScraper)i);
        }
        return objArray;
    }

    public ArrayList<Helicopter> getHelicopters()
    {
        ArrayList<Helicopter> objArray = new ArrayList<Helicopter>();
        for (GameObject i : this)
        {
            if (i instanceof Helicopter)
                objArray.add((Helicopter)i);
        }
        return objArray;
    }

    public ArrayList<NonPlayerHelicopter> getNonPlayerHelicopters()
    {
        ArrayList<NonPlayerHelicopter> objArray = new ArrayList<NonPlayerHelicopter>();
        for (GameObject i : this)
        {
            if (i instanceof NonPlayerHelicopter)
                objArray.add((NonPlayerHelicopter)i);
        }
        return objArray;
    }

    public SkyScraper getNthSkyScraper(int e)
    {
        for (GameObject i : this)
        {
            if (i instanceof SkyScraper && ((SkyScraper) i).getSeq() == e)
                return (SkyScraper) i;
        }
        return null;
    }

    // The following methods are used to assist with indexing objects in the array list
    public int getAmountOfBirds()
    {
        int a = 0;
        for (GameObject i : this)
        {
            if (i instanceof Bird)
                ++a;
        }

        return a;
    }

    public int getAmountOfHelicopters()
    {
        int a = 0;
        for (GameObject i : this)
        {
            if (i instanceof Helicopter)
                ++a;
        }

        return a;
    }

    public int getAmountOfRefuelingBlimps()
    {
        int a = 0;
        for (GameObject i : this)
        {
            if (i instanceof RefuelingBlimp)
                ++a;
        }

        return a;
    }

    public int getAmountOfSkyScrapers()
    {
        int a = 0;
        for (GameObject i : this)
        {
            if (i instanceof SkyScraper)
                ++a;
        }

        return a;
    }
}
