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
			controllerLog.aggiungiEntry("ristoratore", "inserisciCopertoAdulto_" + prezzo);
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.inserisciCopertoBambino(prezzo);
			controllerLog.aggiungiEntry("ristoratore", "inserisciCopertoBambino_" + prezzo);
			return true;
		} else
			return false;
	}

	@Override
	public boolean modificaCoperto(String nome, double prezzo) {
		if (nome.equalsIgnoreCase("adulto")) {
			menu.modificaCopertoAdulto(prezzo);
			controllerLog.aggiungiEntry("ristoratore", "modificaCopertoAdulto_" + prezzo);
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.modificaCopertoBambino(prezzo);
			controllerLog.aggiungiEntry("ristoratore", "modificaCopertoBambino_" + prezzo);
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
		controllerLog.aggiungiEntry("ristoratore", "aggiungiPietanza_" + nome + "_" + prezzo);
		return true;
	}

	@Override
	public boolean aggiungiBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome))
				return false;
		}
		menu.aggiungiBevanda(nome, prezzo);
		controllerLog.aggiungiEntry("ristoratore", "aggiungiBevanda_" + nome + "_" + prezzo);
		return true;
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : menu.elencoPietanze()) {
			if (pietanza.getNome().equalsIgnoreCase(nome)) {
				controllerLog.aggiungiEntry("ristoratore", "modificaPietanza_" + nome + "_" + prezzo);
				return menu.modificaPietanza(nome, prezzo, categoria);
			}
		}
		return false;
	}

	@Override
	public boolean modificaBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome)) {
				controllerLog.aggiungiEntry("ristoratore", "modificaBevanda_" + nome + "_" + prezzo);
				return menu.modificaBevanda(nome, prezzo);
			}
		}
		return false;
	}

	@Override
	public boolean rimuoviElemento(String tipo, String nome) {
		if (tipo.equalsIgnoreCase("pietanza")) {
			controllerLog.aggiungiEntry("ristoratore", "rimuoviPietanza_" + nome);
			return menu.rimuoviPietanza(nome);
		} else if (tipo.equalsIgnoreCase("bevanda")) {
			controllerLog.aggiungiEntry("ristoratore", "rimuoviBevanda_" + nome);
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
