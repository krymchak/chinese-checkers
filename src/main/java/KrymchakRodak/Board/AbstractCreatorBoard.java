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
public abstract class AbstractCreatorBoard {
    public abstract AbstractBoard createBoard(int NumberOfPlayers) throws WrongNumberOfPlayers;
}
