package it.unical.object;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

import it.unical.provaIA.FindPath;
import it.unical.provaIA.Position;
import it.unical.utility.Constant;

public class Ghost {

	private int logic_x;
	private int logic_y;
	private int colour;
	private ArrayList<Vector2> next_position;
	private Vector2 direction;
	private float inter_box;
	private boolean is_died;

	public Ghost(int logic_x, int logic_y, int colour) {
		this.logic_x = logic_x;
		this.logic_y = logic_y;
		this.direction = new Vector2(0, 0);
		this.next_position = new ArrayList<Vector2>();
		this.is_died = false;
		this.colour = colour;
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

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction.x = direction.x;
		this.direction.y = direction.y;
	}

	public boolean isIs_died() {
		return is_died;
	}

	public void setIs_died(boolean is_died) {
		this.is_died = is_died;
	}

	public void move_dx() {
	}

	public void move_sx() {
	}

	public void move_up() {
	}

	public void move_down() {
	}

	// public void setNewDirection(PacMan pacman, int[][] is_crossable) {
	//
	// int ghostx = (int) (this.logic_y * Constant.box_size + inter_box *
	// direction.y);
	// int ghosty = (int) (this.logic_x * Constant.box_size + inter_box *
	// direction.x);
	// int playerx = (int) (pacman.getLogic_y() * Constant.box_size +
	// pacman.getInter_box() * pacman.getDirection().y);
	// int playery = (int) (pacman.getLogic_x() * Constant.box_size +
	// pacman.getInter_box() * pacman.getDirection().x);
	//
	// int destination_ghost_x = (int) (logic_x + direction.x);
	// int destination_ghost_y = (int) (logic_y + direction.y);
	//
	// int greedy[] = new int[4];
	// // peso di andare a destra
	// greedy[0] = playerx - ghostx;
	//
	// // peso di andare sopra
	// greedy[1] = ghosty - playery;
	//
	// // peso di andare sinistra
	// greedy[2] = (-playerx + ghostx);
	//
	// // peso di andare sotto
	// greedy[3] = playery - ghosty;
	//
	// boolean direction_found = false;
	// while (!direction_found) {
	//
	// int max_value = greedy[0];
	// int index = 0;
	//
	// for (int i = 1; i < 4; i++) {
	// if (greedy[i] > max_value) {
	// max_value = greedy[i];
	// index = i;
	// }
	// }
	//
	// switch (index) {
	// case 0:
	// if (is_crossable[destination_ghost_x][destination_ghost_y + 1] !=
	// Constant.WALL) {
	// setNextDirection(Constant.DX);
	// direction_found = true;
	// }
	// break;
	// case 1:
	//
	// if (is_crossable[destination_ghost_x - 1][destination_ghost_y] !=
	// Constant.WALL) {
	// setNextDirection(Constant.UP);
	// direction_found = true;
	// }
	// break;
	// case 2:
	//
	// if (is_crossable[destination_ghost_x][destination_ghost_y - 1] !=
	// Constant.WALL) {
	// setNextDirection(Constant.SX);
	// direction_found = true;
	// }
	// break;
	// case 3:
	// if (is_crossable[destination_ghost_x + 1][destination_ghost_y] !=
	// Constant.WALL) {
	// setNextDirection(Constant.DOWN);
	// direction_found = true;
	// }
	// break;
	// default:
	// break;
	// }
	// if (!direction_found)
	// greedy[index] = Integer.MIN_VALUE;
	// }
	//
	// }

	public void update(PacMan pacman, int[][] is_crossable, float delta) {

		if (!(direction.x == 0 && direction.y == 0)) {
			inter_box += 1.5 * delta * 40;
			if (inter_box >= Constant.box_size + 1) {
				inter_box = 0;
				logic_x += direction.x;
				logic_y += direction.y;
				direction.x = 0;
				direction.y = 0;
			}
		}
		if (next_position.size() == 0 && direction.x == 0 && direction.y == 0) {
			ArrayList<Position> pathposition = new ArrayList<Position>();
			Position pacman_position = new Position(pacman.getLogic_x(), pacman.getLogic_y());
			Position ghost_position = new Position(logic_x, logic_y);
			pathposition.add(ghost_position);
			if (!pacman.isSpecial()) {
				FindPath.getInstance().printPath(ghost_position, pacman_position, pathposition,
						FindPath.getInstance().distanza(ghost_position, pacman_position));
				for (int i = 1; i < 4 + colour && i < pathposition.size(); i++)
					next_position.add(new Vector2(pathposition.get(i).getX(), pathposition.get(i).getY()));
			} else {
				next_position.clear();
				float max_distance = Float.MIN_VALUE;
				Vector2 greedy_corners = null;
				Vector2 pacman_vector = new Vector2(pacman.getLogic_x(), pacman.getLogic_y());
				for (int i = 0; i < Constant.corners.length; i++)
					if( pacman_vector.dst(Constant.corners[i]) > max_distance) {
						max_distance=pacman_vector.dst(Constant.corners[i]);
						greedy_corners=Constant.corners[i];
					}
				FindPath.getInstance().printEscapeRoute(ghost_position, new Position((int)greedy_corners.x, (int)greedy_corners.y), pathposition,
						FindPath.getInstance().distanza(ghost_position, pacman_position), FindPath.getInstance().distanza(ghost_position, new Position((int)greedy_corners.x, (int)greedy_corners.y)),pacman_position);
				for (int i = 1; i < 4 + colour && i < pathposition.size(); i++)
					next_position.add(new Vector2(pathposition.get(i).getX(), pathposition.get(i).getY()));
			}
		}
		while (!next_position.isEmpty() && direction.x == 0 && direction.y == 0) {
			Vector2 next = next_position.remove(0);
			direction.x = next.x - logic_x;
			direction.y = next.y - logic_y;
		}
	}

	public float getInter_box() {
		return inter_box;
	}

	public void setInter_box(float inter_box) {
		this.inter_box = inter_box;
	}

	public Texture getImage(boolean pacmanIsSpecial) {

		if (pacmanIsSpecial)
			return Constant.scaryGhost;

		if (direction.x == Constant.UP.x && direction.y == Constant.UP.y)
			return Constant.ghost[colour][3];
		else if (direction.x == Constant.DX.x && direction.y == Constant.DX.y)
			return Constant.ghost[colour][0];
		else if (direction.x == Constant.DOWN.x && direction.y == Constant.DOWN.y)
			return Constant.ghost[colour][1];
		else if (direction.x == Constant.SX.x && direction.y == Constant.SX.y)
			return Constant.ghost[colour][2];
		return Constant.ghost[colour][3];

	}

}
