package KrymchakRodak.Board;

public class Checker extends AbstractChecker
{
    String color=null;
    
    /*
    * Create Checker with given color
    */
    public Checker (String color)
    {
        this.color=color;
    }
    
    /*
    * @return color of Checker
    */
    public String getColor ()
    {
        return color;
    }
}
