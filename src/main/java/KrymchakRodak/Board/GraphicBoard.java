/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Board;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
/**
 *
 * @author krmwe
 */
public class GraphicBoard extends JPanel
{
    AbstractBoard board;
    public GraphicBoard(AbstractBoard board)
    {
        setBackground(Color.WHITE);
        this.board=board;
        setSize(40*board.getSize(),40*board.getSize());
        int center=40*board.getSize()/2;
        int radius=30;
        for (int i=0; i<board.getSize(); i++)
        {
                    
        }
		
    }
    
    	/** 
	* Rysuje pole, wilka i zajecy na nim
	* @param g Graphics
	*/
	private void doDrawing(Graphics g) 
	{	
		Graphics2D g2d = (Graphics2D) g; 
                g2d.setPaint(Color.BLUE);
		g2d.fill(new Circle(325,10,30,30)); 
                g2d.setPaint(Color.BLUE);
		g2d.fill(new Circle(340,35,30,30));
                g2d.setPaint(Color.BLUE);
		g2d.fill(new Circle(310,35,30,30));
		/*zrect[wolf.getX()][wolf.getY()].changeColor (Color.BLACK);
		for (int i=0; i<counterHares; i++)
		{
			if (zrect[hares.get(i).getX()][hares.get(i).getY()]!=zrect[wolf.getX()][wolf.getY()])
				zrect[hares.get(i).getX()][hares.get(i).getY()].changeColor (Color.WHITE);
			else
			{
				hares.remove(i);
				counterHares--;
				i--;
				wolf.CzyOdpoczywa=5;
			}
		} 
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<m; j++)
			{
				g2d.setPaint(zrect[i][j].getColor());
				g2d.fill(zrect[i][j]);  
			}
		}*/
    }

	/** 
	* Wywoluje metode {@link Pole#doDrawing}
	* @param g Graphics
	*/
    @Override
    public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        
        doDrawing(g);        
    }
    
    
    public static void main(String args[]) 
    {
        try {
           GraphicBoard pole = new GraphicBoard(new CreatorBoard().createBoard(2));
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*JFrame frame = new JFrame();
        try {
            GraphicBoard pole = new GraphicBoard(new CreatorBoard().createBoard(2));
            frame.add(pole);
            frame.setSize(17*40,17*40);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
        }*/
}
}
