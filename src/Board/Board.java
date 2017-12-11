package Board;

public class Board extends AbstractBoard
{
	/*
	 * TODO add Factory Method for create Checker
	 */
	/*
	 * Fill first triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillFirstTriangle (boolean IsChecker)
	{
		for (int i=0; i<4; i++)
		{
			for (int j=4; j<4+i+1; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
				}
			}
		}
	}
	
	/*
	 * Fill second triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillSecondTriangle (boolean IsChecker)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=9+i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
				}
			}
		}
	}
	
	/*
	 * Fill third triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillThirdTriangle (boolean IsChecker)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=13; j<13+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
				}
			}
		}
	}
	
	/*
	 * Fill fourth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillFourthTriangle (boolean IsChecker)
	{
		for (int i=13; i<17; i++)
		{
			for (int j=i-4; j<13; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
				}
			}
		}
	}
	
	/*
	 * Fill fifth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillFifthTriangle (boolean IsChecker)
	{
		for (int i=9; i<13; i++)
		{
			for (int j=4; j<4+i+1-9; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
				}
			}
		}
	}
	
	/*
	 * Fill sixth triangle. If IsChecker = true, fill fiels without checkers, else add checkers
         * @ IsChecker
	 */
	public void fillSixthTriangle (boolean IsChecker)
	{
		for (int i=4; i<8; i++)
		{
			for (int j=i-4; j<4; j++)
			{
                            	board[i][j]=new NotEmptyField();
				if (IsChecker==true)
				{
					board[i][j].setChecker(new Checker());
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
		if (numberOfPlayers==2)
		{
			fillFirstTriangle(true);
			fillSecondTriangle(false);
			fillThirdTriangle(false);
			fillFourthTriangle(true);
			fillFifthTriangle(false);
			fillSixthTriangle(false);
		}
		/*
		 * Jesli graczej jest 3, to wypelniamy pionami 2, 4, 6 trojkaty, pozostale wypelniamy pustymi polami
		 */
		else if (numberOfPlayers==3)
		{
			fillFirstTriangle(false);
			fillSecondTriangle(true);
			fillThirdTriangle(false);
			fillFourthTriangle(true);
			fillFifthTriangle(false);
			fillSixthTriangle(true);
		}
		/*
		 * Jesli graczej jest 4, to wypelniamy pionami 2,3, 5, 6 trojkaty, pozostale wypelniamy pustymi polami
		 */
		else if (numberOfPlayers==4)
		{
			fillFirstTriangle(false);
			fillSecondTriangle(true);
			fillThirdTriangle(true);
			fillFourthTriangle(false);
			fillFifthTriangle(true);
			fillSixthTriangle(true);
		}
		/*
		 * Jesli graczej jest 6, to wypelniamy pionami wszystkie trojkaty
		 */
		else if (numberOfPlayers==6)
		{
			fillFirstTriangle(true);
			fillSecondTriangle(true);
			fillThirdTriangle(true);
			fillFourthTriangle(true);
			fillFifthTriangle(true);
			fillSixthTriangle(true);
		}
		else
		{
			System.out.print("Niepoprawna liczba graczy");
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
						System.out.print("*");
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
