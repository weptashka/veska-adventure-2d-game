package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;
import object.OBJ_Mayonnaise;

public class UI {
	
	GamePanel gp;
	Font font_20, font_40, font_80;
	BufferedImage keyImage;
	BufferedImage mayonnaiseImage;
	public boolean messageOn = false;
	public String message = "You get a key!";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.0");

	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		this.font_20 = new Font("Arial", Font.PLAIN, 20);
		this.font_40 = new Font("Arial", Font.PLAIN, 40);
		this.font_80 = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
		OBJ_Mayonnaise mayonnaise = new OBJ_Mayonnaise(gp);
		mayonnaiseImage = mayonnaise.image;

	}
		
//		loadResources();
		
//			try {
////				my_font = new Font("Arial", Font.PLAIN, 40);
//				my_font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("res/fonts/undertale_font.ttf"));
////				my_font = Font.createFont(Font.TRUETYPE_FONT, new File("VeskaAdventure2DGame\\res\\fonts\\undertale_font.ttf")).deriveFont(40f);
//				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//				ge.registerFont(my_font);
//			}catch(Exception e) {
//			e.printStackTrace();
//		}
//	
//	private void loadResources() {
//		try {
//			Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("/res/fonts/undertale_font.ttf"));
//			this.font = fontRaw;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	
	//this method will be changed 60 times/sec
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			String text;
			int textLength;
			int x;
			int y;
			
			g2.setFont(font_40);
			g2.setColor(new Color(0, 0, 0));
			text = "You foung the treasure"; 
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - gp.tileSize*2;
			g2.drawString(text, x, y);
			
			
			g2.setFont(font_80);
			g2.setColor(new Color(245, 202, 83));
			text = "Congratulations!"; 
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + gp.tileSize*2;
			g2.drawString(text, x, y);
			
			
			g2.setFont(font_20);
			g2.setColor(new Color(0, 0, 0));
			text = "Your playing time: " + dFormat.format(playTime); 
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + gp.tileSize*4;
			g2.drawString(text, x, y);
			
			
			gp.gameThread = null;
			
			
		}else {
			g2.setFont(font_40);
			g2.setColor(new Color(0, 0, 0));
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawImage(mayonnaiseImage, gp.tileSize/2, gp.tileSize*2, gp.tileSize, gp.tileSize, null);

			g2.drawString("x" + gp.player.hasKey, gp.tileSize*3/2, gp.tileSize*5/4);
			g2.drawString("x" + gp.player.hasMayonnaise, gp.tileSize*3/2, gp.tileSize*11/4);
			
			
			//MESSAGE
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize, gp.tileSize*(gp.maxScreenRow/2));
				
				messageCounter++;
				if(messageCounter > 90) {
					messageCounter = 0;
					messageOn = false;
				}
			}
			
			
			
			//TIME
			playTime += (double)1/60;
			int textLength = 4;
			int x = gp.screenWidth/2 - gp.tileSize * (textLength/2);
			int y = gp.tileSize;
			g2.drawString("Time:" + dFormat.format(playTime), x, y);
			

			
		}
		
	}

}
