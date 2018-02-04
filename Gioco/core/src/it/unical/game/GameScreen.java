package it.unical.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;

import it.unical.inputobject.PacmanDLV;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.DLV2AnswerSets;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import it.unical.object.Coin;
import it.unical.object.Ghost;
import it.unical.object.PacMan;
import it.unical.provaIA.Position;
import it.unical.utility.Constant;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.CharArray;

public class GameScreen implements Screen {

	private PacManGame game;
	private PacManWorld world;
	private int loading = 0;
	private boolean isDied;
	private int loading_dead = 0;

	// EmbASP integration
	private static Handler handler;
	private static String encondingPath = "encodings/";
	private static String encondingName = "raccogliMonete";
	private InputProgram facts;

	// disegnare
	private SpriteBatch batch;
	private int cont = 0;
	private FreeTypeFontGenerator generator1;
	private FreeTypeFontGenerator generator2;
	private FreeTypeFontParameter parameterTitle;
	private FreeTypeFontParameter parameterPoint;
	private BitmapFont fontTitle;
	private BitmapFont fontPoint;
	private boolean aiPlays;

	public GameScreen(PacManGame game, boolean aiPlays) {

		this.game = game;
		this.aiPlays = aiPlays;
		batch = new SpriteBatch();
		world = new PacManWorld(1);
		isDied = false;
		generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/CrackMan.TTF"));
		generator2 = new FreeTypeFontGenerator(Gdx.files.internal("font/PacFont.ttf"));
		parameterTitle = new FreeTypeFontParameter();
		parameterPoint = new FreeTypeFontParameter();
		parameterTitle.size = 40;
		parameterTitle.color = Color.YELLOW;
		fontTitle = generator2.generateFont(parameterTitle);
		parameterPoint.size = 24;
		parameterPoint.color = Color.LIGHT_GRAY;
		fontPoint = generator1.generateFont(parameterPoint);

		handler = new DesktopHandler(new DLV2DesktopService("dlv2"));
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		world.updatePlayer(delta);
		if (!isDied) {
			if (!aiPlays) {
				if (Gdx.input.isKeyJustPressed(Keys.UP))
					world.updatePlayerNextDirection(Constant.UP);
				else if (Gdx.input.isKeyJustPressed(Keys.DOWN))
					world.updatePlayerNextDirection(Constant.DOWN);
				else if (Gdx.input.isKeyJustPressed(Keys.LEFT))
					world.updatePlayerNextDirection(Constant.SX);
				else if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
					world.updatePlayerNextDirection(Constant.DX);
			} else {
				if (!world.getPacman().hasMoreSteps() && world.getPacman().getInter_box() == 0.f) {

					// valutare se aggiungere programma dlv che dicide quale programma lanciare
					// encondingName=world.getOptimalProgram();
					handler = new DesktopHandler(new DLV2DesktopService("dlv2"));

					facts = world.getInputFacts(encondingName);
					handler.addProgram(facts);

					InputProgram encoding = new ASPInputProgram();
					encoding.addFilesPath("encodings/raccogliMonete");
					handler.addProgram(encoding);
					
					Output output = handler.startSync();
					AnswerSets answerSets = (AnswerSets) output;
					ArrayList<Position> steps = new ArrayList<>(3);
					steps.add(0, null);
					steps.add(1, null);
					steps.add(2, null);
					for (AnswerSet answerSet : answerSets.getAnswersets()) {
						System.out.println("djfdsfi");
						try {
							for (Object obj : answerSet.getAtoms()) {
								if (obj instanceof PacmanDLV) {
									PacmanDLV step = (PacmanDLV) obj;
									if (step.getT() > 0) {
										steps.set(step.getT() - 1, new Position(step.getX(), step.getY()));
									}
								}
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException | InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						world.getPacman().getSteps().addAll(steps);
						break;
					}
				}
			}
			if (world.getPacman().getInter_box() == 0 && !world.getPacman().getSteps().isEmpty()) {

				Position nextStep = world.getPacman().getSteps().remove(0);
				Position pacmanPosition = new Position(world.getPacman().getLogic_x(), world.getPacman().getLogic_y());
				System.out.println(nextStep.getX() + " " + nextStep.getY());
				world.updatePlayerNextDirection(
						new Vector2(nextStep.getX() - pacmanPosition.getX(), nextStep.getY() - pacmanPosition.getY()));
			}
			isDied = world.checkPlayerDied();
			world.updateEnemy(delta);
		}

		if (isDied) {
			loading_dead++;
		}
		if (loading_dead >= 60)
			JOptionPane.showMessageDialog(null, "hai perso");
		drawWorld(delta);
		if (world.isCompleted()) {
			world = new PacManWorld(world.getLevel() + 1);
		}

	}

	private void drawWorld(float delta) {
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.draw(Constant.maze, 0, 0);
		batch.draw(Constant.icon, 495, 0);
		fontTitle.draw(batch, "CRYPTOPACMAN", 25, 525);
		for (Coin coin : world.getCoins())
			batch.draw(coin.getImage(delta), coin.getLogic_y() * Constant.box_size,
					400 - coin.getLogic_x() * Constant.box_size);

		PacMan pacman = world.getPacman();
		batch.draw(pacman.getImage(),
				pacman.getLogic_y() * Constant.box_size + pacman.getInter_box() * pacman.getDirection().y,
				400 - pacman.getLogic_x() * Constant.box_size - pacman.getInter_box() * pacman.getDirection().x);
		for (Ghost ghost : world.getGhosts())
			batch.draw(ghost.getImage(pacman.isSpecial()),
					ghost.getLogic_y() * Constant.box_size + ghost.getInter_box() * ghost.getDirection().y,
					400 - ghost.getLogic_x() * Constant.box_size - ghost.getInter_box() * ghost.getDirection().x);
		fontPoint.draw(batch, "PUNTI:", 400, 425);
		fontPoint.draw(batch, String.valueOf(world.getPoint()), 420, 395);
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
