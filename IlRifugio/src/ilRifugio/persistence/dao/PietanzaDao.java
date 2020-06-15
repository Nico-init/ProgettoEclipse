package ilRifugio.persistence.dao;

import java.util.List;

import ilRifugio.interfacce.dominio.IPietanza;

public interface PietanzaDao {
	public void create(IPietanza pietanza);
	public IPietanza read(String nomePietanza);
	public boolean update(IPietanza pietanza);
	public boolean delete(String nomePietanza);
	
	public List<IPietanza> leggiPietanze();
	
	public boolean createTable();
	public boolean dropTable();
}
