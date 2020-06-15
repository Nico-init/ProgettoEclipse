package ilRifugio.persistence.dao.db2;


import java.sql.Connection;
import java.sql.DriverManager;

import ilRifugio.persistence.dao.DAOFactory;
import ilRifugio.persistence.dao.LoginDao;
import ilRifugio.persistence.dao.PietanzaDao;
import ilRifugio.persistence.dao.AccountDao;
import ilRifugio.persistence.dao.BevandaDao;
import ilRifugio.persistence.dao.CopertoDao;

public class Db2DAOFactory extends DAOFactory {

	public static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	public static final String DBURL = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

	public static final String USERNAME = "00825611";
	public static final String PASSWORD = "Password123";

	// --------------------------------------------

	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			System.err.println("Failed to load DB2 JDBC driver" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------

	public static Connection createConnection() {
		try {
			return DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		} 
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".createConnection(): failed creating connection" + "\n" + e.toString());
			e.printStackTrace();
			System.err.println("Was the database started? Is the database URL right?");
			return null;
		}
	}
	
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".closeConnection(): failed closing connection" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------

	@Override
	public BevandaDao getBevandaDAO() {
		return new Db2BevandaDAO();
	}

	@Override
	public CopertoDao getCopertoDAO() {
		return new Db2CopertoDAO();
	}

	@Override
	public PietanzaDao getPietanzaDAO() {
		return new Db2PietanzaDAO();
	}


	@Override
	public LoginDao getLoginDAO() {
		return new Db2LoginDao();
	}


	@Override
	public AccountDao getAccountDao() {
		return new Db2AccountDAO();
	}
	
}
