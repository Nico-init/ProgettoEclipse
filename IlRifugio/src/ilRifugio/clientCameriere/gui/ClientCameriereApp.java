package ilRifugio.clientCameriere.gui;

import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import ilRifugio.serverRistorante.gestioneMenu.ControllerMenu;
import ilRifugio.serverRistorante.gestioneOrdine.ControllerOrdine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientCameriereApp extends Application {

	private IControllerOrdine controllerO;
	private static IControllerMenu controllerM;
	private static IOrdine ordineDaVisualizzare;
	private static BevandaOrdinata bevandaOrdinataDaVisualizzare;
	private static PietanzaOrdinata pietanzaOrdinataDaVisualizzare;
	
	private static Stage guiStage;

	public static Stage getStage() {
	     return guiStage;
	}
	
	public void start(Stage guiStage) {
		controllerO = createControllerO();
		if (controllerO == null) {
			System.exit(1);
		}
		controllerM = createControllerM();
		if (controllerM == null) {
			System.exit(1);
		}
		ClientCameriereApp.guiStage = guiStage;
		LoginClientCameriere login = new LoginClientCameriere(controllerO);
		Scene root = new Scene(login, 750, 660, Color.BEIGE);
		guiStage.setScene(root);
		guiStage.show();
	}
	
	protected IControllerOrdine createControllerO() {
		IControllerOrdine c = new ControllerOrdine();
		return c;
	}
	
	protected IControllerMenu createControllerM() {
		IControllerMenu c = new ControllerMenu();
		return c;
	}
	
	public static IControllerMenu getIControllerMenu() {
		return controllerM;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setIOrdineDaVisualizzare(IOrdine IOrdineDaVisualizzare) {
		ordineDaVisualizzare = IOrdineDaVisualizzare;
	}

	public static IOrdine getIOrdineDaVisualizzare() {
		return ordineDaVisualizzare;
	}
	
	public static void setBevandaOrdinataDaVisualizzare(BevandaOrdinata BevandaOrdinataDaVisualizzare) {
		bevandaOrdinataDaVisualizzare = BevandaOrdinataDaVisualizzare;
	}
	
	public static BevandaOrdinata getBevandaOrdinataDaVisualizzare() {
		return bevandaOrdinataDaVisualizzare;
	}
	
	public static void setPietanzaOrdinataDaVisualizzare(PietanzaOrdinata PietanzaOrdinataDaVisualizzare) {
		pietanzaOrdinataDaVisualizzare = PietanzaOrdinataDaVisualizzare;
	}
	
	public static PietanzaOrdinata getPietanzaOrdinataDaVisualizzare(){
		return pietanzaOrdinataDaVisualizzare;
	}
}
