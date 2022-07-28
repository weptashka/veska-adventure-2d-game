package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile; // array of different tiles IMAGES
	public int mapTileNum[][]; //array consist of tiles on THEIR POSITIONS on map 
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[60];
		
		getTileImage();
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		loadMap("/maps/map1.txt");
	}
	
	
	public void getTileImage() {
			
			setup(0, "grass", false);
			setup(1, "water", true);
			setup(2, "wall", true);
			setup(3, "tree", true);
			setup(4, "sand", false);
			setup(5, "earth", false);
			setup(6, "fire", true);
			setup(7, "roof", true);
			setup(8, "roof", true);
			setup(9, "roof", true);
//			setup(10, "grass_00", false);
//			setup(11, "grass_00", false);
//			setup(12, "grass_00", false);
//			setup(13, "grass_00", false);
//			setup(14, "grass_00", false);
//			setup(15, "grass_00", false);
//			setup(16, "grass_00", false);
//			setup(17, "grass_00", false);
//			setup(18, "grass_00", false);
//			setup(19, "grass_00", false);
//			setup(20, "tree_00", false);
//			setup(21, "tree_00", false);
//			setup(22, "tree_00", false);
//			setup(23, "tree_00", false);
//			setup(24, "tree_00", false);
//			setup(25, "tree_00", false);
//			setup(26, "tree_00", false);
//			setup(27, "tree_00", false);
//			setup(28, "tree_00", false);
//			setup(29, "tree_00", false);
//			setup(30, "water_00", false);
//			setup(31, "water_00", false);
//			setup(32, "water_00", false);
//			setup(33, "water_00", false);
//			setup(34, "water_00", false);
//			setup(35, "water_00", false);
//			setup(36, "water_00", false);
//			setup(37, "water_00", false);
//			setup(38, "water_00", false);
//			setup(39, "water_00", false);
//			setup(30, "water_00", false);
//			setup(40, "sand_00", false);
//			setup(41, "sand_00", false);
//			setup(42, "sand_00", false);
//			setup(43, "sand_00", false);
//			setup(44, "sand_00", false);
//			setup(45, "sand_00", false);
//			setup(46, "sand_00", false);
//			setup(47, "sand_00", false);
//			setup(48, "sand_00", false);
//			setup(49, "sand_00", false);


	}
		
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	
	public void loadMap(String filePath) {
		try {
			//read info about map from file
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String[] numbers = line.split(" "); 
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum [col][row] = num;
					
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			
			br.close();
				
		}catch(Exception e) {
				e.printStackTrace();
		}
		
	}
		
	
	
	//worldX, worldY - position n the map
	//screenX, screenY - where on the SCREEN we draw smth
	public void draw(Graphics2D g2) {
		
		// better to read info about tiles from map
				
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			//THAT'S SHOULDN'T BE COMMENTED, but i have some problems with map dissapearing
			
//			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//					worldY  + gp.tileSize> gp.player.worldY - gp.player.screenX &&
//					worldY - gp.tileSize< gp.player.worldX + gp.player.screenX)
			g2.drawImage(tile[tileNum].image, screenX, screenY, null);	
			worldCol ++;
			
			if(worldCol  == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}
	
	
	

}
