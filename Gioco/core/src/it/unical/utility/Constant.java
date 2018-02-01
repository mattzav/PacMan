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

import it.unical.provaIA.Position;

public class Constant {

	public static Vector2 UP = new Vector2(-1, 0);
	public static Vector2 DOWN = new Vector2(1, 0);
	public static Vector2 SX = new Vector2(0, -1);
	public static Vector2 DX = new Vector2(0, 1);
	public static Vector2 corner_upsx_start_maze = new Vector2(0, 0);
	public static int maze_width = 520;
	public static int maze_height = 580;
	public static float box_size = maze_width / 26;

	public static String path = "/home/matteo/git/PacMan/Gioco/core/assets/img/";

	public static Texture maze = new Texture(path + "maze.png");
	public static Texture coin = new Texture(path + "moneta.png");
	public static Texture starting = new Texture(path + "starting-.jpg");
	public static Texture title = new Texture(path + "image.png");
	public static Texture icon = new Texture(path + "icon.png");

	public static Texture[][] pacman = {
			{ new Texture(path + "pacmanDestra1.png"), new Texture(path + "pacmanDestra2.png") },
			{ new Texture(path + "pacmanSinistra1.png"), new Texture(path + "pacmanSinistra2.png") },
			{ new Texture(path + "pacmanSu1.png"), new Texture(path + "pacmanSu2.png") },
			{ new Texture(path + "pacmanGiu1.png"), new Texture(path + "pacmanGiu2.png") } };

	public static Texture[] pacman_died = { new Texture(path + "Morte1.png"), new Texture(path + "Morte2.png"),
			new Texture(path + "Morte3.png"), new Texture(path + "Morte4.png"), new Texture(path + "Morte5.png"),
			new Texture(path + "Morte6.png"), new Texture(path + "Morte7.png"), new Texture(path + "Morte8.png"),
			new Texture(path + "Morte9.png") };

	public static Texture[] coin_image = { new Texture(path + "moneta1.png"), new Texture(path + "moneta2.png"),
			new Texture(path + "moneta3.png"), new Texture(path + "moneta4.png"), new Texture(path + "moneta5.png"),
			new Texture(path + "moneta6.png") };

	public static Texture scaryGhost = new Texture(path + "scaryGhost.png");
	public static Texture[][] ghost = {
			{ new Texture(path + "ArancioDestra.png"), new Texture(path + "ArancioGiu.png"),
					new Texture(path + "ArancioSinistra.png"), new Texture(path + "ArancioSu.png") },
			{ new Texture(path + "CelesteDestra.png"), new Texture(path + "CelesteGiu.png"),
					new Texture(path + "CelesteSinistra.png"), new Texture(path + "CelesteSu.png") },
			{ new Texture(path + "rosaDestra.png"), new Texture(path + "rosaGiu.png"),
					new Texture(path + "rosaSinistra.png"), new Texture(path + "rosaSu.png") },
			{ new Texture(path + "rossoDestra.png"), new Texture(path + "rossoGiu.png"),
					new Texture(path + "rossoSinistra.png"), new Texture(path + "rossoSu.png") } };
	public static Music pacman_dead = Gdx.audio.newMusic(Gdx.files.internal("music/dead.mp3"));
	public static Music intro = Gdx.audio.newMusic(Gdx.files.internal("music/intro.mp3"));
	public static Music pacman_pick_money = Gdx.audio.newMusic(Gdx.files.internal("music/pick.mp3"));
	public static Music ghost_died = Gdx.audio.newMusic(Gdx.files.internal("music/eatghost.mp3"));
	public static Music pacman_eatfruit = Gdx.audio.newMusic(Gdx.files.internal("music/eatfruit.mp3"));

	public static int SPECIALCOIN = 2;
	public static int COIN = 1;
	public static int FREE = 0;
	public static int WALL = -1;
	public static Texture background = new Texture(path + "back.jpg");

	public static TextureAtlas texture = new TextureAtlas(Gdx.files.internal("Skin/arcade-ui.atlas"));
	public static Skin skin = new Skin(Gdx.files.internal("Skin/arcade-ui.json"), texture);

	public static int MENUSTATE = 0;
	public static int GAMESTATE = 1;

	public static Vector2[] corners = { new Vector2(1, 1), new Vector2(1, 17), new Vector2(19, 1),
			new Vector2(19, 17) };

	public static int distanza(Position p1, Position p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	
	

}