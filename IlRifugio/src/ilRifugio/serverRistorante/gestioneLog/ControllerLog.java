package ilRifugio.serverRistorante.gestioneLog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.dominio.IEntry;
import ilRifugio.interfacce.dominio.ILog;
import ilRifugio.serverRistorante.dominio.Log;

public class ControllerLog implements IControllerLog {
	
	private ILog log;
	private PrintWriter fileLog;
	private final String fileName = "log.txt";
	
	public ControllerLog() {
		log = Log.getLogInstance();
	}

	@Override
	public void aggiungiEntry(String user, String action) {
		log.nuovaEntry(user, action);
		try {
			fileLog = new PrintWriter(new FileWriter(fileName, true));
			fileLog.println(log.getLastEntry());
			fileLog.close();
		} catch (IOException e) {
		
		}
	}

	@Override
	public Collection<IEntry> elenca(Date da, Date a) {
		Collection<IEntry> result = new LinkedList<IEntry>();
		for (IEntry entry : log.getAllEntries()) {
			if (entry.getDateTime().after(da) && entry.getDateTime().before(a))
				result.add(entry);
		}
		return result;
	}

}
