/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Board;

import java.awt.Button;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    boolean isFirstPressed=true;
    int aktiveI;
    int aktiveJ;
    Color color;
    public GraphicBoard(AbstractBoard board, Color color)
    {
        this.board=board;
        this.color=color;       
        MyMouseHandler handler = new MyMouseHandler();
	this.addMouseListener(handler);
        //Button button = new Button("End of move");
       //this.add(button);
        setBackground(Color.WHITE);
        setSize(40*board.getSize(),40*board.getSize());
        int center=40*board.getSize()/2;
        int radius=15;
        int sizeOfLine;
        int shift;
        int Y=100;
        for (int i=0; i<board.getSize(); i++)
        {
                sizeOfLine=0;
                for (int j=0; j<board.getSize(); j++)
                {
                    if (board.getField(i, j).IsNotEmpty())
                        sizeOfLine++;
                }
                shift=sizeOfLine;
                for (int j=0; j<board.getSize(); j++)
                {
                    if (board.getField(i, j).IsNotEmpty())
                    {
                        board.getField(i, j).setCircle(new Circle (center-radius*(shift-1), Y, radius*2, radius*2));
                        shift=shift-2;
                    }
                }
            Y=Y+30;
        }
    }
    
    	/** 
	* 
	* @param g Graphics
	*/
	private void doDrawing(Graphics g) 
	{	
		Graphics2D g2d = (Graphics2D) g;
                for (int i=0; i<board.getSize(); i++)
                {
                    for (int j=0; j<board.getSize(); j++)
                    {
                        if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).isActive())
                        {
                            g2d.setPaint(Color.BLACK);
                            g2d.fill(board.getField(i, j).getCircle()); 
                        }
                        else if (board.getField(i, j).IsNotEmpty())
                        {
                            if (!board.getField(i, j).isChecker())
                            {
                                g2d.setPaint(Color.GRAY);
                            }
                            else
                            {
                                g2d.setPaint(board.getField(i, j).getChecker().getColor());
                            }
                            g2d.fill(board.getField(i, j).getCircle()); 
                        }
                    }
                }
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
    
    private class MyMouseHandler extends MouseAdapter 
    {
        @Override
	public void mousePressed(MouseEvent e) 
        {
            if ((SwingUtilities.isLeftMouseButton(e)))
            {
                if (isFirstPressed==true)
                {
                    int x = e.getX();
                    int y = e.getY(); 
                    for (int i=0; i<board.getSize(); i++)
                    {
                        for (int j=0; j<board.getSize(); j++)
                        {
                            if (board.getField(i, j).isChecker())
                            {
                                if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).isActive())
                                {
                                     board.getField(i, j).setActive(false);
                                }
                                else if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).getCircle().isHit(x,y) && board.getField(i, j).getChecker().getColor()==color)
                                {
                                    board.getField(i, j).setActive(true);
                                    aktiveI=i;
                                    aktiveJ=j;
                                    isFirstPressed=false;
                                }
                            }
                        }
                    }
                }
                else
                {
                    int x = e.getX();
                    int y = e.getY(); 
                    for (int i=0; i<board.getSize(); i++)
                    {
                        for (int j=0; j<board.getSize(); j++)
                        {
                            if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).isActive())
                            {
                                 board.getField(i, j).setActive(false);
                            }
                            else if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).getCircle().isHit(x,y))
                            {
                                try {
                                    board.Step(aktiveI, aktiveJ, i, j);
                                    aktiveI=i;
                                    aktiveJ=j;
                                    board.getField(aktiveI, aktiveJ).setActive(true);
                                } catch (ImpossibleStep ex) {
                                    Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IsNotChecker ex) {
                                    Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    //isFirstPressed=true;
                }
                repaint();
            }
                    
        }
    }
    
    
    public static void main(String args[]) 
    {
        JFrame frame = new JFrame();
        try {
            GraphicBoard pole = new GraphicBoard(new CreatorBoard().createBoard(2), Color.ORANGE);
            frame.add(pole);
            frame.setSize(17*40,17*40);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(GraphicBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
