import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IMenu;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Menu;

public class MenuTest {
	
	@Test
	public void aggiuntaBevandaAlMenuTest() {
		String nomeNuovaBevanda = "CocaCola Lattina";
		double prezzoNuovaBevanda = 3.0;
		IMenu menu = new Menu();
		menu.aggiungiBevanda(nomeNuovaBevanda,prezzoNuovaBevanda);
		assertEquals(1, menu.elencoBevande().size());
		assertEquals(nomeNuovaBevanda, ((IBevanda) menu.elencoBevande().toArray()[0]).getNome());
		assertEquals(prezzoNuovaBevanda, ((IBevanda) menu.elencoBevande().toArray()[0]).getPrezzo(),0.001);
	}
	
	@Test
	public void inserisciCopertiMenuTest() {
		IMenu menu = new Menu();
		assertEquals(0, ((ICoperto) menu.elencaCoperti().toArray()[0]).getPrezzo(), 0.001);
		assertEquals(0, ((ICoperto) menu.elencaCoperti().toArray()[1]).getPrezzo(), 0.001);
		double prezzoAdulto = 3;
		double prezzoBambino = 2;
		menu.inserisciCopertoAdulto(prezzoAdulto);
		menu.inserisciCopertoBambino(prezzoBambino);
		assertEquals(prezzoAdulto, ((ICoperto) menu.elencaCoperti().toArray()[0]).getPrezzo(), 0.001);
		assertEquals(prezzoBambino, ((ICoperto) menu.elencaCoperti().toArray()[1]).getPrezzo(), 0.001);
	}
	
	@Test
	public void modificaPietanzaMenuTest() {
		IMenu menu = new Menu();
		String nomePietanza = "Lasagne alla bolognese";
		menu.aggiungiPietanza(nomePietanza, 5, CategoriaPietanza.PRIMO);
		assertEquals(CategoriaPietanza.PRIMO, 
				((IPietanza) menu.elencoPietanze().toArray()[0]).getCategoriaPietanza());
		assertEquals(5, ((IPietanza) menu.elencoPietanze().toArray()[0]).getPrezzo(), 0.001);
		menu.modificaPietanza(nomePietanza, 11, CategoriaPietanza.PRIMO);
		assertNotEquals(5, ((IPietanza) menu.elencoPietanze().toArray()[0]).getPrezzo(), 0.001);
		assertEquals(11, ((IPietanza) menu.elencoPietanze().toArray()[0]).getPrezzo(), 0.001);
	}
	
}
