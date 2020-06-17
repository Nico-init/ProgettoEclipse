package ilRifugio.interfacce.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IControllerLogin extends Remote {
	public String autentica(String username, String password) throws RemoteException;
}
