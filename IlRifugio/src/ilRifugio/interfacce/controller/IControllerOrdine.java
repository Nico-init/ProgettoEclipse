package ilRifugio.interfacce.controller;

import java.util.Date;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;

public interface IControllerOrdine extends Remote{
	
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

}
