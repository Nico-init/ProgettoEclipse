package ilRifugio.serverRistorante.dominio;

import ilRifugio.interfacce.dominio.ICoperto;

public class Coperto extends ElementoMenu implements ICoperto {

	private static final long serialVersionUID = 2528910162242162700L;

	public Coperto(String nome, double prezzo) {
		super(nome,prezzo);
	}
	
	public Coperto() {
		super();
	}
	
}
