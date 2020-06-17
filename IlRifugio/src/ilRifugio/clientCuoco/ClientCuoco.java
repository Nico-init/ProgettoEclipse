package ilRifugio.clientCuoco;

import java.rmi.Naming;
import java.rmi.RemoteException;

import ilRifugio.serverRistorante.IServerRistorante;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public class ClientCuoco {

	static int registryPort = 1099;
    static String registryHost = "localhost";
    static String serviceName = "ServerRistorante";
    static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
	
	public static void main(String[] args) throws RemoteException {
	    IServerRistorante serverRistorante = null;
		try {
			serverRistorante = (IServerRistorante) Naming.lookup(completeName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		String piattiDaPreparare = serverRistorante.elencaPiattiDaPreparare(CategoriaPietanza.PRIMO);
		String[] split1 = piattiDaPreparare.split("_");
		if (!split1[0].equals("")) {
			String[] split2;
			for (int i=0; i<split1.length; i++) {
				split2 = split1[i].split("&");
				System.out.println(split2[0] + "\t" + split2[1] + "\t" + split2[2]);
			}	
		}
		
	}
}
