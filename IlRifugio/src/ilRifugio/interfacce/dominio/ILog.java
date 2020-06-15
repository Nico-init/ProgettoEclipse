package ilRifugio.interfacce.dominio;

import java.util.Collection;

public interface ILog {

	public void nuovaEntry(String user, String action);
	public Collection<IEntry> getAllEntries();
	public IEntry getLastEntry();
	
}
