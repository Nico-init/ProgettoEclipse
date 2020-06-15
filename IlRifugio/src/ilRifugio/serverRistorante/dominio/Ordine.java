package ilRifugio.serverRistorante.dominio;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.gestioneMenu.ControllerMenu;

public class Ordine implements IOrdine {

	private static final long serialVersionUID = -173804405338020071L;
	
	private int copertiAdulti;
	private int copertiBambini;
	private String nomeTavolo;
	private Date dataOra;
	private List<PietanzaOrdinata> pietanze;
	private List<BevandaOrdinata> bevande;

	private static IControllerMenu controllerMenu;
	private double prezzoCopertiAdulti;
	private double prezzoCopertiBambini;
	
	public int getCopertiAdulti() {
		return copertiAdulti;
	}

	public void setCopertiAdulti(int copertiAdulti) {
		this.copertiAdulti = copertiAdulti;
	}

	public int getCopertiBambini() {
		return copertiBambini;
	}

	public void setCopertiBambini(int copertiBambini) {
		this.copertiBambini = copertiBambini;
	}

	public String getNomeTavolo() {
		return nomeTavolo;
	}

	public void setNomeTavolo(String nomeTavolo) {
		this.nomeTavolo = nomeTavolo;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	public Collection<PietanzaOrdinata> getPietanze() {
		return pietanze;
	}

	public void setPietanze(Collection<PietanzaOrdinata> pietanze) {
		this.pietanze = new LinkedList<>();
		for (PietanzaOrdinata po : pietanze)
			this.pietanze.add(po);
	}
	
	public Collection<BevandaOrdinata> getBevande() {
		return bevande;
	}

	public void setBevande(Collection<BevandaOrdinata> bevande) {
		this.bevande = new LinkedList<>();
		for (BevandaOrdinata bo : bevande)
			this.bevande.add(bo);
	}

	public Ordine(int a, int b, String nome) {
		copertiAdulti = a;
		copertiBambini = b;
		nomeTavolo = nome;
		dataOra = new Date();
		pietanze = new LinkedList<>();
		bevande = new LinkedList<>();
		
		if (controllerMenu == null)
			controllerMenu = new ControllerMenu();
		prezzoCopertiAdulti = ((ICoperto)controllerMenu.elencaCoperti().toArray()[0]).getPrezzo();
		prezzoCopertiBambini = ((ICoperto)controllerMenu.elencaCoperti().toArray()[0]).getPrezzo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataOra == null) ? 0 : dataOra.hashCode());
		result = prime * result + ((nomeTavolo == null) ? 0 : nomeTavolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		if (dataOra == null) {
			if (other.dataOra != null)
				return false;
		} else if (!dataEquals(dataOra,other.dataOra))
			return false;
		if (nomeTavolo == null) {
			if (other.nomeTavolo != null)
				return false;
		} else if (!nomeTavolo.equals(other.nomeTavolo))
			return false;
		return true;
	}

	private boolean dataEquals(Date d1, Date d2) {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		return df.format(d1).equals(df.format(d2));
	}

	@Override
	public void aggiungiPietanza(IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna) {
		PietanzaOrdinata nuovaPietanzaOrdinata = new PietanzaOrdinata();
		nuovaPietanzaOrdinata.setPietanza(pietanza);
		nuovaPietanzaOrdinata.setQuantita(quantita);
		nuovaPietanzaOrdinata.setNote(note);
		nuovaPietanzaOrdinata.setOrdineConsegna(ordineConsegna);
		pietanze.add(nuovaPietanzaOrdinata);
	}

	@Override
	public void aggiungiBevanda(IBevanda bevanda, int quantita) {
		BevandaOrdinata nuovaBevandaOrdinata = new BevandaOrdinata();
		nuovaBevandaOrdinata.setBevanda(bevanda);
		nuovaBevandaOrdinata.setQuantita(quantita);
		bevande.add(nuovaBevandaOrdinata);
	}

	// SE SI VUOLE CAMBIARE L'ORDINE DI CONSEGNA BISOGNA RIMUOVERE LA PIETANZA
	// E RIAGGIUNGERLA CON L'ORDINE CORRETTO
	@Override
	public void modificaPietanza(IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna) {
		for (PietanzaOrdinata p : pietanze) {
			if (p.getPietanza().getNome().equals(pietanza.getNome()) &&
					p.getOrdineConsegna().equals(ordineConsegna)) {
				p.setNote(note);
				p.setQuantita(quantita);
				break;
			}
		}
	}

	@Override
	public void modificaBevanda(IBevanda bevanda, int quantita) {
		for (BevandaOrdinata b : bevande) {
			if (b.getBevanda().getNome().equals(bevanda.getNome())) {
				b.setQuantita(quantita);
				break;
			}
		}
	}

	@Override
	public void modificaNomeTavolo(String nome) {
		nomeTavolo = nome;
	}

	@Override
	public void modificaCopertiBambini(int bambini) {
		copertiBambini = bambini;
	}

	@Override
	public void modificaCopertiAdulti(int adulti) {
		copertiAdulti = adulti;
	}

	@Override
	public String dettagliOrdine() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		double totale = 0;
		String result = "";
		result += nomeTavolo + "_";
		result += df.format(dataOra) + "_";
		result += copertiAdulti + "_";
		totale += prezzoCopertiAdulti * copertiAdulti;
		result += copertiBambini;
		totale += prezzoCopertiBambini * copertiBambini;
		
			result += "_pietanze";
			for (PietanzaOrdinata p : pietanze) {
				result += ":" + p.getPietanza().getNome() + "&" + p.getQuantita() + "&" + p.getPietanza().getPrezzo();
				totale += p.getQuantita() * p.getPietanza().getPrezzo();
			}
			result += "_bevande";
			for (BevandaOrdinata b : bevande) {
				result += ":" + b.getBevanda().getNome() + "&" + b.getQuantita() + "&" + b.getBevanda().getPrezzo();
				totale += b.getQuantita() * b.getBevanda().getPrezzo();
			}
			
		result += "_" + totale;
		return result;
	}

	@Override
	public void rimuoviPietanza(IPietanza pietanza, OrdineConsegna ordineConsegna) {
		for (PietanzaOrdinata p : pietanze) {
			if (p.getPietanza().getNome().equals(pietanza.getNome()) &&
					p.getOrdineConsegna().equals(ordineConsegna)) {
				pietanze.remove(p);
				break;
			}
		}
	}

	@Override
	public void rimuoviBevanda(IBevanda bevanda) {
		for (BevandaOrdinata b : bevande) {
			if (b.getBevanda().getNome().equals(bevanda.getNome())) {
				bevande.remove(b);
				break;
			}
		}
	}

}
