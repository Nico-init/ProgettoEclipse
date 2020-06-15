package ilRifugio.persistence.dao;

import ilRifugio.persistence.dao.db2.Db2DAOFactory;

public abstract class DAOFactory {

	public static final int DB2 = 0;	
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		case DB2:
			return new Db2DAOFactory();
		default:
			return null;
		}
	}
	
	
	public abstract BevandaDao getBevandaDAO();

	public abstract CopertoDao getCopertoDAO();

	public abstract PietanzaDao getPietanzaDAO();
	
	public abstract LoginDao getLoginDAO();
	
	public abstract AccountDao getAccountDAO();
	
}
