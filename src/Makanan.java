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

public class Makanan extends Rectangle{

	public Makanan(int x, int y) {
		setBounds(x+10,y+10,8,8);
	}
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
	}
}
