package ilRifugio.serverRistorante.dominio;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import ilRifugio.interfacce.dominio.IEntry;

public class Entry implements IEntry {
	
	private Date dateTime;
	private String user;
	private String action;
	
	public Entry(String u,String a) {
		user = u;
		action = a;
		dateTime = new Date();
	}

	@Override
	public Date getDateTime() {
		return dateTime;
	}
	
	public String toString() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		return df.format(dateTime) + "\t" + action + "\t" + user;
	}

}
