package ilRifugio.interfacce.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import ilRifugio.interfacce.dominio.IEntry;

public interface IControllerLog extends Remote {
	
	public void aggiungiEntry(String user,String action) throws RemoteException;
	public Collection<IEntry> elenca(Date da, Date a) throws RemoteException;

}
