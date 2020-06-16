import ilRifugio.persistence.dao.DAOFactory;
import ilRifugio.interfacce.controller.IControllerLogin;
import ilRifugio.persistence.dao.AccountDao;
import ilRifugio.persistence.dao.BevandaDao;
import ilRifugio.persistence.dao.CopertoDao;
import ilRifugio.persistence.dao.PietanzaDao;
import ilRifugio.serverLogin.ControllerLogin;
import ilRifugio.serverRistorante.dominio.Bevanda;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Coperto;
import ilRifugio.serverRistorante.dominio.Pietanza;

public class TestDb {

	public static void main(String[] args) {
		
		/*
		PietanzaDao pietanzaDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getPietanzaDAO();
		BevandaDao bevandaDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getBevandaDAO();
		CopertoDao copertiDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getCopertoDAO();		
		
		try {
			pietanzaDao.dropTable();
		} catch (Exception e) {	}
		
		try {
			bevandaDao.dropTable();
		} catch (Exception e) {	}
		
		try {
			copertiDao.dropTable();
		} catch (Exception e) {	}
		
		pietanzaDao.createTable();
		bevandaDao.createTable();
		copertiDao.createTable();
		
		pietanzaDao.create(new Pietanza("Tris di affettati", 7, CategoriaPietanza.ANTIPASTO));
		pietanzaDao.create(new Pietanza("Lasagne alla Bolognese", 10, CategoriaPietanza.PRIMO));
		pietanzaDao.create(new Pietanza("Cotoletta alla milanese", 8, CategoriaPietanza.SECONDO));
		pietanzaDao.create(new Pietanza("Tiramisu", 4, CategoriaPietanza.DOLCE));
		
		bevandaDao.create(new Bevanda("CocaCola Lattina", 3.5));
		bevandaDao.create(new Bevanda("Fanta Lattina", 3.5));
		bevandaDao.create(new Bevanda("Sprite Lattina", 3.5));
		
		copertiDao.create(new Coperto("adulto", 2.5));
		copertiDao.create(new Coperto("bambino", 1.5));

		System.out.println("Pietanze: " + pietanzaDao.leggiPietanze().size());
		System.out.println("Bevande: " + bevandaDao.leggiBevande().size());
		System.out.println("Coperto adulto: " + copertiDao.read("adulto").getPrezzo());
		System.out.println("Coperto bambino: " + copertiDao.read("bambino").getPrezzo());	
		*/
		
		/*
		IControllerLogin ctrl = new ControllerLogin();
		String result = ctrl.autentica("niccolò_rif", "niccolo");		
		System.out.println(result);
		*/
		
		/*
		AccountDao accountDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getAccountDAO();
		
		try {
			accountDao.dropTable();
		} catch (Exception e) {	}		
		accountDao.createTable();
		
		accountDao.create("niccolò", "niccolò_rif", "niccolo", "ristoratore");
		accountDao.create("daniele", "daniele_rif", "daniele", "cameriere");
		accountDao.create("ruben", "ruben_rif", "ruben", "cameriere");	
		//accountDao.delete("daniele");
		//accountDao.update("ruben", "ruben_rifugio", "rubenruben", "cameriere");
		System.out.println(accountDao.leggiAccount());		
		*/
		
		System.out.println("Fatto");
	}

}
