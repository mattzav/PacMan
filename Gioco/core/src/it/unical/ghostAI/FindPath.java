package it.unical.ghostAI;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import it.unical.utility.Constant;

public class FindPath {

	private static FindPath instance;
	private int is_crossable[][];
	
	public static FindPath getInstance(int [][] is_crossable) {
		if(instance == null)
			instance=new FindPath(is_crossable);
		return instance;
	}
	public static FindPath getInstance() {
		if(instance==null)
			throw new RuntimeException("non ancora creato il risolutore");
		return instance;
	}
	
	private FindPath() {
	}
	
	private FindPath(int [][] is_crossable) {
		this.is_crossable=is_crossable;
	}

	public boolean printPath(Position start, Position end, ArrayList<Position> path, int start_distance) {
	
		
		int greedy[] = new int[4];
		String dir_greedy[] = { "destra", "sopra", "sinistra", "sotto" };
		
		// peso di andare a destra
		greedy[0] = end.getY() - start.getY();

		// peso di andare sopra
		greedy[1] = start.getX() - end.getX();

		// peso di andare sinistra
		greedy[2] = -(end.getY() - start.getY());

		// peso di andare sotto
		greedy[3] = -(start.getX() - end.getX());

		boolean found = true;
		while (found) {

			found = false;
			for (int i = 0; i < 3; i++)
				if (greedy[i] < greedy[i + 1]) {

					found = true;

					int tmp = greedy[i];
					greedy[i] = greedy[i + 1];
					greedy[i + 1] = tmp;

					String str_tmp = dir_greedy[i];
					dir_greedy[i] = dir_greedy[i + 1];
					dir_greedy[i + 1] = str_tmp;
				}

		}
	
		for (int i = 0; i < 4; i++) {
			
			// destra
			if (dir_greedy[i] == "destra") {
				
				if (is_crossable[start.getX()][start.getY() + 1] != -1
						&& Constant.distance(new Position(start.getX(), start.getY() + 1), end) < start_distance + 4
						&& !path.contains(new Position(start.getX(), start.getY() + 1))) {

					path.add(new Position(start.getX(), start.getY() + 1));
					
					if (start.getX() == end.getX() && start.getY() + 1 == end.getY())
						return true;
					
					if (printPath(new Position(start.getX(), start.getY() + 1), end, path, start_distance))
						return true;
					
					path.remove(path.size() - 1);
					

				}

			}
			// sinistra
			else if (dir_greedy[i] == "sinistra") {
				
				if (is_crossable[start.getX()][start.getY() - 1] != -1
						&& Constant.distance(new Position(start.getX(), start.getY() - 1), end) < start_distance + 4
						&& !path.contains(new Position(start.getX(), start.getY() - 1))) {

					path.add(new Position(start.getX(), start.getY() - 1));
					
					if (start.getX() == end.getX() && start.getY() - 1 == end.getY())
						return true;
					
					if (printPath(new Position(start.getX(), start.getY() - 1), end, path, start_distance))
						return true;
					
					path.remove(path.size() - 1);
					

				}

			}
			// sopra
			else if (dir_greedy[i] == "sopra") {
				

				if (is_crossable[start.getX() - 1][start.getY()] != -1
						&& Constant.distance(new Position(start.getX() - 1, start.getY()), end) < start_distance + 4
						&& !path.contains(new Position(start.getX() - 1, start.getY()))) {

					path.add(new Position(start.getX() - 1, start.getY()));
					
					if (start.getX() - 1 == end.getX() && start.getY() == end.getY())
						return true;
					
					if (printPath(new Position(start.getX() - 1, start.getY()), end, path, start_distance))
						return true;
					
					path.remove(path.size() - 1);
					

				}
			}

			// sotto
			else {

				if (is_crossable[start.getX() + 1][start.getY()] != -1
						&& Constant.distance(new Position(start.getX() + 1, start.getY()), end) < start_distance + 4
						&& !path.contains(new Position(start.getX() + 1, start.getY()))) {

					path.add(new Position(start.getX() + 1, start.getY()));
					
					if (start.getX() + 1 == end.getX() && start.getY() == end.getY())
						return true;
					
					if (printPath(new Position(start.getX() + 1, start.getY()), end, path, start_distance))
						return true;
					
					path.remove(path.size() - 1);
					

				}
			}
		}
		return false;
	}

	public boolean printEscapeRoute(Position start, Position end, ArrayList<Position> pathposition,
			float distance_pacman,float distance_end, Position pacman_position) {
		
		int greedy[] = new int[4];
		String dir_greedy[] = { "destra", "sopra", "sinistra", "sotto" };
		
		// peso di andare a destra
		greedy[0] = (int) (end.getY() - start.getY());

		// peso di andare sopra
		greedy[1] = (int) (start.getX() - end.getX());

		// peso di andare sinistra
		greedy[2] = (int) -(end.getY() - start.getY());

		// peso di andare sotto
		greedy[3] = (int) -(start.getX() - end.getX());

		boolean found = true;
		while (found) {

			found = false;
			for (int i = 0; i < 3; i++)
				if (greedy[i] < greedy[i + 1]) {

					found = true;

					int tmp = greedy[i];
					greedy[i] = greedy[i + 1];
					greedy[i + 1] = tmp;

					String str_tmp = dir_greedy[i];
					dir_greedy[i] = dir_greedy[i + 1];
					dir_greedy[i + 1] = str_tmp;
				}

		}
	
		
		
		for (int i = 0; i < 4; i++) {
			
			// destra
			if (dir_greedy[i] == "destra") {
				
				if (is_crossable[start.getX()][start.getY() + 1] != -1
						&& Constant.distance(new Position(start.getX(), start.getY() + 1), end) < distance_end + 10
						&& Constant.distance(new Position(start.getX(),start.getY()+1),pacman_position) >= 2
						&& (start.getX() != pacman_position.getX() || start.getY()+1 != pacman_position.getY())
						&& !pathposition.contains(new Position(start.getX(), start.getY() + 1))) {

					pathposition.add(new Position(start.getX(), start.getY() + 1));
					
					if (start.getX() == end.getX() && start.getY() + 1 == end.getY())
						return true;
					
					if (printEscapeRoute(new Position(start.getX(), start.getY() + 1), end, pathposition, distance_pacman,distance_end,pacman_position))
						return true;
					
					pathposition.remove(pathposition.size() - 1);
					

				}

			}
			// sinistra
			else if (dir_greedy[i] == "sinistra") {
				
				if (is_crossable[start.getX()][start.getY() - 1] != -1
						&& Constant.distance(new Position(start.getX(), start.getY() - 1), end) < distance_end + 10
						&& Constant.distance(new Position(start.getX(),start.getY()-1),pacman_position) >= 2
						&& (start.getX() != pacman_position.getX() || start.getY()-1 != pacman_position.getY())
						&& !pathposition.contains(new Position(start.getX(), start.getY() - 1))) {

					pathposition.add(new Position(start.getX(), start.getY() - 1));
					
					if (start.getX() == end.getX() && start.getY() - 1 == end.getY())
						return true;
					
					if (printEscapeRoute(new Position(start.getX(), start.getY() - 1), end, pathposition,distance_pacman, distance_end,pacman_position))
						return true;
					
					pathposition.remove(pathposition.size() - 1);
					

				}

			}
			// sopra
			else if (dir_greedy[i] == "sopra") {
				
				if (is_crossable[start.getX() - 1][start.getY()] != -1
						&& Constant.distance(new Position(start.getX() - 1, start.getY()), end) < distance_end + 10
						&& Constant.distance(new Position(start.getX()-1,start.getY()),pacman_position) >= 2
						&& (start.getX()-1 != pacman_position.getX() || start.getY() != pacman_position.getY())
						&& !pathposition.contains(new Position(start.getX() - 1, start.getY()))) {

					pathposition.add(new Position(start.getX() - 1, start.getY()));
					
					if (start.getX() - 1 == end.getX() && start.getY() == end.getY())
						return true;
					
					if (printEscapeRoute(new Position(start.getX() - 1, start.getY()), end, pathposition,distance_pacman,distance_end, pacman_position))
						return true;
					
					pathposition.remove(pathposition.size() - 1);
					

				}
			}

			// sotto
			else {
				

				if (is_crossable[start.getX() + 1][start.getY()] != -1
						&& Constant.distance(new Position(start.getX() + 1, start.getY()), end) < distance_end + 10
						&& Constant.distance(new Position(start.getX()+1,start.getY()),pacman_position) >= 2
						&& (start.getX()+1 != pacman_position.getX() || start.getY() != pacman_position.getY())
						&& !pathposition.contains(new Position(start.getX() + 1, start.getY()))) {

					pathposition.add(new Position(start.getX() + 1, start.getY()));
					
					if (start.getX() + 1 == end.getX() && start.getY() == end.getY())
						return true;
					
					if (printEscapeRoute(new Position(start.getX() + 1, start.getY()), end, pathposition,distance_pacman,distance_end,pacman_position))
						return true;
					
					pathposition.remove(pathposition.size() - 1);
					

				}
			}
		}
		return false;
		
	}
	
}
