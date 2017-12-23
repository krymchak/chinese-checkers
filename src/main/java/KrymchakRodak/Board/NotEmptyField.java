package KrymchakRodak.Board;

/**
 *
 * @author Krymchak Veranika
 */
public class NotEmptyField extends Field
{
	private Checker checker = null;
        private Circle circle;
        private boolean active=false;
        private int triangle;
	
        @Override
	public boolean IsNotEmpty() 
	{
		return true;
	}
        
        public NotEmptyField()
        {
            
        }
        
        public NotEmptyField(int triangle)
        {
            this.triangle=triangle;
        }
        
        @Override
	public boolean isActive() 
	{
		return active;
	}
        
        @Override
	public void setActive(boolean active) 
	{
		this.active=active;
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
            if(checker==null)
                return false;
            else
                return true;
	}
        @Override
        public int getTriangle ()
        {
            return triangle;
        }
}
