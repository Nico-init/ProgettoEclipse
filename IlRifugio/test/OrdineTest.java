import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.Pietanza;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import ilRifugio.serverRistorante.gestioneOrdine.ControllerOrdine;

public class OrdineTest {
	
	@Test
	public void ordiniNotNullTest() {
		ControllerOrdine controllerOrdine = new ControllerOrdine();
		assertNotNull(controllerOrdine);
	}
	
	@Test
	public void nuovoOrdineTest() {
		ControllerOrdine controllerOrdine = new ControllerOrdine();
		controllerOrdine.nuovoOrdine("tavolo1", 2, 3);
		assertEquals(controllerOrdine.elencaOrdini().size(), 1);
		assertEquals(((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getNomeTavolo(), "tavolo1");
		assertEquals(((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getCopertiAdulti(), 2);
		assertEquals(((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getCopertiBambini(), 3);
	}
	
	@Test
	public void aggiuntaPietanzaTest() {
		ControllerOrdine controllerOrdine = new ControllerOrdine();
		controllerOrdine.nuovoOrdine("tavolo1", 2, 3);
		Date data = ((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getDataOra();
		IPietanza pietanza = new Pietanza("lasagne",11,CategoriaPietanza.PRIMO);
		controllerOrdine.ordinaPietanza("tavolo1", data, pietanza, 3, "", OrdineConsegna.DOPO_PRIMI);
		assertEquals(((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getPietanze().size(),1);
		PietanzaOrdinata po = (PietanzaOrdinata) ((IOrdine)controllerOrdine.elencaOrdini().toArray()[0]).getPietanze().toArray()[0];
		assertEquals(po.getOrdineConsegna(), OrdineConsegna.DOPO_PRIMI );
	}

}
