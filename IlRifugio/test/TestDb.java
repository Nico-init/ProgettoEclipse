import ilRifugio.serverRistorante.dao.BevandaDao;
import ilRifugio.serverRistorante.dao.CopertoDao;
import ilRifugio.serverRistorante.dao.DAOFactory;
import ilRifugio.serverRistorante.dao.PietanzaDao;
import ilRifugio.serverRistorante.dominio.Bevanda;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Coperto;
import ilRifugio.serverRistorante.dominio.Pietanza;

public class TestDb {

	public static void main(String[] args) {
		PietanzaDao pietanzaDao = DAOFactory.getDAOFactory(0).getPietanzaDAO();
		BevandaDao bevandaDao = DAOFactory.getDAOFactory(0).getBevandaDAO();
		CopertoDao copertiDao = DAOFactory.getDAOFactory(0).getCopertoDAO();
		
		pietanzaDao.create( new Pietanza("Tris di affettati", 7, CategoriaPietanza.ANTIPASTO));
		pietanzaDao.create( new Pietanza("Lasagne alla Bolognese", 10, CategoriaPietanza.PRIMO));
		pietanzaDao.create( new Pietanza("Cotoletta alla milanese", 8, CategoriaPietanza.SECONDO));
		pietanzaDao.create( new Pietanza("Tiramisu", 4, CategoriaPietanza.DOLCE));
		
		bevandaDao.create(new Bevanda("CocaCola Lattina", 3.5));
		bevandaDao.create(new Bevanda("Fanta Lattina", 3.5));
		bevandaDao.create(new Bevanda("Sprite Lattina", 3.5));
		
		copertiDao.create(new Coperto("adulto", 2.5));
		copertiDao.create(new Coperto("bambino", 1.5));
		
		System.out.println("fatto");
	}

}
