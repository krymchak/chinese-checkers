package KrymchakRodak.Board;

/**
 *
 * @author Krymchak Veranika
 */
public class NotEmptyField extends Field
{
	Checker checker = null;
        Circle circle;
	
        @Override
	public boolean IsNotEmpty() 
	{
		return true;
	}
	
        @Override
	public void setChecker (Checker checker)
	{
		this.checker=checker;
	}
        
        @Override
        public Checker getChecker ()
        {
            return checker;
        }
        
        @Override
        public Circle getCircle ()
        {
            return circle;
        }
        
        @Override
        public void setCircle (Circle circle)
        {
            this.circle=circle;
        }
                
        @Override
	public boolean isChecker ()
	{
            return checker!=null;
	}
}
