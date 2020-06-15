package ilRifugio.clientCameriere;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.IServerRistoranteProva;
import ilRifugio.serverRistorante.dominio.Bevanda;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.Pietanza;

public class ClientCameriere {
	
	static int registryPort = 1099;
    static String registryHost = "localhost";
    static String serviceName = "ServerRistorante";
    static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws RemoteException {
	    IServerRistoranteProva serverRistorante = null;
		try {
			serverRistorante = (IServerRistoranteProva) Naming.lookup(completeName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		if (serverRistorante == null)
			System.exit(2);
		
		System.out.println("--- MENU ---");
		System.out.println("--- PIETANZE ---");
		for (IPietanza pietanza : serverRistorante.elencaPietanze())
			System.out.println(pietanza);
		System.out.println("--- BEVANDE ---");
		for (IBevanda bevanda : serverRistorante.elencaBevande())
			System.out.println(bevanda);
		System.out.println("------------");
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);		
		
		if (serverRistorante.elencaOrdini().size() != 2) {
			serverRistorante.nuovoOrdine("tavolo1", 4, 3);
			serverRistorante.nuovoOrdine("tavolo2", 3, 0);
			Date now = new Date();
			calendar.set(now.getYear() , now.getMonth(), now.getDate(), now.getHours(), now.getMinutes());
		}
		
		Date date = calendar.getTime();
		
		Pietanza pietanza1 = new Pietanza("lasagne", 11, CategoriaPietanza.PRIMO);
		serverRistorante.ordinaPietanza("tavolo1", date, pietanza1, 2, "", OrdineConsegna.DOPO_PRIMI);
		Pietanza pietanza2 = new Pietanza("bistecca", 15, CategoriaPietanza.SECONDO);
		serverRistorante.ordinaPietanza("tavolo1", date, pietanza2, 2, "", OrdineConsegna.DOPO_PRIMI);
		Bevanda bevanda1 = new Bevanda("coca cola", 3);
		serverRistorante.ordinaBevanda("tavolo1", date, bevanda1, 2);
		Bevanda bevanda2 = new Bevanda("birra", 5);
		serverRistorante.ordinaBevanda("tavolo1", date, bevanda2, 2);
		Bevanda bevanda3 = new Bevanda("acqua naturale", 2);
		serverRistorante.ordinaBevanda("tavolo1", date, bevanda3, 4);
		serverRistorante.modificaBevandaOrdinata("tavolo1", date, bevanda2, 1);
		
		Pietanza pietanza4 = new Pietanza("spaghetti", 8, CategoriaPietanza.PRIMO);
		serverRistorante.ordinaPietanza("tavolo2", date, pietanza4, 5, "", OrdineConsegna.DOPO_ANTIPASTI);
		
		String ordineString;
		String split1[],split2[],split3[];
		System.out.println("--- ORDINI ---");
		for (IOrdine o : serverRistorante.elencaOrdini()) {
			ordineString = o.dettagliOrdine();
			split1 = ordineString.split("_");
			System.out.println("Nome tavolo: " + split1[0]);
			System.out.println("DataOra: " + split1[1]);
			System.out.println("Coperti (adulti - bambini) : " + split1[2] + " - " + split1[3]);
			split2 = split1[4].split(":");
			if (split2.length > 1) {
				System.out.println("Pietanze:");
				System.out.println("Nome\tQta");
				for (int i=1; i<split2.length; i++) {
					split3 = split2[i].split("&");
					System.out.println(split3[0] + "\t" + split3[1]);
				}
			}
			split2 = split1[5].split(":");
			if (split2.length > 1) {
				System.out.println("Bevande:");
				System.out.println("Nome\tQta");
				for (int i=1; i<split2.length; i++) {
					split3 = split2[i].split("&");
					System.out.println(split3[0] + "\t" + split3[1]);
				}
			}
			System.out.println("------------");
		}
	}

}
