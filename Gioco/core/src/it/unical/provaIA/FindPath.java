package it.unical.provaIA;

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
						&& Constant.distanza(new Position(start.getX(), start.getY() + 1), end) < start_distance + 4
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
						&& Constant.distanza(new Position(start.getX(), start.getY() - 1), end) < start_distance + 4
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
						&& Constant.distanza(new Position(start.getX() - 1, start.getY()), end) < start_distance + 4
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
						&& Constant.distanza(new Position(start.getX() + 1, start.getY()), end) < start_distance + 4
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
	
	public static void main(String[] args) {
		int [][] is_crossable=new int[21][19];
		for(int i = 0 ;i<21;i++)
			for(int j=0;j<19;j++)
				is_crossable[i][j]=0;
		for(int i=0;i<19;i++) {
			is_crossable[0][i]=-1;
			is_crossable[20][i]=-1;
		}
		for(int i=0;i<21;i++){
			is_crossable[i][0]=-1;
			is_crossable[i][18]=-1;
		}
		is_crossable[2][2]=-1;
		is_crossable[2][3]=-1;
		is_crossable[2][5]=-1;
		is_crossable[2][6]=-1;
		is_crossable[2][7]=-1;
		is_crossable[1][9]=-1;
		is_crossable[2][9]=-1;
		is_crossable[2][11]=-1;
		is_crossable[2][12]=-1;
		is_crossable[2][13]=-1;
		is_crossable[2][15]=-1;	
		is_crossable[2][16]=-1;
		
		is_crossable[4][2]=-1;
		is_crossable[4][3]=-1;
		is_crossable[4][5]=-1;
		is_crossable[4][7]=-1;
		is_crossable[4][8]=-1;
		is_crossable[4][9]=-1;
		is_crossable[4][11]=-1;
		is_crossable[4][10]=-1;
		is_crossable[4][13]=-1;
		
		is_crossable[4][15]=-1;	
		is_crossable[4][16]=-1;
	
		is_crossable[5][5]=-1;
		is_crossable[5][9]=-1;
		is_crossable[5][13]=-1;
		
		is_crossable[6][1]=-1;
		is_crossable[6][2]=-1;
		is_crossable[6][3]=-1;
		is_crossable[6][5]=-1;
		is_crossable[6][6]=-1;
		is_crossable[6][7]=-1;
		is_crossable[6][9]=-1;
		is_crossable[6][11]=-1;	
		is_crossable[6][12]=-1;
		is_crossable[6][13]=-1;
		is_crossable[6][15]=-1;	
		is_crossable[6][16]=-1;
		is_crossable[6][17]=-1;	
		

		is_crossable[7][3]=-1;
		is_crossable[7][5]=-1;
		is_crossable[7][13]=-1;
		is_crossable[7][15]=-1;	
	

		is_crossable[8][1]=-1;
		is_crossable[8][2]=-1;
		is_crossable[8][3]=-1;
		is_crossable[8][5]=-1;
		is_crossable[8][7]=-1;
		is_crossable[8][8]=-1;
		is_crossable[8][10]=-1;
		is_crossable[8][11]=-1;
		is_crossable[8][13]=-1;	
		is_crossable[8][15]=-1;
		is_crossable[8][16]=-1;
		is_crossable[8][17]=-1;	
	

		is_crossable[9][7]=-1;
		is_crossable[9][11]=-1;	

		is_crossable[10][1]=-1;
		is_crossable[10][2]=-1;
		is_crossable[10][3]=-1;
		is_crossable[10][5]=-1;
		is_crossable[10][7]=-1;
		is_crossable[10][8]=-1;
		is_crossable[10][9]=-1;
		is_crossable[10][10]=-1;
		is_crossable[10][11]=-1;
		is_crossable[10][13]=-1;	
		is_crossable[10][15]=-1;
		is_crossable[10][16]=-1;
		is_crossable[10][17]=-1;	
		
		is_crossable[11][3]=-1;
		is_crossable[11][5]=-1;
		is_crossable[11][13]=-1;
		is_crossable[11][15]=-1;
		
		is_crossable[12][1]=-1;
		is_crossable[12][2]=-1;
		is_crossable[12][3]=-1;
		is_crossable[12][5]=-1;
		is_crossable[12][7]=-1;
		is_crossable[12][8]=-1;
		is_crossable[12][9]=-1;
		is_crossable[12][10]=-1;
		is_crossable[12][11]=-1;
		is_crossable[12][13]=-1;	
		is_crossable[12][15]=-1;
		is_crossable[12][16]=-1;
		is_crossable[12][17]=-1;	

		is_crossable[13][9]=-1;
		
		is_crossable[14][2]=-1;
		is_crossable[14][3]=-1;
		is_crossable[14][5]=-1;
		is_crossable[14][6]=-1;
		is_crossable[14][7]=-1;
		is_crossable[14][9]=-1;
		is_crossable[14][11]=-1;
		is_crossable[14][12]=-1;
		is_crossable[14][13]=-1;
		is_crossable[14][15]=-1;	
		is_crossable[14][16]=-1;
		
		is_crossable[15][3]=-1;
		is_crossable[15][15]=-1;
		
		
		is_crossable[16][1]=-1;
		is_crossable[16][3]=-1;
		is_crossable[16][5]=-1;
		is_crossable[16][7]=-1;
		is_crossable[16][8]=-1;
		is_crossable[16][9]=-1;
		is_crossable[16][10]=-1;
		is_crossable[16][11]=-1;
		is_crossable[16][13]=-1;	
		is_crossable[16][15]=-1;
		is_crossable[16][17]=-1;	
		
		is_crossable[17][5]=-1;
		is_crossable[17][9]=-1;
		is_crossable[17][13]=-1;
		
		is_crossable[18][2]=-1;
		is_crossable[18][3]=-1;
		is_crossable[18][4]=-1;
		is_crossable[18][5]=-1;
		is_crossable[18][6]=-1;
		is_crossable[18][7]=-1;
		is_crossable[18][9]=-1;
		is_crossable[18][11]=-1;
		is_crossable[18][12]=-1;
		is_crossable[18][13]=-1;
		is_crossable[18][14]=-1;
		is_crossable[18][15]=-1;
		is_crossable[18][16]=-1;
		
		ArrayList<Position> p = new ArrayList<Position>();
		p.add(new Position(15, 4));
		System.out.println(FindPath.getInstance(is_crossable).printPath(new Position(15,4), new Position(19, 7), p, Constant.distanza(new Position(15, 4),new Position(19, 7))));
		for(Position t:p)
			System.out.println(t);
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
						&& Constant.distanza(new Position(start.getX(), start.getY() + 1), end) < distance_end + 10
						&& Constant.distanza(new Position(start.getX(),start.getY()+1),pacman_position) > 2
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
						&& Constant.distanza(new Position(start.getX(), start.getY() - 1), end) < distance_end + 10
						&& Constant.distanza(new Position(start.getX(),start.getY()-1),pacman_position) > 2
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
						&& Constant.distanza(new Position(start.getX() - 1, start.getY()), end) < distance_end + 10
						&& Constant.distanza(new Position(start.getX()-1,start.getY()),pacman_position) > 2
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
						&& Constant.distanza(new Position(start.getX() + 1, start.getY()), end) < distance_end + 10
						&& Constant.distanza(new Position(start.getX()+1,start.getY()),pacman_position) > 2
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
