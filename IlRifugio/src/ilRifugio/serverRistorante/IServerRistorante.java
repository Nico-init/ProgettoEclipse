package ilRifugio.serverRistorante;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Collection;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public interface IServerRistorante extends Remote {
	
	// CONTROLLER MENU
	public boolean inserisciCoperto(String nome, double prezzo) throws RemoteException;
	public boolean modificaCoperto(String nome, double prezzo) throws RemoteException;
	public Collection<ICoperto> elencaCoperti() throws RemoteException;
	public boolean aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException;
	public boolean aggiungiBevanda(String nome, double prezzo) throws RemoteException;
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException;
	public boolean modificaBevanda(String nome, double prezzo) throws RemoteException;
	public boolean rimuoviElemento(String tipo, String nome) throws RemoteException;
	public Collection<IPietanza> elencaPietanze() throws RemoteException;
	public Collection<IBevanda> elencaBevande() throws RemoteException;
	
	// CONTROLLER ORDINE
	public boolean nuovoOrdine(String nomeTavolo, int copertiA, int copertiB) throws RemoteException;
	public String dettagliOrdine(IOrdine ordine) throws RemoteException;
	public boolean eliminaOrdine(IOrdine ordine) throws RemoteException;
	public Collection<IOrdine> elencaOrdini() throws RemoteException;
	public boolean ordinaPietanza (String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna) throws RemoteException;
	public boolean ordinaBevanda (String tavolo, Date dataOra, IBevanda bevanda, int quantita) throws RemoteException;
	public boolean modificaPietanzaOrdinata (String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note, OrdineConsegna ordineConsegna) throws RemoteException;
	public boolean modificaBevandaOrdinata (String tavolo, Date dataOra, IBevanda bevanda, int quantita) throws RemoteException;
	public boolean rimuoviPietanza (String tavolo, Date dataOra, IPietanza pietanza, OrdineConsegna ordineConsegna) throws RemoteException;
	public boolean rimuoviBevanda (String tavolo, Date dataOra, IBevanda bevanda) throws RemoteException;
	public void modificaNomeTavolo (String tavolo, Date dataOra, String nome) throws RemoteException;
	public void modificaCopertiBambini (String tavolo, Date dataOra, int bambini) throws RemoteException;
	public void modificaCopertiAdulti (String tavolo, Date dataOra, int adulti) throws RemoteException;
	
	// CONTROLLER ACCOUNT
	public boolean aggiungi(String nome, String username, String password, String ruolo) throws RemoteException;
	public boolean modifica(String nome, String username, String password, String ruolo) throws RemoteException;
	public boolean rimuovi(String nome) throws RemoteException;
	public String elenca() throws RemoteException;
	
	// CONTROLLER PREPARAZIONI
	public String elencaPiattiDaPreparare(CategoriaPietanza categoriaPietanza) throws RemoteException;
	public boolean segnaPiattoComeConsegnato(PietanzaOrdinata pietanzaOrdinata) throws RemoteException;

	//CONTROLLER LOGIN
	public String autentica(String username, String password) throws RemoteException;
}
