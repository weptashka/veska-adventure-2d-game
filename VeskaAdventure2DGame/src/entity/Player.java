package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	public int hasKey = 0;
	public int hasMayonnaise = 0;

	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		
		//manage collision area of player
		solidArea = new Rectangle();
		solidArea.x = 20;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 8;
		solidArea.height = 14;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	void setDefaultValues(){
		worldX = gp.tileSize * 28; // start Player point coordinates
		worldY = gp.tileSize * 15;
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
			}
			if(keyH.leftPressed == true) {
				direction = "left";
			}
			if(keyH.downPressed == true) {
				direction = "down";
			}
			if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			
			//CHECK THE TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			//CHECK OBJECTS COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case"up": worldY -= speed; break;
				case"down": worldY += speed; break;
				case"left":	worldX -= speed; break;
				case"right": worldX += speed; break;
				}
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
	
	
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a Key!");
				gp.playSE(4);				
				break;
			case "Door":
				if(hasKey > 0) {
					hasKey--;
					gp.obj[i] = null;
					gp.ui.showMessage("You opened Door!");
					gp.playSE(1);				

				}else {
					gp.ui.showMessage("You haven't got keys!");
				}
				break;
			case "Chest":
				if(hasKey > 0) {
					hasKey--;
					gp.obj[i] = null;
					gp.playSE(1);
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(2);				
				}else {
					gp.ui.showMessage("You haven't got keys!");
				}
				break;
			case "Boots":
				speed++;
				gp.obj[i] = null;
				gp.ui.showMessage("You've become faster!!");
				gp.playSE(5);				
				break;
			case "Mayonnaise":
				gp.obj[i] = null;
				hasMayonnaise++;
				gp.ui.showMessage("You found mayounase for Natally!");
				gp.playSE(5);				
				break;
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
