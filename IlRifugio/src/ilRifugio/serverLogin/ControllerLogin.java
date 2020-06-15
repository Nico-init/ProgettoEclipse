package ilRifugio.serverLogin;

import org.apache.commons.codec.digest.DigestUtils;

import ilRifugio.interfacce.controller.IControllerLogin;
import ilRifugio.login.dao.LoginDao;
import ilRifugio.persistence.dao.DAOFactory;

public class ControllerLogin implements IControllerLogin {

	@Override
	public String autentica(String username, String password) {
		String result = null;
		LoginDao loginDao = DAOFactory.getDAOFactory(DAOFactory.DB2).getLoginDAO();
		
		password = DigestUtils.md5Hex(password);
		result = loginDao.getNomeRuolo(username, password);
		
		return result;
	}

}
