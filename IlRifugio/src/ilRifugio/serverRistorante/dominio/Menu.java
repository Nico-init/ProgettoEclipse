package ilRifugio.serverRistorante.dominio;

import java.util.Collection;
import java.util.LinkedList;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IMenu;
import ilRifugio.interfacce.dominio.IPietanza;

public class Menu implements IMenu {
	
	private ICoperto copertoAdulto,copertoBambino;
	private Collection<IPietanza> pietanze;
	private Collection<IBevanda> bevande;
	
	public Menu() {
		copertoAdulto = new Coperto("adulto",0);
		copertoBambino = new Coperto("bambino",0);
		pietanze = new LinkedList<IPietanza>();
		bevande = new LinkedList<IBevanda>();
	}
	
	private static Menu menuInstance;

	public static Menu getMenuInstance() {
		if (menuInstance == null)
			menuInstance = new Menu();
		return menuInstance;
	}

	@Override
	public void inserisciCopertoAdulto(double prezzo) {
		copertoAdulto.setPrezzo(prezzo);
	}

	@Override
	public void inserisciCopertoBambino(double prezzo) {
		copertoBambino.setPrezzo(prezzo);
	}

	@Override
	public void modificaCopertoAdulto(double prezzo) {
		copertoAdulto.setPrezzo(prezzo);
	}

	@Override
	public void modificaCopertoBambino(double prezzo) {
		copertoBambino.setPrezzo(prezzo);
	}

	@Override
	public Collection<ICoperto> elencaCoperti() {
		Collection<ICoperto> coperti = new LinkedList<>();
		coperti.add(copertoAdulto);
		coperti.add(copertoBambino);
		return coperti;
	}

	@Override
	public void aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		Pietanza pietanza = new Pietanza(nome,prezzo,categoria);
		pietanze.add(pietanza);
	}

	@Override
	public void aggiungiBevanda(String nome, double prezzo) {
		Bevanda bevanda = new Bevanda(nome,prezzo);
		bevande.add(bevanda);
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : pietanze) {
			if (pietanza.getNome().equals(nome)) {
				pietanza.setPrezzo(prezzo);
				pietanza.setCategoriaPietanza(categoria);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modificaBevanda(String nome, double prezzo) {
		for (IBevanda bevanda : bevande) {
			if (bevanda.getNome().equals(nome)) {
				bevanda.setPrezzo(prezzo);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean rimuoviPietanza(String nome) {
		for (IPietanza pietanza : pietanze) {
			if (pietanza.getNome().equals(nome)) {
				pietanze.remove(pietanza);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean rimuoviBevanda(String nome) {
		for (IBevanda bevanda : bevande) {
			if (bevanda.getNome().equals(nome)) {
				bevande.remove(bevanda);
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<IPietanza> elencoPietanze() {
		return pietanze;
	}

	@Override
	public Collection<IBevanda> elencoBevande() {
		return bevande;
	}

}
