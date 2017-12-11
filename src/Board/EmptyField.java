package Board;

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
	
	public boolean IsChecker ()
	{
			return false;
	}
}