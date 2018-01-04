/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak;

import KrymchakRodak.Board.Circle;
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
public class CircleTest {
    
    @Test
    public void testIsHit() {
        Circle instance = new Circle (100,100,100,100);
        assertEquals(true, instance.isHit(100.0F, 100.0F));
    }
    
}
