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
    int i;
    int j;
    public Neighbors(Field field, boolean isJump, int i, int j)
    {
        this.field=field;
        this.isJump=isJump;
        this.i=i;
        this.j=j;
    }
    public Field getField()
    {
        return field;        
    }
    
    public boolean isJump()
    {
        return isJump;        
    }
    
        public int getI()
    {
        return i;
    }
    
    public int getJ()
    {
        return j;
    }
}
