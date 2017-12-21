package KrymchakRodak.Board;

public class EmptyField extends Field
{
        @Override
	public boolean IsNotEmpty() 
	{
		return false;
	}
	
	
	/*
	 * TODO add exception
	 */
        @Override
	public void setChecker (Checker checker)
	{
	}
        
        @Override
        public Checker getChecker()
        {
            return null;
        }
	
        @Override
	public boolean isChecker ()
	{
            return false;
	}
        
        @Override
        public Circle getCircle ()
        {
            return null;
        }
        
        @Override
        public void setCircle (Circle circle)
        {
        }
}