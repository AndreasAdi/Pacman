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
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;

public class Musuh extends Rectangle{
	
	public Musuh(int x, int y) {
		r = new Random();
		setBounds(x,y,32,32);
		dir = r.nextInt(5);
	}
	public Image img;
	private int Random = 0,smart =1, find_path =2;
	private int state = smart;
	private int right =0 , left = 1, up = 2, down = 3;
	private int dir =-1;
	private int lastdir = -1;
	
	public Random r;
	
	private int time = 0;
	private int targetTime = 60 *4;// Kapan balik jadi random 
	public int spd = 2; // Kecepatan 
	
	public void render(Graphics g) {
		try {
			img = ImageIO.read(new File("Src\\Char\\ghost_0_0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//g.setColor(Color.magenta);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(img,x,y,32,32,null);
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
	
	public void tick() {
		if (state == Random) {
			
			if (dir == right) {
				if (canmove(x+spd, y)) {
					x+=spd;
					lastdir = right;
				}
				else {
					dir = r.nextInt(4);
				}
			}
			else if (dir == left) {
				if (canmove(x-spd, y)) {
					x-=spd;
					lastdir = left;
				}
				else {
					dir = r.nextInt(4);
				}
			} 
			else if (dir == up) {
				if (canmove(x, y-spd)) {
					y-=spd;
					lastdir = up;
				}
				else {
					dir = r.nextInt(4);
				}
			} 
			else if (dir == down) {
				if (canmove(x, y+spd)) {
					y+=spd;
					lastdir = down;
				}
				else {
					dir = r.nextInt(4);
				}
			} 			
			time ++;
			//System.out.println(time);
		}
		if (time == targetTime) {
			state = smart;
			time =0;
		}
		if (state==smart) {
			
	
			//System.out.println("Smart");
			boolean move = false;
			
			if (x<Game.player.x) {
				if(canmove(x+spd,y)) {
					x+=spd;
					move = true;
					lastdir = left;
				}
			}
			else if (x>Game.player.x) {
				if(canmove(x-spd,y)) {
					x-=spd;
					move = true;
					lastdir= left;
				}
			}
			else if (y<Game.player.y) {
				if(canmove(x,y+spd)) {
					y+=spd;
					move = true;
					lastdir= down;
				}
			}
			else if (y>Game.player.y) {
				if(canmove(x,y-spd)) {
					y-=spd;
					move = true;
					lastdir = up;
				}
			}
			
			if (x == Game.player.x && y == Game.player.y) {
				move = true;
			}
			 if(!move) {
				 state = find_path;
				 
				if (lastdir == right) {
					if (y < Game.player.y) {
						if (canmove(x, y+spd)) {
							y+=spd;
						}
					}
					else {
						if (canmove(x, y-spd)) {
							y-=spd;
						}
					}
					if (canmove(x+spd, y)) {
						x+=spd;
					}
				}
				if (lastdir == left) {
					if (y < Game.player.y) {
						if (canmove(x, y+spd)) {
							y+=spd;
						}
					}
					else {
						if (canmove(x, y-spd)) {
							y-=spd;
						}
					}
					if (canmove(x-spd, y)) {
						x-=spd;
					}
				}
				if (lastdir == up) {
					if (x < Game.player.x) {
						if (canmove(x+spd, y)) {
							x+=spd;
						}
					}
					else {
						if (canmove(x-spd, y)) {
							x-=spd;
						}
					}
					if (canmove(x, y-spd)) {
						y-=spd;
					}
				}
				if (lastdir == down) {
					if (x < Game.player.x) {
						if (canmove(x+spd, y)) {
							x+=spd;
						}
					}
					else {
						if (canmove(x-spd, y)) {
							x-=spd;
						}
					}
					if (canmove(x, y+spd)) {
						y+=spd;
					}
				}
			}
			
			//time ++;
			//if (time == targetTime*2) {
			//	state = Random;
			//	time =0;
			//}
		}
		
		if (state==find_path) {
			if (lastdir == right) {
				if (y < Game.player.y) {
					if (canmove(x, y+spd)) {
						y+=spd;
						state = smart;
					}
				}
				else {
					if (canmove(x, y-spd)) {
						y-=spd;
						state = smart;
					}
				}
				if (canmove(x+spd, y)) {
					x+=spd;
				}
			}
			if (lastdir == left) {
				if (y < Game.player.y) {
					if (canmove(x, y+spd)) {
						y+=spd;
						state = smart;
					}
				}
				else {
					if (canmove(x, y-spd)) {
						y-=spd;
						state = smart;
					}
				}
				if (canmove(x-spd, y)) {
					x-=spd;
				}
			}
			if (lastdir == up) {
				if (x < Game.player.x) {
					if (canmove(x+spd, y)) {
						x+=spd;
						state = smart;
					}
				}
				else {
					if (canmove(x-spd, y)) {
						x-=spd;
						state = smart;
					}
				}
				if (canmove(x, y-spd)) {
					y-=spd;
				}
			}
			if (lastdir == down) {
				if (x < Game.player.x) {
					if (canmove(x+spd, y)) {
						x+=spd;
						state = smart;
					}
				}
				else {
					if (canmove(x-spd, y)) {
						x-=spd;
						state = smart;
					}
				}
				if (canmove(x, y+spd)) {
					y+=spd;
				}
			}
		}
		

	}
}