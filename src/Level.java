/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andreas
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Level {
	public int width;
	public int heigth;
	public ArrayList<Makanan> makanan;
	public ArrayList<Musuh> musuh;
        public ArrayList<Slowing> slowing;
	public Tile [][] tiles;
	public Level (String path) {
		makanan = new ArrayList<>();
		musuh = new ArrayList<>();
                slowing = new ArrayList<>();
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.heigth = map.getHeight();
			int [] pixels = new int [width * heigth];
			tiles = new Tile[width][heigth];
			map.getRGB(0, 0, width,heigth,pixels,0,width);
			
			for (int xx = 0; xx < width; xx++) {
				for (int yy = 0; yy < heigth; yy++) {
					int val = pixels[xx+(yy*width)];
					//System.out.println("val : " + val);
					
					if (val==0xFF000000) {//Hitam
						//Wall
						tiles[xx][yy] = new Tile(xx*32, yy*32);
					}
					else if (val == 0xFF0000FF) {//Biru
						//Player
						Game.player.y = yy*32;
						Game.player.x = xx*32;
						//System.out.println("Biru");
						
					}
					
					else if (val == 0xFFFF0000) {//Merah
						// Musuh
						musuh.add(new Musuh(xx*32, yy*32));
					}
                                        else if (val== 0xFF00FF00) {
                                            //Slowing 
                                                slowing.add(new Slowing(xx*32, yy*32));
                                        }
					else{					
						makanan.add(new Makanan(xx*32, yy*32));
						//System.out.println("masuk");	
						//System.out.println("val : "+ val);
						//System.out.println("makanan : " +makanan.size());

					}
					//System.out.println("val : "+ val);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void render(Graphics g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < heigth; y++) {
				if (tiles[x][y]!=null) {
					tiles[x][y].render(g);
				}
				
			}
			for (Makanan m : makanan) {	
				 m.render(g);
				// System.out.println("Masuk");
		}
			for (Musuh m : musuh) {
				m.render(g);
			}
                        for (Slowing s :slowing) {
                            s.render(g);
                    }

		}

	}
	
	public void tick() {
		for (int i = 0; i < musuh.size(); i++) {
			musuh.get(i).tick();
			
		}
	}
}
