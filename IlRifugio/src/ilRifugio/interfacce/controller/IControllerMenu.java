package ilRifugio.interfacce.controller;

import java.util.Collection;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public interface IControllerMenu {
	
	public boolean inserisciCoperto(String nome, double prezzo);
	public boolean modificaCoperto(String nome, double prezzo);
	public Collection<ICoperto> elencaCoperti();
	public boolean aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria);
	public boolean aggiungiBevanda(String nome, double prezzo);
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria);
	public boolean modificaBevanda(String nome, double prezzo);
	public boolean rimuoviElemento(String tipo, String nome);
	public Collection<IPietanza> elencaPietanze();
	public Collection<IBevanda> elencaBevande();

}
