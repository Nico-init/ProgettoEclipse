package ilRifugio.clientRistoratore.gui;

import java.rmi.RemoteException;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.ICoperto;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
public class OrdinePaneClientRistoratore extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private IOrdine iOrdine;
	private Button elimina, chiudi;
	private TextArea taDataOra, taTavolo, taCoperti, taTotale;
	private TableView<IOrdine> tableCoperti;
	private TableView<BevandaOrdinata> tableBevande;
	private TableView<PietanzaOrdinata> tablePietanze;
	private VBox onlyPane;
	private HBox hCoperti, hDettagli, hBevande, hPietanze, hTotale;
	
	@SuppressWarnings({ "unchecked" })
	public OrdinePaneClientRistoratore(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		this.iOrdine = ClientRistoratoreApp.getIOrdineDaVisualizzare();
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
		
		Label ordineL = new Label("Dettaglio Ordine");
		onlyPane.getChildren().add(ordineL);
		onlyPane.setSpacing(10);
		
		hDettagli = new HBox();
		hDettagli.setAlignment(Pos.CENTER);
		hDettagli.setSpacing(5);
	
		Label data = new Label("Data                        ");
		hDettagli.getChildren().add(data);
		hDettagli.setSpacing(5);
		
		taDataOra = new TextArea();
		taDataOra.setText(iOrdine.getDataOra().toString());
		taDataOra.setMaxWidth(200);
		taDataOra.setMaxHeight(20);
		taDataOra.setEditable(false);
		hDettagli.getChildren().add(taDataOra);
		hDettagli.setSpacing(5);
		
		Label tavolo = new Label("                     Tavolo    ");
		hDettagli.getChildren().add(tavolo);
		hDettagli.setSpacing(5);
		
		taTavolo = new TextArea();
		taTavolo.setText(iOrdine.getNomeTavolo());
		taTavolo.setMaxWidth(195);
		taTavolo.setMaxHeight(20);
		taTavolo.setEditable(false);
		hDettagli.getChildren().add(taTavolo);
		hDettagli.setSpacing(5);
		onlyPane.getChildren().add(hDettagli);
		onlyPane.setSpacing(10);
		
		hCoperti = new HBox();
		hCoperti.setAlignment(Pos.CENTER);
		hCoperti.setSpacing(10);
		
		Label coperti = new Label("Coperti");
		hCoperti.getChildren().add(coperti);
		hCoperti.setSpacing(5);
		
		ObservableList<IOrdine> tvCopertiObservableList = FXCollections.observableArrayList();
		tvCopertiObservableList.addAll(iOrdine);
		tableCoperti = new TableView<IOrdine>();		
		tableCoperti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableCoperti.setMaxWidth(250);
		tableCoperti.setMaxHeight(50);
		tableCoperti.setItems(tvCopertiObservableList);
        TableColumn<IOrdine, Integer> colCopertiAdulti = new TableColumn<IOrdine, Integer>("N� Adulti");
        colCopertiAdulti.setCellValueFactory(new PropertyValueFactory<>("copertiAdulti"));
        TableColumn<IOrdine, Integer> colCopertiBambini = new TableColumn<IOrdine, Integer>("N� Bambini");
        colCopertiBambini.setCellValueFactory(new PropertyValueFactory<>("copertiBambini"));  
        tableCoperti.getColumns().addAll(colCopertiAdulti, colCopertiBambini);
        hCoperti.getChildren().add(tableCoperti);
        hCoperti.setSpacing(5);
        
        taCoperti = new TextArea();
		taCoperti.setEditable(false);
		taCoperti.setMaxWidth(250);
		taCoperti.setMaxHeight(50);
		taCoperti.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		try {
			for(ICoperto c :controllerM.elencaCoperti()) {
				if (c.getNome().equalsIgnoreCase("Adulto")) {
					taCoperti.appendText("Prezzo Adulti:\n" + c.getPrezzo() + " Euro\n");
				}
				else if (c.getNome().equalsIgnoreCase("Bambino")) {
					taCoperti.appendText("Prezzo Bambini:\n" + c.getPrezzo() + " Euro\n");
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		hCoperti.getChildren().add(taCoperti);
		hCoperti.setSpacing(50);
		onlyPane.getChildren().add(hCoperti);
		onlyPane.setSpacing(10);
		
		hBevande = new HBox();
		hBevande.setAlignment(Pos.CENTER);
		hBevande.setSpacing(10);
        
        ObservableList<BevandaOrdinata> tvBevandeObservableList = FXCollections.observableArrayList();
		tvBevandeObservableList.addAll(iOrdine.getBevande());
		tableBevande = new TableView<BevandaOrdinata>();		
		tableBevande.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableBevande.setMinWidth(500);
		tableBevande.setMaxHeight(180);
		tableBevande.setItems(tvBevandeObservableList);
        TableColumn<BevandaOrdinata, IBevanda> colNomeBevanda = new TableColumn<BevandaOrdinata, IBevanda>("Bevanda e Prezzo");
        colNomeBevanda.setCellValueFactory(new PropertyValueFactory<>("bevanda"));
        colNomeBevanda.setMinWidth(150);
        TableColumn<BevandaOrdinata, Integer> colQuantitaBevanda = new TableColumn<BevandaOrdinata, Integer>("Quantit�");
        colQuantitaBevanda.setCellValueFactory(new PropertyValueFactory<>("quantita"));  
        tableBevande.getColumns().addAll(colNomeBevanda, colQuantitaBevanda);
        hBevande.getChildren().add(tableBevande);
        hBevande.setSpacing(10);
		onlyPane.getChildren().add(hBevande);
		onlyPane.setSpacing(10);
		
		hPietanze = new HBox();
		hPietanze.setAlignment(Pos.CENTER);
		hPietanze.setSpacing(10);
		
        ObservableList<PietanzaOrdinata> tvPietanzeObservableList = FXCollections.observableArrayList();
		tvPietanzeObservableList.addAll(iOrdine.getPietanze());
		tablePietanze = new TableView<PietanzaOrdinata>();		
		tablePietanze.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablePietanze.setMinWidth(500);
		tablePietanze.setMaxHeight(180);
		tablePietanze.setItems(tvPietanzeObservableList);
        TableColumn<PietanzaOrdinata, IPietanza> colNomePietanza = new TableColumn<PietanzaOrdinata, IPietanza>("Pietanza, Prezzo e Categoria");
        colNomePietanza.setCellValueFactory(new PropertyValueFactory<>("pietanza"));
        colNomePietanza.setMinWidth(150);
        TableColumn<PietanzaOrdinata, Integer> colQuantita = new TableColumn<PietanzaOrdinata, Integer>("Quantit�"); 
        colQuantita.setCellValueFactory(new PropertyValueFactory<>("quantita"));  
        TableColumn<PietanzaOrdinata, String> colNote = new TableColumn<PietanzaOrdinata, String>("Note");
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));  
        TableColumn<PietanzaOrdinata, Boolean> colConsegnato = new TableColumn<PietanzaOrdinata, Boolean>("Consegnato");
        colConsegnato.setCellValueFactory(new PropertyValueFactory<>("consegnato"));  
        tablePietanze.getColumns().addAll(colNomePietanza, colQuantita, colNote, colConsegnato);
        hPietanze.getChildren().add(tablePietanze);
        hPietanze.setSpacing(10);
        onlyPane.getChildren().add(hPietanze);
		onlyPane.setSpacing(10);
		
		hTotale = new HBox();
		hTotale.setAlignment(Pos.CENTER);
		hTotale.setSpacing(10);
		
		Label totale = new Label("Totale:");
		hTotale.getChildren().add(totale);
		hTotale.setSpacing(10);
		
		taTotale = new TextArea();
		double dTotale = 0.0;
		try {
			for(ICoperto c :controllerM.elencaCoperti()) {
				if (c.getNome().equalsIgnoreCase("ADULTI")) {
					dTotale += iOrdine.getCopertiAdulti() * c.getPrezzo();
				}
				else if (c.getNome().equalsIgnoreCase("BAMBINI")) {
					dTotale += iOrdine.getCopertiBambini() * c.getPrezzo();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(BevandaOrdinata bo :iOrdine.getBevande()) {
			dTotale += bo.getBevanda().getPrezzo() * bo.getQuantita();
		}
		for(PietanzaOrdinata bo :iOrdine.getPietanze()) {
			dTotale += bo.getPietanza().getPrezzo() * bo.getQuantita();
		}
		try {
			taTotale.setText(controllerO.dettagliOrdine(iOrdine).split("_")[6] + " Euro");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		taTotale.setMaxWidth(120);
		taTotale.setMaxHeight(10);
		taTotale.setEditable(false);
		hTotale.getChildren().add(taTotale);
		hTotale.setSpacing(10);
		onlyPane.getChildren().add(hTotale);
		onlyPane.setSpacing(10);
       
		elimina = new Button("ELIMINA");
		elimina.setAlignment(Pos.CENTER);
		elimina.setOnAction(event -> {
			try {
				controllerO.eliminaOrdine(iOrdine);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HomeClientRistoratore home = new HomeClientRistoratore(controllerO, controllerA, controllerM, controllerL);
			Scene newScene = new Scene(home, 750, 660, Color.BEIGE);
			ClientRistoratoreApp.getStage().setScene(newScene);
         });
        onlyPane.getChildren().add(elimina);
		onlyPane.setSpacing(15);
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
}
