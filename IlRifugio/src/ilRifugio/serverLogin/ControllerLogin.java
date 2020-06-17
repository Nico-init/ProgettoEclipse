package ilRifugio.serverLogin;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.codec.digest.DigestUtils;

import ilRifugio.interfacce.controller.IControllerLogin;
import ilRifugio.persistence.dao.DAOFactory;
import ilRifugio.persistence.dao.LoginDao;
import ilRifugio.serverRistorante.ServerRistorante;

public class ControllerLogin extends UnicastRemoteObject implements IControllerLogin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerLogin() throws RemoteException {

	}
	
	@Override
	public String autentica(String username, String password) {
		String result = null;
		LoginDao loginDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getLoginDAO();
		
		password = DigestUtils.md5Hex(password);
		result = loginDao.getNomeRuolo(username, password);
		
		return result;
	}

}
