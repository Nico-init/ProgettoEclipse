package ilRifugio.serverRistorante.gestioneMenu;

import java.util.Collection;

import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Menu;

public class ControllerMenu implements IControllerMenu {
	
	private Menu menu;	
	
	public ControllerMenu() {
		menu = Menu.getMenuInstance();
	}

	@Override
	public boolean inserisciCoperto(String nome, double prezzo) {
		if (nome.equalsIgnoreCase("adulto")) {
			menu.inserisciCopertoAdulto(prezzo);
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.inserisciCopertoBambino(prezzo);
			return true;
		} else
			return false;
	}

	@Override
	public boolean modificaCoperto(String nome, double prezzo) {
		if (nome.equalsIgnoreCase("adulto")) {
			menu.modificaCopertoAdulto(prezzo);
			return true;
		} else if (nome.equalsIgnoreCase("bambino")) {
			menu.modificaCopertoBambino(prezzo);
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
		return true;
	}

	@Override
	public boolean aggiungiBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome))
				return false;
		}
		menu.aggiungiBevanda(nome, prezzo);
		return true;
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : menu.elencoPietanze()) {
			if (pietanza.getNome().equalsIgnoreCase(nome))
				return menu.modificaPietanza(nome, prezzo, categoria);
		}
		return false;
	}

	@Override
	public boolean modificaBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : menu.elencoBevande()) {
			if (bevanda.getNome().equalsIgnoreCase(nome))
				return menu.modificaBevanda(nome, prezzo);
		}
		return false;
	}

	@Override
	public boolean rimuoviElemento(String tipo, String nome) {
		if (tipo.equalsIgnoreCase("pietanza"))
			return menu.rimuoviPietanza(nome);
		else if (tipo.equalsIgnoreCase("bevanda"))
			return menu.rimuoviBevanda(nome);
		else
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
