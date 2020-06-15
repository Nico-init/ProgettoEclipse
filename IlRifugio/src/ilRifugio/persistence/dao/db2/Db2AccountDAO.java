package ilRifugio.persistence.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import ilRifugio.persistence.dao.AccountDao;

public class Db2AccountDAO implements AccountDao {

	@Override
	public void create(String nome, String username, String password, String ruolo) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement1 = null, statement2 = null, statement3 = null;

		if (nome != null && username != null &&
				password != null && (ruolo.equals("cameriere") || ruolo.equals("ristoratore"))) {
			try {
				password = DigestUtils.md5Hex(password);
				statement1 = con.prepareStatement("insert into password (password) values (?)");
				statement1.setString(1, password);
				statement1.executeUpdate();
				
				System.out.println(password);
				
				statement2 = con.prepareStatement("select idpassword from password where password = ?");
				statement2.setString(1, password);
				ResultSet resultSet = statement2.executeQuery();
				
				
				int id = -1;
				if (resultSet.next()) {
					id = resultSet.getInt("idpassword");
					statement3 = con.prepareStatement("insert into utenti (username,nome,ruolo,idpassword) values (?,?,?,?)");
					statement3.setString(1, username);
					statement3.setString(2, nome);
					statement3.setString(3, ruolo);
					statement3.setInt(4, id);
					statement3.executeUpdate();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}

					if (statement1 != null) {
						statement1.close();
					}
					
					if (statement2 != null) {
						statement2.close();
					}
					
					if (statement3 != null) {
						statement3.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String read(String nome) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String res = "";

		try {
			statement = con.prepareStatement("select * from utenti where nome = ?");
			statement.setString(1, nome);
			result = statement.executeQuery();
			
			if(result.next()) {							
				res += result.getString("nome") + "&";
				res += result.getString("username") + "&";
				res += result.getString("ruolo");
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
		return res;
	}

	@Override
	public boolean update(String nome, String username, String password, String ruolo) {
		boolean result = false;
		// DA COMPLETARE
		return result;
	}

	@Override
	public boolean delete(String nome) {
		boolean result = false;
		// DA COMPLETARE
		return result;
	}

	@Override
	public boolean createTable() {
		Connection con = Db2DAOFactory.createConnection();
		Statement statement1 = null, statement2 = null;
		boolean result = false;

		try {
			statement1 = con.createStatement();
			statement1.executeUpdate("CREATE TABLE password ( " + 
					"	idPassword int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), " + 
					"	password varchar(64) NOT NULL, " + 
					"	PRIMARY KEY (IDPASSWORD) " +
					")");	
			statement2 = con.createStatement();
			statement2.executeUpdate("CREATE TABLE utenti ( " + 
					"	username varchar(50) NOT NULL PRIMARY KEY, " + 
					"	nome varchar(50) NOT NULL, " + 
					"	ruolo varchar(50), " +
					"	idPassword int NOT NULL REFERENCES password " +
					")");
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

				if (statement1 != null) {
					statement1.close();
				}
				
				if (statement2 != null) {
					statement2.close();
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
		Statement statement1 = null, statement2 = null;
		boolean result = false;

		try {
			statement1 = con.createStatement();
			statement1.executeUpdate("drop table utenti ");
			statement2 = con.createStatement();
			statement2.executeUpdate("drop table password ");
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

				if (statement1 != null) {
					statement1.close();
				}
				
				if (statement2 != null) {
					statement2.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String leggiAccount() {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String res = "";

		try {
			statement = con.prepareStatement("select * from utenti");
			result = statement.executeQuery();
			
			while (result.next()) {							
				res += result.getString("nome") + "&";
				res += result.getString("username") + "&";
				res += result.getString("ruolo") + ":";
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
		return res;
	}
	

}
