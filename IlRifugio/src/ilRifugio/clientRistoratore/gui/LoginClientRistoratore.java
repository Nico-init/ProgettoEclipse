package ilRifugio.clientRistoratore.gui;

import java.rmi.Naming;
import java.rmi.RemoteException;

import ilRifugio.clientCameriere.gui.ClientCameriereApp;
import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerLogin;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.serverRistorante.IServerRistorante;
import ilRifugio.serverRistorante.ServerRistorante;
import ilRifugio.serverLogin.ControllerLogin;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginClientRistoratore extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	
	private TextField username;
	private PasswordField password;
	private Button accedi;
	private VBox onlyPane;
	
	   
    private IControllerLogin controllerLogin;
	static int registryPort = 1099;
    static String registryHost = "localhost";
    static String serviceName = "ControllerLogin";
    static String completeName = "//" + registryHost + ":" + registryPort + "/" + serviceName;
    
	public LoginClientRistoratore(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		
		controllerLogin = null;
		try {
			controllerLogin = (IControllerLogin) Naming.lookup(completeName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if (controllerLogin == null)
			System.exit(2);
		
		
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		
		Label ristorante = new Label("RISTORATORE");
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
		
		//password = new TextField();
		password = new PasswordField();
		password.setPromptText("Password");
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
			else 
			try {
				System.out.println(password.getText());
				String res = controllerLogin.autentica(username.getText(), password.getText());
				if(res != null && res.split(":")[1].equals("RISTORATORE")) {
					System.out.println("Autenticato");
					HomeClientRistoratore home = new HomeClientRistoratore(controllerO, controllerA, controllerM, controllerL);
					//HomeClientRistoratore home = new HomeClientRistoratore(null);
					Scene newScene = new Scene(home, 750, 660, Color.BEIGE);
					ClientRistoratoreApp.getStage().setScene(newScene);
				} else {
					alert("Errore", "Inserimento non corretto", "Username o Password errato");
				}
			}catch(Exception error) {
				error.printStackTrace();
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
