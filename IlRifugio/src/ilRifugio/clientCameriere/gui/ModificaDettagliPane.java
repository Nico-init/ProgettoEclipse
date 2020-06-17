package ilRifugio.clientCameriere.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.dominio.IOrdine;
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

public class ModificaDettagliPane extends BorderPane {
	
	private IOrdine iOrdine;
	private Button inserisciT, inserisciB, inserisciA, annulla;
	private VBox onlyPane;
	private TextField tfTavolo, tfBambini, tfAdulti;
	private HBox hTavolo, hBambini, hAdulti;
	
	public ModificaDettagliPane() {
		this.iOrdine = ClientCameriereApp.getIOrdineDaVisualizzare();
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(50);
		
		Label modifica = new Label("Modifica Dettagli");
		modifica.setAlignment(Pos.TOP_CENTER);
		onlyPane.getChildren().add(modifica);
		onlyPane.setSpacing(80);
		
		hTavolo = new HBox();
		hTavolo.setAlignment(Pos.CENTER);
	
		Label tavolo = new Label("Tavolo                ");
		tavolo.setAlignment(Pos.CENTER_RIGHT);
		hTavolo.getChildren().add(tavolo);
		
		tfTavolo = new TextField();
		tfTavolo.setText("");
		tfTavolo.setMaxWidth(400);
		tfTavolo.setAlignment(Pos.CENTER);
		hTavolo.getChildren().add(tfTavolo);
		hTavolo.setSpacing(50);
		
		inserisciT = new Button("INSERISCI");
		inserisciT.setAlignment(Pos.CENTER_LEFT);
		inserisciT.setOnAction(event -> inserisciTHandler(inserisciT));
		hTavolo.getChildren().add(inserisciT);
		hTavolo.setSpacing(50);
		onlyPane.getChildren().add(hTavolo);
		onlyPane.setSpacing(80);
		
		hBambini = new HBox();
		hBambini.setAlignment(Pos.CENTER);
		
		Label bambini = new Label("Coperti Bambini");
		bambini.setAlignment(Pos.CENTER_LEFT);
		hBambini.getChildren().add(bambini);
		
		tfBambini = new TextField();
		tfBambini.setText("");
		tfBambini.setMaxWidth(400);
		tfBambini.setAlignment(Pos.CENTER);
		hBambini.getChildren().add(tfBambini);
		hBambini.setSpacing(50);
		
		inserisciB = new Button("INSERISCI");
		inserisciB.setAlignment(Pos.CENTER_RIGHT);
		inserisciB.setOnAction(event -> inserisciBHandler(inserisciB));
		hBambini.getChildren().add(inserisciB);
		hBambini.setSpacing(50);
		onlyPane.getChildren().add(hBambini);
		onlyPane.setSpacing(80);
		
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
		
		inserisciA = new Button("INSERISCI");
		inserisciA.setAlignment(Pos.CENTER_RIGHT);
		inserisciA.setOnAction(event -> inserisciAHandler(inserisciA));
		hAdulti.getChildren().add(inserisciA);
		hAdulti.setSpacing(50);
		onlyPane.getChildren().add(hAdulti);
		onlyPane.setSpacing(80);
		
		annulla = new Button("ANNULLA");
		annulla.setAlignment(Pos.CENTER_RIGHT);
		annulla.setOnAction(event -> {
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere();
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
         });
		onlyPane.getChildren().add(annulla);
		onlyPane.setSpacing(50);
		this.setCenter(onlyPane);
	 }
	
	private void inserisciTHandler(Button inserisciT) {
		if(tfTavolo.getText().isEmpty() || tfTavolo.getText().equals(null) || tfTavolo.getText().length() > 20) {
			alert("Errore", "Inserimento non corretto", "Nome tavolo errato, vuoto o troppo con troppi caratteri (> 20)");
			return;
		}
		String sTavolo = tfTavolo.getText().trim();
		try {
			ClientCameriereApp.serverRistorante.modificaNomeTavolo(iOrdine.getNomeTavolo(), iOrdine.getDataOra(), sTavolo);
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere();
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
		} catch (RemoteException e) {

		}
	}
	
	private void inserisciAHandler(Button inserisciA) {
		if(tfAdulti.getText().isEmpty() || tfAdulti.getText().equals(null) || tfAdulti.getText().length() > 2) {
			alert("Errore", "Inserimento non corretto", "Coperto adulti errato, vuoto o troppo con troppi caratteri (> 2)");
			return;
		}
		int iAdulti = 0;
		String sAdulti = tfAdulti.getText().trim();
		try {
			iAdulti = Integer.parseInt(sAdulti);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Coperto adulti deve essere un numero");
			return;
		}
		try {
			ClientCameriereApp.serverRistorante.modificaCopertiAdulti(iOrdine.getNomeTavolo(), iOrdine.getDataOra(), iAdulti);
			OrdinePaneClientCameriere ordine = new OrdinePaneClientCameriere();
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
		} catch (RemoteException e) {
		}
	}
	
	private void inserisciBHandler(Button inserisciB) {
		if(tfBambini.getText().isEmpty() || tfBambini.getText().equals(null) || tfBambini.getText().length() > 2) {
			alert("Errore", "Inserimento non corretto", "Coperto bambini errato, vuoto o troppo con troppi caratteri (> 2)");
			return;
		}
		int iBambini = 0;
		String sBambini = tfBambini.getText().trim();
		try {
			iBambini = Integer.parseInt(sBambini);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Coperto bambini deve essere un numero");
			return;
		}
		try {
			ClientCameriereApp.serverRistorante.modificaCopertiBambini(iOrdine.getNomeTavolo(), iOrdine.getDataOra(), iBambini);
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
