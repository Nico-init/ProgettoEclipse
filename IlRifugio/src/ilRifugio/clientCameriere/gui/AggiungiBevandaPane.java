package ilRifugio.clientCameriere.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AggiungiBevandaPane extends BorderPane {

	private Button inserisci, annulla;
	private TextField tfQuantita;
	private ComboBox<IBevanda> cbNome;
	private VBox onlyPane;
	private HBox hNome, hQuantita, hBottoni;
	public IOrdine ordineDaVisualizzare;
	
	public AggiungiBevandaPane() throws RemoteException {

		this.ordineDaVisualizzare = ClientCameriereApp.getIOrdineDaVisualizzare();

		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(50);
		
		Label aggiungi = new Label("Aggiungi Bevanda");
		aggiungi.setAlignment(Pos.TOP_CENTER);
		onlyPane.getChildren().add(aggiungi);
		onlyPane.setSpacing(80);
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                   ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		cbNome = new ComboBox<IBevanda>(FXCollections.observableArrayList(ClientCameriereApp.serverRistorante.elencaBevande()));
		cbNome.setEditable(false);
		cbNome.setMaxWidth(400);
		hNome.getChildren().add(cbNome);
		hNome.setSpacing(50);
		onlyPane.getChildren().add(hNome);
		onlyPane.setSpacing(80);
		
		hQuantita = new HBox();
		hQuantita.setAlignment(Pos.CENTER);
	
		Label quantita = new Label("Quantita                ");
		quantita.setAlignment(Pos.CENTER_LEFT);
		hQuantita.getChildren().add(quantita);
		
		tfQuantita = new TextField();
		tfQuantita.setText("");
		tfQuantita.setMaxWidth(400);
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
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere();
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
			alert("Errore", "Inserimento non corretto", "Quantita errata o vuota");
			return;
		}
		if(cbNome.getValue() == null) {
			alert("Errore", "Inserimento non corretto", "Nome nullo");
			return;
		}
		IBevanda n = cbNome.getValue();
		int iQuantita = 0;
		String sQuantita = tfQuantita.getText().trim();
		try {
			iQuantita = Integer.parseInt(sQuantita);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Quantita deve essere un numero");
			return;
		}
		
		try {
			ClientCameriereApp.serverRistorante.ordinaBevanda(ordineDaVisualizzare.getNomeTavolo(), ordineDaVisualizzare.getDataOra(), n, iQuantita);
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere();
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
		} catch (RemoteException e) {
			
		}
		
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
