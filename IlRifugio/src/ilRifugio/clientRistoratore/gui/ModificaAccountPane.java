package ilRifugio.clientRistoratore.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ModificaAccountPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private Account accountVisualizzare;
	private Button inserisci, annulla;
	private TextArea taNome;
	private TextField tfUsername;
	private PasswordField tfPassword;
	private ComboBox<RuoloAccount> cbTipo;
	private VBox onlyPane;
	private HBox hNome, hTipo, hUsername, hPassword, hBottoni;
	
	public ModificaAccountPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		this.accountVisualizzare = ClientRistoratoreApp.getAccountDaVisualizzare();
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
	
		Label titolo = new Label("Modifica Account");
		onlyPane.getChildren().add(titolo);
		onlyPane.setSpacing(10); 
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                            ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		taNome = new TextArea();
		taNome.setText(accountVisualizzare.getNome());
		taNome.setEditable(false);
		taNome.setPrefWidth(200);
		taNome.setPrefHeight(5);
		hNome.getChildren().add(taNome);
		hNome.setSpacing(20);
		onlyPane.getChildren().add(hNome);
		onlyPane.setSpacing(50);
		
		hTipo = new HBox();
		hTipo.setAlignment(Pos.CENTER);
	
		Label tipo = new Label("Tipo                               ");
		tipo.setAlignment(Pos.CENTER_LEFT);
		hTipo.getChildren().add(tipo);
		
		cbTipo = new ComboBox<RuoloAccount>(FXCollections.observableArrayList(RuoloAccount.values()));
		cbTipo.setEditable(false);
		cbTipo.setPrefWidth(200);
		hTipo.getChildren().add(cbTipo);
		hTipo.setSpacing(20);
		onlyPane.getChildren().add(hTipo);
		onlyPane.setSpacing(50);
		
		hUsername = new HBox();
		hUsername.setAlignment(Pos.CENTER);
	
		Label username = new Label("Username                       ");
		username.setAlignment(Pos.CENTER_LEFT);
		hUsername.getChildren().add(username);
		
		tfUsername = new TextField();
		tfUsername.setText("");
		tfUsername.setPrefWidth(200);
		tfUsername.setAlignment(Pos.CENTER_RIGHT);
		hUsername.getChildren().add(tfUsername);
		hUsername.setSpacing(20);
		onlyPane.getChildren().add(hUsername);
		onlyPane.setSpacing(50);
		
		hPassword = new HBox();
		hPassword.setAlignment(Pos.CENTER);
	
		Label password = new Label("Password                        ");
		password.setAlignment(Pos.CENTER_LEFT);
		hPassword.getChildren().add(password);
		
		tfPassword = new PasswordField();
		tfPassword.setText("");
		tfPassword.setPrefWidth(200);
		tfPassword.setAlignment(Pos.CENTER_RIGHT);
		hPassword.getChildren().add(tfPassword);
		hPassword.setSpacing(20);
		onlyPane.getChildren().add(hPassword);
		onlyPane.setSpacing(50);
		
		hBottoni = new HBox();
		hBottoni.setAlignment(Pos.CENTER);
		
		inserisci = new Button("INSERISCI");
		inserisci.setAlignment(Pos.CENTER_LEFT);
		inserisci.setOnAction(event -> {
			if(cbTipo.getValue() == null) {
				alert("Errore", "Inserimento non corretto", "Tipo nullo");
				return;
			}
			else if(tfUsername.getText().isEmpty() || tfUsername.getText().equals(null) || tfUsername.getText().length() > 20) {
				alert("Errore", "Inserimento non corretto", "Username errato, vuoto o con troppi caratteri (> 20)");
				return;
			}
			else if(tfPassword.getText().isEmpty() || tfPassword.getText().equals(null) || tfPassword.getText().length() > 20) {
				alert("Errore", "Inserimento non corretto", "Password errata, vuota o con troppi caratteri (> 20)");
				return;
			}
			else {
				String sTipo = cbTipo.getValue().toString();
				String sUsername = tfUsername.getText().trim();
				String sPassword = tfPassword.getText().trim();
				try {
					controllerA.modifica(accountVisualizzare.getNome(), sUsername, sPassword, sTipo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AccountPane aPane = new AccountPane(controllerO, controllerA, controllerM, controllerL);
				Scene newScene = new Scene(aPane, 750, 660, Color.BEIGE);
				ClientRistoratoreApp.getStage().setScene(newScene);
			}
		});
		hBottoni.getChildren().add(inserisci);
		hBottoni.setSpacing(30);
		
		annulla = new Button("ANNULLA");
		annulla.setAlignment(Pos.CENTER_RIGHT);
		annulla.setOnAction(event -> {
			AccountPane aPane = new AccountPane(controllerO, controllerA, controllerM, controllerL);
			Scene oldScene = new Scene(aPane, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(oldScene);
         });
		hBottoni.getChildren().add(annulla);
		hBottoni.setSpacing(30);
		onlyPane.getChildren().add(hBottoni);
		onlyPane.setSpacing(50);
		this.setCenter(onlyPane);
	 }
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
