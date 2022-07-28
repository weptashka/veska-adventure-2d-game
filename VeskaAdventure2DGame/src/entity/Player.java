package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	public final int screenX; // make player position in the center of the screen
	public final int screenY;
	public int hasKey = 0;
	public int hasMayonnaise = 0;
	public int standCounter = 0; //count how many times player pictures of player changed  

	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		
		//manage collision area of player
		solidArea = new Rectangle();
		solidArea.x = 21;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 9;
		solidArea.height = 18;
		
		
		setDefaultValues();
		
		getPlayerImage();
	}
	
	void setDefaultValues(){
		worldX = gp.tileSize * 28; // start Player point coordinates
		worldY = gp.tileSize * 15;
		speed = 5;
		direction = "down";
	}
	
	
	//method which gets out pictures from folder and put them into variables
	public void getPlayerImage() {
			
			up_1 = setup("up_1");
			up_2 = setup("up_2");
			down_1 = setup("down_1");
			down_2 = setup("down_2");
			right_1 = setup("right_1");
			right_2 = setup("right_2");
			left_1 = setup("left_1");
			left_2 = setup("left_2");

	}
	
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return image;
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
			
		}else {
			standCounter++;
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
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
		
		g2.drawImage(image, screenX, screenY, null);
		
		//DEBUG
		g2.setColor(new Color(117, 13, 16));
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
	}

}
