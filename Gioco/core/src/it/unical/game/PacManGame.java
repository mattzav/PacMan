package it.unical.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.unical.object.Ghost;
import it.unical.utility.Constant;

public class PacManGame extends Game {

	private GameScreen game;
	private StartScreen menu;

	@Override
	public void create() {
		menu = new StartScreen(this);
		game = new GameScreen(this,true);
		setScreen(menu);
	}

	public void swap(int state) {
		if (state == Constant.MENUSTATE)
			setScreen(menu);
		else if (state == Constant.GAMESTATE)
			setScreen(game);
	}

	@Override
	public void dispose() {
	}
}
