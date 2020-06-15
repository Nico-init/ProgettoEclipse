package ilRifugio.interfacce.controller;

import java.util.Collection;
import java.util.Date;

import ilRifugio.interfacce.dominio.IEntry;

public interface IControllerLog {
	
	public void aggiungiEntry(String user,String action);
	public Collection<IEntry> elenca(Date da, Date a);

}
