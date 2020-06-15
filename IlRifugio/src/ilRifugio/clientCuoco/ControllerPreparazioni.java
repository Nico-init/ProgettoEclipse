package ilRifugio.clientCuoco;

import java.rmi.Remote;

import ilRifugio.interfacce.IOrdineEvents;
import ilRifugio.interfacce.dominio.IOrdine;

public class ControllerPreparazioni implements IOrdineEvents,Remote {
	
	private static final long serialVersionUID = -4820366918215347129L;

	@Override
	public void aggiunta(IOrdine ordine) {
		System.out.println("NOTIFICA");
	}

	@Override
	public void modifca(IOrdine ordine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consegna(IOrdine ordine) {
		// TODO Auto-generated method stub
		
	}

}
