package ilRifugio.serverRistorante.dominio;

import ilRifugio.interfacce.dominio.IBevanda;

public class Bevanda extends ElementoMenu implements IBevanda {

	private static final long serialVersionUID = -7562144816761449301L;

	public Bevanda(String nome, double prezzo) {
		super(nome,prezzo);
	}

	public Bevanda() {
		super();
	}

}
