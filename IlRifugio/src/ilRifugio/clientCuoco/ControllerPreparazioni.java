package ilRifugio.clientCuoco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.controller.IControllerPreparazioni;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IOrdineEvents;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.Ordini;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;

public class ControllerPreparazioni extends UnicastRemoteObject implements IControllerPreparazioni,IOrdineEvents {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ordini ordini;
	
	public ControllerPreparazioni() throws RemoteException {
		ordini = Ordini.getOrdiniInstance();
		for (IOrdine ordine : ordini.elencaOrdini())
			ordine.aggiungiObs(this);
	}

	@Override
	public void aggiunta(IOrdine ordine) {
		System.out.println("AGGIUNTA UNA PIETANZA");
	}

	@Override
	public void modifica(IOrdine ordine) {
		System.out.println("MODIFICATA UNA PIETANZA");
	}

	@Override
	public void consegna(IOrdine ordine) {
		System.out.println("CONSEGNATA UNA PIETANZA");
	}

	@Override
	public String elencaPiattiDaPreparare(CategoriaPietanza categoriaPietanza) {
		List<PietanzaOrdinata> pietanze = new LinkedList<PietanzaOrdinata>();
		for (IOrdine ordine : ordini.elencaOrdini()) {
			for (PietanzaOrdinata pietanzaOrdinata : ordine.getPietanze()) {
				if (!pietanzaOrdinata.isConsegnato() && 
						pietanzaOrdinata.getPietanza().getCategoriaPietanza() == categoriaPietanza)
					pietanze.add(pietanzaOrdinata);
			}
		}
		String res = "";
		for (PietanzaOrdinata po : pietanze)
			res += po.getPietanza().getNome() + "&" + po.getQuantita() + "&" + categoriaPietanza + "_";
		return res;
	}

	@Override
	public boolean segnaPiattoComeConsegnato(PietanzaOrdinata pietanzaOrdinata) {
		pietanzaOrdinata.consegnaPietanza();
		return true;
	}

}
