package ilRifugio.interfacce.controller;

import java.util.Date;
import java.util.Collection;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;

public interface IControllerOrdine {
	
	public boolean nuovoOrdine(String nomeTavolo, int copertiA, int copertiB);
	public String dettagliOrdine(IOrdine ordine);
	public boolean eliminaOrdine(IOrdine ordine);
	public Collection<IOrdine> elencaOrdini();
	public boolean ordinaPietanza (String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna);
	public boolean ordinaBevanda (String tavolo, Date dataOra, IBevanda bevanda, int quantita);
	public boolean modificaPietanzaOrdinata (String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna);
	public boolean modificaBevandaOrdinata (String tavolo, Date dataOra, IBevanda bevanda, int quantita);
	public boolean rimuoviPietanza (String tavolo, Date dataOra, IPietanza pietanza, OrdineConsegna ordineConsegna);
	public boolean rimuoviBevanda (String tavolo, Date dataOra, IBevanda bevanda);
	public void modificaNomeTavolo (String tavolo, Date dataOra, String nome);
	public void modificaCopertiBambini (String tavolo, Date dataOra, int bambini);
	public void modificaCopertiAdulti (String tavolo, Date dataOra, int adulti);

}
