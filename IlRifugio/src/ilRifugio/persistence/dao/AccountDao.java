package ilRifugio.persistence.dao;

import java.util.List;

public interface AccountDao {
	public boolean aggiungiAccount(String username, String nome, String password, String ruolo);
	public boolean rimuoviAccount(String username);
	public boolean modificaAccount(String username, String nome, String password, String ruolo);
	public List<String> elencaAccount();
	
	public boolean createTable();
	public boolean dropTable();
}
