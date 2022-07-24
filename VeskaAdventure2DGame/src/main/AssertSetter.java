package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssertSetter {

	GamePanel gp;
	
	public AssertSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = gp.tileSize * 78;
		gp.obj[0].worldY = gp.tileSize * 40;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = gp.tileSize * 78;
		gp.obj[1].worldY = gp.tileSize * 42;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = gp.tileSize * 47;
		gp.obj[2].worldY = gp.tileSize * 8;
		
		
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = gp.tileSize * 23;
		gp.obj[3].worldY = gp.tileSize * 22;

		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = gp.tileSize * 75;
		gp.obj[4].worldY = gp.tileSize * 40;
		
		
		
		
		gp.obj[5] = new OBJ_Chest();
		gp.obj[5].worldX = gp.tileSize * 21;
		gp.obj[5].worldY = gp.tileSize * 30;
	}
	
}
