package ilRifugio.clientCameriere.gui;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.serverRistorante.IServerRistorante;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientCameriereApp extends Application {
	
	static int registryPort = 1099;
    static String registryHost = "localhost";
    static String serviceName = "ServerRistorante";
    static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;

    protected static IServerRistorante serverRistorante = null;
    
	private static IOrdine ordineDaVisualizzare;
	private static BevandaOrdinata bevandaOrdinataDaVisualizzare;
	private static PietanzaOrdinata pietanzaOrdinataDaVisualizzare;
	
	private static Stage guiStage;

	public static Stage getStage() {
	     return guiStage;
	}
	
	public void start(Stage guiStage) {
		
	    serverRistorante = null;
		try {
			serverRistorante = (IServerRistorante) Naming.lookup(completeName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		if (serverRistorante == null)
			System.exit(2);
		
		ClientCameriereApp.guiStage = guiStage;
		LoginClientCameriere login = new LoginClientCameriere();
		Scene root = new Scene(login, 750, 660, Color.BEIGE);
		guiStage.setScene(root);
		guiStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setIOrdineDaVisualizzare(IOrdine IOrdineDaVisualizzare) {
		ordineDaVisualizzare = IOrdineDaVisualizzare;
	}

	public static IOrdine getIOrdineDaVisualizzare() {
		try {
			for (IOrdine ordine : serverRistorante.elencaOrdini()) {
				if (ordine.getNomeTavolo().equals(ordineDaVisualizzare.getNomeTavolo()) &&
						dataEquals(ordine.getDataOra(),ordineDaVisualizzare.getDataOra())) {
					ordineDaVisualizzare = ordine;
				}
			}
		} catch (RemoteException e) {
			ordineDaVisualizzare = null;
		}
		return ordineDaVisualizzare;
	}
	
	public static void setBevandaOrdinataDaVisualizzare(BevandaOrdinata BevandaOrdinataDaVisualizzare) {
		bevandaOrdinataDaVisualizzare = BevandaOrdinataDaVisualizzare;
	}
	
	public static BevandaOrdinata getBevandaOrdinataDaVisualizzare() {
		getIOrdineDaVisualizzare();
			for (BevandaOrdinata bevanda : ordineDaVisualizzare.getBevande()) {
				if (bevanda.getBevanda().getNome().equals(bevandaOrdinataDaVisualizzare.getBevanda().getNome())) {
					bevandaOrdinataDaVisualizzare = bevanda;
				}
			}
		return bevandaOrdinataDaVisualizzare;
	}
	
	public static void setPietanzaOrdinataDaVisualizzare(PietanzaOrdinata PietanzaOrdinataDaVisualizzare) {
		pietanzaOrdinataDaVisualizzare = PietanzaOrdinataDaVisualizzare;
	}
	
	public static PietanzaOrdinata getPietanzaOrdinataDaVisualizzare(){
		getIOrdineDaVisualizzare();
		for (PietanzaOrdinata pietanza : ordineDaVisualizzare.getPietanze()) {
			if (pietanza.getPietanza().getNome().equals(pietanzaOrdinataDaVisualizzare.getPietanza().getNome()) &&
					pietanza.getOrdineConsegna() == pietanzaOrdinataDaVisualizzare.getOrdineConsegna()) {
				pietanzaOrdinataDaVisualizzare = pietanza;
			}
		}
		return pietanzaOrdinataDaVisualizzare;
	}
	
	private static boolean dataEquals(Date d1, Date d2) {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		return df.format(d1).equals(df.format(d2));
	}
}
