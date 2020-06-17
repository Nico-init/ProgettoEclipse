package ilRifugio.clientRistoratore.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
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

public class ModificaPietanzaMenuPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	private IControllerAccount controllerA;
	private IControllerMenu controllerM;
	private IControllerLog controllerL;
	private Button inserisci, annulla;
	private TextField tfPrezzo, tfNome;
	private ComboBox<CategoriaPietanza> cbCategoria;
	private VBox onlyPane;
	private HBox hBottoni, hNome, hCategoria, hPrezzo;
	private IPietanza pietanzaDaVisualizzare;
	
	public ModificaPietanzaMenuPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		pietanzaDaVisualizzare = ClientRistoratoreApp.getPietanzaDaVisualizzare();
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
		
		Label ordineL = new Label("Modifica Pietanza");
		onlyPane.getChildren().add(ordineL);
		onlyPane.setSpacing(80);
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                       ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		tfNome = new TextField();
		tfNome.setText(pietanzaDaVisualizzare.getNome());
		tfNome.setEditable(false);
		tfNome.setPrefWidth(200);
		tfNome.setAlignment(Pos.CENTER_RIGHT);
		hNome.getChildren().add(tfNome);
		hNome.setSpacing(50);
		onlyPane.getChildren().add(hNome);
		onlyPane.setSpacing(80);

		hCategoria = new HBox();
		hCategoria.setAlignment(Pos.CENTER);
	
		Label categoria = new Label("Categoria                   ");
		categoria.setAlignment(Pos.CENTER_LEFT);
		hCategoria.getChildren().add(categoria);
		
		cbCategoria = new ComboBox<CategoriaPietanza>(FXCollections.observableArrayList(CategoriaPietanza.values()));
		cbCategoria.setEditable(false);
		cbCategoria.setPrefWidth(200);
		hCategoria.getChildren().add(cbCategoria);
		hCategoria.setSpacing(50);
		onlyPane.getChildren().add(hCategoria);
		onlyPane.setSpacing(80);
		
		hPrezzo = new HBox();
		hPrezzo.setAlignment(Pos.CENTER);
	
		Label prezzo = new Label("Prezzo                        ");
		prezzo.setAlignment(Pos.CENTER_LEFT);
		hPrezzo.getChildren().add(prezzo);
		
		tfPrezzo = new TextField();
		tfPrezzo.setText("");
		tfPrezzo.setPrefWidth(200);
		tfPrezzo.setAlignment(Pos.CENTER_RIGHT);
		hPrezzo.getChildren().add(tfPrezzo);
		hPrezzo.setSpacing(50);
		onlyPane.getChildren().add(hPrezzo);
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
			MenuPane menu = new MenuPane(controllerO, controllerA, controllerM, controllerL);
			Scene oldScene = new Scene(menu, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(oldScene);
         });
		hBottoni.getChildren().add(annulla);
		hBottoni.setSpacing(50);
		onlyPane.getChildren().add(hBottoni);
		onlyPane.setSpacing(50);
		this.setCenter(onlyPane);
	 }
	
	private void handler(Button inserisci) {
		if(cbCategoria.getValue().equals(null)) {
			alert("Errore", "Inserimento non corretto", "Categoria vuota");
			return;
		}
		if(tfPrezzo.getText().isEmpty() || tfPrezzo.getText().equals(null)) {
			alert("Errore", "Inserimento non corretto", "Prezzo errato o vuoto");
			return;
		}
		double dPrezzo = 0;
		CategoriaPietanza categoria = cbCategoria.getValue();
		String nome = pietanzaDaVisualizzare.getNome();
		String sPrezzo = tfPrezzo.getText().trim();
		try {
			dPrezzo = Double.parseDouble(sPrezzo);
		}catch(NumberFormatException e) {
			alert("Errore", "Inserimento non corretto", "Prezzo deve essere un numero");
			return;
		}
		try {
			controllerM.modificaPietanza(nome, dPrezzo, categoria);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuPane menu = new MenuPane(controllerO, controllerA, controllerM, controllerL);
		Scene oldScene = new Scene(menu, 750, 660, Color.BEIGE);
		ClientRistoratoreApp.getStage().setScene(oldScene);
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}