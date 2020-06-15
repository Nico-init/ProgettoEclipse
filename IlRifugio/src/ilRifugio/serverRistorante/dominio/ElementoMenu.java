package ilRifugio.serverRistorante.dominio;

import java.io.Serializable;

public class ElementoMenu implements Serializable {
	
	private static final long serialVersionUID = 7085572454043467684L;
	
	private String nome;
	private double prezzo;
	
	public ElementoMenu() {
		nome = "";
		prezzo = 0;
	}
	
	public ElementoMenu(String n,double p) {
		nome = n;
		prezzo = p;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public String toString() {
		return nome + ":" + prezzo;
	}

}
