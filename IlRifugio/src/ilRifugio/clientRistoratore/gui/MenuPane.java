package ilRifugio.clientRistoratore.gui;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	private IControllerAccount controllerA;
	private IControllerMenu controllerM;
	private IControllerLog controllerL;
	private Button account, log, menu;
	private TableView<IOrdine> table;
	private VBox onlyPane;
	private HBox hBottoni;
	@SuppressWarnings("unused")
	public IOrdine ordineDaVisualizzare;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
	
		Label titolo = new Label("Sistema di Gestione Ristoratore");
		onlyPane.getChildren().add(titolo);
		onlyPane.setSpacing(10); 
	}
}
