package it.unical.object;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.CharArray;

import it.unical.game.GameScreen;
import it.unical.game.PacManWorld;
import it.unical.game.SettingsScreen;
import it.unical.ghostAI.Position;
import it.unical.utility.Constant;

public class PacMan {

	private boolean isSpecial;
	private long startTimeSpecial;

	private int logic_x;
	private int logic_y;
	private Vector2 next_direction;
	private Vector2 direction;
	private float inter_box;
	private boolean is_died;
	private int speed;
	private float animation;
	private PacManWorld world;

	ArrayList<Position> nextSteps;

	public PacMan(int logic_x, int logic_y, PacManWorld world) {
		this.isSpecial = false;
		this.startTimeSpecial = 0;
		this.animation = 0;
		this.logic_x = logic_x;
		this.logic_y = logic_y;
		this.next_direction = new Vector2(0, 0);
		this.speed = 80;
		inter_box = 0;
		is_died = false;
		direction = new Vector2(0, 0);
		this.world = world;
		nextSteps = new ArrayList<>();
	}

	public long getStartTimeSpecial() {
		return startTimeSpecial;
	}

	public void setStartTimeSpecial(long startTimeSpecial) {
		this.startTimeSpecial = startTimeSpecial;
	}

	public float getInter_box() {
		return inter_box;
	}

	public int getLogic_x() {
		return logic_x;
	}

	public void setLogic_x(int logic_x) {
		this.logic_x = logic_x;
	}

	public int getLogic_y() {
		return logic_y;
	}

	public void setLogic_y(int logic_y) {
		this.logic_y = logic_y;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public Vector2 getPhysicalPosition() {
		return new Vector2(logic_x * 20 + inter_box * direction.x, logic_y * 20 + inter_box * direction.y);
	}

	public void setNextDirection(Vector2 next_direction) {
		this.next_direction.x = next_direction.x;
		this.next_direction.y = next_direction.y;
	}

	public void setDirection(Vector2 direction) {
		this.direction.x = direction.x;
		this.direction.y = direction.y;
	}

	public void update(int[][] is_crossable, float delta) {
		if (GameScreen.aiPlays) {
			if (logic_x == 9 && logic_y == 17 && direction.y == 1) {
				logic_y = 0;
			} else if (logic_x == 9 && logic_y == 1 && direction.y == -1) {
				logic_y = 18;
			} else if (is_crossable[(int) (logic_x + next_direction.x)][(int) (logic_y
					+ next_direction.y)] == Constant.WALL) {
				if (is_crossable[(int) (logic_x + direction.x)][(int) (logic_y + direction.y)] != Constant.WALL) {
				} else {
					next_direction.x = 0;
					next_direction.y = 0;
					direction.x = 0;
					direction.y = 0;
				}
			} else
				setDirection(next_direction);
		}
		if (!is_died) {
			animation += 0.1;
			animation %= 2;
		} else {
			animation += 0.5;
			animation %= 9;
			return;
		}

		if (isSpecial && System.currentTimeMillis() - startTimeSpecial >= 10000) {
			isSpecial = false;
		}
		if (direction.x == 0 && direction.y == 0)
			return;

		inter_box += delta * speed;

		if (inter_box >= Constant.box_size + 2) {

			logic_x += direction.x;
			logic_y += direction.y;
			if (is_crossable[logic_x][logic_y] == Constant.COIN
					|| is_crossable[logic_x][logic_y] == Constant.SPECIALCOIN) {

				if (is_crossable[logic_x][logic_y] == Constant.SPECIALCOIN) {
					isSpecial = true;
					startTimeSpecial = System.currentTimeMillis();
					world.pacmanPicked(Constant.SPECIALCOIN);

					if (SettingsScreen.musicEnabled)
						Constant.pacman_eatfruit.play();
				}

				is_crossable[logic_x][logic_y] = Constant.FREE;
				world.pacmanPicked(Constant.COIN);
				world.remove_coin(logic_x, logic_y);

				if (SettingsScreen.musicEnabled)
					Constant.pacman_pick_money.play();
			}
			inter_box = 0.f;

			if (!GameScreen.aiPlays) {
				if (logic_x == 9 && logic_y == 18 && direction.y == 1) {
					logic_y = 0;
				} else if (logic_x == 9 && logic_y == 0 && direction.y == -1) {
					logic_y = 18;
				} else if (is_crossable[(int) (logic_x + next_direction.x)][(int) (logic_y
						+ next_direction.y)] == Constant.WALL) {
					if (is_crossable[(int) (logic_x + direction.x)][(int) (logic_y + direction.y)] != Constant.WALL) {
					} else {
						next_direction.x = 0;
						next_direction.y = 0;
						direction.x = 0;
						direction.y = 0;
					}
				} else
					setDirection(next_direction);
			}
		}

	}

	public void updateNextDirection(Vector2 new_direction, int[][] is_accessible) {
		setNextDirection(new_direction);
		if (direction.x == 0 && direction.y == 0) {
			if ((new_direction.equals(Constant.DOWN) && is_accessible[logic_x + 1][logic_y] != Constant.WALL)
					|| (new_direction.equals(Constant.UP) && is_accessible[logic_x - 1][logic_y] != Constant.WALL)
					|| (new_direction.equals(Constant.SX) && is_accessible[logic_x][logic_y - 1] != Constant.WALL)
					|| (new_direction.equals(Constant.DX) && is_accessible[logic_x][logic_y + 1] != Constant.WALL))
				setDirection(new_direction);
		}
	}

	public Texture getImage() {
		if (!is_died) {
			int row = 1;
			if (direction.equals(Constant.SX))
				row = 1;
			else if (direction.equals(Constant.DX))
				row = 0;
			else if (direction.equals(Constant.UP))
				row = 2;
			else if (direction.equals(Constant.DOWN))
				row = 3;
			return Constant.pacman[row][(int) animation];
		} else {
			return Constant.pacman_died[(int) animation];
		}

	}

	public void setDied() {
		this.is_died = true;
	}

	public boolean hasMoreSteps() {
		return !nextSteps.isEmpty();
	}

	public ArrayList<Position> getSteps() {
		// TODO Auto-generated method stub
		return nextSteps;
	}

	public long getRemainingTimeSpecial() {
		return startTimeSpecial + 10000 - System.currentTimeMillis();
	}

}
