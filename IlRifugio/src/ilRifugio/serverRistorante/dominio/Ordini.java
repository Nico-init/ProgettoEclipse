package ilRifugio.serverRistorante.dominio;

import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IOrdini;

public class Ordini implements IOrdini {
	
	private List<IOrdine> ordini;
	
	private static Ordini ordiniInstance;
	
	private Ordini() {
		ordini = new LinkedList<IOrdine>();
	}
	
	public static Ordini getOrdiniInstance() {
		if (ordiniInstance == null)
			ordiniInstance = new Ordini();
		return ordiniInstance;
	}

	@Override
	public List<PietanzaOrdinata> visualizzaPiatti(CategoriaPietanza categoriaPietanza) {
		List<PietanzaOrdinata> res = new LinkedList<>();
		for (IOrdine o : ordini) {
			for (PietanzaOrdinata po : o.getPietanze()) {
				if (po.getPietanza().getCategoriaPietanza() == categoriaPietanza)
					res.add(po);
			}
		}
		return res;
	}

	@Override
	public boolean aggiungiOrdine(IOrdine ordine) {
		for (IOrdine o : ordini) {
			if (o.getNomeTavolo().equals(ordine.getNomeTavolo()) &&
					o.getDataOra().equals(ordine.getDataOra()))
					return false;
		}
		ordini.add(ordine);
		return true;
	}

	@Override
	public boolean rimuoviOrdine(IOrdine ordine) {
		for (IOrdine o : ordini) {
			if (o.getNomeTavolo().equals(ordine.getNomeTavolo()) &&
					o.getDataOra().equals(ordine.getDataOra())) {
					ordini.remove(o);
					return true;
			}
		}
		return false;
	}

	@Override
	public List<IOrdine> elencaOrdini() {
		return ordini;
	}

}
