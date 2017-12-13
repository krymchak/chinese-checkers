package KrymchakRodak.Board;

import java.util.ArrayList;

/**
 *
 * @author Krymchak Veranika
 */
public class Checker extends AbstractChecker
{
    ArrayList<Field> listOfNeighbors;
    String color=null;
    
    /*
    * Create Checker with given color and given list of neighbors
    */
    public Checker (String color, ArrayList<Field> listOfNeighbors)
    {
        this.color=color;
        this.listOfNeighbors=listOfNeighbors;
    }
    
    /*
    * @return color of Checker
    */
    public String getColor ()
    {
        return color;
    }
}
