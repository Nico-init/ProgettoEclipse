package ilRifugio.clientCameriere.gui;

import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
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

public class AggiungiPietanzaPane extends BorderPane {

	private IControllerOrdine controllerO;
	private IControllerMenu controllerM;
	private Button inserisci, annulla;
	private TextField tfQuantita, tfNote;
	private ComboBox<IPietanza> cbNome;
	private ComboBox<OrdineConsegna> cbOrdineConsegna;
	private VBox onlyPane;
	private HBox hNome, hQuantita, hBottoni, hNote, hOrdineConsegna;
	public IOrdine ordineDaVisualizzare;
	
	public AggiungiPietanzaPane(IControllerOrdine controllerO) {
		this.controllerO = controllerO;
		this.controllerM = ClientCameriereApp.getIControllerMenu();
		this.ordineDaVisualizzare = ClientCameriereApp.getIOrdineDaVisualizzare();

		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(50);
		
		Label aggiungi = new Label("Aggiungi Pietanza");
		aggiungi.setAlignment(Pos.TOP_CENTER);
		onlyPane.getChildren().add(aggiungi);
		onlyPane.setSpacing(80);
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                                ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		cbNome = new ComboBox<IPietanza>(FXCollections.observableArrayList(controllerM.elencaPietanze()));
		cbNome.setEditable(false);
		cbNome.setPrefWidth(200);
		hNome.getChildren().add(cbNome);
		hNome.setSpacing(50);
		onlyPane.getChildren().add(hNome);
		onlyPane.setSpacing(80);
		
		hQuantita = new HBox();
		hQuantita.setAlignment(Pos.CENTER);
	
		Label quantita = new Label("Quantita                             ");
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
		
		hNote = new HBox();
		hNote.setAlignment(Pos.CENTER);
	
		Label note = new Label("Note                                    ");
		note.setAlignment(Pos.CENTER_LEFT);
		hNote.getChildren().add(note);
		
		tfNote = new TextField();
		tfNote.setText("");
		tfNote.setPrefWidth(200);
		tfNote.setAlignment(Pos.CENTER_RIGHT);
		hNote.getChildren().add(tfNote);
		hNote.setSpacing(50);
		onlyPane.getChildren().add(hNote);
		onlyPane.setSpacing(80);
		
		hOrdineConsegna = new HBox();
		hOrdineConsegna.setAlignment(Pos.CENTER);
	
		Label ordineConsegna = new Label("Ordine Consegna                ");
		ordineConsegna.setAlignment(Pos.CENTER_LEFT);
		hOrdineConsegna.getChildren().add(ordineConsegna);
		
		cbOrdineConsegna = new ComboBox<OrdineConsegna>(FXCollections.observableArrayList(OrdineConsegna.values()));
		cbOrdineConsegna.setEditable(false);
		cbOrdineConsegna.setPrefWidth(200);
		hOrdineConsegna.getChildren().add(cbOrdineConsegna);
		hOrdineConsegna.setSpacing(50);
		onlyPane.getChildren().add(hOrdineConsegna);
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
		if(cbNome.getValue() == null) {
			alert("Errore", "Inserimento non corretto", "Nome nullo");
			return;
		}
		if(tfQuantita.getText().isEmpty() || tfQuantita.getText().equals(null)) {
			alert("Errore", "Inserimento non corretto", "Quantit� errata o vuota");
			return;
		}
		if(tfNote.getText().length() > 500) {
			alert("Errore", "Inserimento non corretto", "Numero caratteri Note troppo elevato (>500)");
			return;
		}
		if(cbOrdineConsegna.getValue() == null) {
			alert("Errore", "Inserimento non corretto", "Ordine Consegna nullo");
			return;
		}
		IPietanza n = cbNome.getValue();
		OrdineConsegna oc = cbOrdineConsegna.getValue();
		String sNote = tfNote.getText().trim();
		int iQuantita = 0;
		String sQuantita = tfQuantita.getText().trim();
		try {
			iQuantita = Integer.parseInt(sQuantita);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Quantit� deve essere un numero");
			return;
		}
		controllerO.ordinaPietanza(ordineDaVisualizzare.getNomeTavolo(), ordineDaVisualizzare.getDataOra(), n, iQuantita, sNote, oc);
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