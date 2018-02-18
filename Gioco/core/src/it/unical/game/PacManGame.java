package it.unical.game;

import java.io.IOException;

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
	private ChooseParamScreen chooseParam;
	private ScoreScreen score;
	private boolean aiPlays;

	@Override
	public void create() {
		aiPlays = true;
		menu = new StartScreen(this);
		chooseParam = new ChooseParamScreen(this);
		
		setScreen(menu);
	}

	public void swap(int state, String player, int playerScore,int level) {
		if (state == Constant.MENUSTATE)
			setScreen(menu);
		else if (state == Constant.GAMESTATE) {
			game = new GameScreen(this, aiPlays,chooseParam.getPlayerName());
			setScreen(game);
		} else if (state == Constant.SETTINGSTATE)
			setScreen(chooseParam);
		else if (state == Constant.SCORESTATE) {
			try {
				score = new ScoreScreen(this, player, playerScore,level);
				setScreen(score);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dispose() {
	}

	public void setAIPlays(boolean b) {
		aiPlays = b;
	}
}
