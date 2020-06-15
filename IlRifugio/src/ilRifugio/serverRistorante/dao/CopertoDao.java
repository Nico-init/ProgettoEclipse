package ilRifugio.serverRistorante.dao;

import ilRifugio.serverRistorante.dominio.Coperto;

public interface CopertoDao {
	
	public void create(Coperto coperto);
	public Coperto read(String nomeCoperto);
	public boolean update(Coperto coperto);
	public boolean delete(String nomeCoperto);
	
	public boolean createTable();
	public boolean dropTable();
}
