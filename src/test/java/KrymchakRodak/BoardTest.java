/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author krmwe
 */
public class BoardTest {

    String boardForTwo=
    "    O            \n" +
    "    OO           \n" +
    "    OOO          \n" +
    "    OOOO         \n" +
    ".............    \n" +
    " ............    \n" +
    "  ...........    \n" +
    "   ..........    \n" +
    "    .........    \n" +
    "    ..........   \n" +
    "    ...........  \n" +
    "    ............ \n" +
    "    .............\n" +
    "         RRRR    \n" +
    "          RRR    \n" +
    "           RR    \n" +
    "            R    \n";
    String boardForThree=
    "    .            \n" +
    "    ..           \n" +
    "    ...          \n" +
    "    ....         \n" +
    "BBBB.....OOOO    \n" +
    " BBB......OOO    \n" +
    "  BB.......OO    \n" +
    "   B........O    \n" +
    "    .........    \n" +
    "    ..........   \n" +
    "    ...........  \n" +
    "    ............ \n" +
    "    .............\n" +
    "         RRRR    \n" +
    "          RRR    \n" +
    "           RR    \n" +
    "            R    \n";
    
    String boardForFour=
    "    .            \n" +
    "    ..           \n" +
    "    ...          \n" +
    "    ....         \n" +
    "GGGG.....OOOO    \n" +
    " GGG......OOO    \n" +
    "  GG.......OO    \n" +
    "   G........O    \n" +
    "    .........    \n" +
    "    B........R   \n" +
    "    BB.......RR  \n" +
    "    BBB......RRR \n" +
    "    BBBB.....RRRR\n" +
    "         ....    \n" +
    "          ...    \n" +
    "           ..    \n" +
    "            .    \n";
    
    
    String boardForSix=
    "    O            \n" +
    "    OO           \n" +
    "    OOO          \n" +
    "    OOOO         \n" +
    "PPPP.....RRRR    \n" +
    " PPP......RRR    \n" +
    "  PP.......RR    \n" +
    "   P........R    \n" +
    "    .........    \n" +
    "    C........B   \n" +
    "    CC.......BB  \n" +
    "    CCC......BBB \n" +
    "    CCCC.....BBBB\n" +
    "         GGGG    \n" +
    "          GGG    \n" +
    "           GG    \n" +
    "            G    \n";  
    
    /**
    * Test of create board for two players, of class Board.
    */
    @Test
    public void testArrayForTwo() throws WrongNumberOfPlayers {
        Board board = new Board(2);
        assertEquals(boardForTwo, board.write());
    }
    
    /**
    * Test of create board for three players, of class Board.
    */
    @Test
    public void testArrayForThree() throws WrongNumberOfPlayers {
        Board board = new Board(3);
        assertEquals(boardForThree, board.write());
    }
    
    /**
     * Test of create board for four players, of class Board.
     */
    @Test
    public void testArrayForFour() throws WrongNumberOfPlayers {
        Board board = new Board(4);
        assertEquals(boardForFour, board.write());
    }
    
    /**
     * Test of create board for six players, of class Board.
     */
    @Test
    public void testArrayForSix() throws WrongNumberOfPlayers {
        Board board = new Board(6);
        assertEquals(boardForSix, board.write());
    }
    
    /**
    * Test of create board for wrong number of players, of class Board.
    */
    @Test(expected = WrongNumberOfPlayers.class)
    public void testWrongNumberOfPlayers() throws WrongNumberOfPlayers{
        Board board = new Board(5);
        assertEquals(boardForSix, board.write());
    }
}
