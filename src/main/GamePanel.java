package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private int xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] Animations;
	private int aniTick, aniIndex, aniSpeed = 12;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean isMoving = false;

	public GamePanel() {

		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void loadAnimations() {
		Animations = new BufferedImage[9][6];
		for (int j = 0; j < Animations.length; j++) {
			for (int i = 0; i < Animations[j].length; i++) {
				Animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
			}
		}

	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/pngwing.com.png");
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

	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);

	}

	public void setDirection(int direction) {
		this.playerDir = direction;
		isMoving = true;
	}

	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}
	
	private void updatePos() {
		if (isMoving) {
			switch (playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}

	}

	private void setAnimation() {
		if (isMoving) {
			this.playerAction = RUNNING;
		} else {
			this.playerAction = IDLE;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		setAnimation();
		updatePos();
		g.drawImage(Animations[playerAction][aniIndex], xDelta, yDelta, 128, 80, null);

	}

	

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
	}

}
