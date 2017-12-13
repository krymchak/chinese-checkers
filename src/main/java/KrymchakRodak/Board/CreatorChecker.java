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
public class CreatorChecker extends AbstractCreatorChecker
{
    /*
    * Creare new Checker with given color
    */
    @Override
    public Checker createChecker(String color, ArrayList <Field> listOfNeighbors)
    {
        return new Checker(color, listOfNeighbors);
    }
}
