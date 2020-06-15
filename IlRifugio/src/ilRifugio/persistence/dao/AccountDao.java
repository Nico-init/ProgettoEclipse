package ilRifugio.persistence.dao;

public interface AccountDao {
	public void create(String nome, String username, String password, String ruolo);
	public String read(String nome);
	public boolean update(String nome, String username, String password, String ruolo);
	public boolean delete(String nome);
	
	public String leggiAccount();
	
	public boolean createTable();
	public boolean dropTable();
}
