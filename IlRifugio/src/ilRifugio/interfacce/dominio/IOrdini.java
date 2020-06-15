package ilRifugio.interfacce.dominio;

import java.util.List;

import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public interface IOrdini {
	
	public List<PietanzaOrdinata> visualizzaPiatti(CategoriaPietanza categoriaPietanza);
	public boolean aggiungiOrdine(IOrdine ordine);
	public boolean rimuoviOrdine(IOrdine ordine);
	public List<IOrdine> elencaOrdini();

}
