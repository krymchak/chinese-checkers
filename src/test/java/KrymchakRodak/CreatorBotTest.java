/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.AbstractBoard;
import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Bot.AbstractBot;
import KrymchakRodak.Bot.Bot;
import KrymchakRodak.Bot.CreatorBot;
import java.awt.Color;
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
public class CreatorBotTest {
    

    /**
     * Test of createBot method, of class CreatorBot.
     */
    @Test
    public void testCreateBot() throws Exception {
        CreatorBot instance = new CreatorBot();
        Bot result = instance.createBot(new Board(4), Color.ORANGE);
        assertNotNull(result);
    }
    
}
