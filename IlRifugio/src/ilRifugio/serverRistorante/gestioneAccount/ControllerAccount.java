package ilRifugio.serverRistorante.gestioneAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.persistence.dao.AccountDao;
import ilRifugio.persistence.dao.DAOFactory;
import ilRifugio.serverRistorante.gestioneLog.ControllerLog;

public class ControllerAccount extends UnicastRemoteObject implements IControllerAccount {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private static IControllerLog controllerLog;
	
	public ControllerAccount() throws RemoteException{
		accountDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getAccountDAO();
		if (controllerLog == null)
			controllerLog = new ControllerLog();
	}

	@Override
	public boolean aggiungi(String nome, String username, String password, String ruolo) {
		accountDao.create(nome, username, password, ruolo);
		try {
			controllerLog.aggiungiEntry("ristoratore", "aggiungiAccount_" + nome + "_" + username + "_" + ruolo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean modifica(String nome, String username, String password, String ruolo) {
		return accountDao.update(nome, username, password, ruolo);
	}

	@Override
	public boolean rimuovi(String nome) {
		return accountDao.delete(nome);
	}

	@Override
	public String elenca() {
		return accountDao.leggiAccount();
	}

}
