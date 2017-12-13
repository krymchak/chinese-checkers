package KrymchakRodak.Board;

/**
 *
 * @author Krymchak Veranika
 */
public abstract class AbstractBoard 
{
	Field board[][];
        protected abstract void  FillBoard (int numberOfPlayers);
        /*
        * Common constructor
        */
        public AbstractBoard(int numberOfPlayers) throws WrongNumberOfPlayers
        {
            if (numberOfPlayers==2 || numberOfPlayers==3 || numberOfPlayers==4 || numberOfPlayers==6)
            {
                FillBoard (numberOfPlayers);
            }
            else
            {
                throw new WrongNumberOfPlayers();
            }
        }
	
}

