package ilRifugio.persistence.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import ilRifugio.persistence.dao.AccountDao;

public class Db2AccountDAO implements AccountDao {

	@Override
	public boolean aggiungiAccount(String username, String nome, String password, String ruolo) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean res = false;

		if (username != null && nome != null && password != null && ruolo != null) {
			try {
				statement = con.prepareStatement("insert into password  (password) values (?)");
				statement.setString(1, password);
				statement.executeUpdate();
				statement.close();
				
				statement = con.prepareStatement("SELECT max(IDPASSWORD ) FROM PASSWORD");
				ResultSet result = statement.executeQuery();
				int idPassword = result.getInt(1);		//idPassword che e' stato scelto dal db
				statement.close();
				
				statement = con.prepareStatement("insert into username (username, nome, ruolo, idpassword) values (?, ?, ?, ?)");
				statement.setString(1, username);
				statement.setString(1, nome);
				statement.setString(1, ruolo);
				statement.setInt(1, idPassword);
				statement.executeUpdate();
				statement.close();
				
				res = true;				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}

					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	@Override
	public boolean rimuoviAccount(String username) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (username != null) {
			try {
				statement = con.prepareStatement("select idPassword from utenti where username = ?");
				statement.setString(1, username);
				ResultSet res = statement.executeQuery();
				int idPassword = res.getInt(1);
				statement.close();
				
				//eliminazione password
				statement = con.prepareStatement("delete from password where idpassword = ?");
				statement.setInt(1, idPassword);
				statement.executeUpdate();
				statement.close();
				
				//eliminazione utente
				statement = con.prepareStatement("delete from utenti where username = ?");
				statement.setString(1, username);
				
				if(statement.executeUpdate() == 1) 
					result = true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}

					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public boolean modificaAccount(String username, String nome, String password, String ruolo) {
		rimuoviAccount(username);
		return aggiungiAccount(username, nome, password, ruolo);
	}

	@Override
	public List<String> elencaAccount() {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<String> accounts = new Vector<String>();

		try {
			statement = con.prepareStatement("select username, nome, ruolo from utenti");
			result = statement.executeQuery();
			
			while (result.next()) {							
				accounts.add(result.getString("username") + ":" + result.getString("nome") 
				+ ":" + result.getString("ruolo"));
			}
			
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return accounts;
	}

	@Override
	public boolean createTable() {
		Connection con = Db2DAOFactory.createConnection();
		Statement statement = null;
		boolean result = false;

		try {
			statement = con.createStatement();
			statement.executeUpdate("CREATE TABLE password ( " + 
					"	idPassword int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), " + 
					"	password varchar(64) NOT NULL, " + 
					"	PRIMARY KEY (IDPASSWORD) )");
			statement.close();
			
			statement = con.createStatement();
			statement.executeUpdate("CREATE TABLE utenti ( " + 
					"	username varchar(50) NOT NULL PRIMARY KEY, " + 
					"	nome varchar(50) NOT NULL, " + 
					"	ruolo varchar(50), " + 
					"	idPassword int NOT NULL REFERENCES password )");
			statement.close();
			
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean dropTable() {
		Connection con = Db2DAOFactory.createConnection();
		Statement statement = null;
		boolean result = false;

		try {
			statement = con.createStatement();
			statement.addBatch("drop table utenti");
			statement.executeUpdate("drop table password ");
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
