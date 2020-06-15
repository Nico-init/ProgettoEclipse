package ilRifugio.serverRistorante.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ilRifugio.serverRistorante.dao.PietanzaDao;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Pietanza;

public class Db2PietanzaDAO implements PietanzaDao {

	@Override
	public void create(Pietanza pietanza) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;

		if (pietanza != null) {
			try {
				statement = con.prepareStatement("insert into pietanze (nome, prezzo, categoriapietanza) values (?, ?, ?)");
				statement.setString(1, pietanza.getNome());
				statement.setDouble(2, pietanza.getPrezzo());
				statement.setString(3, pietanza.getCategoriaPietanza().name());
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
	public Pietanza read(String nomePietanza) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Pietanza pietanza = null;

		try {
			statement = con.prepareStatement("select * from pietanze where nome = ?");
			statement.setString(1, nomePietanza);
			result = statement.executeQuery();
			
			if(result.next()) {								
				pietanza = new Pietanza();
				pietanza.setNome(result.getString("nome"));
				pietanza.setPrezzo(result.getDouble("prezzo"));
				pietanza.setCategoriaPietanza(CategoriaPietanza.valueOf(result.getString("categoriapietanza")));
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
		return pietanza;
	}

	@Override
	public boolean update(Pietanza pietanza) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (pietanza != null) {
			try {
				statement = con.prepareStatement("update pietanze set prezzo = ?, categoriapietanza = ? where nome = ? ");
				statement.setDouble(1, pietanza.getPrezzo());
				statement.setString(2, pietanza.getCategoriaPietanza().name());
				statement.setString(3, pietanza.getNome());
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
	public boolean delete(String nomePietanza) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (nomePietanza != null) {
			try {
				statement = con.prepareStatement("delete from pietanze where nome = ?");
				statement.setString(1, nomePietanza);
				
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
			statement.executeUpdate("CREATE TABLE pietanze ( " + 
					"	nome varchar(50) NOT NULL PRIMARY key, " + 
					"	prezzo double NOT NULL, " + 
					"	categoriaPietanza varchar(30) NOT NULL " + 
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
			statement.executeUpdate("drop table pietanze ");
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
