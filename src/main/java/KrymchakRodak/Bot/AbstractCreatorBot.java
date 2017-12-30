/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Bot;

import KrymchakRodak.Board.AbstractBoard;
import java.awt.Color;

/**
 *
 * @author krmwe
 */
public abstract class AbstractCreatorBot {
    public abstract AbstractBot createBot(AbstractBoard board, Color color);
}
