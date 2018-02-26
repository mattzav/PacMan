package it.unical.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import it.unical.utility.Constant;

public class SettingsScreen implements Screen {

	private SpriteBatch batch;
	private Stage stage;
	private FreeTypeFontGenerator generator;
	private BitmapFont font;

	private PacManGame game;
	private long start;

	public static boolean musicEnabled;

	public SettingsScreen(PacManGame game) {
		this.game = game;
		start = System.currentTimeMillis();
		musicEnabled = true;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/arcade.ttf"));
		batch = new SpriteBatch();
		FreeTypeFontParameter parameter;
		parameter = new FreeTypeFontParameter();
		parameter.size = 10;
		parameter.color = Color.BLACK;
		font = generator.generateFont(parameter);

		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.bottom();
		mainTable.setLayoutEnabled(false);

		Button menu = new TextButton("Menu", Constant.skin);
		menu.setColor(Color.RED);
		menu.setPosition(410, 20);
		menu.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.swap(Constant.MENUSTATE, null, 0, 0);
			}
		});
		stage = new Stage();
		stage.addActor(mainTable);
		mainTable.add(menu);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage); // Start taking input from the ui
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render(float delta) {
		if (System.currentTimeMillis()>start+30 && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 120 && Gdx.input.getX() <= 320
				&& Gdx.input.getY() >= 290 && Gdx.input.getY() <= 490) {
			musicEnabled = !musicEnabled;
			start=System.currentTimeMillis();
		}
		batch.begin();

		batch.draw(Constant.directionKeys, 120, 310);
		batch.draw(Constant.soundImage, 120, 80);
		

		if(!musicEnabled)
			batch.draw(Constant.notDirection, 120, 80);
			
		font.draw(batch, "CLICK ON PACMAN TO DISABLE SOUND", 80, 80);
		font.draw(batch, "GO UP", 190, 510);
		font.draw(batch, "GO DOWN", 190, 320);
		font.draw(batch, "GO LEFT", 20, 390);
		font.draw(batch, "GO RIGHT", 340, 390);

		batch.end();

		stage.act();
		stage.draw();
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

}
