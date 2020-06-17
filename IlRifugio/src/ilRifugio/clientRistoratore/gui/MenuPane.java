package ilRifugio.clientRistoratore.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private Button inserisci, aggiungiBevanda, aggiungiPietanza, chiudi, btnRimuoviBevanda, btnRimuoviPietanza, btnModificaBevanda, btnModificaPietanza;
	private TextArea taCoperti, taBevande, taPietanze;
	private TextField tfPrezzo;
	private ComboBox<String> cbTipo, cbBevande, cbPietanze;
	private VBox onlyPane, vCoperti, vBevande, vPietanze;
	private HBox hCoperti, hTipo, hPrezzo, hBevande, hPietanze;
	
	public MenuPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
		
		Label ordineL = new Label("Menù");
		onlyPane.getChildren().add(ordineL);
		onlyPane.setSpacing(10);
		
		hCoperti = new HBox();
		hCoperti.setAlignment(Pos.CENTER);
		hCoperti.setSpacing(10);
		
		Label coperti = new Label("Coperti");
		hCoperti.getChildren().add(coperti);
		hCoperti.setSpacing(5);
		
		taCoperti = new TextArea();
		taCoperti.setText("");
		taCoperti.setEditable(false);
		taCoperti.setMaxWidth(260);
		taCoperti.setMinHeight(70);
		taCoperti.setMaxHeight(70);
		taCoperti.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		try {
			System.out.println(controllerM.elencaCoperti());
			for(ICoperto c : controllerM.elencaCoperti()) {
				if (c.getNome().equalsIgnoreCase("Adulto")) {
					taCoperti.appendText("Prezzo Adulto: " + c.getPrezzo() + " Euro\n");
				}
				else if (c.getNome().equalsIgnoreCase("Bambino")) {
					taCoperti.appendText("Prezzo Bambino: " + c.getPrezzo() + " Euro\n");
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		hCoperti.getChildren().add(taCoperti);
		hCoperti.setSpacing(50);
			
		vCoperti = new VBox();
		vCoperti.setAlignment(Pos.CENTER_RIGHT);
		vCoperti.setSpacing(10);
			
		hTipo = new HBox();
		hTipo.setAlignment(Pos.CENTER);
		
		Label tipo = new Label("Tipo   ");
		tipo.setAlignment(Pos.CENTER_LEFT);
		hTipo.getChildren().add(tipo);
			
		String sCopertiBambini = "Adulto";
		String sCopertiAdulti = "Bambino";
		ObservableList<String> sCoperti = FXCollections.observableArrayList();
		sCoperti.add(sCopertiBambini);			
		sCoperti.add(sCopertiAdulti);
		cbTipo = new ComboBox<String>(sCoperti);
		cbTipo.setEditable(false);
		cbTipo.setMinWidth(200);
		hTipo.getChildren().add(cbTipo);
		hTipo.setSpacing(20);
		vCoperti.getChildren().add(hTipo);
		vCoperti.setSpacing(20);
			
		hPrezzo = new HBox();
		hPrezzo.setAlignment(Pos.CENTER);
			
		Label prezzo = new Label("Prezzo");
		prezzo.setAlignment(Pos.CENTER_LEFT);
		hPrezzo.getChildren().add(prezzo);
			
		tfPrezzo = new TextField();
		tfPrezzo.setText("");
		tfPrezzo.setMinWidth(200);
		tfPrezzo.setAlignment(Pos.CENTER_RIGHT);
		hPrezzo.getChildren().add(tfPrezzo);
		hPrezzo.setSpacing(20);
		vCoperti.getChildren().add(hPrezzo);
		vCoperti.setSpacing(20);
		hCoperti.getChildren().add(vCoperti);
		hCoperti.setSpacing(20);
		onlyPane.getChildren().add(hCoperti);
		onlyPane.setSpacing(50);
		
        inserisci = new Button("INSERISCI");
        inserisci.setOnAction(event -> {
        	if(cbTipo.getValue() == null) {
        		alert("Errore", "Inserimento non corretto", "Tipo nullo");
    			return;
        	}
        	if(tfPrezzo.getText().isEmpty() || tfPrezzo.getText().equals(null)) {
    			alert("Errore", "Inserimento non corretto", "Prezzo errato o vuoto");
    			return;
    		}
    		double dPrezzo = 0;
    		String sPrezzo = tfPrezzo.getText().trim();
    		try {
    			dPrezzo = Double.parseDouble(sPrezzo);
    		}catch(NumberFormatException e) {
    			alert("Errore", "Inserimento non corretto", "Prezzo deve essere un numero");
    			return;
    		}
    		try {
				controllerM.modificaCoperto(cbTipo.getValue(), dPrezzo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	taCoperti.setText("");
        	try {
				for(ICoperto c :controllerM.elencaCoperti()) {
					if (c.getNome().equalsIgnoreCase("Adulto")) {
						taCoperti.appendText("Prezzo Adulto: " + c.getPrezzo() + " Euro\n");
					}
					else if (c.getNome().equalsIgnoreCase("Bambino")) {
						taCoperti.appendText("Prezzo Bambino: " + c.getPrezzo() + " Euro\n");
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         });
        onlyPane.getChildren().add(inserisci);
        onlyPane.setSpacing(10);
		
		hBevande = new HBox();
		hBevande.setAlignment(Pos.CENTER);
		hBevande.setSpacing(10);
		
		Label bevande = new Label("Elenco Bevande");
		hBevande.getChildren().add(bevande);
		hBevande.setSpacing(5);
		
		taBevande = new TextArea();
		taBevande.setText("");
		taBevande.setEditable(false);
		taBevande.setMaxWidth(370);
		taBevande.setMinWidth(370);
		taBevande.setMinHeight(100);
		taBevande.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		try {
			for(IBevanda b : controllerM.elencaBevande()) {
				taBevande.appendText("Nome: " + b.getNome() + "\tPrezzo: " + b.getPrezzo() + "\n");		
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hBevande.getChildren().add(taBevande);
		hBevande.setSpacing(5);
		
		vBevande = new VBox();
		vBevande.setAlignment(Pos.CENTER_RIGHT);
		vBevande.setSpacing(10);
		
		ObservableList<String> bevandeObservableList = FXCollections.observableArrayList();
		try {
			for(IBevanda b : controllerM.elencaBevande()) {
				bevandeObservableList.add(b.getNome());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbBevande = new ComboBox<String>(bevandeObservableList);
		cbBevande.setEditable(false);
		cbBevande.setPrefWidth(200);
		vBevande.getChildren().add(cbBevande);
		vBevande.setSpacing(5);
		
		btnRimuoviBevanda = new Button("Rimuovi");
		btnRimuoviBevanda.setAlignment(Pos.CENTER);
        btnRimuoviBevanda.setOnAction(event -> {
        	if(cbBevande.getValue() == null) {
        		alert("Errore", "Inserimento non corretto", "Bevanda nulla");
    			return;
        	}
        	else {
	        	try {
					controllerM.rimuoviElemento("Bevanda", cbBevande.getValue());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	cbBevande.setValue(null);
	        	bevandeObservableList.clear();
	        	try {
					for(IBevanda b : controllerM.elencaBevande()) {
						bevandeObservableList.add(b.getNome());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		cbBevande.setItems(bevandeObservableList);;
	        	taBevande.setText("");
	        	try {
					for(IBevanda b : controllerM.elencaBevande()) {
						taBevande.appendText("Nome: " + b.getNome() + "\tPrezzo: " + b.getPrezzo() + "\n");		
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        vBevande.getChildren().add(btnRimuoviBevanda);
		vBevande.setSpacing(5);
		
        btnModificaBevanda = new Button("Modifica");
        btnModificaBevanda.setAlignment(Pos.CENTER);
        btnModificaBevanda.setOnAction(event -> {
        	if(cbBevande.getValue() == null) {
        		alert("Errore", "Inserimento non corretto", "Bevanda nulla");
    			return;
        	}
        	else {
        		try {
					for(IBevanda b : controllerM.elencaBevande()) {
						if(b.getNome().equalsIgnoreCase(cbBevande.getValue()))	{
							ClientRistoratoreApp.setBevandaDaVisualizzare(b);
							ModificaBevandaMenuPane modificaB = new ModificaBevandaMenuPane(controllerO, controllerA, controllerM, controllerL);
							Scene nextSceneMB = new Scene(modificaB, 750, 660, Color.BEIGE);
							ClientRistoratoreApp.getStage().setScene(nextSceneMB);
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}                 
        });
        vBevande.getChildren().add(btnModificaBevanda);
		vBevande.setSpacing(5);
        hBevande.getChildren().add(vBevande);
        hBevande.setSpacing(10);
        onlyPane.getChildren().add(hBevande);
        onlyPane.setSpacing(10);
        
		aggiungiBevanda = new Button("AGGIUNGI BEVANDA");
		aggiungiBevanda.setAlignment(Pos.CENTER);
		aggiungiBevanda.setOnAction(event -> {
			AggiungiBevandaAlMenuPane aggiungiB = new AggiungiBevandaAlMenuPane(controllerO, controllerA, controllerM, controllerL);
			Scene nextSceneAB = new Scene(aggiungiB, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(nextSceneAB);
         });
		onlyPane.getChildren().add(aggiungiBevanda);
		onlyPane.setSpacing(10);
		
		hPietanze = new HBox();
		hPietanze.setAlignment(Pos.CENTER);
		hPietanze.setSpacing(10);
		
		Label pietanze = new Label("Elenco Pietanze");
		hPietanze.getChildren().add(pietanze);
		hPietanze.setSpacing(5);
		
		taPietanze = new TextArea();
		taPietanze.setText("");
		taPietanze.setEditable(false);
		taPietanze.setMaxWidth(370);
		taPietanze.setMinWidth(370);
		taPietanze.setMinHeight(100);
		taPietanze.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		for(CategoriaPietanza c : CategoriaPietanza.values()) {
			taPietanze.appendText("Categoria: " + c.toString() + "\n");
			try {
				for(IPietanza p : controllerM.elencaPietanze()) {
					if(p.getCategoriaPietanza().equals(c)) {
						taPietanze.appendText("Nome: " + p.getNome() + "\tPrezzo: " + p.getPrezzo() + "\n");	
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hPietanze.getChildren().add(taPietanze);
		hPietanze.setSpacing(5);
		
		vPietanze = new VBox();
		vPietanze.setAlignment(Pos.CENTER_RIGHT);
		vPietanze.setSpacing(10);
		
		ObservableList<String> pietanzeObservableList = FXCollections.observableArrayList();
		try {
			for(IPietanza p : controllerM.elencaPietanze()) {
				pietanzeObservableList.add(p.getNome());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbPietanze = new ComboBox<String>(pietanzeObservableList);
		cbPietanze.setEditable(false);
		cbPietanze.setPrefWidth(200);
		vPietanze.getChildren().add(cbPietanze);
		vPietanze.setSpacing(5);
		
		btnRimuoviPietanza = new Button("Rimuovi");
		btnRimuoviPietanza.setAlignment(Pos.CENTER);
		btnRimuoviPietanza.setOnAction(event -> {
        	if(cbPietanze.getValue() == null) {
        		alert("Errore", "Inserimento non corretto", "Pietanza nulla");
    			return;
        	}
        	else {
	        	try {
					controllerM.rimuoviElemento("Pietanza", cbPietanze.getValue());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	cbPietanze.setValue(null);
	        	pietanzeObservableList.clear();
	    		try {
					for(IPietanza p : controllerM.elencaPietanze()) {
						pietanzeObservableList.add(p.getNome());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	cbPietanze.setItems(pietanzeObservableList);;
	        	taPietanze.setText("");
	        	for(CategoriaPietanza c : CategoriaPietanza.values()) {
	    			taPietanze.appendText("Categoria: " + c.toString() + "\n");
	    			try {
						for(IPietanza p : controllerM.elencaPietanze()) {
							if(p.getCategoriaPietanza().equals(c)) {
								taPietanze.appendText("Nome: " + p.getNome() + "\tPrezzo: " + p.getPrezzo() + "\n");	
							}
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
        	}
        });
		vPietanze.getChildren().add(btnRimuoviPietanza);
		vPietanze.setSpacing(5);
		
        btnModificaPietanza = new Button("Modifica");
        btnModificaPietanza.setAlignment(Pos.CENTER);
        btnModificaPietanza.setOnAction(event -> {
        	if(cbPietanze.getValue() == null) {
        		alert("Errore", "Inserimento non corretto", "Pietanza nulla");
    			return;
        	}	
        	else {
        		try {
					for(IPietanza p : controllerM.elencaPietanze()) {
						if(p.getNome().equalsIgnoreCase(cbPietanze.getValue()))	{
							ClientRistoratoreApp.setPietanzaDaVisualizzare(p);
							ModificaPietanzaMenuPane modificaP = new ModificaPietanzaMenuPane(controllerO, controllerA, controllerM, controllerL);
							Scene nextSceneMP = new Scene(modificaP, 750, 660, Color.BEIGE);
							ClientRistoratoreApp.getStage().setScene(nextSceneMP);
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}                 
        });
        vPietanze.getChildren().add(btnModificaPietanza);
        vPietanze.setSpacing(5);
		hPietanze.getChildren().add(vPietanze);
		hPietanze.setSpacing(10);
        onlyPane.getChildren().add(hPietanze);
        onlyPane.setSpacing(10);
        
		aggiungiPietanza = new Button("AGGIUNGI PIETANZA");
		aggiungiPietanza.setAlignment(Pos.CENTER);
		aggiungiPietanza.setOnAction(event -> {
			AggiungiPietanzaAlMenuPane aggiungiP = new AggiungiPietanzaAlMenuPane(controllerO, controllerA, controllerM, controllerL);
			Scene nextSceneAP = new Scene(aggiungiP, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(nextSceneAP);
         });
        onlyPane.getChildren().add(aggiungiPietanza);
		onlyPane.setSpacing(10);
		this.setTop(onlyPane);
		
		chiudi = new Button("CHIUDI");
		chiudi.setAlignment(Pos.BOTTOM_RIGHT);
		chiudi.setOnAction(event -> {
			HomeClientRistoratore home = new HomeClientRistoratore(controllerO, controllerA, controllerM, controllerL);
			Scene oldScene = new Scene(home, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(oldScene);
         });
		this.setRight(chiudi);
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
