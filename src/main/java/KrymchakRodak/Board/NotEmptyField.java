package KrymchakRodak.Board;

public class NotEmptyField extends Field
{
	Checker checker = null;
	
	public boolean IsNotEmpty() 
	{
		return true;
	}
	
	public void setChecker (Checker checker)
	{
		this.checker=checker;
	}
	public boolean IsChecker ()
	{
		if (checker!=null)
			return true;
		else
			return false;
	}
}
