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
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Player extends Rectangle {


	public boolean right,left,up,down;
	private int speed =4;
	public Image img;
        public Timer t;
        public static boolean cekpowerup;
        int Waktu =0;
	public Player(int x, int y) {
		setBounds(x,y,32,32);
	}
        

        
	public void tick() {
            

            
		if (left && canmove(x-speed, y)) {
			x-=speed;
		}
		if (right && canmove(x+speed, y)) {
			x+=speed;
		}
		if (up && canmove(x, y-speed)) {
			y-=speed;
		}
		if (down && canmove(x,y+speed)) {
			y+=speed;
		}
		
		Level level = Game.level;
		
		for (int i = 0; i <level.makanan.size(); i++) {
			if (this.intersects(level.makanan.get(i))) {
				level.makanan.remove(i);
                                Game.Score +=10;
				break;
			}
		}
         
                for (int i = 0; i < level.slowing.size(); i++) {
                    
                    t= new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (cekpowerup==true) {
   
                            System.out.println(Waktu);
                           for (Musuh M : level.musuh) {        
                             if (Waktu<=0) {
                                  M.spd =0;
                                }
                             else if (Waktu == 5) {
                                    M.spd =2;
                                    t.stop();
                                
                                    cekpowerup =false;
                                    }
                            }
                        Waktu++;
                    }
                        }

                   });
			if (this.intersects(level.slowing.get(i))) {
				level.slowing.remove(i);
                                //Game.Score +=10;
                                 Waktu =0;
                                 cekpowerup = true;
                                 t.start();
				break;
			}                    
                
            }
		
		if (level.makanan.size()==0) {
			//win reset permainan
			Game.player = new Player(0, 0);
			Game.level = new Level("/Map/map.png");
		}
		
		// Pengecekan Kalah
		for (int i = 0; i < level.musuh.size(); i++) {
			if (this.intersects(level.musuh.get(i))) {
				//Game.player = new Player(0, 0);
				//Game.level = new Level("/Map/map.png");
				int retry = JOptionPane.showConfirmDialog(null, "Retry","Game Over",JOptionPane.YES_NO_OPTION);
				if (retry==JOptionPane.YES_OPTION) {
					Game.player = new Player(0, 0);
					Game.level = new Level("/Map/map.png");
				}
				else if (retry ==JOptionPane.NO_OPTION) {
					System.exit(1);
					
				}

			}
		}
	}
	
	public boolean canmove(int nextx, int nexy) {
		
		Rectangle bounds= new Rectangle(nextx,nexy,width,height);
		Level level = Game.level;
		
		for (int xx = 0; xx < level.tiles.length; xx++) {
			for (int yy = 0; yy < level.tiles[0].length; yy++) {
				if(level.tiles[xx][yy]!= null) {
					if (bounds.intersects(level.tiles[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void render (Graphics g) {
		 try {
                img = ImageIO.read(new File("Src\\Char\\pacman.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, width, height);
	
		g.drawImage(img,x,y,32,32,null);
                
		
	}
}
