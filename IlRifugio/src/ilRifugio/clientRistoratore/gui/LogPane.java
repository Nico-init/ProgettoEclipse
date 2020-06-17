package ilRifugio.clientRistoratore.gui;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IEntry;
import ilRifugio.interfacce.dominio.IOrdine;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.rmi.RemoteException;
import java.util.Date;

public class LogPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private Button chiudi;
	private TextArea ta;
	private VBox onlyPane;
	@SuppressWarnings("unused")
	public IOrdine ordineDaVisualizzare;
	
	@SuppressWarnings({ "deprecation" })
	public LogPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		Date da = new Date();
		da.setYear(0);
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
	
		Label titolo = new Label("Log");
		onlyPane.getChildren().add(titolo);
		onlyPane.setSpacing(10);
		
		ta = new TextArea();
		ta.setEditable(false);
		ta.setMaxWidth(600);
		ta.setMinHeight(500);
		ta.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		onlyPane.getChildren().add(ta);
		onlyPane.setSpacing(50);
	
		try {
			for(IEntry e : controllerL.elenca(da, new Date())) {
				String sEntry = e.toString();
				String[] s = sEntry.split("\t");
				ta.appendText("Data e Ora:\t" + s[0].trim() + "\n");
				ta.appendText("Operazione:\t" + s[1].trim() + "\n");
				ta.appendText("Username  :\t" + s[2].trim() + "\n\n");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		chiudi = new Button("CHIUDI");
		chiudi.setAlignment(Pos.BOTTOM_RIGHT);
		chiudi.setOnAction(event -> {
			HomeClientRistoratore ordine = new HomeClientRistoratore(controllerO, controllerA, controllerM, controllerL);
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(oldScene);
         });
		onlyPane.getChildren().add(chiudi);
		onlyPane.setSpacing(50);
		this.setTop(onlyPane);
	}
}
