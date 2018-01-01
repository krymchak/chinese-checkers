/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Bot;

import KrymchakRodak.Board.AbstractBoard;
import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.Field;
import KrymchakRodak.Board.ImpossibleStep;
import KrymchakRodak.Board.IsNotChecker;
import KrymchakRodak.Board.Neighbors;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krmwe
 */
public class Bot extends AbstractBot
{
    AbstractBoard board;
    final Random random = new Random();
    ArrayList <FieldForBot> listOfCheckers;
    ArrayList <FieldForBot> listOfEndField;
    Color color;
    
    public Bot(AbstractBoard board, Color color)
    {
        this.board=board;
        this.color=color;
        int endTriangle=0;
        listOfCheckers=new ArrayList <FieldForBot>(); 
        listOfEndField=new ArrayList <FieldForBot>(); 
        for (int i=0; i<17; i++)
        {
            for (int j=0; j<17; j++)
            {
                if (board.getField(i,j).isChecker() && board.getField(i,j).getChecker().getColor()==color)
                {
                    listOfCheckers.add(new FieldForBot(i,j));
                    if(endTriangle==0)
                        endTriangle=board.getField(i,j).getChecker().getEndTriangle();
                }
            }
        }
        
        for (int i=0; i<17; i++)
        {
            for (int j=0; j<17; j++)
            {
                if (board.getField(i,j).isChecker() && board.getField(i,j).getTriangle()==endTriangle)
                {
                    listOfEndField.add(new FieldForBot(i,j));
                }
            }
        }
    }
    
    public double distance(int i1, int j1, int i2, int j2)
    {
        return Math.sqrt((i1-i2)*(i1-i2) + (j1-j2)*(j1-j2));
    }
    
    public MaxDistance maxDistance()
    {
       double max=100;
       MaxDistance maxDistance = new MaxDistance();
       int j=0;
       ArrayList <Neighbors> listOfNeighbors=new ArrayList<>();
       while (listOfNeighbors.isEmpty())
        {
            j = random.nextInt(listOfCheckers.size());
            listOfNeighbors = board.createListOfNeighbors(listOfCheckers.get(j).getI(), listOfCheckers.get(j).getJ());
        }
        for (int i=0; i<listOfNeighbors.size(); i++)
        {
            if (distance(listOfNeighbors.get(i).getI(), listOfNeighbors.get(i).getJ(), listOfEndField.get(0).getI(),listOfEndField.get(0).getJ())<max)
            {
                max=distance(listOfNeighbors.get(i).getI(), listOfNeighbors.get(i).getJ(), listOfEndField.get(0).getI(),listOfEndField.get(0).getJ());
                maxDistance.setChecker(listOfCheckers.get(j));
                maxDistance.setNeighbors(listOfNeighbors.get(i));
            }
        }
        System.out.println(listOfCheckers.get(j).getI());
        System.out.println(listOfCheckers.get(j).getJ());
       return maxDistance;
    }
    
    
    public void moveBot()
    {
        if (!board.win(color))
        {
            MaxDistance maxDistance = maxDistance();
            try 
            {
                board.Step(maxDistance.getChecker().getI(), maxDistance.getChecker().getJ(), maxDistance.getNeighbors().getI(), maxDistance.getNeighbors().getJ());
                listOfCheckers.remove(maxDistance.getChecker());
                listOfCheckers.add(new FieldForBot(maxDistance.getNeighbors().getI(), maxDistance.getNeighbors().getJ()));
                listOfEndField.remove(new FieldForBot(maxDistance.getNeighbors().getI(), maxDistance.getNeighbors().getJ())); 
                board.endMove();
            } catch (IsNotChecker ex) 
            {
            } catch (ImpossibleStep ex) 
            {
            }
        }
    }
    
    public static void main(String args[])
    {
        try {
            Board board = new Board(6);
            Bot bot1 = new Bot(board, Color.ORANGE);
            Bot bot2 = new Bot(board, Color.RED);
            Bot bot3 = new Bot(board, Color.BLUE);
            Bot bot4 = new Bot(board, Color.GREEN);
            Bot bot5 = new Bot(board, Color.CYAN);
            Bot bot6 = new Bot(board, Color.PINK);
            System.out.println("�����");
            for(int i=0; i<300; i++)
            {
                bot1.moveBot();
                bot2.moveBot();
                bot3.moveBot();
                bot4.moveBot();
                bot5.moveBot();
                bot6.moveBot();
            }
            
            System.out.println(board.write());
           
            
        } catch (WrongNumberOfPlayers ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}