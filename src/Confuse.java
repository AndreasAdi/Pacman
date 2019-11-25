


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Confuse extends Rectangle{
        
        Image img;
	public Confuse(int x, int y) {
		setBounds(x+10,y+10,8,8);
	}
	public void render(Graphics g) {
		//g.setColor(Color.GREEN);
		//g.fillOval(x, y, width, height);
               		 try {
                img = ImageIO.read(new File("Src\\Char\\stroberi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                g.drawImage(img,x,y,16,16,null);
	}
}
