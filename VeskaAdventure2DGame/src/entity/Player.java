package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	void setDefaultValues(){
		x = 100;
		y = 100;
		speed = 4;
		direction = "up";
	}
	
	public void getPlayerImage() {
		try {
			
			up_1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
			up_2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
			down_1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
			down_2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
			right_1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
			right_2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
			left_1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
			left_2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// this method called 60 times/sec
	public void update() {
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		if(keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		if(keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		if(keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
		
		
		// method that change frames 6 times per second (FPS/10)
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		 
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up_1;
			}
			if(spriteNum == 2) {
				image = up_2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left_1;
			}
			if(spriteNum == 2) {
				image = left_2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down_1;
			}
			if(spriteNum == 2) {
				image = down_2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right_1;
			}
			if(spriteNum == 2) {
				image = right_2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
	}

}
