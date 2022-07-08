package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
	public final int screenHeight= tileSize * maxScreenRow; // 576 pixel
	
	
	//WORLD SETTINGS
	public final int maxWorldCol = 20;
	public final int maxWorldRow = 20;
	
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;
	
	
	//FPS
	int FPS = 60;
		
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	

	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(80, 171, 118));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	
	public void startGamethread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	//secod loop (delta)
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;


		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}			

	}	
	
	
	
	public void update() {
		
		player.update();
		
	}
	
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2  = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
		
	}

}
