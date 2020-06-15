package ilRifugio.interfacce.controller;

public interface IControllerAccount {

	public boolean aggiungi(String nome, String username, String password, String ruolo);
	public boolean modifica(String nome, String username, String password, String ruolo);
	public boolean rimuovi(String nome);
	public String elenca();
	
}
