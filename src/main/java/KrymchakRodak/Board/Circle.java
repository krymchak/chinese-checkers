/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KrymchakRodak.Board;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
/**
 *
 * @author krmwe
 */
public class Circle extends Ellipse2D.Float 
{
	/** 
	* Zmienna, pokazujaca, czy dany okrag jest aktywny
	*/
    private boolean active=false;
	
	/** 
	* Color okrega.
	*/
	
	private Color color = Color.GRAY;

		/** 
	* Stwarza nowy objekt z danymi zmiennymi
	* @param x - wspolrzedna x srodka okregu
	* @param y - wspolrzedna y srodka okregu
	* @param width - szerokosc okregu
	* @param height - wysokosc okregu
	*/
	public Circle(float x, float y, float width, float height) 
	{
		setFrame(x, y, width, height);
	}
	
}
