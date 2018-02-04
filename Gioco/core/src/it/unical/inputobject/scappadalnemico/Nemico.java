package it.unical.inputobject.scappadalnemico;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("nemico")
public class Nemico {
	
	@Param(0)
	int id;
	@Param(1)
	int peso;
	@Param(2)
	int x;
	@Param(3)
	int y;
	
	public Nemico() {
		// TODO Auto-generated constructor stub
	}
	
	public Nemico(int id, int peso, int x, int y) {
		super();
		this.id = id;
		this.peso = peso;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
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
