package it.unical.game;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("a")
public class Int {
	@Param(0)
	private int numero;

	public Int() {
	}

	public Int(int numero) {
		this.numero = numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}
}
