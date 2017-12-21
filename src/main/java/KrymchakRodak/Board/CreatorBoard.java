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
public class CreatorBoard extends AbstractCreatorBoard{
    
    /*
    * Creare new Board with given number of Players
    */
    @Override
    public Board createBoard(int numberOfPlayers) throws WrongNumberOfPlayers
    {
        
        try {
            return new Board(numberOfPlayers);
        } catch (WrongNumberOfPlayers ex) {
            throw new WrongNumberOfPlayers();
        }
    }
}
