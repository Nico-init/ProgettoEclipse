package ilRifugio.interfacce.controller;

import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public interface IControllerPreparazioni {
	
	public String elencaPiattiDaPreparare(CategoriaPietanza categoriaPietanza);
	public boolean segnaPiattoComeConsegnato(PietanzaOrdinata pietanzaOrdinata);

}
