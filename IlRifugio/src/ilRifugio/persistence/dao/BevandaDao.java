package ilRifugio.persistence.dao;

import java.util.List;

import ilRifugio.interfacce.dominio.IBevanda;

public interface BevandaDao {
	public void create(IBevanda bevanda);
	public IBevanda read(String nomeBevanda);
	public boolean update(IBevanda bevanda);
	public boolean delete(String nomeBevanda);
	
	public List<IBevanda> leggiBevande();
	
	public boolean createTable();
	public boolean dropTable();
}
