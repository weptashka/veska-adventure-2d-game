package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	
	final int originalTileSize = 20; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 10;
	
//	public final int screenWidth = 1920; 
//	public final int screenHeight= 1080; 
	
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
	public final int screenHeight= tileSize * maxScreenRow; // 576 pixel
	
	
	//WORLD SETTINGS
	public final int maxWorldCol = 89;
	public final int maxWorldRow = 53;
	
//	public final int worldWidth = maxWorldCol * tileSize;
//	public final int worldHeight = maxWorldRow * tileSize;
	
	
	//FPS
	int FPS = 60;
		
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public Player player = new Player(this, keyH);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//ENTITY AND OBJECT
	public SuperObject[] obj = new SuperObject[10]; //object class
	public AssertSetter aSetter = new AssertSetter(this); // objects creator 
	
	

	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(80, 171, 118));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	
	public void setupGame() {
		aSetter.setObject();
		
//		playMusic(0);
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	//second loop (delta)
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;


		//пока не пройдёт определённое время, дельта не станет больше единицы и 
		//update и repaint не вызовутся
		
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
		
		
		//DEBUG
		long drawStart = 0;
		if (keyH.checkDrawTime == true) {		
			drawStart = System.nanoTime();
		}
		

		
		//TILE
		tileM.draw(g2);
		
		
		//OBJECTS
		for(int i = 0; i < obj.length;  i++) {
			if (obj[i] != null) {obj[i].draw(g2, this); }
		}
		
		
		//PLAYER
		player.draw(g2);
		
		
		//UI (we call it last to be the top layer)
		ui.draw(g2);
		
		
		
		
		//DEBUG
		if (keyH.checkDrawTime == true) {	
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(new Color(255, 255, 255));
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		}
		
		g2.dispose();
	}
	
	
	
	//for bg music
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	
	//for sound effect
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

}
