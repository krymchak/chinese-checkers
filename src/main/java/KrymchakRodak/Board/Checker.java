package KrymchakRodak.Board;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Krymchak Veranika
 */
public class Checker
{
    Color color=null;
    
    /*
    * Create Checker with given color
    */
    public Checker (Color color)
    {
        this.color=color;
    }
    
    
    /*
    * @return color of Checker
    */
    public Color getColor ()
    {
        return color;
    }
}
