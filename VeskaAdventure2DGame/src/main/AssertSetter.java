package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Mayonnaise;

public class AssertSetter {

	GamePanel gp;
	
	public AssertSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = gp.tileSize * 78;
		gp.obj[0].worldY = gp.tileSize * 42;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = gp.tileSize * 78;
		gp.obj[1].worldY = gp.tileSize * 44;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = gp.tileSize * 47;
		gp.obj[2].worldY = gp.tileSize * 10;
		
		
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = gp.tileSize * 23;
		gp.obj[3].worldY = gp.tileSize * 24;

		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = gp.tileSize * 75;
		gp.obj[4].worldY = gp.tileSize * 42;
		
		
		
		
		gp.obj[5] = new OBJ_Chest(gp);
		gp.obj[5].worldX = gp.tileSize * 21;
		gp.obj[5].worldY = gp.tileSize * 32;
		
		
		
		
		gp.obj[6] = new OBJ_Boots(gp);
		gp.obj[6].worldX = gp.tileSize * 44;
		gp.obj[6].worldY = gp.tileSize * 12;
		
		
		
		
		gp.obj[7] = new OBJ_Mayonnaise(gp);
		gp.obj[7].worldX = gp.tileSize * 47;
		gp.obj[7].worldY = gp.tileSize * 9;
	}
	
}
