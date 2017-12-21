package KrymchakRodak.Board;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Krymchak Veranika
 */
public class Checker
{
    ArrayList<Field> listOfNeighbors;
    Color color=null;
    
    /*
    * Create Checker with given color and given list of neighbors
    */
    public Checker (Color color, ArrayList<Field> listOfNeighbors)
    {
        this.color=color;
        this.listOfNeighbors=listOfNeighbors;
    }
    
    
    /*
    * Check if the move in given field is possible 
    */
    public boolean isPossibleStep (Field targetField)
    {
       for (int i=0; i<listOfNeighbors.size(); i++)
       {
           if (listOfNeighbors.get(i)==targetField)
                return true;
       }
       return false;
    }
    
    /*
    * Change list of neighbors
    */
    public void changelistOfNeighbors (ArrayList<Field> listOfNeighbors)
    {
        this.listOfNeighbors=listOfNeighbors;
    }
    
    /*
    * @return color of Checker
    */
    public Color getColor ()
    {
        return color;
    }
}
