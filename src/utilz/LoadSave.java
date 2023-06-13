package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	public static final String PLAYER_SPRITE = "pngwing.com.png";
	public static final String LEVEL_SPRITE = "outside_sprites.png";
	public static final String LEVEL_ONE = "level_one_data.png";
	public static BufferedImage getPlayerAtlas(String sprite) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + sprite);
		try {
			img = ImageIO.read(is);
			
			
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return img;
	}
	public static int[][] getLevelData(){
		int[][] lvlData = new int[Game.TILES_IN_HEIGH][Game.TILES_IN_WIDTH];
		BufferedImage img = getPlayerAtlas(LEVEL_ONE);
		for(int j = 0; j<img.getHeight(); j++) {
			for(int i = 0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value =color.getRed();
				if(value >= 48) {					
					value = 0;
				}
				lvlData[j][i] = value;
			}
		}
		return lvlData;
	}
}