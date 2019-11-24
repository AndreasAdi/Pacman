/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andreas
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile extends Rectangle{
	public Tile(int x, int y) {
		setBounds(x,y,32,32);
	}
	public void render(Graphics g) {
		g.setColor(new Color(30,0,127));
		g.fillRect(x, y, width, height);
	}
}
