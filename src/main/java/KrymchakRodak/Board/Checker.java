package KrymchakRodak.Board;

import java.awt.Color;

/**
 *
 * @author Krymchak Veranika
 */
public class Checker
{
    private Color color=null;
    private int endTriangle;
    
    /*
    * Create Checker with given color
    */
    public Checker (Color color, int endTriangle)
    {
        this.color=color;
        this.endTriangle=endTriangle;
    }
    
     public int getEndTriangle ()
    {
        return endTriangle;
    }
    
    /*
    * @return color of Checker
    */
    public Color getColor ()
    {
        return color;
    }
}
