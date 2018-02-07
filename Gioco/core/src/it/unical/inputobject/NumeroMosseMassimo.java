package it.unical.inputobject;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("numeroMosseMassimo")
public class NumeroMosseMassimo {

	@Param(0)
	int distanza;

	public NumeroMosseMassimo() {
	}

	public NumeroMosseMassimo(int distanza) {
		this.distanza=distanza;
	}

	public int getDistanza() {
		return distanza;
	}

	public void setDistanza(int distanza) {
		this.distanza = distanza;
	}

}
