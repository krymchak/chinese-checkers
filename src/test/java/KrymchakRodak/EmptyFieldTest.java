/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Checker;
import KrymchakRodak.Board.Circle;
import KrymchakRodak.Board.EmptyField;
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
public class EmptyFieldTest {
    
    /**
     * Test of IsNotEmpty method, of class EmptyField.
     */
    @Test
    public void testIsNotEmpty() {
        System.out.println("IsNotEmpty");
        EmptyField instance = new EmptyField();
        boolean expResult = false;
        boolean result = instance.IsNotEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChecker method, of class EmptyField.
     */
    @Test
    public void testSetChecker() {
        System.out.println("setChecker");
        Checker checker = null;
        EmptyField instance = new EmptyField();
        instance.setChecker(checker);
    }

    /**
     * Test of getChecker method, of class EmptyField.
     */
    @Test
    public void testGetChecker() {
        System.out.println("getChecker");
        EmptyField instance = new EmptyField();
        Checker expResult = null;
        Checker result = instance.getChecker();
        assertEquals(expResult, result);
    }

    /**
     * Test of isChecker method, of class EmptyField.
     */
    @Test
    public void testIsChecker() {
        System.out.println("isChecker");
        EmptyField instance = new EmptyField();
        boolean expResult = false;
        boolean result = instance.isChecker();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCircle method, of class EmptyField.
     */
    @Test
    public void testGetCircle() {
        System.out.println("getCircle");
        EmptyField instance = new EmptyField();
        Circle expResult = null;
        Circle result = instance.getCircle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCircle method, of class EmptyField.
     */
    @Test
    public void testSetCircle() {
        System.out.println("setCircle");
        Circle circle = null;
        EmptyField instance = new EmptyField();
        instance.setCircle(circle);
    }

    /**
     * Test of isActive method, of class EmptyField.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        EmptyField instance = new EmptyField();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActive method, of class EmptyField.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        boolean active = false;
        EmptyField instance = new EmptyField();
        instance.setActive(active);
    }

    /**
     * Test of getTriangle method, of class EmptyField.
     */
    @Test
    public void testGetTriangle() {
        System.out.println("getTriangle");
        EmptyField instance = new EmptyField();
        int expResult = 0;
        int result = instance.getTriangle();
        assertEquals(expResult, result);
    }
    
}
