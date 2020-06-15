package ilRifugio.serverRistorante.dominio;

import java.util.Collection;
import java.util.LinkedList;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IMenu;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.persistence.dao.BevandaDao;
import ilRifugio.persistence.dao.CopertoDao;
import ilRifugio.persistence.dao.DAOFactory;
import ilRifugio.persistence.dao.PietanzaDao;

public class Menu implements IMenu {
	
	private ICoperto copertoAdulto,copertoBambino;
	private Collection<IPietanza> pietanze;
	private Collection<IBevanda> bevande;
	
	private PietanzaDao pietanzaDao;
	private BevandaDao bevandaDao;
	private CopertoDao copertiDao;
	
	public Menu() {
		pietanze = new LinkedList<IPietanza>();
		bevande = new LinkedList<IBevanda>();
		
		pietanzaDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getPietanzaDAO();
		bevandaDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getBevandaDAO();
		copertiDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getCopertoDAO();
		
		for (IPietanza pietanza : pietanzaDao.leggiPietanze())
			pietanze.add(pietanza);
		
		for (IBevanda bevanda : bevandaDao.leggiBevande())
			bevande.add(bevanda);
		
		copertoAdulto = copertiDao.read("adulto");
		if (copertoAdulto == null) {
			copertoAdulto = new Coperto("adulto",0);
			copertiDao.create(copertoAdulto);
		}
		
		copertoBambino = copertiDao.read("bambino");
		if (copertoBambino == null) {
			copertoBambino = new Coperto("bambino",0);
			copertiDao.create(copertoBambino);
		}
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
		copertiDao.create(copertoAdulto);
	}

	@Override
	public void inserisciCopertoBambino(double prezzo) {
		copertoBambino.setPrezzo(prezzo);
		copertiDao.create(copertoBambino);
	}

	@Override
	public void modificaCopertoAdulto(double prezzo) {
		copertoAdulto.setPrezzo(prezzo);
		copertiDao.update(copertoAdulto);
	}

	@Override
	public void modificaCopertoBambino(double prezzo) {
		copertoBambino.setPrezzo(prezzo);
		copertiDao.update(copertoBambino);
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
		pietanzaDao.create(pietanza);
	}

	@Override
	public void aggiungiBevanda(String nome, double prezzo) {
		Bevanda bevanda = new Bevanda(nome,prezzo);
		bevande.add(bevanda);
		bevandaDao.create(bevanda);
	}

	@Override
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) {
		for (IPietanza pietanza : pietanze) {
			if (pietanza.getNome().equals(nome)) {
				pietanza.setPrezzo(prezzo);
				pietanza.setCategoriaPietanza(categoria);
				pietanzaDao.update(pietanza);
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
				bevandaDao.update(bevanda);
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
				pietanzaDao.delete(nome);
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
				bevandaDao.delete(nome);
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
