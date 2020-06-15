package ilRifugio.interfacce;

import java.io.Serializable;

import ilRifugio.interfacce.dominio.IOrdine;

public interface IOrdineEvents extends Serializable {
	public void aggiunta(IOrdine ordine);
	public void modifca(IOrdine ordine);
	public void consegna(IOrdine ordine);
}
