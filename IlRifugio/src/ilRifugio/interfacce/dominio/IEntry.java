package ilRifugio.interfacce.dominio;

import java.io.Serializable;
import java.util.Date;

public interface IEntry extends Serializable{

	public String toString();
	public Date getDateTime();
	
}
