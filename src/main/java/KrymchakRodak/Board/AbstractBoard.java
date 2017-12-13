package KrymchakRodak.Board;

public abstract class AbstractBoard 
{
	Field board[][];
        public abstract void  FillBoard (int numberOfPlayers);
        /*
        * Common constructor
        */
        public AbstractBoard(int numberOfPlayers)
        {
            FillBoard (numberOfPlayers);
        }
	
}

