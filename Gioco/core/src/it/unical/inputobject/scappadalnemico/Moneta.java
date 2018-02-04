package it.unical.inputobject.scappadalnemico;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("moneta")
public class Moneta {

	@Param(0)
	private int x;
	@Param(1)
	private int y;
	@Param(2)
	private String type;
	@Param(3)
	private int peso;
	
	
	public Moneta(int x, int y,String type,int peso) {
		super();
		this.x = x;
		this.y = y;
		this.type=type;
		this.peso=peso;
	}
	
	public Moneta() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
	
}
