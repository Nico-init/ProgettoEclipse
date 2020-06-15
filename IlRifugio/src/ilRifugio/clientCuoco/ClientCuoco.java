package ilRifugio.clientCuoco;

import java.rmi.Naming;
import java.rmi.RemoteException;

import ilRifugio.serverRistorante.IServerRistoranteProva;

public class ClientCuoco {

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
		ControllerPreparazioni controllerPreparazioni = new ControllerPreparazioni();
	}
}
