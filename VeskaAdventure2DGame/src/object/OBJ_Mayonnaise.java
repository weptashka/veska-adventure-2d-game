package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Mayonnaise extends SuperObject{
	
	public OBJ_Mayonnaise(){
		
		name = "Mayonnaise";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/mayonnaise.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
