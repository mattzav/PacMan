package it.unical.ghostAI;

public class Position {

	private int x;
	private int y;
	
	public Position() {
		super();
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Position objw=(Position)(obj);
		return this.x==objw.x && this.y==objw.y;
	}

	@Override
	public String toString() {
		return x+" "+y;
	}
	
}
