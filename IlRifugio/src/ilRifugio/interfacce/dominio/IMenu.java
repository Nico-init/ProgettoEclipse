package ilRifugio.interfacce.dominio;

import java.util.Collection;

import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public interface IMenu {
	public void inserisciCopertoAdulto(double prezzo);
	public void inserisciCopertoBambino(double prezzo);
	public void modificaCopertoAdulto(double prezzo);
	public void modificaCopertoBambino(double prezzo);
	public Collection<ICoperto> elencaCoperti();
	public void aggiungiPietanza(String nome, double prezzo, CategoriaPietanza categoria);
	public void aggiungiBevanda(String nome, double prezzo);
	public boolean modificaPietanza(String nome, double prezzo, CategoriaPietanza categoria);
	public boolean modificaBevanda(String nome, double prezzo);
	public boolean rimuoviPietanza(String nome);
	public boolean rimuoviBevanda(String nome);
	public Collection<IPietanza> elencoPietanze();
	public Collection<IBevanda> elencoBevande();
}
