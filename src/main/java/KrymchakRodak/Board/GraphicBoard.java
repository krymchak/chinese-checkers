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
import java.util.ArrayList;
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
    ArrayList<MoveInfo> moves = null;
    private boolean activeTurn = false;
    boolean isFirstPressed=true;
    int aktiveI;
    int aktiveJ;
    Color color;
    public Button endMoveButton, cancelMoveButton;
    public GraphicBoard(AbstractBoard board, Color color)
    {
        String colorOfPlayer="";
        this.board=board;
        this.color=color;
        this.moves = new ArrayList<>();

        MyMouseHandler handler = new MyMouseHandler();
	this.addMouseListener(handler);
        endMoveButton = new Button("End of move");
        cancelMoveButton = new Button("Reset of move");
        endMoveButton.setEnabled(false);
        cancelMoveButton.setEnabled(false);
        this.add(endMoveButton);
        this.add(cancelMoveButton);
        if (color==Color.RED)
            colorOfPlayer="Red";
        if (color==Color.ORANGE)
            colorOfPlayer="Orange";
        if (color==Color.PINK)
            colorOfPlayer="Pink";
        if (color==Color.BLUE)
            colorOfPlayer="Blue";
        if (color==Color.GREEN)
            colorOfPlayer="Green";
        if (color==Color.CYAN)
            colorOfPlayer="Cyan";
        this.add (new JLabel(colorOfPlayer));
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
	* Wywoluje metode doDrawing
	* @param g Graphics
	*/
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        doDrawing(g);
    }
    
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (activeTurn) {
                if ((SwingUtilities.isLeftMouseButton(e))) {
                    if (isFirstPressed) {
                        int x = e.getX();
                        int y = e.getY();
                        for (int i = 0; i < board.getSize(); i++) {
                            for (int j = 0; j < board.getSize(); j++) {
                                if (board.getField(i, j).isChecker()) {
                                    if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).isActive()) {
                                        board.getField(i, j).setActive(false);
                                    } else if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).getCircle().isHit(x, y) && board.getField(i, j).getChecker().getColor() == color) {
                                        board.getField(i, j).setActive(true);
                                        aktiveI = i;
                                        aktiveJ = j;
                                        System.out.println(i);
                                        System.out.println(j);
                                        isFirstPressed = false;
                                    }
                                }
                            }
                        }
                    } else {
                        int x = e.getX();
                        int y = e.getY();
                        for (int i = 0; i < board.getSize(); i++) {
                            for (int j = 0; j < board.getSize(); j++) {
                                if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).isActive()) {
                                    board.getField(i, j).setActive(false);
                                } else if (board.getField(i, j).IsNotEmpty() && board.getField(i, j).getCircle().isHit(x, y)) {
                                    try {
                                        board.Step(aktiveI, aktiveJ, i, j);
                                        moves.add(new MoveInfo(aktiveI, aktiveJ, i, j));
                                        aktiveI = i;
                                        aktiveJ = j;
                                        board.getField(aktiveI, aktiveJ).setActive(true);
                                    } catch (ImpossibleStep ex) {
                                        board.getField(aktiveI, aktiveJ).setActive(true);
                                    } catch (IsNotChecker ex) {
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
    }

    public ArrayList<MoveInfo> getMoves() {
        return moves;
    }

    public AbstractBoard getBoard() {
        return board;
    }

    public void newTurn() {
        this.moves.clear();
        this.isFirstPressed = true;
        board.endMove();
        setActiveTurn(true);
    }

    public void cancelMove() {
        int stepNumber = this.moves.size() - 1;

        if (stepNumber>= 0 && board.getField(moves.get(stepNumber).getNewI(), moves.get(stepNumber).getNewJ()).isActive()) {
            board.getField(moves.get(stepNumber).getNewI(), moves.get(stepNumber).getNewJ()).setActive(false);
        }

        for(; stepNumber >= 0; stepNumber--) {
            board.endMove();
            MoveInfo move = this.moves.get(stepNumber);
            try {
                board.Step(move.getNewI(), move.getNewJ(), move.getOldI(), move.getOldJ());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.moves.clear();
        board.endMove();
        isFirstPressed=true;
        repaint();
    }

    public void moveChecker(ArrayList<MoveInfo> moves) {
        board.endMove();

        for(MoveInfo move : moves) {
            try {
                board.Step(move.getOldI(), move.getOldJ(), move.getNewI(), move.getNewJ());
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unmarkActive() {
        int lastStep = moves.size() - 1;

        if (lastStep>=0)
        {
            MoveInfo step = moves.get(lastStep);
            if (board.getField(step.getNewI(), step.getNewJ()).isActive()) {
                board.getField(step.getNewI(), step.getNewJ()).setActive(false);
                repaint();
            }
        }
    }

    public void setActiveTurn(boolean bool) {
        this.activeTurn = bool;
    }

    public boolean isActiveTurn() {
        return this.activeTurn;
    }

}
