/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.CreatorBoard;
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
public class CreatorBoardTest {

    /**
     * Test of createBoard method, of class CreatorBoard.
     */
    @Test
    public void testCreateBoard() throws Exception {
        CreatorBoard instance = new CreatorBoard();
        Board result = instance.createBoard(4);
        assertNotNull(result);
    }
    
}
