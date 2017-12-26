/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Checker;
import KrymchakRodak.Board.Circle;
import KrymchakRodak.Board.Field;
import KrymchakRodak.Board.NotEmptyField;
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
public class NotEmptyFieldTest {


    /**
     * Test of IsNotEmpty method, of class NotEmptyField.
     */
    @Test
    public void testIsNotEmpty() {
        Field instance = new NotEmptyField();
        assertEquals(true, instance.IsNotEmpty());
    }

    /**
     * Test of isActive method, of class NotEmptyField.
     */
    @Test
    public void testIsActive() {
        Field instance = new NotEmptyField();
        assertEquals(false, instance.isActive());
    }

    /**
     * Test of setActive method, of class NotEmptyField.
     */
    @Test
    public void testSetActive() {
        Field instance = new NotEmptyField();
        instance.setActive(true);
        assertEquals(true, instance.isActive());
        instance.setActive(false);
        assertEquals(false, instance.isActive());
    }

    /**
     * Test of setChecker method, of class NotEmptyField.
     */
    @Test
    public void testSetChecker() {
        Checker checker = new Checker (Color.BLUE, 1);
        Field instance = new NotEmptyField();
        instance.setChecker(checker);
        assertNotNull(instance.getChecker());
    }

    /**
     * Test of getChecker method, of class NotEmptyField.
     */
    @Test
    public void testGetChecker() {
        Field instance = new NotEmptyField();
        assertEquals(null, instance.getChecker());
    }

    /**
     * Test of getCircle method, of class NotEmptyField.
     */
    @Test
    public void testGetCircle() {
        Field instance = new NotEmptyField();
        assertEquals(null, instance.getCircle());
    }

    /**
     * Test of setCircle method, of class NotEmptyField.
     */
    @Test
    public void testSetCircle() {
        System.out.println("setCircle");
        Circle circle = new Circle (100,100,5,5);
        Field instance = new NotEmptyField();
        instance.setCircle(circle);
        assertNotNull(instance.getCircle());
        
    }

    /**
     * Test of isChecker method, of class NotEmptyField.
     */
    @Test
    public void testIsChecker() {
        Field instance = new NotEmptyField();
        assertEquals(false, instance.isChecker());
        instance.setChecker(new Checker(Color.ORANGE,3));
        assertEquals(true, instance.isChecker());
    }

    /**
     * Test of getTriangle method, of class NotEmptyField.
     */
    @Test
    public void testGetTriangle() {
        NotEmptyField instance = new NotEmptyField(1);
        assertEquals(1, instance.getTriangle());
    }
    
}
