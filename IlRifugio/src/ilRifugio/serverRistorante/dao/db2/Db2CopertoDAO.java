package ilRifugio.serverRistorante.dao.db2;

import ilRifugio.serverRistorante.dao.CopertoDao;
import ilRifugio.serverRistorante.dominio.Coperto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db2CopertoDAO implements CopertoDao {

	@Override
	public void create(Coperto coperto) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;

		if (coperto != null) {
			try {
				statement = con.prepareStatement("insert into coperti (nome, prezzo) values (?, ?)");
				statement.setString(1, coperto.getNome());
				statement.setDouble(2, coperto.getPrezzo());
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
	public Coperto read(String nomeCoperto) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Coperto coperto = null;

		try {
			statement = con.prepareStatement("select * from coperti where nome = ?");
			statement.setString(1, nomeCoperto);
			result = statement.executeQuery();
			
			if(result.next()) {				
				coperto = new Coperto();
				coperto.setNome(result.getString("nome"));
				coperto.setPrezzo(result.getDouble("prezzo"));
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
		return coperto;
	}

	@Override
	public boolean update(Coperto coperto) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (coperto != null) {
			try {
				statement = con.prepareStatement("update coperti set prezzo = ? where nome = ? ");
				statement.setDouble(1, coperto.getPrezzo());
				statement.setString(2, coperto.getNome());
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
	public boolean delete(String nomeCoperto) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		boolean result = false;
		
		if (nomeCoperto != null) {
			try {
				statement = con.prepareStatement("delete from coperti where nome = ?");
				statement.setString(1, nomeCoperto);
				
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
			statement.executeUpdate("CREATE TABLE coperti ( " + 
					"nome varchar(50) NOT NULL PRIMARY key, " + 
					"prezzo double NOT NULL " + 
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
			statement.executeUpdate("drop table coperti ");
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
