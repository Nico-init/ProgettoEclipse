package ilRifugio.serverRistorante.dominio;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.dominio.IEntry;
import ilRifugio.interfacce.dominio.ILog;

public class Log implements ILog {
	
	private List<IEntry> entries;
	
	private static Log logInstance;
	
	private Log() {
		entries = new LinkedList<IEntry>();
	}
	
	public static Log getLogInstance() {
		if (logInstance == null)
			logInstance = new Log();
		return logInstance;
	}

	@Override
	public void nuovaEntry(String user, String action) {
		entries.add(new Entry(user,action));
	}

	@Override
	public Collection<IEntry> getAllEntries() {
		return entries;
	}

	@Override
	public IEntry getLastEntry() {
		return entries.get(entries.size() - 1);
	}

}
