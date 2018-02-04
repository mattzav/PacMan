package it.unical.inputobject;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("casella")
public class Casella {
	
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	
	public Casella(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Casella() {
		// TODO Auto-generated constructor stub
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
