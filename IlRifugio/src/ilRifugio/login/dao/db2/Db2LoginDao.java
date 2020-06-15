package ilRifugio.login.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ilRifugio.login.dao.LoginDao;
import ilRifugio.persistence.dao.db2.Db2DAOFactory;

public class Db2LoginDao implements LoginDao {

	@Override
	public String getNomeRuolo(String username, String password) {
		Connection con = Db2DAOFactory.createConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String nomeRuolo = null;

		try {
			statement = con.prepareStatement("select nome, ruolo from utenti join password on utenti.idpassword = password.idpassword "
					+ "where username = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			result = statement.executeQuery();
			
			if(result.next()) {							
				nomeRuolo = result.getString("nome");
				nomeRuolo += ":" + result.getString("ruolo");
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
		return nomeRuolo;
	}

}
