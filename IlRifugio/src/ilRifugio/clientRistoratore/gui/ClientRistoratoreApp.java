package ilRifugio.clientRistoratore.gui;

import java.rmi.Naming;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerLogin;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.IServerRistorante;
import ilRifugio.serverRistorante.gestioneAccount.ControllerAccount;
import ilRifugio.serverRistorante.gestioneLog.ControllerLog;
import ilRifugio.serverRistorante.gestioneMenu.ControllerMenu;
import ilRifugio.serverRistorante.gestioneOrdine.ControllerOrdine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientRistoratoreApp extends Application {

	private IControllerOrdine controllerO = null;
	private IControllerAccount controllerA = null;
	private IControllerMenu controllerM = null;
	private IControllerLog controllerL = null;
	private static IOrdine ordineDaVisualizzare;
	private static Account accountDaVisualizzare;
	private static IBevanda bevandaDaVisualizzare;
	private static IPietanza pietanzaDaVisualizzare;

	private IControllerLogin controllerLogin;
	static int registryPort = 1099;
	static String registryHost = "localhost";
	static String serviceName = "ControllerLogin";
	static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;

	private static Stage guiStage;

	public static Stage getStage() {
		return guiStage;
	}

	public void start(Stage guiStage) {
		/*
		 * controllerO = createControllerO(); if (controllerO == null) { System.exit(1);
		 * } controllerA = createControllerA(); if (controllerA == null) {
		 * System.exit(1); } controllerM = createControllerM(); if (controllerM == null)
		 * { System.exit(1); } controllerL = createControllerL(); if (controllerL ==
		 * null) { System.exit(1); }
		 */
		controllerO = null;
		controllerA = null;
		controllerM = null;
		controllerL = null;
		try {
			serviceName = "ControllerOrdine";
			completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
			controllerO = (IControllerOrdine) Naming.lookup(completeName);

			serviceName = "ControllerAccount";
			completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
			controllerA = (IControllerAccount) Naming.lookup(completeName);

			serviceName = "ControllerMenu";
			completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
			controllerM = (IControllerMenu) Naming.lookup(completeName);

			serviceName = "ControllerLog";
			completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
			controllerL = (IControllerLog) Naming.lookup(completeName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		if (controllerA == null || controllerO == null || controllerM == null)
			System.exit(2);

		ClientRistoratoreApp.guiStage = guiStage;
		LoginClientRistoratore login = new LoginClientRistoratore(controllerO, controllerA, controllerM, controllerL);
		Scene root = new Scene(login, 750, 660, Color.BEIGE);
		guiStage.setScene(root);
		guiStage.show();
	}

	public static void setIOrdineDaVisualizzare(IOrdine IOrdineDaVisualizzare) {
		ordineDaVisualizzare = IOrdineDaVisualizzare;
	}

	public static IOrdine getIOrdineDaVisualizzare() {
		return ordineDaVisualizzare;
	}
	
	public static void setAccountDaVisualizzare(Account AccountDaVisualizzare) {
		accountDaVisualizzare = AccountDaVisualizzare;
	}
	
	public static Account getAccountDaVisualizzare(){
		return accountDaVisualizzare;
	}

	public static void setBevandaDaVisualizzare(IBevanda IBevandaDaVisualizzare) {
		bevandaDaVisualizzare = IBevandaDaVisualizzare;
	}
	
	public static IBevanda getBevandaDaVisualizzare() {
		return bevandaDaVisualizzare;
	}
	
	public static void setPietanzaDaVisualizzare(IPietanza IPietanzaDaVisualizzare) {
		pietanzaDaVisualizzare = IPietanzaDaVisualizzare;
	}
	
	public static IPietanza getPietanzaDaVisualizzare() {
		return pietanzaDaVisualizzare;
	}

	public static void main(String[] args) {
		launch(args);
	}
}