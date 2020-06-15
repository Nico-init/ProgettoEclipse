package ilRifugio.serverRistorante.dominio;

import ilRifugio.interfacce.dominio.IPietanza;

public class Pietanza extends ElementoMenu implements IPietanza {
	
	private static final long serialVersionUID = -1357810103088675265L;
	
	private CategoriaPietanza categoriaPietanza;

	public Pietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		super(nome,prezzo);
		categoriaPietanza = categoria;
	}
	
	public Pietanza() {
		super();
	}

	public CategoriaPietanza getCategoriaPietanza() {
		return categoriaPietanza;
	}

	@Override
	public void setCategoriaPietanza(CategoriaPietanza categoria) {
		categoriaPietanza = categoria;
	}
	
	public String toString() {
		return super.toString() + ":" + categoriaPietanza;
	}

}
