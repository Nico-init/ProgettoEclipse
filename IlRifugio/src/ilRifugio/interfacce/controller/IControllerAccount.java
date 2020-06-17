package ilRifugio.interfacce.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IControllerAccount extends Remote{

	public boolean aggiungi(String nome, String username, String password, String ruolo) throws RemoteException;
	public boolean modifica(String nome, String username, String password, String ruolo) throws RemoteException;
	public boolean rimuovi(String nome) throws RemoteException;
	public String elenca() throws RemoteException;
	
}
