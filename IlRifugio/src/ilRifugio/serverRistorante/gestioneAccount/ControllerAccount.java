package ilRifugio.serverRistorante.gestioneAccount;

import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.serverRistorante.gestioneLog.ControllerLog;

public class ControllerAccount implements IControllerAccount {
	
	private static List<Account> listaAccount;
	private static IControllerLog controllerLog;
	
	public ControllerAccount() {
		if (listaAccount == null)
			listaAccount = new LinkedList<Account>();
		if (controllerLog == null)
			controllerLog = new ControllerLog();
	}

	@Override
	public boolean aggiungi(String nome, String username, String password, String ruolo) {
		for (Account account : listaAccount) {
			if (account.getNome().equals(nome))
				return false;
		}
		Account account = new Account(nome,username,password,RuoloAccount.valueOf(ruolo.toUpperCase()));
		listaAccount.add(account);
		controllerLog.aggiungiEntry("ristoratore", "aggiungiAccount_" + nome + "_" + username + "_" + ruolo);
		return true;
	}

	@Override
	public boolean modifica(String nome, String username, String password, String ruolo) {
		for (Account account : listaAccount) {
			if (account.getNome().equals(nome)) {
				account.setUsername(username);
				account.setPassword(password);
				account.setRuolo(RuoloAccount.valueOf(ruolo.toUpperCase()));
				controllerLog.aggiungiEntry("ristoratore", "modificaAccount_" + nome + "_" + username + "_" + ruolo);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean rimuovi(String nome) {
		for (Account account : listaAccount) {
			if (account.getNome().equals(nome)) {
				listaAccount.remove(account);
				controllerLog.aggiungiEntry("ristoratore", "rimuoviAccount_" + nome);
				return true;
			}
		}
		return false;
	}

	@Override
	public String elenca() {
		String res = "";
		for (Account account : listaAccount)
			res += account.getNome() + "&" + account.getUsername() + "&" + account.getRuolo() + ":";
		return res;
	}

}
