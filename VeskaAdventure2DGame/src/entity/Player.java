package entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX; // make player position in the center of the screen
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	void setDefaultValues(){
		worldX = gp.tileSize*5; // just some coordinates
		worldY = gp.tileSize*5;
		speed = 4;
		direction = "down";
	}
	
	
	//method which gets out pictures from folder and put them into variables
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
		
		if(keyH.upPressed == true || keyH.leftPressed == true ||
			keyH.downPressed == true || keyH.rightPressed == true) {
			
		
			// changing player speed
			// we change player coordinates on the gp
			if(keyH.upPressed == true) {
				direction = "up";
				worldY -= speed;
			}
			if(keyH.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			}
			if(keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
			}
			if(keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}
			
			
			// "method" that change frames 6 times per second (FPS/10)
			spriteCounter++;
			if(spriteCounter > 10) { // this number can change frame speed
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
	}
	
	
	
	// change frames op player when it's coordinates are changed
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
	}

}
