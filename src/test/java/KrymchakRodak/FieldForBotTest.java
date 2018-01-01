/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Bot.FieldForBot;
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
public class FieldForBotTest {
    

    /**
     * Test of getI method, of class FieldForBot.
     */
    @Test
    public void testGetI() {
        FieldForBot instance = new FieldForBot(1,1);
        assertEquals(1, instance.getI());
    }

    /**
     * Test of getJ method, of class FieldForBot.
     */
    @Test
    public void testGetJ() {
        FieldForBot instance = new FieldForBot(1,1);
        assertEquals(1, instance.getJ());
    }
    
}
