package ilRifugio.interfacce.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public interface IControllerMenu extends Remote{
	
	public boolean inserisciCoperto(String nome, double prezzo) throws RemoteException;
	public boolean modificaCoperto(String nome, double prezzo) throws RemoteException;
	public Collection<ICoperto> elencaCoperti() throws RemoteException;
	public boolean aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException;
	public boolean aggiungiBevanda(String nome, double prezzo) throws RemoteException;
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria) throws RemoteException;
	public boolean modificaBevanda(String nome, double prezzo) throws RemoteException;
	public boolean rimuoviElemento(String tipo, String nome) throws RemoteException;
	public Collection<IPietanza> elencaPietanze() throws RemoteException;
	public Collection<IBevanda> elencaBevande() throws RemoteException;

}
