package ilRifugio.clientRistoratore;

import java.rmi.Naming;
import java.rmi.RemoteException;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.IServerRistoranteProva;

public class ClientRistoratore {
	
	static int registryPort = 1099;
    static String registryHost = "localhost";
    static String serviceName = "ServerRistorante";
    static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
	
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
		
		serverRistorante.modificaCoperto("adulto", 3);
		
		System.out.println("--- MENU ---");
		System.out.println("--- COPERTI ---");
		for (ICoperto coperto : serverRistorante.elencaCoperti())
			System.out.println(coperto);
		System.out.println("--- PIETANZE ---");
		for (IPietanza pietanza : serverRistorante.elencaPietanze())
			System.out.println(pietanza);
		System.out.println("--- BEVANDE ---");
		for (IBevanda bevanda : serverRistorante.elencaBevande())
			System.out.println(bevanda);
		System.out.println("------------");
		
		System.out.println("--- ACCOUNT ---");
		String listaAccount = serverRistorante.elenca();
		String[] accountSingoli = listaAccount.split(":");
		String[] account;
		for (String a : accountSingoli) {
			account = a.split("&");
			System.out.println("Nome: " + account[0] + "\tUsername: " + account[1] + "\tRuolo: " + account[2]);
		}		
		System.out.println("------------");
		
		String ordineString;
		String split1[],split2[],split3[];
		System.out.println("--- DETTAGLI ORDINI ---");
		for (IOrdine o : serverRistorante.elencaOrdini()) {
			ordineString = o.dettagliOrdine();
			split1 = ordineString.split("_");
			System.out.println("Nome tavolo: " + split1[0]);
			System.out.println("DataOra: " + split1[1]);
			System.out.println("Coperti (adulti - bambini) : " + split1[2] + " - " + split1[3]);
			split2 = split1[4].split(":");
			if (split2.length > 1) {
				System.out.println("Pietanze:");
				System.out.println("Nome\tQta\tCosto unitario");
				for (int i=1; i<split2.length; i++) {
					split3 = split2[i].split("&");
					System.out.println(split3[0] + "\t" + split3[1] + "\t" + split3[2] + "€");
				}
			}
			split2 = split1[5].split(":");
			if (split2.length > 1) {
				System.out.println("Bevande:");
				System.out.println("Nome\tQta\tCosto unitario");
				for (int i=1; i<split2.length; i++) {
					split3 = split2[i].split("&");
					System.out.println(split3[0] + "\t" + split3[1] + "\t" + split3[2] + "€");
				}
			}
			System.out.println("Totale: " + split1[6] + "€");
			System.out.println("------------");
		}
		
	}

}
