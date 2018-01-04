/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.AbstractBoard;
import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.GraphicBoard;
import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author krmwe
 */
public class GraphicBoardTest {

    /**
     * Test of getMoves method, of class GraphicBoard.
     */
    @Test
    public void testGetMoves() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.GREEN);
        assertNotNull(instance.getMoves());
    }

    /**
     * Test of getBoard method, of class GraphicBoard.
     */
    @Test
    public void testGetBoard() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.CYAN);
        assertNotNull(instance.getBoard());
    }

    /**
     * Test of newTurn method, of class GraphicBoard.
     */
    @Test
    public void testNewTurn() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.BLUE);
        instance.newTurn();
    }

    /**
     * Test of moveChecker method, of class GraphicBoard.
     */
    @Test
    public void testMoveChecker() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.PINK);
        ArrayList<MoveInfo> moves = new ArrayList<MoveInfo>();
        instance.moveChecker(moves);
    }


    /**
     * Test of setActiveTurn method, of class GraphicBoard.
     */
    @Test
    public void testSetActiveTurn() throws WrongNumberOfPlayers {
        boolean bool = false;
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.ORANGE);
        instance.setActiveTurn(bool);
    }

    /**
     * Test of isActiveTurn method, of class GraphicBoard.
     */
    @Test
    public void testIsActiveTurn() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.RED);
        assertEquals(false, instance.isActiveTurn());
    }
    
    @Test
    public void testCancelMove() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.GREEN);
        instance.cancelMove();
    }
    
    @Test
    public void testUnmarkActive() throws WrongNumberOfPlayers {
        GraphicBoard instance = new GraphicBoard(new Board(2), Color.GREEN);
        instance.unmarkActive();
    }
    
}
