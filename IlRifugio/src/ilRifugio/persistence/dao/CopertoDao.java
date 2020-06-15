package ilRifugio.persistence.dao;

import ilRifugio.interfacce.dominio.ICoperto;

public interface CopertoDao {
	
	public void create(ICoperto coperto);
	public ICoperto read(String nomeCoperto);
	public boolean update(ICoperto coperto);
	public boolean delete(String nomeCoperto);
	
	public boolean createTable();
	public boolean dropTable();
}
