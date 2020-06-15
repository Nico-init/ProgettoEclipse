package ilRifugio.persistence.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.persistence.dao.BevandaDao;
import ilRifugio.serverRistorante.dominio.Bevanda;

public class Db2BevandaDAO implements BevandaDao {

	@Override
	public void create(IBevanda bevanda) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;

		if (bevanda != null) {
			try {
				statement = con.prepareStatement("insert into bevande (nome, prezzo) values (?, ?)");
				statement.setString(1, bevanda.getNome());
				statement.setDouble(2, bevanda.getPrezzo());
				statement.executeUpdate();

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
	}

	@Override
	public IBevanda read(String nomeBevanda) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Bevanda bevanda = null;

		try {
			statement = con.prepareStatement("select * from bevande where nome = ?");
			statement.setString(1, nomeBevanda);
			result = statement.executeQuery();
			
			if(result.next()) {							
				bevanda = new Bevanda();
				bevanda.setNome(result.getString("nome"));
				bevanda.setPrezzo(result.getDouble("prezzo"));
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
		return bevanda;
	}

	@Override
	public boolean update(IBevanda bevanda) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (bevanda != null) {
			try {
				statement = con.prepareStatement("update bevande set prezzo = ? where nome = ? ");
				statement.setDouble(1, bevanda.getPrezzo());
				statement.setString(2, bevanda.getNome());
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
	public boolean delete(String nomeBevanda) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (nomeBevanda != null) {
			try {
				statement = con.prepareStatement("delete from bevande where nome = ?");
				statement.setString(1, nomeBevanda);
				
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
	public boolean createTable() {
		Connection con = Db2DAOFactory.createConnection();
		Statement statement = null;
		boolean result = false;

		try {
			statement = con.createStatement();
			statement.executeUpdate("CREATE TABLE bevande ( " + 
					"	nome varchar(50) NOT NULL PRIMARY key, " + 
					"	prezzo double NOT NULL " + 
					")");
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
			statement.executeUpdate("drop table bevande ");
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
	public List<IBevanda> leggiBevande() {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Bevanda bevanda;
		List<IBevanda> bevande = new LinkedList<IBevanda>();

		try {
			statement = con.prepareStatement("select * from bevande");
			result = statement.executeQuery();
			
			while (result.next()) {							
				bevanda = new Bevanda();
				bevanda.setNome(result.getString("nome"));
				bevanda.setPrezzo(result.getDouble("prezzo"));
				bevande.add(bevanda);
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
		return bevande;
	}
	

}
