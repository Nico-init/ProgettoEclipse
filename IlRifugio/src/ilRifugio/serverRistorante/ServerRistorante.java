package ilRifugio.serverRistorante;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Date;

import ilRifugio.clientCuoco.ControllerPreparazioni;
import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.controller.IControllerPreparazioni;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import ilRifugio.serverRistorante.gestioneAccount.ControllerAccount;
import ilRifugio.serverRistorante.gestioneMenu.ControllerMenu;
import ilRifugio.serverRistorante.gestioneOrdine.ControllerOrdine;


public class ServerRistorante extends UnicastRemoteObject 
	implements IServerRistoranteProva {

	private static final long serialVersionUID = -5214254358931718758L;
	
	private static IControllerOrdine controllerOrdine;
	private static IControllerMenu controllerMenu;
	private static IControllerAccount controllerAccount;
	private static IControllerPreparazioni controllerPreparazioni;

	protected ServerRistorante() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		String registryHost = "localhost";
		String registryPort = "1099";
		String serviceName = "ServerRistorante";
		String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;		
		try{
			ServerRistorante serverRMI = new ServerRistorante();
			Naming.rebind(completeName, serverRMI);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		controllerOrdine = new ControllerOrdine();
		controllerMenu = new ControllerMenu();
		controllerAccount = new ControllerAccount();
		controllerPreparazioni = new ControllerPreparazioni();
		//DbMock.popolaMenu(controllerMenu);
		//DbMock.popolaAccount(controllerAccount);
	}

	
	@Override
	public boolean nuovoOrdine(String nomeTavolo, int copertiA, int copertiB) {
		return controllerOrdine.nuovoOrdine(nomeTavolo, copertiA, copertiB);
	}

	@Override
	public String dettagliOrdine(IOrdine ordine) {
		return controllerOrdine.dettagliOrdine(ordine);
	}

	@Override
	public boolean eliminaOrdine(IOrdine ordine) {
		return controllerOrdine.eliminaOrdine(ordine);
	}

	@Override
	public Collection<IOrdine> elencaOrdini() {
		return controllerOrdine.elencaOrdini();
	}

	@Override
	public boolean ordinaPietanza(String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note,
			OrdineConsegna ordineConsegna) throws RemoteException {
		return controllerOrdine.ordinaPietanza(tavolo, dataOra, pietanza, quantita, note, ordineConsegna);
	}

	@Override
	public boolean ordinaBevanda(String tavolo, Date dataOra, IBevanda bevanda, int quantita) throws RemoteException {
		return controllerOrdine.ordinaBevanda(tavolo, dataOra, bevanda, quantita);
	}

	@Override
	public boolean modificaPietanzaOrdinata(String tavolo, Date dataOra, IPietanza pietanza, int quantita, String note,
			OrdineConsegna ordineConsegna) throws RemoteException {
		return controllerOrdine.modificaPietanzaOrdinata(tavolo, dataOra, pietanza, quantita, note, ordineConsegna);
	}

	@Override
	public boolean modificaBevandaOrdinata(String tavolo, Date dataOra, IBevanda bevanda, int quantita) throws RemoteException {
		return controllerOrdine.modificaBevandaOrdinata(tavolo, dataOra, bevanda, quantita);
	}

	@Override
	public boolean rimuoviPietanza(String tavolo, Date dataOra, IPietanza pietanza, OrdineConsegna ordineConsegna) throws RemoteException {
		return controllerOrdine.rimuoviPietanza(tavolo, dataOra, pietanza, ordineConsegna);
	}

	@Override
	public boolean rimuoviBevanda(String tavolo, Date dataOra, IBevanda bevanda) throws RemoteException {
		return controllerOrdine.rimuoviBevanda(tavolo, dataOra, bevanda);
	}

	@Override
	public void modificaNomeTavolo(String tavolo, Date dataOra, String nome) throws RemoteException {
		controllerOrdine.modificaNomeTavolo(tavolo, dataOra, nome);
	}

	@Override
	public void modificaCopertiBambini(String tavolo, Date dataOra, int bambini) throws RemoteException {
		controllerOrdine.modificaCopertiBambini(tavolo, dataOra, bambini);
	}

	@Override
	public void modificaCopertiAdulti(String tavolo, Date dataOra, int adulti) throws RemoteException {
		controllerOrdine.modificaCopertiAdulti(tavolo, dataOra, adulti);
	}

	@Override
	public boolean inserisciCoperto(String nome, double prezzo) throws RemoteException {
		return controllerMenu.inserisciCoperto(nome, prezzo);
	}

	@Override
	public boolean modificaCoperto(String nome, double prezzo) throws RemoteException {
		return controllerMenu.modificaCoperto(nome, prezzo);
	}

	@Override
	public Collection<ICoperto> elencaCoperti() throws RemoteException {
		return controllerMenu.elencaCoperti();
	}

	@Override
	public boolean aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException {
		return controllerMenu.aggiungiPietanza(nome, prezzo, categoria);
	}

	@Override
	public boolean aggiungiBevanda(String nome, double prezzo) throws RemoteException {
		return controllerMenu.aggiungiBevanda(nome, prezzo);
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException {
		return controllerMenu.modificaPietanza(nome, prezzo, categoria);
	}

	@Override
	public boolean modificaBevanda(String nome, double prezzo) throws RemoteException {
		return controllerMenu.modificaBevanda(nome, prezzo);
	}

	@Override
	public boolean rimuoviElemento(String tipo, String nome) throws RemoteException {
		return controllerMenu.rimuoviElemento(tipo, nome);
	}

	@Override
	public Collection<IPietanza> elencaPietanze() throws RemoteException {
		return controllerMenu.elencaPietanze();
	}

	@Override
	public Collection<IBevanda> elencaBevande() throws RemoteException {
		return controllerMenu.elencaBevande();
	}

	@Override
	public boolean aggiungi(String nome, String username, String password, String ruolo) throws RemoteException {
		return controllerAccount.aggiungi(nome, username, password, ruolo);
	}

	@Override
	public boolean modifica(String nome, String username, String password, String ruolo) throws RemoteException {
		return controllerAccount.modifica(nome, username, password, ruolo);
	}

	@Override
	public boolean rimuovi(String nome) throws RemoteException {
		return controllerAccount.rimuovi(nome);
	}

	@Override
	public String elenca() throws RemoteException {
		return controllerAccount.elenca();
	}

	@Override
	public String elencaPiattiDaPreparare(CategoriaPietanza categoriaPietanza) throws RemoteException {
		return controllerPreparazioni.elencaPiattiDaPreparare(categoriaPietanza);
	}

	@Override
	public boolean segnaPiattoComeConsegnato(PietanzaOrdinata pietanzaOrdinata) throws RemoteException {
		return controllerPreparazioni.segnaPiattoComeConsegnato(pietanzaOrdinata);
	}
	
}
