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
		
		tile = new Tile[10];
		
		getTileImage();
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		loadMap("/maps/map1.txt");
	}
	
	
	public void getTileImage() {
			
			setup(0, "grass", false);
			setup(1, "water", false);
			setup(2, "wall", true);
			setup(3, "tree", true);
			setup(4, "sand", false);
			setup(5, "earth", false);
			setup(6, "fire", true);
			setup(7, "roof", true);
			
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
