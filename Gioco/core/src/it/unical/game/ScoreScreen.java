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

	private int score[];
	private String players[];
	private String playerName;
	private int playerScore;
	private HashMap<Integer, BitmapFont> font_position;
	private Vector2 pacmanDirection;
	private Vector2 pacmanPosition;
	private int colour;
	private float animation;

	private BufferedReader reader;
	private PrintWriter writer;

	public ScoreScreen(PacManGame game, String playerName, int playerScore) throws IOException {
		this.game = game;
		this.colour = 2;
		this.animation = 0;
		generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/arcade.ttf"));

		batch = new SpriteBatch();

		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.bottom();
		mainTable.setLayoutEnabled(false);

		this.playerName = playerName;
		this.playerScore = playerScore;
		this.score = new int[10];
		this.players = new String[10];
		this.font_position = new HashMap();
		this.pacmanDirection = new Vector2(1, 0);
		this.pacmanPosition = new Vector2(15, 495);

		reader = new BufferedReader(new FileReader("assets/bestScore.txt"));

		for (int i = 0; i < 10; i++) {
			String line;
			try {
				line = reader.readLine();
				System.out.println(line);
				players[i] = line.substring(0, line.indexOf(";"));
				score[i] = Integer.valueOf(line.substring(line.indexOf(";") + 1, line.length()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reader.close();

		writer = new PrintWriter(new FileWriter("assets/bestScore.txt"));
		int index = -1;
		for (int i = 0; i < 10; i++) {
			if (score[i] > playerScore)
				continue;
			index = i;
			break;
		}
		if (index != -1) {
			for (int i = 9; i > index; i--) {
				score[i] = score[i - 1];
				players[i] = players[i - 1];
			}
		}

		if (index != -1) {
			score[index] = playerScore;
			players[index] = playerName;
		}
		for (int i = 0; i < 10; i++)
			writer.println(players[i] + ";" + score[i]);
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
		batch.draw(getGhostImage(), pacmanPosition.x + pacmanDirection.x * 40,
				pacmanPosition.y - pacmanDirection.y * 40);
		font_position.get(Integer.valueOf(10)).draw(batch, "THE 10 BEST PLAYERS", 125, 450);
		font_position.get(Integer.valueOf(10)).draw(batch, "HIGH SCORE " + score[0], 145, 495);

		for (int i = 0; i < 10; i++) {
			BitmapFont font = font_position.get(Integer.valueOf(i));
			font.draw(batch, String.valueOf(i + 1) + "ST", 110, 400 - 30 * i);
			if (players[i].equals(playerName) && score[i] == playerScore)
				font.draw(batch, "YOU!", 40, 400 - 30 * i);
			font.draw(batch, players[i], 235, 400 - 30 * i);
			font.draw(batch, String.valueOf(score[i]), 435, 400 - 30 * i);
		}
		batch.end();
	}

	private Texture getGhostImage() {
		int row = 1;
		if (pacmanDirection.equals(Constant.SX))
			row = 3;
		else if (pacmanDirection.equals(Constant.DX))
			row = 1;
		else if (pacmanDirection.equals(Constant.UP))
			row = 2;
		else if (pacmanDirection.equals(Constant.DOWN))
			row = 0;
		return Constant.ghost[colour][row];

	}

	private void updateImage() {
		animation += 0.1;
		animation %= 2;
		if (pacmanPosition.x + pacmanDirection.x * 2 >= 500 || pacmanPosition.y - pacmanDirection.y * 2 >= 500
				|| pacmanPosition.x + pacmanDirection.x * 2 <= 10 || pacmanPosition.y - pacmanDirection.y * 2 <= 10) {
			updateDirection();
			Random random = new Random();
			int startColour = colour;
			while (startColour == colour)
				colour = random.nextInt(4);
		} else {
			pacmanPosition.x += pacmanDirection.x * 2;
			pacmanPosition.y -= pacmanDirection.y * 2;
		}
	}

	private void updateDirection() {
		if (pacmanDirection.x == 0)
			if (pacmanDirection.y == 1) {
				pacmanDirection.y = 0;
				pacmanDirection.x = -1;
			} else {
				pacmanDirection.y = 0;
				pacmanDirection.x = 1;
			}
		else if (pacmanDirection.y == 0)
			if (pacmanDirection.x == 1) {
				pacmanDirection.x = 0;
				pacmanDirection.y = 1;
			} else {
				pacmanDirection.x = 0;
				pacmanDirection.y = -1;
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
