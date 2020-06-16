package ilRifugio.clientCameriere.gui;

import ilRifugio.interfacce.controller.IControllerOrdine;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginClientCameriere extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	private TextField username, password;
	private Button accedi;
	private VBox onlyPane;
	
	public LoginClientCameriere(IControllerOrdine controllerO) {
		this.controllerO = controllerO;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		
		Label ristorante = new Label("RISTORANTE");
		onlyPane.getChildren().add(ristorante);
		onlyPane.setSpacing(25);
		
		Label rifugio = new Label("IL RIFUGIO");
		onlyPane.getChildren().add(rifugio);
		onlyPane.setSpacing(100);
		
		username = new TextField();
		username.setText("Username");
		username.setMaxWidth(400);
		onlyPane.getChildren().add(username);
		onlyPane.setSpacing(25);
		
		password = new TextField();
		password.setText("Password");
		password.setMaxWidth(400);
		onlyPane.getChildren().add(password);
		onlyPane.setSpacing(25);
		
		accedi = new Button("ACCEDI");
		accedi.setOnAction(e -> { 
			if(username.getText().isEmpty() || username.getText().equals(null)  || username.getText().length() > 20) {
				alert("Errore", "Inserimento non corretto", "Username errato, vuoto o con troppi caratteri (> 20)");
				return;
			}
			else if(password.getText().isEmpty() || password.getText().equals(null) || password.getText().length() > 20){
				alert("Errore", "Inserimento non corretto", "password errata, vuota o con troppi caratteri (> 20)");
				return;
			}
			else if (username.getText().contentEquals("Username") && password.getText().contentEquals("Password")) { //PROVA
				HomeClientCameriere home = new HomeClientCameriere(controllerO);
				Scene newScene = new Scene(home, 750, 660, Color.BEIGE);
				ClientCameriereApp.getStage().setScene(newScene);
			}
			else {
				alert("Errore", "Inserimento non corretto", "Username o Password errato");
			}
		});
		onlyPane.getChildren().add(accedi);
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
