package ilRifugio.serverRistorante.dao;

import ilRifugio.serverRistorante.dominio.Pietanza;

public interface PietanzaDao {
	public void create(Pietanza pietanza);
	public Pietanza read(String nomePietanza);
	public boolean update(Pietanza pietanza);
	public boolean delete(String nomePietanza);
	
	public boolean createTable();
	public boolean dropTable();
}
