package it.unical.inputobject;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("raccoltaIstante")
public class RaccoltaIstante {

	@Param(0)
	private int t;
	@Param(1)
	private int x;
	@Param(2)
	private int y;
	
	public RaccoltaIstante() {
		// TODO Auto-generated constructor stub
	}

	public RaccoltaIstante(int t, int x, int y) {
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
