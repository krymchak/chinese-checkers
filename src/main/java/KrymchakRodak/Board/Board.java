package KrymchakRodak.Board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board extends AbstractBoard
{
    /*
 * TODO add exception for numberOfPlayers!=2,3,4,6
 */
    /*
    * Call the constructor from AbstractBoard
    * @ numberOfPlayers 
    */
    public Board(int numberOfPlayers) throws WrongNumberOfPlayers{
        super(numberOfPlayers);
    }
    
    @Override
   /*
    * Fill the array according to the number of players
     * @ numberOfPlayers 
    */
    protected void FillBoard (int numberOfPlayers)
    {
            board=new Field[17][17];
            switch (numberOfPlayers) {
                /*
                * If numberOfPlayers 2, tneh fill 1 i 4 triangle with checker, rest fill with NotEmpty fields
                */
                case 2:
                    fillWithEmpty();
                    fillCenter();
                    fillFirstTriangle(Color.ORANGE);
                    fillSecondTriangle(null);
                    fillThirdTriangle(null);
                    fillFourthTriangle(Color.RED);
                    fillFifthTriangle(null);
                    fillSixthTriangle(null);
                    break;
                /*
                 * If numberOfPlayers 3, tneh fill 2,4,6 triangle with checker, rest fill with NotEmpty fields
                 */
                case 3:
                    fillWithEmpty();
                    fillCenter();
                    fillFirstTriangle(null);
                    fillSecondTriangle(Color.ORANGE);
                    fillThirdTriangle(null);
                    fillFourthTriangle(Color.RED);
                    fillFifthTriangle(null);
                    fillSixthTriangle(Color.BLUE);
                    break;
                /*
                * If numberOfPlayers 4, tneh fill 2, 3, 5, 6 triangle with checker, rest fill with NotEmpty fields
                */
                case 4:
                    fillWithEmpty();
                    fillCenter();
                    fillFirstTriangle(null);
                    fillSecondTriangle(Color.ORANGE);
                    fillThirdTriangle(Color.RED);
                    fillFourthTriangle(null);
                    fillFifthTriangle(Color.BLUE);
                    fillSixthTriangle(Color.GREEN);
                    break;
                /*
                 * If numberOfPlayers 6, tneh fill all triangle with checker, rest fill with NotEmpty fields
                 */
                case 6:
                    fillWithEmpty();
                    fillCenter();
                    fillFirstTriangle(Color.ORANGE);
                    fillSecondTriangle(Color.RED);
                    fillThirdTriangle(Color.BLUE);
                    fillFourthTriangle(Color.GREEN);
                    fillFifthTriangle(Color.CYAN);
                    fillSixthTriangle(Color.PINK);
                    break;
            }
    }
        /*
        * Fill the array with empty fields
        */
        public void fillWithEmpty ()
        {
            for (int i=0; i<17; i++)
		{
			for (int j=0; j<17; j++)
				board[i][j]=new EmptyField();
		}
        }
        
        /*
        * Fill the center of array with not empty fields
        */
        public void fillCenter ()
        {
            for (int i=4; i<13; i++)
            {
                for (int j=4; j<13; j++)
                    board[i][j]=new NotEmptyField();
            }
        }
	/*
	 * Fill first triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFirstTriangle (Color color)
	{
		for (int i=0; i<4; i++)
		{
			for (int j=4; j<4+i+1; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
	 * Fill second triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillSecondTriangle (Color color)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=9+i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
	 * Fill third triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillThirdTriangle (Color color)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=13; j<13+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
	 * Fill fourth triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFourthTriangle (Color color)
	{
		for (int i=13; i<17; i++)
		{
			for (int j=i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
	 * Fill fifth triangle. If checker empty, fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFifthTriangle (Color color)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=4; j<4+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
	 * Fill sixth triangle. If checker empty, fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillSixthTriangle (Color color)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=i-4; j<4; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (color!=null)
				{
					board[i][j].setChecker(new Checker(color, createListOfNeighbors(i, j)));
				}
			}
		}
	}
	
	/*
        * write array
        */
	public String write ()
	{
            String result="";
		for (int i=0; i<17; i++)
		{
			for (int j=0; j<17; j++)
			{
				if (board[i][j].IsNotEmpty()==false)
				{
                                        result=result+" ";
				}
				else
				{
					if (board[i][j].isChecker()==false)
                                        {
                                                result=result+".";
                                        }
					else
					{
                                                if(board[i][j].getChecker().getColor()==Color.ORANGE)
                                                {
                                                        result=result+"O";
                                                }
                                                if(board[i][j].getChecker().getColor()==Color.RED)
                                                {
                                                        result=result+"R";
                                                }
                                                if(board[i][j].getChecker().getColor()==Color.BLUE)
                                                {
                                                        result=result+"B";
                                                }
                                                if(board[i][j].getChecker().getColor()==Color.GREEN)
                                                {
                                                        result=result+"G";
                                                }
                                                if(board[i][j].getChecker().getColor()==Color.CYAN)
                                                {
                                                        result=result+"C";
                                                }
                                                if(board[i][j].getChecker().getColor()==Color.PINK)
                                                {
                                                        result=result+"P";
                                                }
					}
				}
			}
                        result=result+"\n";
		}
                return result;
	}
    /*
    * Create list of neighbors for given field of board. Check all possibilities and add it in list
    * @param i, j - coordinates of field
    * @return list of neighbors
    */
    @Override
    public ArrayList<Field> createListOfNeighbors(int i, int j)
    {
        ArrayList<Field> ListOfNeighbors=new ArrayList<Field>();
        if (i>0 && board[i-1][j].IsNotEmpty() &&!board[i-1][j].isChecker())
        {
            ListOfNeighbors.add(board[i-1][j]);
        }
        else if (i>1 && board[i-2][j].IsNotEmpty() && !board[i-2][j].isChecker())
        {
            ListOfNeighbors.add(board[i-2][j]);
        }
        if (i>0 && j>0 && board[i-1][j-1].IsNotEmpty() && !board[i-1][j-1].isChecker())
        {
            ListOfNeighbors.add(board[i-1][j-1]);
        }
        else if (i>1 && j>2 && board[i-2][j-2].IsNotEmpty() && !board[i-2][j-2].isChecker())
        {
            ListOfNeighbors.add(board[i-2][j-2]);
        }
        if (j>0 && board[i][j-1].IsNotEmpty() && !board[i][j-1].isChecker())
        {
            ListOfNeighbors.add(board[i][j-1]);
        }
        else if (j>1 && board[i][j-2].IsNotEmpty() && !board[i][j-2].isChecker())
        {
            ListOfNeighbors.add(board[i][j-2]);
        }
        if (i<16 && board[i+1][j].IsNotEmpty() && !board[i+1][j].isChecker())
        {
            ListOfNeighbors.add(board[i+1][j]);
        }
        else if (i<15 && board[i+2][j].IsNotEmpty() && !board[i+2][j].isChecker())
        {
            ListOfNeighbors.add(board[i+2][j]);

        }
        if (i< 16 && j<16 && board[i+1][j+1].IsNotEmpty() && !board[i+1][j+1].isChecker())
        {
            ListOfNeighbors.add(board[i+1][j+1]);
        }
        else if (i<15 && j<15 && board[i+2][j+2].IsNotEmpty() &&!board[i+2][j+2].isChecker())
        {
            ListOfNeighbors.add(board[i+2][j+2]);

        }
        if (j<16 && board[i][j+1].IsNotEmpty() && !board[i][j+1].isChecker())
        {
            ListOfNeighbors.add(board[i][j+1]);
        }
        else if (j<15 &&  board[i][j+2].IsNotEmpty() && !board[i][j+2].isChecker())
        {
            ListOfNeighbors.add(board[i][j+2]);
        }
        return ListOfNeighbors;
    }
    
    
    /*
    * TODO add exception, if not checker and if not possible step
    */
    /*
    * If the first field contains a checker and in his list of neighbors is contained second field,
    * move checker in second field, create new list of neighbors for checker and delete checker from first field.
    * @param i1, j1 - coordinates of first field
    * @param i2, j3 - coordinates of second field
    */
    @Override
    public void  Step (int i1, int j1, int i2, int j2) 
    {
        if (board[i1][j1].isChecker())
        {
            if (board[i1][j1].getChecker().isPossibleStep(board[i2][j2]))
            {
                board[i2][j2].setChecker(board[i1][j1].getChecker());
                board[i2][j2].getChecker().changelistOfNeighbors(createListOfNeighbors(i2, j2));
                board[i1][j1].setChecker(null);
            }
            else
            {
                System.out.print("nie mozliwy ruch");
            }
        }
        else
        {
            System.out.print("to nie jest pion");
        }
    }
        
    @Override
       public int getSize()
        {
            return 17;
        }
        
    @Override
    public Field getField(int i, int j)
    {
        return board[i][j];
    }
    
    /*@Override
    public int MaxNumberNotEmptyField()
    {
        return 13;
    }*/
        
	public static void main(String args[]) 
	{
            try {
                Board a = new Board(6);
                a.Step(5, 1, 5, 0);
                //a.Step(4, 4, 4, 5);
                System.out.print(a.write());
            } 
            catch (WrongNumberOfPlayers ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
    