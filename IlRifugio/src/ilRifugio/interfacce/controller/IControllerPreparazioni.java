package ilRifugio.interfacce.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public interface IControllerPreparazioni extends Remote{
	
	public String elencaPiattiDaPreparare(CategoriaPietanza categoriaPietanza) throws RemoteException;
	public boolean segnaPiattoComeConsegnato(PietanzaOrdinata pietanzaOrdinata) throws RemoteException;

}
