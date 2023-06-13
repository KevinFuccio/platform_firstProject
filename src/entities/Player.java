package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] Animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private boolean isMoving = false, attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;

	public Player(float x, float y,int width,int heigh) {
		super(x, y, width, heigh);
		loadAnimations();

	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(Animations[playerAction][aniIndex], (int) x, (int) y,width, heigh, null);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}
	}

	private void setAnimation() {
		int startAni = playerAction;
		if (isMoving) {
			this.playerAction = RUNNING;
		} else {
			this.playerAction = IDLE;
		}

		if (attacking) {
			this.playerAction = ATTACK_1;
		}
		if (startAni != playerAction) {
			resetAniTick();

		}
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;

	}

	private void updatePos() {
		isMoving = false;

		if (left && !right) {
			x -= playerSpeed;
			isMoving = true;
		} else if (right && !left) {
			x += playerSpeed;
			isMoving = true;
		}

		if (up && !down) {
			y -= playerSpeed;
			isMoving = true;
		} else if (down && !up) {
			y += playerSpeed;
			isMoving = true;
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.getPlayerAtlas(LoadSave.PLAYER_SPRITE);
		Animations = new BufferedImage[9][6];
		for (int j = 0; j < Animations.length; j++) {
			for (int i = 0; i < Animations[j].length; i++) {
				Animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
			}

		}
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
