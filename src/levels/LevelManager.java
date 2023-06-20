package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static main.Game.TILES_SIZE;
import main.Game;
import utilz.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;

	public LevelManager(Game game) {
		this.game = game;
		// levelSprite = LoadSave.getPlayerAtlas(LoadSave.LEVEL_SPRITE);
		importOutsideSprites();
		levelOne = new Level(LoadSave.getLevelData());
		System.out.println(Arrays.deepToString(LoadSave.getLevelData()));
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_SPRITE);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
		}

	}

	public void draw(Graphics g) {
		for(int j = 0; j< Game.TILES_IN_HEIGH; j++) {
			for(int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], TILES_SIZE*i, TILES_SIZE*j,TILES_SIZE,TILES_SIZE, null);
			}
		}
	}

	public void update() {

	}
	public Level getCurrentLevel() {
		return levelOne;
				
	}
}
