package ilRifugio.interfacce.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public interface IOrdine extends Serializable {
	public void aggiungiPietanza(IPietanza pietanza, int quantita, String note,
																OrdineConsegna ordineConsegna);
	public void aggiungiBevanda(IBevanda bevanda, int quantita);
	public void modificaPietanza(IPietanza pietanza, int quantita, String note,
																OrdineConsegna ordineConsegna);
	public void modificaBevanda(IBevanda bevanda, int quantita);
	public void rimuoviPietanza(IPietanza pietanza, OrdineConsegna ordineConsegna);
	public void rimuoviBevanda(IBevanda bevanda);
	public void modificaNomeTavolo(String nome);
	public void modificaCopertiBambini(int bambini);
	public void modificaCopertiAdulti(int adulti);
	public String dettagliOrdine();
	
	public int getCopertiAdulti();
	public void setCopertiAdulti(int copertiAdulti);
	public int getCopertiBambini();
	public void setCopertiBambini(int copertiBambini);
	public String getNomeTavolo();
	public void setNomeTavolo(String nomeTavolo);
	public Date getDataOra();
	public void setDataOra(Date dataOra);
	public Collection<PietanzaOrdinata> getPietanze();
	public void setPietanze(Collection<PietanzaOrdinata> pietanze);
	public Collection<BevandaOrdinata> getBevande();
	public void setBevande(Collection<BevandaOrdinata> bevande);
	
}
