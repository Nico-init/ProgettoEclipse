package ilRifugio.serverRistorante.dominio;

import java.io.Serializable;

import ilRifugio.interfacce.dominio.IBevanda;

public class BevandaOrdinata implements Serializable {
	
	private static final long serialVersionUID = 6331732972615974478L;
	
	private IBevanda bevanda;
	private int quantita;
	
	public IBevanda getBevanda() {
		return bevanda;
	}
	public void setBevanda(IBevanda bevanda) {
		this.bevanda = bevanda;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
