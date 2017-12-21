package KrymchakRodak.Board;

public abstract class Field 
{
	public abstract boolean IsNotEmpty();
	public abstract void setChecker(Checker checker);
	public abstract boolean isChecker ();
        public abstract Checker getChecker();
        public abstract Circle getCircle ();
        public abstract void setCircle (Circle circle);
}
