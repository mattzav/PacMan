package it.unical.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

	private FreeTypeFontGenerator generator1;
	private FreeTypeFontParameter parameterTitle;
	private BitmapFont fontTitle;

	private PacManGame game;
	private SpriteBatch batch;
	private Stage stage;

	public SettingsScreen(PacManGame game) {
		this.game = game;
		generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/CrackMan.TTF"));
		parameterTitle = new FreeTypeFontParameter();
		parameterTitle.size=24;
		parameterTitle.color=Color.YELLOW;
		fontTitle = generator1.generateFont(parameterTitle);

		batch = new SpriteBatch();

		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.bottom();
		mainTable.setLayoutEnabled(false);


		TextButton aiPlays = new TextButton("AI Plays", Constant.skin);
		aiPlays.setColor(Color.WHITE);
		aiPlays.setPosition(290, 45);
		aiPlays.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setAIPlays(true);
				game.swap(Constant.MENUSTATE,null,0);
			}
		});

		TextButton youPlay = new TextButton("You Play", Constant.skin);
		youPlay.setColor(Color.WHITE);
		youPlay.setPosition(100, 45);
		youPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setAIPlays(false);
				game.swap(Constant.MENUSTATE,null,0);
			}
		});

		stage = new Stage();
		stage.addActor(mainTable);
		mainTable.add(aiPlays);
		mainTable.add(youPlay);
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(Constant.starting, 0, 0);
		fontTitle.draw(batch,"WHAT ABOUT YOUR EXPERIENCE?",30,200);
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
