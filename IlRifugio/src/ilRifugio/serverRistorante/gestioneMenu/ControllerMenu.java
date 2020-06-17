package ilRifugio.serverRistorante.gestioneMenu;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.ServerRistorante;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Menu;
import ilRifugio.serverRistorante.gestioneLog.ControllerLog;

public class ControllerMenu extends UnicastRemoteObject implements IControllerMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;	
	private static IControllerLog controllerLog;
	
	public ControllerMenu() throws RemoteException {
		menu = Menu.getMenuInstance();
		if (controllerLog == null)
			controllerLog = new ControllerLog();
	}

	@Override
	public boolean inserisciCoperto(String nome, double prezzo) {
		if (nome.equalsIgnoreCase("adulto")) {
			menu.inserisciCopertoAdulto(prezzo);
			try {
				controllerLog.aggiungiEntry("ristoratore", "inserisciCopertoAdulto_" + prezzo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.inserisciCopertoBambino(prezzo);
			try {
				controllerLog.aggiungiEntry("ristoratore", "inserisciCopertoBambino_" + prezzo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	@Override
	public boolean modificaCoperto(String nome, double prezzo) {
		if (nome.equalsIgnoreCase("adulto")) {
			menu.modificaCopertoAdulto(prezzo);
			try {
				controllerLog.aggiungiEntry("ristoratore", "modificaCopertoAdulto_" + prezzo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.modificaCopertoBambino(prezzo);
			try {
				controllerLog.aggiungiEntry("ristoratore", "modificaCopertoBambino_" + prezzo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	@Override
	public Collection<ICoperto> elencaCoperti() {
		return menu.elencaCoperti();
	}

	@Override
	public boolean aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : menu.elencoPietanze()) {
			if (pietanza.getNome().equalsIgnoreCase(nome))
				return false;
		}
		menu.aggiungiPietanza(nome, prezzo, categoria);
		try {
			controllerLog.aggiungiEntry("ristoratore", "aggiungiPietanza_" + nome + "_" + prezzo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean aggiungiBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome))
				return false;
		}
		menu.aggiungiBevanda(nome, prezzo);
		try {
			controllerLog.aggiungiEntry("ristoratore", "aggiungiBevanda_" + nome + "_" + prezzo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : menu.elencoPietanze()) {
			if (pietanza.getNome().equalsIgnoreCase(nome)) {
				try {
					controllerLog.aggiungiEntry("ristoratore", "modificaPietanza_" + nome + "_" + prezzo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return menu.modificaPietanza(nome, prezzo, categoria);
			}
		}
		return false;
	}

	@Override
	public boolean modificaBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome)) {
				try {
					controllerLog.aggiungiEntry("ristoratore", "modificaBevanda_" + nome + "_" + prezzo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return menu.modificaBevanda(nome, prezzo);
			}
		}
		return false;
	}

	@Override
	public boolean rimuoviElemento(String tipo, String nome) {
		if (tipo.equalsIgnoreCase("pietanza")) {
			try {
				controllerLog.aggiungiEntry("ristoratore", "rimuoviPietanza_" + nome);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return menu.rimuoviPietanza(nome);
		} else if (tipo.equalsIgnoreCase("bevanda")) {
			try {
				controllerLog.aggiungiEntry("ristoratore", "rimuoviBevanda_" + nome);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return menu.rimuoviBevanda(nome);
		} else
			return false;
	}

	@Override
	public Collection<IPietanza> elencaPietanze() {
		return menu.elencoPietanze();
	}

	@Override
	public Collection<IBevanda> elencaBevande() {
		return menu.elencoBevande();
	}

}
