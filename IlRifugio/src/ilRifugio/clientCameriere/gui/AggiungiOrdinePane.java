package ilRifugio.clientCameriere.gui;

import ilRifugio.interfacce.controller.IControllerOrdine;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class AggiungiOrdinePane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	private Button inserisci, annulla;
	private TextField tfTavolo,tfBambini, tfAdulti;
	private VBox vPane;
	private HBox hTavolo, hBambini, hAdulti, hBottoni;

	public AggiungiOrdinePane(IControllerOrdine controllerO) {
		this.controllerO = controllerO;
		
		vPane = new VBox();
		vPane.setAlignment(Pos.CENTER);
		vPane.setSpacing(50);
		
		Label aggiungi = new Label("Aggiungi Ordine");
		aggiungi.setAlignment(Pos.TOP_CENTER);
		vPane.getChildren().add(aggiungi);
		vPane.setSpacing(80);
		
		hTavolo = new HBox();
		hTavolo.setAlignment(Pos.CENTER);
	
		Label tavolo = new Label("Tavolo                ");
		tavolo.setAlignment(Pos.CENTER_LEFT);
		hTavolo.getChildren().add(tavolo);
		
		tfTavolo = new TextField();
		tfTavolo.setText("");
		tfTavolo.setMaxWidth(400);
		tfTavolo.setAlignment(Pos.CENTER_RIGHT);
		hTavolo.getChildren().add(tfTavolo);
		hTavolo.setSpacing(50);
		vPane.getChildren().add(hTavolo);
		vPane.setSpacing(80);
		
		hBambini = new HBox();
		hBambini.setAlignment(Pos.CENTER);
		
		Label bambini = new Label("Coperti Bambini");
		bambini.setAlignment(Pos.CENTER_LEFT);
		hBambini.getChildren().add(bambini);
		
		tfBambini = new TextField();
		tfBambini.setText("");
		tfBambini.setMaxWidth(400);
		tfBambini.setAlignment(Pos.CENTER_RIGHT);
		hBambini.getChildren().add(tfBambini);
		hBambini.setSpacing(50);
		vPane.getChildren().add(hBambini);
		vPane.setSpacing(80);
		
		hAdulti = new HBox();
		hAdulti.setAlignment(Pos.CENTER);
	
		Label adulti = new Label("Coperti Adulti   ");
		adulti.setAlignment(Pos.CENTER_LEFT);
		hAdulti.getChildren().add(adulti);
		
		tfAdulti = new TextField();
		tfAdulti.setText("");
		tfAdulti.setMaxWidth(400);
		tfAdulti.setAlignment(Pos.CENTER_RIGHT);
		hAdulti.getChildren().add(tfAdulti);
		hAdulti.setSpacing(50);
		vPane.getChildren().add(hAdulti);
		vPane.setSpacing(80);
		
		hBottoni = new HBox();
		hBottoni.setAlignment(Pos.BOTTOM_CENTER);
		
		inserisci = new Button("INSERISCI");
		inserisci.setAlignment(Pos.CENTER_LEFT);
		inserisci.setOnAction(event -> handle(inserisci));
		hBottoni.getChildren().add(inserisci);
		hBottoni.setSpacing(50);
		
		annulla = new Button("ANNULLA");
		annulla.setAlignment(Pos.CENTER_RIGHT);
		annulla.setOnAction(event -> {
			HomeClientCameriere home = new HomeClientCameriere(controllerO);
			 Scene oldScene = new Scene(home, 750, 660, Color.BEIGE);
			 ClientCameriereApp.getStage().setScene(oldScene);
         });
		hBottoni.getChildren().add(annulla);
		hBottoni.setSpacing(50);
		vPane.getChildren().add(hBottoni);
		vPane.setSpacing(50);
		this.setCenter(vPane);
	 }
	
	private void handle(Button inserisci) {
		if(tfTavolo.getText().isEmpty() || tfTavolo.getText().equals(null)  || tfTavolo.getText().length() > 20) {
			alert("Errore", "Inserimento non corretto", "Nome tavolo errato, vuoto o con troppi caratteri (> 20)");
			return;
		}
		else if(tfBambini.getText().isEmpty() || tfBambini.getText().equals(null) || tfBambini.getText().length() > 2){
			alert("Errore", "Inserimento non corretto", "Coperto bambini errato, vuoto o con troppi caratteri (> 2)");
			return;
		}
		else if(tfAdulti.getText().isEmpty() || tfAdulti.getText().equals(null) || tfAdulti.getText().length() > 2) {
			alert("Errore", "Inserimento non corretto", "Coperto adulti errato, vuoto o con troppi caratteri (> 2)");
			return;
		}
		int iBambini = 0; 
		int iAdulti = 0;
		String sTavolo = tfTavolo.getText().trim();
		String sBambini = tfBambini.getText().trim();
		String sAdulti = tfAdulti.getText().trim();
		try {
			iBambini = Integer.parseInt(sBambini);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Coperto bambini deve essere un numero");
			return;
		}
		try {
			iAdulti = Integer.parseInt(sAdulti);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Coperto adulti deve essere un numero");
			return;
		}
		controllerO.nuovoOrdine(sTavolo, iAdulti, iBambini);
		HomeClientCameriere ordine = new HomeClientCameriere(controllerO);
		Scene newScene = new Scene(ordine, 750, 660, Color.BEIGE);
		ClientCameriereApp.getStage().setScene(newScene); 
	}
	
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
