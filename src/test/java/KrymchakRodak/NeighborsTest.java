/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Field;
import KrymchakRodak.Board.Neighbors;
import KrymchakRodak.Board.NotEmptyField;
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
public class NeighborsTest {

    /**
     * Test of getField method, of class Neighbors.
     */
    @Test
    public void testGetField() {
        Field field = new NotEmptyField();
        Neighbors instance = new Neighbors(field, true, 1,1);
        assertEquals(field, instance.getField());
    }

    /**
     * Test of isJump method, of class Neighbors.
     */
    @Test
    public void testIsJump() {
        Neighbors instance = new Neighbors(new NotEmptyField(), true, 1,1);
        assertEquals(true, instance.isJump());
    }

    /**
     * Test of getI method, of class Neighbors.
     */
    @Test
    public void testGetI() {
        Neighbors instance = new Neighbors(new NotEmptyField(), true, 1,1);
        assertEquals(1, instance.getI());
    }

    /**
     * Test of getJ method, of class Neighbors.
     */
    @Test
    public void testGetJ() {
        Neighbors instance = new Neighbors(new NotEmptyField(), true, 1,1);
        assertEquals(1, instance.getJ());
    }
    
}
