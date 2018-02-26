package it.unical.utility;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unical.ghostAI.Position;

public class Constant {

	public static Vector2 UP = new Vector2(-1, 0);
	public static Vector2 DOWN = new Vector2(1, 0);
	public static Vector2 SX = new Vector2(0, -1);
	public static Vector2 DX = new Vector2(0, 1);
	public static Vector2 corner_upsx_start_maze = new Vector2(0, 0);
	public static int maze_width = 380;
	public static int maze_height = 420;
	public static float box_size = maze_width / 19;

	public static Texture directionKeys = new Texture("img/keys.png");
	public static Texture soundImage = new Texture("img/soundImage.jpg");
	public static Texture notDirection = new Texture("img/linea.png");


	public static Texture maze = new Texture("img/maze.png");
	public static Texture coin = new Texture("img/coin.png");
	public static Texture starting = new Texture("img/starting-.jpg");

	public static Texture[][] pacman = {
			{ new Texture("img/pacmanDestra1.png"), new Texture("img/pacmanDestra2.png") },
			{ new Texture("img/pacmanSinistra1.png"), new Texture("img/pacmanSinistra2.png") },
			{ new Texture("img/pacmanSu1.png"), new Texture("img/pacmanSu2.png") },
			{ new Texture("img/pacmanGiu1.png"), new Texture("img/pacmanGiu2.png") } };

	public static Texture[] pacman_died = { new Texture("img/Morte1.png"), new Texture("img/Morte2.png"),
			new Texture("img/Morte3.png"), new Texture("img/Morte4.png"), new Texture("img/Morte5.png"),
			new Texture("img/Morte6.png"), new Texture("img/Morte7.png"), new Texture("img/Morte8.png"),
			new Texture("img/Morte9.png") };

	public static Texture[] coin_image = { new Texture("img/moneta1.png"), new Texture("img/moneta2.png"),
			new Texture("img/moneta3.png"), new Texture("img/moneta4.png"), new Texture("img/moneta5.png"),
			new Texture("img/moneta6.png") };

	public static Texture scaryGhost = new Texture("img/scaryGhost.png");
	public static Texture[][] ghost = {
			{ new Texture("img/ArancioDestra.png"), new Texture("img/ArancioGiu.png"),
					new Texture("img/ArancioSinistra.png"), new Texture("img/ArancioSu.png") },
			{ new Texture("img/CelesteDestra.png"), new Texture("img/CelesteGiu.png"),
					new Texture("img/CelesteSinistra.png"), new Texture("img/CelesteSu.png") },
			{ new Texture("img/rosaDestra.png"), new Texture("img/rosaGiu.png"),
					new Texture("img/rosaSinistra.png"), new Texture("img/rosaSu.png") },
			{ new Texture("img/rossoDestra.png"), new Texture("img/rossoGiu.png"),
					new Texture("img/rossoSinistra.png"), new Texture("img/rossoSu.png") } };
	
	public static Music pacman_dead = Gdx.audio.newMusic(Gdx.files.internal("music/dead.mp3"));
	public static Music intro = Gdx.audio.newMusic(Gdx.files.internal("music/intro.mp3"));
	public static Music pacman_pick_money = Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
	public static Music ghost_died = Gdx.audio.newMusic(Gdx.files.internal("music/eatghost.mp3"));
	public static Music pacman_eatfruit = Gdx.audio.newMusic(Gdx.files.internal("music/eatfruit.mp3"));

	public static int SPECIALCOIN = 2;
	public static int COIN = 1;
	public static int FREE = 0;
	public static int WALL = -1;

	public static TextureAtlas texture = new TextureAtlas(Gdx.files.internal("Skin/arcade-ui.atlas"));
	public static Skin skin = new Skin(Gdx.files.internal("Skin/arcade-ui.json"), texture);

	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final int SETTINGSTATE = 2;
	public static final int SCORESTATE = 3;
	public static final int INFOSTATE = 4;
	

	public static Vector2[] corners = { new Vector2(1, 1), new Vector2(1, 17), new Vector2(19, 1),
			new Vector2(19, 17) };

	public static int distance(Position p1, Position p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	
	

}