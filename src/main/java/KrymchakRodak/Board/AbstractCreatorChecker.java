/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Board;

import java.util.ArrayList;

/**
 *
 * @author Krymchak Veranika
 */
public abstract class AbstractCreatorChecker 
{
    public abstract AbstractChecker createChecker(String color, ArrayList <Field> listOfNeighbors);    
}
