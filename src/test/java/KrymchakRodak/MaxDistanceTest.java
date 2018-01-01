/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Neighbors;
import KrymchakRodak.Board.NotEmptyField;
import KrymchakRodak.Bot.FieldForBot;
import KrymchakRodak.Bot.MaxDistance;
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
public class MaxDistanceTest {
    
    /**
     * Test of getChecker method, of class MaxDistance.
     */
    @Test
    public void testGetChecker() {
        MaxDistance instance = new MaxDistance();
        FieldForBot checker = new FieldForBot(1,1);
        instance.setChecker(checker);
        assertEquals(checker, instance.getChecker());
    }

    /**
     * Test of getNeighbors method, of class MaxDistance.
     */
    @Test
    public void testGetNeighbors() {
        MaxDistance instance = new MaxDistance();
        Neighbors neighbors = new Neighbors(new NotEmptyField(), true, 1,1);
        instance.setNeighbors(neighbors);
        assertEquals(neighbors, instance.getNeighbors());
    }

    /**
     * Test of setChecker method, of class MaxDistance.
     */
    @Test
    public void testSetChecker() {
        MaxDistance instance = new MaxDistance();
        FieldForBot checker = new FieldForBot(1,1);
        instance.setChecker(checker);
        assertEquals(checker, instance.getChecker());
    }

    /**
     * Test of setNeighbors method, of class MaxDistance.
     */
    @Test
    public void testSetNeighbors() {
        MaxDistance instance = new MaxDistance();
        Neighbors neighbors = new Neighbors(new NotEmptyField(), true, 1,1);
        instance.setNeighbors(neighbors);
        assertEquals(neighbors, instance.getNeighbors());
    }
    
}
