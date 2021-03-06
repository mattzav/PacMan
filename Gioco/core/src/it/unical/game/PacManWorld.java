package it.unical.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

import it.unical.ghostAI.FindPath;
import it.unical.ghostAI.Position;
import it.unical.inputobject.Casella;
import it.unical.inputobject.Moneta;
import it.unical.inputobject.NemicoVicino;
import it.unical.inputobject.NumeroMosseMassimo;
import it.unical.inputobject.Pacman;
import it.unical.inputobject.PacmanDLV;
import it.unical.inputobject.RaccoltaIstante;
import it.unical.inputobject.raccoglimonete.MonetaVicina;
import it.unical.inputobject.scappadalnemico.Nemico;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.object.Coin;
import it.unical.object.Ghost;
import it.unical.object.PacMan;
import it.unical.utility.Constant;

public class PacManWorld {

	private ArrayList<Ghost> ghosts;
	private PacMan pacman;
	private ArrayList<Coin> coins; // mettere nella matrice
	private int is_crossable[][];
	private int level;
	private int point;
	private int life;

	public PacManWorld(int level, int point, int life) {

		this.ghosts = new ArrayList<Ghost>();
		this.point = point;
		this.life = life;
		int speed_ghost = 30;

		this.ghosts.add(new Ghost(9, 10, 3, speed_ghost + level % 10));
		this.ghosts.add(new Ghost(9, 9, 1, speed_ghost + level % 10));
		this.ghosts.add(new Ghost(9, 8, 0, speed_ghost + level % 10));
		this.ghosts.add(new Ghost(8, 9, 2, speed_ghost + level % 10));
		Random random = new Random();

		for (int i = 0; i < level / 10; i++) {
			this.ghosts.add(new Ghost(19, 17 - i, random.nextInt(4), speed_ghost + level % 10));
		}
		this.level = level;

		this.coins = new ArrayList<Coin>();
		this.pacman = new PacMan(1, 1, this);
		this.is_crossable = new int[21][19];
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 19; j++)
				is_crossable[i][j] = 0;
		for (int i = 0; i < 19; i++) {
			is_crossable[0][i] = Constant.WALL;
			is_crossable[20][i] = Constant.WALL;
		}
		for (int i = 0; i < 21; i++) {
			if (i == 9)
				continue;
			is_crossable[i][0] = Constant.WALL;
			is_crossable[i][18] = Constant.WALL;
		}
		is_crossable[2][2] = Constant.WALL;
		is_crossable[2][3] = Constant.WALL;
		is_crossable[2][5] = Constant.WALL;
		is_crossable[2][6] = Constant.WALL;
		is_crossable[2][7] = Constant.WALL;
		is_crossable[1][9] = Constant.WALL;
		is_crossable[2][9] = Constant.WALL;
		is_crossable[2][11] = Constant.WALL;
		is_crossable[2][12] = Constant.WALL;
		is_crossable[2][13] = Constant.WALL;
		is_crossable[2][15] = Constant.WALL;
		is_crossable[2][16] = Constant.WALL;

		is_crossable[4][2] = Constant.WALL;
		is_crossable[4][3] = Constant.WALL;
		is_crossable[4][5] = Constant.WALL;
		is_crossable[4][7] = Constant.WALL;
		is_crossable[4][8] = Constant.WALL;
		is_crossable[4][9] = Constant.WALL;
		is_crossable[4][11] = Constant.WALL;
		is_crossable[4][10] = Constant.WALL;
		is_crossable[4][13] = Constant.WALL;

		is_crossable[4][15] = Constant.WALL;
		is_crossable[4][16] = Constant.WALL;

		is_crossable[5][5] = Constant.WALL;
		is_crossable[5][9] = Constant.WALL;
		is_crossable[5][13] = Constant.WALL;

		is_crossable[6][1] = Constant.WALL;
		is_crossable[6][2] = Constant.WALL;
		is_crossable[6][3] = Constant.WALL;
		is_crossable[6][5] = Constant.WALL;
		is_crossable[6][6] = Constant.WALL;
		is_crossable[6][7] = Constant.WALL;
		is_crossable[6][9] = Constant.WALL;
		is_crossable[6][11] = Constant.WALL;
		is_crossable[6][12] = Constant.WALL;
		is_crossable[6][13] = Constant.WALL;
		is_crossable[6][15] = Constant.WALL;
		is_crossable[6][16] = Constant.WALL;
		is_crossable[6][17] = Constant.WALL;

		is_crossable[7][3] = Constant.WALL;
		is_crossable[7][5] = Constant.WALL;
		is_crossable[7][13] = Constant.WALL;
		is_crossable[7][15] = Constant.WALL;

		is_crossable[8][1] = Constant.WALL;
		is_crossable[8][2] = Constant.WALL;
		is_crossable[8][3] = Constant.WALL;
		is_crossable[8][5] = Constant.WALL;
		is_crossable[8][7] = Constant.WALL;
		is_crossable[8][8] = Constant.WALL;
		is_crossable[8][10] = Constant.WALL;
		is_crossable[8][11] = Constant.WALL;
		is_crossable[8][13] = Constant.WALL;
		is_crossable[8][15] = Constant.WALL;
		is_crossable[8][16] = Constant.WALL;
		is_crossable[8][17] = Constant.WALL;

		is_crossable[9][7] = Constant.WALL;
		is_crossable[9][11] = Constant.WALL;

		is_crossable[10][1] = Constant.WALL;
		is_crossable[10][2] = Constant.WALL;
		is_crossable[10][3] = Constant.WALL;
		is_crossable[10][5] = Constant.WALL;
		is_crossable[10][7] = Constant.WALL;
		is_crossable[10][8] = Constant.WALL;
		is_crossable[10][9] = Constant.WALL;
		is_crossable[10][10] = Constant.WALL;
		is_crossable[10][11] = Constant.WALL;
		is_crossable[10][13] = Constant.WALL;
		is_crossable[10][15] = Constant.WALL;
		is_crossable[10][16] = Constant.WALL;
		is_crossable[10][17] = Constant.WALL;

		is_crossable[11][3] = Constant.WALL;
		is_crossable[11][5] = Constant.WALL;
		is_crossable[11][13] = Constant.WALL;
		is_crossable[11][15] = Constant.WALL;

		is_crossable[12][1] = Constant.WALL;
		is_crossable[12][2] = Constant.WALL;
		is_crossable[12][3] = Constant.WALL;
		is_crossable[12][5] = Constant.WALL;
		is_crossable[12][7] = Constant.WALL;
		is_crossable[12][8] = Constant.WALL;
		is_crossable[12][9] = Constant.WALL;
		is_crossable[12][10] = Constant.WALL;
		is_crossable[12][11] = Constant.WALL;
		is_crossable[12][13] = Constant.WALL;
		is_crossable[12][15] = Constant.WALL;
		is_crossable[12][16] = Constant.WALL;
		is_crossable[12][17] = Constant.WALL;

		is_crossable[13][9] = Constant.WALL;

		is_crossable[14][2] = Constant.WALL;
		is_crossable[14][3] = Constant.WALL;
		is_crossable[14][5] = Constant.WALL;
		is_crossable[14][6] = Constant.WALL;
		is_crossable[14][7] = Constant.WALL;
		is_crossable[14][9] = Constant.WALL;
		is_crossable[14][11] = Constant.WALL;
		is_crossable[14][12] = Constant.WALL;
		is_crossable[14][13] = Constant.WALL;
		is_crossable[14][15] = Constant.WALL;
		is_crossable[14][16] = Constant.WALL;

		is_crossable[15][3] = Constant.WALL;
		is_crossable[15][15] = Constant.WALL;

		is_crossable[16][1] = Constant.WALL;
		is_crossable[16][3] = Constant.WALL;
		is_crossable[16][5] = Constant.WALL;
		is_crossable[16][7] = Constant.WALL;
		is_crossable[16][8] = Constant.WALL;
		is_crossable[16][9] = Constant.WALL;
		is_crossable[16][10] = Constant.WALL;
		is_crossable[16][11] = Constant.WALL;
		is_crossable[16][13] = Constant.WALL;
		is_crossable[16][15] = Constant.WALL;
		is_crossable[16][17] = Constant.WALL;

		is_crossable[17][5] = Constant.WALL;
		is_crossable[17][9] = Constant.WALL;
		is_crossable[17][13] = Constant.WALL;

		is_crossable[18][2] = Constant.WALL;
		is_crossable[18][3] = Constant.WALL;
		is_crossable[18][4] = Constant.WALL;
		is_crossable[18][5] = Constant.WALL;
		is_crossable[18][6] = Constant.WALL;
		is_crossable[18][7] = Constant.WALL;
		is_crossable[18][9] = Constant.WALL;
		is_crossable[18][11] = Constant.WALL;
		is_crossable[18][12] = Constant.WALL;
		is_crossable[18][13] = Constant.WALL;
		is_crossable[18][14] = Constant.WALL;
		is_crossable[18][15] = Constant.WALL;
		is_crossable[18][16] = Constant.WALL;

		FindPath.getInstance(is_crossable);

		for (int i = 1; i < 6; i++)
			for (int j = 1; j < 19; j++)
				if (is_crossable[i][j] != Constant.WALL && ((i != 3) || (j != 1 && j != 17))) {
					is_crossable[i][j] = Constant.COIN;
					coins.add(new Coin(i, j, false));
				}
		for (int i = 13; i < 20; i++)
			for (int j = 1; j < 19; j++)
				if (is_crossable[i][j] != Constant.WALL && ((i != 17) || (j != 1 && j != 17))) {
					is_crossable[i][j] = Constant.COIN;
					coins.add(new Coin(i, j, false));
				}

		is_crossable[3][1] = Constant.SPECIALCOIN;
		coins.add(new Coin(3, 1, true));

		is_crossable[3][17] = Constant.SPECIALCOIN;
		coins.add(new Coin(3, 17, true));

		is_crossable[17][1] = Constant.SPECIALCOIN;
		coins.add(new Coin(17, 1, true));

		is_crossable[17][17] = Constant.SPECIALCOIN;
		coins.add(new Coin(17, 17, true));

	}

	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public PacMan getPacman() {
		return pacman;
	}

	public void setPacman(PacMan pacman) {
		this.pacman = pacman;
	}

	public void updateEnemy(float delta) {

		for (Ghost ghost : ghosts)
			ghost.update(pacman, is_crossable, delta);
	}

	public void updatePlayerNextDirection(Vector2 new_direction) {
		pacman.updateNextDirection(new_direction, is_crossable);
	}

	public void updatePlayer(float delta) {
		pacman.update(is_crossable, delta);
	}

	public ArrayList<Coin> getCoins() {
		return coins;
	}

	public void setCoins(ArrayList<Coin> coins) {
		this.coins = coins;
	}

	public void remove_coin(int logic_x, int logic_y) {
		ArrayList<Coin> remove_coin = new ArrayList<Coin>();
		for (Coin coin : coins)
			if (coin.getLogic_x() == logic_x && coin.getLogic_y() == logic_y)
				remove_coin.add(coin);
		coins.removeAll(remove_coin);
	}

	public boolean checkPlayerDied() {
		ArrayList<Ghost> died = new ArrayList<Ghost>();
		for (Ghost ghost : ghosts) {
			if (Math.abs(ghost.getPhysicalPosition().x - pacman.getPhysicalPosition().x)
					+ Math.abs(ghost.getPhysicalPosition().y - pacman.getPhysicalPosition().y) <= 5) {
				// if (ghost.getLogic_x() == pacman.getLogic_x() && ghost.getLogic_y() ==
				// pacman.getLogic_y()) {
				if (pacman.isSpecial()) {
					died.add(ghost);
					point += 300;
					
					if (SettingsScreen.musicEnabled)
						Constant.ghost_died.play();
				
				} else {
					pacman.setDied();
					
					if (SettingsScreen.musicEnabled)
						Constant.pacman_dead.play();
					
					return true;
				}
			}
		}
		ghosts.removeAll(died);
		return false;

	}

	public int getLevel() {
		return level;
	}

	public boolean isCompleted() {
		return coins.size() == 0;
	}

	public void pacmanPicked(int COIN) {
		point += COIN * 50;
	}

	public int getPoint() {
		return point;
	}

	public String getOptimalProgram() {
		boolean changePacman = false;
		Position pacmanPosition = new Position(pacman.getLogic_x(), pacman.getLogic_y());
		if (pacman.isSpecial() && !ghosts.isEmpty()) {

			// cambiare il 200 per variare l 'insistenza nella ricerca del nemico ( se
			// aumenta si arrende prima)
			int distance = (int) pacman.getRemainingTimeSpecial() / (25 * level + 50);

			boolean found = false;
			if (distance >= 7) {
				for (Ghost ghost : ghosts) {
					if (Constant.distance(pacmanPosition,
							new Position(ghost.getLogic_x(), ghost.getLogic_y())) <= distance)
						found = true;
				}
			}
			if (found || distance >= 7)
				return "inseguiNemico";
			changePacman = true;

		}

		if (!pacman.isSpecial() || changePacman) {
			int nearEnemies = 0;
			for (Ghost ghost : ghosts) {
				if (Constant.distance(pacmanPosition, new Position(ghost.getLogic_x(), ghost.getLogic_y())) <= 7)
					nearEnemies++;
			}
			if (nearEnemies >= 1)
				return "scappaDalNemico";
		}
		return "raccogliMonete";
	}

	public InputProgram getInputFacts(String program) {

		InputProgram returnValue = new ASPInputProgram();
		try {
			if (program.equals("raccogliMonete")) {
				returnValue.addObjectInput(new PacmanDLV(0, pacman.getLogic_x(), pacman.getLogic_y()));
				for (int i = 0; i < is_crossable.length; i++)
					for (int j = 0; j < is_crossable[i].length; j++) {
						if ((i == 8 && j == 9) || (i == 9 && j == 2) || (i == 9 && j == 16))
							continue;
						if (Math.abs(i - pacman.getLogic_x()) + Math.abs(j - pacman.getLogic_y()) < 15) {
							if (is_crossable[i][j] == Constant.COIN)
								returnValue.addObjectInput(new Moneta(i, j, "normale"));
							else if (is_crossable[i][j] == Constant.SPECIALCOIN)
								returnValue.addObjectInput(new Moneta(i, j, "speciale"));
							if (is_crossable[i][j] != Constant.WALL)
								returnValue.addObjectInput(new Casella(i, j));
						}
					}

				MonetaVicina nearestCoin = nearestCoin();
				if (nearestCoin != null)
					returnValue.addObjectInput(nearestCoin);
				else
					returnValue.addObjectInput(nearestSpecialCoin());

				NemicoVicino nearestEnemy = nearestEnemy();

				if (nearestEnemy != null)
					returnValue.addObjectInput(nearestEnemy);

				// passiamo un oggetto RaccoltaIstante altrimenti dlv non esegue il mapping
				// tempo 10 perchè è un valore che non influisce sul nostro output
				returnValue.addObjectInput(new RaccoltaIstante(10, 0, 0));

			} else if (program.equals("inseguiNemico")) {
				returnValue.addObjectInput(new PacmanDLV(0, pacman.getLogic_x(), pacman.getLogic_y()));
				for (int i = 0; i < is_crossable.length; i++)
					for (int j = 0; j < is_crossable[i].length; j++) {
						if ((i == 8 && j == 9) || (i == 9 && j == 2) || (i == 9 && j == 16))
							continue;
						if (Math.abs(i - pacman.getLogic_x()) + Math.abs(j - pacman.getLogic_y()) < 10) {

							if (is_crossable[i][j] == Constant.COIN)
								returnValue.addObjectInput(new Moneta(i, j, "normale"));
							else if (is_crossable[i][j] == Constant.SPECIALCOIN)
								returnValue.addObjectInput(new Moneta(i, j, "speciale"));
							if (is_crossable[i][j] != Constant.WALL)
								returnValue.addObjectInput(new Casella(i, j));
						}
					}
				NemicoVicino nearestEnemy = nearestEnemy();
				if (nearestEnemy != null) {
					returnValue.addObjectInput(nearestEnemy);
				}
				returnValue.addObjectInput(new NumeroMosseMassimo(
						Math.min(7, (int) pacman.getRemainingTimeSpecial() / ((25 * (level % 10)) + 50))));

			} else if (program.equals("scappaDalNemico")) {
				returnValue.addObjectInput(new PacmanDLV(0, pacman.getLogic_x(), pacman.getLogic_y()));
				for (int i = 0; i < is_crossable.length; i++)
					for (int j = 0; j < is_crossable[i].length; j++) {
						if ((i == 8 && j == 9) || (i == 9 && j == 2) || (i == 9 && j == 16))
							continue;
						if (Math.abs(i - pacman.getLogic_x()) + Math.abs(j - pacman.getLogic_y()) < 10) {
							if (is_crossable[i][j] == Constant.COIN)
								returnValue.addObjectInput(new Moneta(i, j, "normale"));
							else if (is_crossable[i][j] == Constant.SPECIALCOIN)
								returnValue.addObjectInput(new Moneta(i, j, "speciale"));
							if (is_crossable[i][j] != Constant.WALL)
								returnValue.addObjectInput(new Casella(i, j));
						}
					}
				MonetaVicina nearestSpecialCoin = nearestSpecialCoin();
				if (nearestSpecialCoin != null)
					returnValue.addObjectInput(nearestSpecialCoin);
				else
					returnValue.addObjectInput(nearestCoin());

				addEnemy(returnValue);
			}
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	private void addEnemy(InputProgram returnValue) {

		ArrayList<Ghost> orderedGhosts = new ArrayList<>();
		Position pacmanPosition = new Position(pacman.getLogic_x(), pacman.getLogic_y());
		orderedGhosts.add(ghosts.get(0));
		for (int j = 1; j < ghosts.size(); j++) {
			Ghost ghost = ghosts.get(j);
			int currentDistance = Constant.distance(pacmanPosition,
					new Position(ghost.getLogic_x(), ghost.getLogic_y()));
			int index = orderedGhosts.size();
			for (int i = 0; i < orderedGhosts.size(); i++) {
				Ghost currentGhost = orderedGhosts.get(i);
				if (Constant.distance(pacmanPosition,
						new Position(currentGhost.getLogic_x(), currentGhost.getLogic_y())) < currentDistance) {
					index = i;
					break;
				}
			}
			orderedGhosts.add(index, ghost);
		}
		int i = 0;
		for (Ghost ghost : orderedGhosts) {
			i++;
			try {
				returnValue.addObjectInput(new Nemico(i, 3 + i, ghost.getLogic_x(), ghost.getLogic_y()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private MonetaVicina nearestSpecialCoin() {

		int minDist = Integer.MAX_VALUE;
		Coin nearestCoin = null;
		for (Coin coin : coins) {
			int dist = Constant.distance(new Position(coin.getLogic_x(), coin.getLogic_y()),
					new Position(pacman.getLogic_x(), pacman.getLogic_y()));
			if (dist < minDist && coin.isSpecialCoin()) {
				minDist = dist;
				nearestCoin = coin;
			}
		}
		if (nearestCoin == null)
			return null;
		return new MonetaVicina(nearestCoin.getLogic_x(), nearestCoin.getLogic_y());
	}

	private NemicoVicino nearestEnemy() {
		int minDist = Integer.MAX_VALUE;
		Ghost nearestEnemy = null;
		for (Ghost ghost : ghosts) {
			int dist = Constant.distance(new Position(ghost.getLogic_x(), ghost.getLogic_y()),
					new Position(pacman.getLogic_x(), pacman.getLogic_y()));
			if (dist < minDist) {
				minDist = dist;
				nearestEnemy = ghost;
			}
		}
		if (nearestEnemy != null)
			return new NemicoVicino(nearestEnemy.getLogic_x(), nearestEnemy.getLogic_y());
		return null;
	}

	private MonetaVicina nearestCoin() {

		int minDist = Integer.MAX_VALUE;
		Coin nearestCoin = null;
		for (Coin coin : coins) {
			int dist = Constant.distance(new Position(coin.getLogic_x(), coin.getLogic_y()),
					new Position(pacman.getLogic_x(), pacman.getLogic_y()));
			if (dist < minDist && !coin.isSpecialCoin()) {
				minDist = dist;
				nearestCoin = coin;
			}
		}
		if (nearestCoin == null)
			return null;
		return new MonetaVicina(nearestCoin.getLogic_x(), nearestCoin.getLogic_y());
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
