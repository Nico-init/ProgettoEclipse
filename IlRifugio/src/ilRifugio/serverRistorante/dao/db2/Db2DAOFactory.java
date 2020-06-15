package ilRifugio.serverRistorante.dao.db2;


import java.sql.Connection;
import java.sql.DriverManager;

import ilRifugio.serverRistorante.dao.BevandaDao;
import ilRifugio.serverRistorante.dao.CopertoDao;
import ilRifugio.serverRistorante.dao.DAOFactory;

public class Db2DAOFactory extends DAOFactory {

	/**
	 * Name of the class that holds the jdbc driver implementation for the DB2 database
	 */
	public static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	/**
	 * URI of the database to connect to
	 */
	public static final String DBURL = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

	public static final String USERNAME = "00825611";
	public static final String PASSWORD = "Password123";

	// --------------------------------------------

	// static initializer block to load db driver class in memory
	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			System.err.println("HsqldbDAOFactory: failed to load DB2 JDBC driver" + "\n" + e.toString());
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
	public Db2PietanzaDAO getPietanzaDAO() {
		return new Db2PietanzaDAO();
	}
	
}
