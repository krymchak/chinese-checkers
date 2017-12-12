package KrymchakRodak.Board;

public class Checker extends AbstractChecker
{
    String color=null;
    
    public Checker (String color)
    {
        this.color=color;
    }
    
    public String getColor ()
    {
        return color;
    }
}
