/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Board;

/**
 *
 * @author krmwe
 */
public class Neighbors 
{
    Field field;
    boolean isJump;
    public Neighbors(Field field, boolean isJump)
    {
        this.field=field;
        this.isJump=isJump;
    }
    public Field getField()
    {
        return field;        
    }
    
    public boolean isJump()
    {
        return isJump;        
    }
}
