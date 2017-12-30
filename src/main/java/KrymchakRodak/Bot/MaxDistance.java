/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Bot;

import KrymchakRodak.Board.Neighbors;

/**
 *
 * @author krmwe
 */
public class MaxDistance 
{
    FieldForBot checker;
    Neighbors neighbors;
    
    public FieldForBot getChecker()
    {
        return checker;
    }
    
    public Neighbors getNeighbors()
    {
        return neighbors;
    }
        
    public void setChecker(FieldForBot checker)
    {
        this.checker=checker;
    }
    
    public void setNeighbors(Neighbors neighbors)
    {
        this.neighbors=neighbors;
    }
    
}
