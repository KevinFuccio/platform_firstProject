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
import static main.Game.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {

		mouseInputs = new MouseInputs(this);
		this.game = game;
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGH);
		setPreferredSize(size);

	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);

	}
	public Game getGame() {
		return game;
	}
	

}
