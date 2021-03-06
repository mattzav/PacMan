package it.unical.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import it.unical.utility.Constant;

public class StartScreen implements Screen{
	
	private PacManGame game;
	private SpriteBatch batch;
	private Stage stage;
	
	public StartScreen(final PacManGame game) {
		Constant.intro.play();
		this.game=game;
		batch = new SpriteBatch();
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.bottom();
		mainTable.setLayoutEnabled(false);
		
		Button start= new TextButton("Start", Constant.skin);
		start.setColor(Color.RED);
		start.setPosition(210, 185);
		start.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				game.swap(Constant.SETTINGSTATE,null,0,0);
			}
		});
	
		
		TextButton settings= new TextButton("Settings", Constant.skin);
		settings.setColor(Color.RED);
		settings.setPosition(190, 75);
		settings.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	        	game.swap(Constant.INFOSTATE, null,0, 0);
	        }
	    });
		
		stage = new Stage();
		stage.addActor(mainTable);
		mainTable.add(start);
		mainTable.add(settings);
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
		
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(Constant.starting, 0, 0);
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
