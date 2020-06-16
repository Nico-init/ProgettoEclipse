package ilRifugio.clientRistoratore.gui;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.serverRistorante.gestioneAccount.ControllerAccount;
import ilRifugio.serverRistorante.gestioneLog.ControllerLog;
import ilRifugio.serverRistorante.gestioneMenu.ControllerMenu;
import ilRifugio.serverRistorante.gestioneOrdine.ControllerOrdine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientRistoratoreApp extends Application {

	private IControllerOrdine controllerO;
	private IControllerAccount controllerA;
	private IControllerMenu controllerM;
	private IControllerLog controllerL;
	private static IOrdine ordineDaVisualizzare;
	private static Account accountDaVisualizzare;
	
	private static Stage guiStage;

	public static Stage getStage() {
	     return guiStage;
	}
	
	public void start(Stage guiStage) {
		controllerO = createControllerO();
		if (controllerO == null) {
			System.exit(1);
		}
		controllerA = createControllerA();
		if (controllerA == null) {
			System.exit(1);
		}
		controllerM = createControllerM();
		if (controllerM == null) {
			System.exit(1);
		}
		controllerL = createControllerL();
		if (controllerL == null) {
			System.exit(1);
		}
		ClientRistoratoreApp.guiStage = guiStage;
		LoginClientRistoratore login = new LoginClientRistoratore(controllerO, controllerA, controllerM, controllerL);
		Scene root = new Scene(login, 750, 660, Color.BEIGE);
		guiStage.setScene(root);
		guiStage.show();
	}
	
	protected IControllerOrdine createControllerO() {
		IControllerOrdine c = new ControllerOrdine();
		return c;
	}
	
	protected IControllerAccount createControllerA() {
		IControllerAccount c = new ControllerAccount();
		return c;
	}
	
	protected IControllerMenu createControllerM() {
		IControllerMenu c = new ControllerMenu();
		return c;
	}
	
	protected IControllerLog createControllerL() {
		IControllerLog c = new ControllerLog();
		return c;
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

	public static void main(String[] args) {
		launch(args);
	}
}