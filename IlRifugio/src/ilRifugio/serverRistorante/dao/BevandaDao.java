package ilRifugio.serverRistorante.dao;

import ilRifugio.serverRistorante.dominio.Bevanda;

public interface BevandaDao {
	public void create(Bevanda bevanda);
	public Bevanda read(String nomeBevanda);
	public boolean update(Bevanda bevanda);
	public boolean delete(String nomeBevanda);
	
	public boolean createTable();
	public boolean dropTable();
}
