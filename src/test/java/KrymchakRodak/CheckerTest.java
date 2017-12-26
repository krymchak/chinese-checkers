/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Checker;
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
public class CheckerTest {

    /**
     * Test of getEndTriangle method, of class Checker.
     */
    @Test
    public void testGetEndTriangle() {
        Checker checker = new Checker(Color.ORANGE, 3);
        assertEquals(3, checker.getEndTriangle());
    }

    /**
     * Test of getColor method, of class Checker.
     */
    @Test
    public void testGetColor() {
        Checker checker = new Checker(Color.ORANGE, 3);
        assertEquals(Color.ORANGE, checker.getColor());
    }
    
}
