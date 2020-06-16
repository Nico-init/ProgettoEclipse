package ilRifugio.clientCameriere.gui;

import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ModificaBevandaPane extends BorderPane {

	private IControllerOrdine controllerO;
	private Button inserisci, annulla;
	private TextArea taNome;
	private TextField tfQuantita;
	private VBox onlyPane;
	private HBox hNome, hQuantita, hBottoni;
	private IOrdine ordineDaVisualizzare;
	private BevandaOrdinata bevandaOrdinataDaVisualizzare;
	
	public ModificaBevandaPane(IControllerOrdine controllerO) {
		this.controllerO = controllerO;
		this.bevandaOrdinataDaVisualizzare = ClientCameriereApp.getBevandaOrdinataDaVisualizzare();
		this.ordineDaVisualizzare = ClientCameriereApp.getIOrdineDaVisualizzare();

		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(50);
		
		Label modifica = new Label("Modifica Bevanda");
		modifica.setAlignment(Pos.TOP_CENTER);
		onlyPane.getChildren().add(modifica);
		onlyPane.setSpacing(80);
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                   ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		taNome = new TextArea();
		taNome.setText(bevandaOrdinataDaVisualizzare.getBevanda().getNome());
		taNome.setEditable(false);
		taNome.setMaxWidth(200);
		taNome.setPrefHeight(5);
		hNome.getChildren().add(taNome);
		hNome.setSpacing(50);
		onlyPane.getChildren().add(hNome);
		onlyPane.setSpacing(80);
		
		hQuantita = new HBox();
		hQuantita.setAlignment(Pos.CENTER);
	
		Label quantita = new Label("Quantit�                ");
		quantita.setAlignment(Pos.CENTER_LEFT);
		hQuantita.getChildren().add(quantita);
		
		tfQuantita = new TextField();
		tfQuantita.setText("");
		tfQuantita.setPrefWidth(200);
		tfQuantita.setAlignment(Pos.CENTER_RIGHT);
		hQuantita.getChildren().add(tfQuantita);
		hQuantita.setSpacing(50);
		onlyPane.getChildren().add(hQuantita);
		onlyPane.setSpacing(80);
		
		hBottoni = new HBox();
		hBottoni.setAlignment(Pos.CENTER);
		
		inserisci = new Button("INSERISCI");
		inserisci.setAlignment(Pos.CENTER_LEFT);
		inserisci.setOnAction(event -> handler(inserisci));
		hBottoni.getChildren().add(inserisci);
		hBottoni.setSpacing(50);
		
		annulla = new Button("ANNULLA");
		annulla.setAlignment(Pos.CENTER_RIGHT);
		annulla.setOnAction(event -> {
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere(controllerO);
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
         });
		hBottoni.getChildren().add(annulla);
		hBottoni.setSpacing(50);
		onlyPane.getChildren().add(hBottoni);
		onlyPane.setSpacing(50);
		this.setCenter(onlyPane);
	 }
	
	private void handler(Button inserisci) {
		if(tfQuantita.getText().isEmpty() || tfQuantita.getText().equals(null)) {
			alert("Errore", "Inserimento non corretto", "Quantit� errata o vuota");
			return;
		}
		int iQuantita = 0;
		String sQuantita = tfQuantita.getText().trim();
		try {
			iQuantita = Integer.parseInt(sQuantita);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Quantit� deve essere un numero");
			return;
		}
		controllerO.modificaBevandaOrdinata(ordineDaVisualizzare.getNomeTavolo(), ordineDaVisualizzare.getDataOra(), bevandaOrdinataDaVisualizzare.getBevanda(), iQuantita);
		OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere(controllerO);
		Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
		ClientCameriereApp.getStage().setScene(oldScene);
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}