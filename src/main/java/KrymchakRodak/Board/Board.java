package KrymchakRodak.Board;

public class Board extends AbstractBoard
{
	/*
	 * TODO add Factory Method for create Checker
	 */
	/*
	 * Fill first triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill second triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill third triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill fourth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill fifth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	/*
	 * Fill sixth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
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
					board[i][j].setChecker(new Checker(checker));
				}
			}
		}
	}
	
	
	/*
	 * TODO add exception for numberOfPlayers!=2,3,4,6
	 */
	public Board (int numberOfPlayers)
	{
		board=new Field[17][17];
                /*
                * Fill the table with empty fields
                */
		for (int i=0; i<17; i++)
		{
			for (int j=0; j<17; j++)
				board[i][j]=new EmptyField();
		}
                /*
                * Fill the table with not empty fields
                */
		for (int i=4; i<13; i++)
		{
			for (int j=4; j<13; j++)
				board[i][j]=new NotEmptyField();
		}
            /*
             * Jesli graczej jest 2, to wypelniamy pionami 1 i 4 trojkaty, pozostale wypelniamy pustymi polami
             */
            switch (numberOfPlayers) {
            /*
             * Jesli graczej jest 3, to wypelniamy pionami 2, 4, 6 trojkaty, pozostale wypelniamy pustymi polami
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
             * Jesli graczej jest 4, to wypelniamy pionami 2,3, 5, 6 trojkaty, pozostale wypelniamy pustymi polami
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
             * Jesli graczej jest 6, to wypelniamy pionami wszystkie trojkaty
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
		Board a = new Board(2);
		a.write();
	}
}
