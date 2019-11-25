/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andreas
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private boolean isrunning = false;
	public static final int width =800 , height = 480;
	public static final String Title ="Puck-Man";
	public static int Score=0;
	private Thread thread;
	public static Player player;
	public static Level level;
	
	public Game() {
		Dimension dimension = new Dimension(Game.width,Game.height);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);
		player = new Player(Game.width/2, Game.height/2);
		level = new Level("/Map/map.png");
	}
	public synchronized void start() {
		if (isrunning) return;{
			isrunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	public synchronized void stop() {
		if (!isrunning)return; {
			isrunning = false;
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void tick() {
		player.tick();
		level.tick();
	}
	private void render() {
		BufferStrategy bS = getBufferStrategy();
		if (bS==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = bS.getDrawGraphics();
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, Game.width, Game.height);
                Font a = new Font("Arial",Font.BOLD, 20);
                graphics.setFont(a);
                graphics.setColor(Color.YELLOW);
               graphics.drawString("score: " +Score,680,20);
               
              // graphics.drawS
		player.render(graphics);
		level.render(graphics);
		graphics.dispose();
		bS.show();
	}
	@Override
	public void run() {
		requestFocus();
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lasttime = System.nanoTime();
		double delta =0;
		double targettick = 60.0;
		double ns = 1000000000/targettick;
		
		
		while (isrunning) {
			long now = System.nanoTime();
			delta += (now - lasttime)/ns;
			lasttime = now;
			
			while (delta>=1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >=1000) {
				//System.out.println(fps);
				fps =0;
				timer+=1000;
			}

		}
		stop();
	}
public static void main(String[] args) {
	Game game  = new Game();
	JFrame frame = new JFrame();
	frame.setTitle(Game.Title);
	frame.add(game);
	frame.setResizable(false);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	
	frame.setVisible(true);
	
	game.start();
}
@Override
public void keyPressed(KeyEvent e) {
	if (e.getKeyCode()==KeyEvent.VK_A) {
		player.left = true;
	}
	if (e.getKeyCode()==KeyEvent.VK_D) {
		player.right = true;
	}
	if (e.getKeyCode()==KeyEvent.VK_W) {
		player.up = true;
	}
	if (e.getKeyCode()==KeyEvent.VK_S) {
		player.down = true;
	}
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	if (e.getKeyCode()==KeyEvent.VK_A) {
		player.left = false;
	}
	if (e.getKeyCode()==KeyEvent.VK_D) {
		player.right = false;
	}
	if (e.getKeyCode()==KeyEvent.VK_W) {
		player.up = false;
	}
	if (e.getKeyCode()==KeyEvent.VK_S) {
		player.down = false;
	}
	
}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}


