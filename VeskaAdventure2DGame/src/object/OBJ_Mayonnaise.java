package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Mayonnaise extends SuperObject{
	GamePanel gp;
	
	public OBJ_Mayonnaise(GamePanel gp){
		this.gp = gp;
		name = "Mayonnaise";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/mayonnaise.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
