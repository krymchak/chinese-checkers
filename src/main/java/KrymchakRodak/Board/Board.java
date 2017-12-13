package KrymchakRodak.Board;

public class Board extends AbstractBoard
{
    /*
 * TODO add exception for numberOfPlayers!=2,3,4,6
 */
    /*
    * Call the constructor from AbstractBoard
    * @ numberOfPlayers 
    */
    public Board(int numberOfPlayers) {
        super(numberOfPlayers);
    }
    
    @Override
    
   /*
    * Fill the array according to the number of players
     * @ numberOfPlayers 
    */
    public void FillBoard (int numberOfPlayers)
    {
        board=new Field[17][17];
                /*
                * Fill the array with empty fields
                */
		for (int i=0; i<17; i++)
		{
			for (int j=0; j<17; j++)
				board[i][j]=new EmptyField();
		}
                /*
                * Fill the center of array with not empty fields
                */
		for (int i=4; i<13; i++)
		{
			for (int j=4; j<13; j++)
				board[i][j]=new NotEmptyField();
		}
            /*
             * If numberOfPlayers 2, tneh fill 1 i 4 triangle with checker, rest fill with NotEmpty fields
             */
            switch (numberOfPlayers) {
            /*
             * If numberOfPlayers 3, tneh fill 2,4,6 triangle with checker, rest fill with NotEmpty fields
             */
                case 2:
                    fillFirstTriangle("O");
                    fillSecondTriangle(null);
                    fillThirdTriangle(null);
                    fillFourthTriangle("R");
                    fillFifthTriangle(null);
                    fillSixthTriangle(null);
                    break;
            /*
             * If numberOfPlayers 4, tneh fill 2, 3, 5, 6 triangle with checker, rest fill with NotEmpty fields
             */
                case 3:
                    fillFirstTriangle(null);
                    fillSecondTriangle("O");
                    fillThirdTriangle(null);
                    fillFourthTriangle("R");
                    fillFifthTriangle(null);
                    fillSixthTriangle("B");
                    break;
            /*
             * If numberOfPlayers 6, tneh fill all triangle with checker, rest fill with NotEmpty fields
             */
                case 4:
                    fillFirstTriangle(null);
                    fillSecondTriangle("O");
                    fillThirdTriangle("R");
                    fillFourthTriangle(null);
                    fillFifthTriangle("B");
                    fillSixthTriangle("G");
                    break;
                case 6:
                    fillFirstTriangle("O");
                    fillSecondTriangle("R");
                    fillThirdTriangle("B");
                    fillFourthTriangle("G");
                    fillFifthTriangle("Y");
                    fillSixthTriangle("P");
                    break;
                default:
                    System.out.print("Niepoprawna liczba graczy");
                    break;
            }
    }
	/*
	 * Fill first triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFirstTriangle (String checker)
	{
		for (int i=0; i<4; i++)
		{
			for (int j=4; j<4+i+1; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill second triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillSecondTriangle (String checker)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=9+i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill third triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillThirdTriangle (String checker)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=13; j<13+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill fourth triangle. If checker empty,  fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFourthTriangle (String checker)
	{
		for (int i=13; i<17; i++)
		{
			for (int j=i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill fifth triangle. If checker empty, fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillFifthTriangle (String checker)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=4; j<4+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill sixth triangle. If checker empty, fill fiels without checkers, else add checkers
         * @ checker which color have checker
	 */
	public void fillSixthTriangle (String checker)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=i-4; j<4; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (checker!=null)
				{
					board[i][j].setChecker(new CreatorChecker().createChecker(checker));
				}
			}
		}
	}
	
	/*
        * write array
        */
	public void write ()
	{
		for (int i=0; i<17; i++)
		{
			for (int j=0; j<17; j++)
			{
				if (board[i][j].IsNotEmpty()==false)
				{
					System.out.print(" ");
				}
				else
				{
					if (board[i][j].IsChecker()==false)
						System.out.print(".");
					else
					{
						System.out.print(board[i][j].getChecker().getColor());
					}
				}
			}
			System.out.println("");
		}
	}
	public static void main(String args[]) 
	{
		Board a = new Board(4);
		a.write();
	}
}
