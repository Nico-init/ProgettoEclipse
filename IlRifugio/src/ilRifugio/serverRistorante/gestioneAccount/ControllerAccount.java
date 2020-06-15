package ilRifugio.serverRistorante.gestioneAccount;

import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.controller.IControllerAccount;

public class ControllerAccount implements IControllerAccount {
	
	private static List<Account> listaAccount;
	
	public ControllerAccount() {
		if (listaAccount == null)
			listaAccount = new LinkedList<Account>();
	}

	@Override
	public boolean aggiungi(String nome, String username, String password, String ruolo) {
		for (Account account : listaAccount) {
			if (account.getNome().equals(nome))
				return false;
		}
		Account account = new Account(nome,username,password,RuoloAccount.valueOf(ruolo.toUpperCase()));
		listaAccount.add(account);
		return true;
	}

	@Override
	public boolean modifica(String nome, String username, String password, String ruolo) {
		for (Account account : listaAccount) {
			if (account.getNome().equals(nome)) {
				account.setUsername(username);
				account.setPassword(password);
				account.setRuolo(RuoloAccount.valueOf(ruolo.toUpperCase()));
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
