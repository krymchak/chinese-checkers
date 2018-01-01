/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import KrymchakRodak.Board.IsNotChecker;
import KrymchakRodak.Board.ImpossibleStep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    "         GGGG    \n" +
    "          GGG    \n" +
    "           GG    \n" +
    "            G    \n";
    String boardForThree=
    "    .            \n" +
    "    ..           \n" +
    "    ...          \n" +
    "    ....         \n" +
    "BBBB.....RRRR    \n" +
    " BBB......RRR    \n" +
    "  BB.......RR    \n" +
    "   B........R    \n" +
    "    .........    \n" +
    "    ..........   \n" +
    "    ...........  \n" +
    "    ............ \n" +
    "    .............\n" +
    "         GGGG    \n" +
    "          GGG    \n" +
    "           GG    \n" +
    "            G    \n";
    
    String boardForFour=
    "    .            \n" +
    "    ..           \n" +
    "    ...          \n" +
    "    ....         \n" +
    "OOOO.....RRRR    \n" +
    " OOO......RRR    \n" +
    "  OO.......RR    \n" +
    "   O........R    \n" +
    "    .........    \n" +
    "    G........B   \n" +
    "    GG.......BB  \n" +
    "    GGG......BBB \n" +
    "    GGGG.....BBBB\n" +
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
    
    String boardAfterStep="    O            \n" +
"    OO           \n" +
"    OOO          \n" +
"    OOOO         \n" +
"PPP.P....RRRR    \n" +
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
    
        @Test
    public void testStep() throws WrongNumberOfPlayers, ImpossibleStep, IsNotChecker {
        Board board = new Board(6);
        board.Step(4, 3, 4, 4);
        assertEquals(boardAfterStep, board.write());
    }
    
    /**
    * Test of step with not checker, of class Board.
    */
    @Test(expected = IsNotChecker.class)
    public void testIsNotChecker() throws IsNotChecker, WrongNumberOfPlayers, ImpossibleStep{
            Board board = new Board(6);
            board.Step(10, 10, 11, 10);
    }
    
    /**
    * Test of impossible step, of class Board.
    */
    @Test(expected = ImpossibleStep.class)
    public void testImpossibleStep() throws ImpossibleStep, WrongNumberOfPlayers, IsNotChecker{
            Board board = new Board(6);
            board.Step(5, 1, 5, 0);
    }
}
