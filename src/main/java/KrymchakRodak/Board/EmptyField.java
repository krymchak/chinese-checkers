package KrymchakRodak.Board;

public class EmptyField extends Field
{
	public boolean IsNotEmpty() 
	{
		return false;
	}
	
	
	/*
	 * TODO add exception
	 */
	public void setChecker (Checker checker)
	{
	}
        
        public Checker getChecker()
        {
            return null;
        }
	
	public boolean isChecker ()
	{
            return false;
	}
}