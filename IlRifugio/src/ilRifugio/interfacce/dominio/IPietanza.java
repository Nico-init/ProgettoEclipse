package ilRifugio.interfacce.dominio;

import java.io.Serializable;

import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public interface IPietanza extends Serializable {
	public String getNome();
	public void setNome(String nome);
	public double getPrezzo();
	public void setPrezzo(double prezzo);
	public CategoriaPietanza getCategoriaPietanza();
	public void setCategoriaPietanza(CategoriaPietanza categoria);
}
