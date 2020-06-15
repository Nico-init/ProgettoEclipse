package ilRifugio.interfacce.dominio;

import java.io.Serializable;

public interface IBevanda extends Serializable {
	public String getNome();
	public void setNome(String nome);
	public double getPrezzo();
	public void setPrezzo(double prezzo);
}
