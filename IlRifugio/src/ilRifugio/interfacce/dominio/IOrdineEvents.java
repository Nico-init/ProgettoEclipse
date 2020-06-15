package ilRifugio.interfacce.dominio;

import java.io.Serializable;

public interface IOrdineEvents extends Serializable {
	public void aggiunta(IOrdine ordine);
	public void modifica(IOrdine ordine);
	public void consegna(IOrdine ordine);
}
