package ilRifugio.serverRistorante.dominio;

import java.io.Serializable;

import ilRifugio.interfacce.dominio.IPietanza;

public class PietanzaOrdinata implements Serializable {
	
	private static final long serialVersionUID = 1544208464311744660L;
	
	private IPietanza pietanza;
	private int quantita;
	private String note;
	private boolean consegnato;
	private OrdineConsegna ordineConsegna;
	
	public IPietanza getPietanza() {
		return pietanza;
	}

	public void setPietanza(IPietanza pietanza) {
		this.pietanza = pietanza;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setOrdineConsegna(OrdineConsegna ordineConsegna) {
		this.ordineConsegna = ordineConsegna;
	}
	
	public void modificaOrdineConsegna(OrdineConsegna ordine) {
		ordineConsegna = ordine;
	}
	
	public OrdineConsegna getOrdineConsegna() {
		return ordineConsegna;
	}
	
	public boolean isConsegnato() {
		return consegnato;
	}
	
	public void consegnaPietanza() {
		consegnato = true;
	}
	
}
