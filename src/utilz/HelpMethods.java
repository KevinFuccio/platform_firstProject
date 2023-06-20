package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	public static boolean canMoveHere(float x,float y,float width,float heigh,int[][] lvlData){
		if(!isSolid(x, y, lvlData)) {
			if(!isSolid(x+width, y+heigh, lvlData)) {
				if(!isSolid(x+width,y,lvlData)) {
					if(!isSolid(x, y+heigh, lvlData)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isSolid(float x,float y,int[][] lvlData) {
		if(x < 0 || x >= Game.GAME_WIDTH) {
			return true;
		}
		if(y < 0 || y >= Game.GAME_HEIGH) {
			return true;
		}
		float xIndex = x/Game.TILES_SIZE;
		float yIndex = y/Game.TILES_SIZE;
		
		int value = lvlData[(int)yIndex][(int)xIndex];
		if(value >= 48 || value < 0 || value != 11) {
			return true;
		}else return false;
		
	}
	public static float getEnityXPosNextToWall(Rectangle2D.Float hitbox,float xSpeed) {
		int currentTile = (int)(hitbox.x/Game.TILES_SIZE);
		if(xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffSet = (int)(Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffSet -1;
		}else {
			// Left
			return currentTile * Game.TILES_SIZE;
			
		}
	}
	public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y /Game.TILES_SIZE);
		if(airSpeed > 0) {
			// Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffSet = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffSet -1;
		}else {
			// Jumping
			return currentTile * Game.TILES_SIZE;
		}
	}
	public static boolean isEntityOnFloor(Rectangle2D.Float hitbox,int[][]lvlData) {
		if(!isSolid(hitbox.x, hitbox.y + hitbox.height +1, lvlData))
			if(!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height +1, lvlData))
				return false;
		return true;
	}
}
