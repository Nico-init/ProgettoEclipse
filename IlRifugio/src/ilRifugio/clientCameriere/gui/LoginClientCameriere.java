package ilRifugio.clientCameriere.gui;

import java.rmi.RemoteException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginClientCameriere extends BorderPane {

	private TextField username;
	private PasswordField password;
	private Button accedi;
	private VBox onlyPane;
	
	public LoginClientCameriere() {
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		
		Label ristorante = new Label("CAMERIERE");
		onlyPane.getChildren().add(ristorante);
		onlyPane.setSpacing(25);
		
		Label rifugio = new Label("IL RIFUGIO");
		onlyPane.getChildren().add(rifugio);
		onlyPane.setSpacing(100);
		
		username = new TextField();
		username.setPromptText("Username");
		username.setMaxWidth(400);
		onlyPane.getChildren().add(username);
		onlyPane.setSpacing(25);
		
		password = new PasswordField();
		password.setPromptText("Password");
		password.setMaxWidth(400);
		onlyPane.getChildren().add(password);
		onlyPane.setSpacing(25);
		
		accedi = new Button("ACCEDI");
		accedi.setOnAction(e -> { 
			if (username.getText().isEmpty() || username.getText().equals(null)  || username.getText().length() > 20 ||
				password.getText().isEmpty() || password.getText().equals(null) || password.getText().length() > 20){
				alert("Errore", "Inserimento non corretto", "Username o password errati!");
				return;
			}
			else {
				String res;
				try {
					res = ClientCameriereApp.serverRistorante.autentica(username.getText(), password.getText());
				} catch (RemoteException e1) {
					res = null;
				}
				if (res != null && res.split(":")[1].equals("CAMERIERE")) {
					HomeClientCameriere home;
					try {
						home = new HomeClientCameriere();
						Scene newScene = new Scene(home, 750, 660, Color.BEIGE);
						ClientCameriereApp.getStage().setScene(newScene);
					} catch (RemoteException e1) {
					}
				} else
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
