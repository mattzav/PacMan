package it.unical.game;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import it.unical.object.Coin;
import it.unical.object.Ghost;
import it.unical.object.PacMan;
import it.unical.utility.Constant;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameScreen implements Screen {

	private PacManGame game;
	private PacManWorld world;
	private int loading = 0;
	private boolean isDied;
	private int loading_dead = 0;

	// disegnare
	private SpriteBatch batch;
	private int cont = 0;

	public GameScreen(PacManGame game) {
		this.game = game;
		batch = new SpriteBatch();
		world = new PacManWorld();
		isDied = false;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
			world.updatePlayer(delta);
			if (!isDied) {
				if (Gdx.input.isKeyJustPressed(Keys.UP))
					world.updatePlayerNextDirection(Constant.UP);
				else if (Gdx.input.isKeyJustPressed(Keys.DOWN))
					world.updatePlayerNextDirection(Constant.DOWN);
				else if (Gdx.input.isKeyJustPressed(Keys.LEFT))
					world.updatePlayerNextDirection(Constant.SX);
				else if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
					world.updatePlayerNextDirection(Constant.DX);
				isDied = world.checkPlayerDied();
				world.updateEnemy(delta);
			}

			if (isDied) {
				loading_dead++;
			}
			if (loading_dead >= 60)
				JOptionPane.showMessageDialog(null, "hai perso");
			drawWorld(delta);
		
	}

	private void drawWorld(float delta) {
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(Constant.maze, 0, 0);
		batch.draw(Constant.title, 50, 550);
		batch.draw(Constant.icon, 480, 595);

		PacMan pacman = PacMan.getIstance(1, 1, world);
		for (Coin coin : world.getCoins())
			batch.draw(coin.getImage(delta), coin.getLogic_y() * Constant.box_size,
					400 - coin.getLogic_x() * Constant.box_size);
		batch.draw(pacman.getImage(),
				pacman.getLogic_y() * Constant.box_size + pacman.getInter_box() * pacman.getDirection().y,
				400 - pacman.getLogic_x() * Constant.box_size - pacman.getInter_box() * pacman.getDirection().x);
		for (Ghost ghost : world.getGhosts())
			batch.draw(ghost.getImage(),
					ghost.getLogic_y() * Constant.box_size + ghost.getInter_box() * ghost.getDirection().y,
					400 - ghost.getLogic_x() * Constant.box_size - ghost.getInter_box() * ghost.getDirection().x);
		batch.end();
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
