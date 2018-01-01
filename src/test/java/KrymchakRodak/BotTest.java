/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import KrymchakRodak.Bot.Bot;
import KrymchakRodak.Bot.MaxDistance;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BotTest {

    /**
     * Test of distance method, of class Bot.
     */
    @Test
    public void testDistance() {
        try {
            Bot instance = new Bot(new Board(2), Color.ORANGE);
            assertEquals(0.0, instance.distance(0, 0, 0, 0), 0.0);
            assertEquals(9.0, instance.distance(0, 0, 9, 0), 0.0);
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(BotTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of maxDistance method, of class Bot.
     */
    @Test
    public void testMaxDistance() {
        try {
            Bot instance = new Bot(new Board(2), Color.ORANGE);
            assertNotNull(instance.maxDistance());
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(BotTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of moveBot method, of class Bot.
     */
    @Test
    public void testMoveBot() {
        try {
            Bot instance = new Bot(new Board(2), Color.ORANGE);
            instance.moveBot();
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(BotTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
