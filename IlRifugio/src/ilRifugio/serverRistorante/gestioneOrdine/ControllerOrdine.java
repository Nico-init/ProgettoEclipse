package ilRifugio.serverRistorante.gestioneOrdine;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.util.Collection;

import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.Ordine;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.Ordini;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public class ControllerOrdine implements IControllerOrdine {
	
	private Ordini ordini;	
	
	public ControllerOrdine() {
		ordini = Ordini.getOrdiniInstance();
	}

	@Override
	public boolean nuovoOrdine(String nomeTavolo, int copertiA, int copertiB) {
		IOrdine ordine = new Ordine(copertiA, copertiB, nomeTavolo);
		return ordini.aggiungiOrdine(ordine);
	}

	@Override
	public String dettagliOrdine(IOrdine ordine) {
		return ordine.dettagliOrdine();
	}

	@Override
	public boolean eliminaOrdine(IOrdine ordine) {
		return ordini.rimuoviOrdine(ordine);
	}

	@Override
	public Collection<IOrdine> elencaOrdini() {
		return ordini.elencaOrdini();
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public boolean ordinaPietanza(String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna) {
		if (quantita < 0)
			return false;
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				for (PietanzaOrdinata pietanzaOrdinata : ordine.getPietanze()) {
					if (pietanzaOrdinata.getPietanza().getNome().equals(pietanza.getNome()) &&
								pietanzaOrdinata.getOrdineConsegna() == ordineConsegna)
							return modificaPietanzaOrdinata(tavolo, dataOra, pietanza, quantita, note, ordineConsegna);
				}
				ordine.aggiungiPietanza(pietanza, quantita, note, ordineConsegna);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public boolean ordinaBevanda(String tavolo, Date dataOra, IBevanda bevanda, int quantita) {
		if (quantita < 0)
			return false;
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				for (BevandaOrdinata bevandaOrdinata : ordine.getBevande()) {
					if (bevandaOrdinata.getBevanda().getNome().equals(bevanda.getNome()))
							return modificaBevandaOrdinata(tavolo, dataOra, bevanda, quantita);
				}
				ordine.aggiungiBevanda(bevanda, quantita);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public boolean modificaPietanzaOrdinata(String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note,
			OrdineConsegna ordineConsegna) {
		if (quantita < 0)
			return false;
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.modificaPietanza(pietanza, quantita, note, ordineConsegna);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public boolean modificaBevandaOrdinata(String tavolo, Date dataOra, IBevanda bevanda, int quantita) {
		if (quantita < 0)
			return false;
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.modificaBevanda(bevanda, quantita);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	// AGGIUNTO ORDINE CONSEGNA: LA STESSA PIETANZA POTREBBE ESSERE PRESENTE PIù
	// VOLTE SE SI VUOLE AVERE ORDINI DI CONSEGNA DIVERSI
	@Override
	public boolean rimuoviPietanza(String tavolo, Date dataOra, IPietanza pietanza, OrdineConsegna ordineConsegna) {
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.rimuoviPietanza(pietanza, ordineConsegna);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public boolean rimuoviBevanda(String tavolo, Date dataOra, IBevanda bevanda) {
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.rimuoviBevanda(bevanda);
				return true;
			}
		}
		return false;
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public void modificaNomeTavolo(String tavolo, Date dataOra, String nome) {
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.setNomeTavolo(nome);
				break;
			}
		}		
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public void modificaCopertiBambini(String tavolo, Date dataOra, int bambini) {
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.setCopertiBambini(bambini);
				break;
			}
		}
	}

	// NEL PROGETTO NON C'ERANO RIFERIMENTI ALL'ORDINE !!!
	@Override
	public void modificaCopertiAdulti(String tavolo, Date dataOra, int adulti) {
		for (IOrdine ordine : ordini.elencaOrdini()) {
			if (ordine.getNomeTavolo().equals(tavolo) &&
					dataEquals(ordine.getDataOra(),dataOra)) {
				ordine.setCopertiAdulti(adulti);
				break;
			}
		}
	}
	
	private boolean dataEquals(Date d1, Date d2) {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		return df.format(d1).equals(df.format(d2));
	}

}
