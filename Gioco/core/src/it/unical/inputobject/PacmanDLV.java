package it.unical.inputobject;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pacmanDLV")
public class PacmanDLV {

	@Param(0)
	int t;
	@Param(1)
	int x;
	@Param(2)
	int y;
	
	public PacmanDLV() {
		// TODO Auto-generated constructor stub
	}

	public PacmanDLV(int t, int x, int y) {
		super();
		this.t = t;
		this.x = x;
		this.y = y;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
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
	
	
}
