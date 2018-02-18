package it.unical.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import it.unical.object.PacMan;
import it.unical.utility.Constant;

public class ScoreScreen implements Screen {

	private FreeTypeFontGenerator generator1;

	private PacManGame game;
	private SpriteBatch batch;
	private Stage stage;

	private int scores[];
	private String players[];
	private int[] levels;
	private String playerName;
	private int playerScore;
	private int level;
	private HashMap<Integer, BitmapFont> font_position;
	private Vector2 pacmanDirection;
	private Vector2 pacmanPosition;
	private Vector2 ghostDirection;
	private Vector2 ghostPosition;
	private int colour;
	private float animation;

	private BufferedReader reader;
	private PrintWriter writer;

	public ScoreScreen(PacManGame game, String playerName, int playerScore, int level) throws IOException {
		this.game = game;
		this.colour = 2;
		this.animation = 0;
		this.level=level;
		generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/arcade.ttf"));

		batch = new SpriteBatch();

		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.bottom();
		mainTable.setLayoutEnabled(false);

		this.playerName = playerName;
		this.playerScore = playerScore;
		this.scores = new int[10];
		this.players = new String[10];
		this.levels = new int[10];
		this.font_position = new HashMap();
		this.pacmanDirection = new Vector2(1, 0);
		this.pacmanPosition = new Vector2(15, 495);
		this.ghostDirection = new Vector2(1, 0);
		this.ghostPosition = new Vector2(45, 495);

		reader = new BufferedReader(new FileReader("assets/bestScore.txt"));

		for (int i = 0; i < 10; i++) {
			String line;
			try {
				line = reader.readLine();
				players[i] = line.substring(0, line.indexOf(";"));
				scores[i] = Integer.valueOf(line.substring(line.indexOf(";") + 1, line.indexOf(":")));
				levels[i] = Integer.valueOf(line.substring(line.indexOf(":") + 1, line.length()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reader.close();

		writer = new PrintWriter(new FileWriter("assets/bestScore.txt"));
		int index = -1;
		for (int i = 0; i < 10; i++) {
			if (scores[i] > playerScore)
				continue;
			index = i;
			break;
		}
		if (index != -1) {
			for (int i = 9; i > index; i--) {
				scores[i] = scores[i - 1];
				players[i] = players[i - 1];
				levels[i] = levels[i - 1];
			}
		}

		if (index != -1) {
			scores[index] = playerScore;
			players[index] = playerName;
			levels[index] = level;
		}
		for (int i = 0; i < 10; i++)
			writer.println(players[i] + ";" + scores[i]+":"+levels[i]);
		writer.flush();

		FreeTypeFontParameter parameter;
		parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		for (int i = 0; i < 10; i++) {
			parameter.color = getColor(i);
			font_position.put(Integer.valueOf(i), generator1.generateFont(parameter));
		}
		parameter.color = Color.WHITE;
		font_position.put(Integer.valueOf(10), generator1.generateFont(parameter));
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage); // Start taking input from the ui

	}

	@Override
	public void render(float delta) {
		updateImage();

		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(getImage(), pacmanPosition.x, pacmanPosition.y);
		batch.draw(getGhostImage(), ghostPosition.x, ghostPosition.y);
		font_position.get(Integer.valueOf(10)).draw(batch, "THE 10 BEST PLAYERS", 125, 445);
		font_position.get(Integer.valueOf(10)).draw(batch, "HIGH SCORE " + scores[0], 145, 495);

		font_position.get(Integer.valueOf(10)).draw(batch, "RANK", 50, 395);
		font_position.get(Integer.valueOf(10)).draw(batch, "NAME", 165, 395);
		font_position.get(Integer.valueOf(10)).draw(batch, "SCORE", 350, 395);
		font_position.get(Integer.valueOf(10)).draw(batch, "LEVEL", 445, 395);

		
		for (int i = 0; i < 10; i++) {
			BitmapFont font = font_position.get(Integer.valueOf(i));
			font.draw(batch, String.valueOf(i + 1) + "ST", 50, 360 - 30 * i);
			if (players[i].equals(playerName) && scores[i] == playerScore && level==levels[i])
				font.draw(batch, "->", 20, 360 - 30 * i);
			font.draw(batch, players[i], 165, 360 - 30 * i);
			font.draw(batch, String.valueOf(scores[i]), 350, 360 - 30 * i);
			font.draw(batch, String.valueOf(levels[i]), 465, 360 - 30 * i);
		}
		batch.end();
	}

	private Texture getGhostImage() {
		int row = 1;
		if (ghostDirection.equals(Constant.SX))
			row = 3;
		else if (ghostDirection.equals(Constant.DX))
			row = 1;
		else if (ghostDirection.equals(Constant.UP))
			row = 2;
		else if (ghostDirection.equals(Constant.DOWN))
			row = 0;
		return Constant.ghost[colour][row];

	}

	private void updateImage() {
		animation += 0.1;
		animation %= 2;
		if (pacmanPosition.x + pacmanDirection.x * 2 >= 500 || pacmanPosition.y - pacmanDirection.y * 2 >= 500
				|| pacmanPosition.x + pacmanDirection.x * 2 <= 10 || pacmanPosition.y - pacmanDirection.y * 2 <= 10) {
			updateDirection(pacmanDirection);
		} else {
			pacmanPosition.x += pacmanDirection.x * 2;
			pacmanPosition.y -= pacmanDirection.y * 2;
		}

		if (ghostPosition.x + ghostDirection.x * 2 >= 500 || ghostPosition.y - ghostDirection.y * 2 >= 500
				|| ghostPosition.x + ghostDirection.x * 2 <= 10 || ghostPosition.y - ghostDirection.y * 2 <= 10) {
			updateDirection(ghostDirection);
		} else {
			ghostPosition.x += ghostDirection.x * 2;
			ghostPosition.y -= ghostDirection.y * 2;
		}
	}

	private void updateDirection(Vector2 direction) {
		if (direction.x == 0)
			if (direction.y == 1) {
				direction.y = 0;
				direction.x = -1;
			} else {
				direction.y = 0;
				direction.x = 1;
			}
		else if (direction.y == 0)
			if (direction.x == 1) {
				direction.x = 0;
				direction.y = 1;
			} else {
				direction.x = 0;
				direction.y = -1;
			}

	}

	private Color getColor(int i) {
		switch (i) {
		case 0:
			return Color.RED;
		case 1:
			return Color.PINK;
		case 2:
			return Color.SKY;
		case 3:
			return Color.ORANGE;
		case 4:
			return Color.YELLOW;
		case 5:
			return Color.RED;
		case 6:
			return Color.PINK;
		case 7:
			return Color.SKY;
		case 8:
			return Color.ORANGE;
		case 9:
			return Color.YELLOW;
		default:
			return null;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Texture getImage() {
		int row = 1;
		if (pacmanDirection.equals(Constant.SX))
			row = 2;
		else if (pacmanDirection.equals(Constant.DX))
			row = 3;
		else if (pacmanDirection.equals(Constant.UP))
			row = 1;
		else if (pacmanDirection.equals(Constant.DOWN))
			row = 0;
		return Constant.pacman[row][(int) animation];
	}

}
