package KrymchakRodak.Board;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Krymchak Veranika
 */
public abstract class AbstractBoard 
{
	Field board[][];
        protected abstract void  FillBoard (int numberOfPlayers);  
        public abstract void  Step (int i1, int j1, int i2, int j2) throws ImpossibleStep, IsNotChecker;
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

        public AbstractBoard() {}
	public abstract ArrayList<Neighbors> createListOfNeighbors(int i, int j);
        public abstract int getSize();
        public abstract Field getField(int i, int j);
        public abstract void endMove();
        public abstract boolean win(Color color);
        //public abstract int MaxNumberNotEmptyField();
}

