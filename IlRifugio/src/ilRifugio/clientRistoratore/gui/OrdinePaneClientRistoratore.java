package ilRifugio.clientRistoratore.gui;

import ilRifugio.clientCameriere.gui.AggiungiBevandaPane;
import ilRifugio.clientCameriere.gui.AggiungiPietanzaPane;
import ilRifugio.clientCameriere.gui.ClientCameriereApp;
import ilRifugio.clientCameriere.gui.HomeClientCameriere;
import ilRifugio.clientCameriere.gui.ModificaBevandaPane;
import ilRifugio.clientCameriere.gui.ModificaDettagliPane;
import ilRifugio.clientCameriere.gui.ModificaPietanzaPane;
import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IBevanda;
import ilRifugio.interfacce.dominio.IOrdine;
import ilRifugio.interfacce.dominio.IPietanza;
import ilRifugio.serverRistorante.dominio.BevandaOrdinata;
import ilRifugio.serverRistorante.dominio.OrdineConsegna;
import ilRifugio.serverRistorante.dominio.PietanzaOrdinata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

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
	private Button aggiungiBevanda, aggiungiPietanza, modificaDettagli, chiudi;
	private TextArea taDataOra, taTavolo;
	private TableView<IOrdine> tableCoperti;
	private TableView<BevandaOrdinata> tableBevande;
	private TableView<PietanzaOrdinata> tablePietanze;
	private VBox onlyPane;
	private HBox hDataOra, hDettagli, hBevande, hPietanze;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OrdinePaneClientRistoratore(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
		
		Label ordineL = new Label("Ordine");
		onlyPane.getChildren().add(ordineL);
		onlyPane.setSpacing(10);
		
		hDataOra = new HBox();
		hDataOra.setAlignment(Pos.CENTER);
		hDataOra.setSpacing(10);
	
		Label data = new Label("Data");
		hDataOra.getChildren().add(data);
		hDataOra.setSpacing(10);
		
		taDataOra = new TextArea();
		taDataOra.setText(iOrdine.getDataOra().toString());
		taDataOra.setMaxWidth(200);
		taDataOra.setMaxHeight(30);
		taDataOra.setEditable(false);
		hDataOra.getChildren().add(taDataOra);
		hDataOra.setSpacing(10);
		onlyPane.getChildren().add(hDataOra);
		onlyPane.setSpacing(10);
		
		hDettagli = new HBox();
		hDettagli.setAlignment(Pos.CENTER);
		hDettagli.setSpacing(10);
		
		Label tavolo = new Label("Tavolo");
		hDettagli.getChildren().add(tavolo);
		hDettagli.setSpacing(10);
		
		taTavolo = new TextArea();
		taTavolo.setText(iOrdine.getNomeTavolo());
		taTavolo.setMaxWidth(200);
		taTavolo.setMaxHeight(30);
		taTavolo.setEditable(false);
		hDettagli.getChildren().add(taTavolo);
		hDettagli.setSpacing(10);
		
		Label coperti = new Label("Coperti");
		hDettagli.getChildren().add(coperti);
		hDettagli.setSpacing(10);
		
		ObservableList<IOrdine> tvCopertiObservableList = FXCollections.observableArrayList();
		tvCopertiObservableList.addAll(iOrdine);
		tableCoperti = new TableView<IOrdine>();		
		tableCoperti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableCoperti.setMaxWidth(300);
		tableCoperti.setMaxHeight(50);
		tableCoperti.setItems(tvCopertiObservableList);
        TableColumn<IOrdine, Integer> colCopertiAdulti = new TableColumn<IOrdine, Integer>("N� Adulti");
        colCopertiAdulti.setCellValueFactory(new PropertyValueFactory<>("copertiAdulti"));
        TableColumn<IOrdine, Integer> colCopertiBambini = new TableColumn<IOrdine, Integer>("N� Bambini");
        colCopertiBambini.setCellValueFactory(new PropertyValueFactory<>("copertiBambini"));  
        tableCoperti.getColumns().addAll(colCopertiAdulti, colCopertiBambini);
        hDettagli.getChildren().add(tableCoperti);
        hDettagli.setSpacing(10);
        
        modificaDettagli = new Button("MODIFICA DETTAGLI");
        modificaDettagli.setOnAction(event -> {
        	ModificaDettagliPane modificaD = new ModificaDettagliPane(controllerO);
			Scene nextSceneD = new Scene(modificaD, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(nextSceneD);
         });
        hDettagli.getChildren().add(modificaDettagli);
        hDettagli.setSpacing(10);
        onlyPane.getChildren().add(hDettagli);
		onlyPane.setSpacing(10);
		
		hBevande = new HBox();
		hBevande.setAlignment(Pos.CENTER);
		hBevande.setSpacing(10);
        
        Label bevande = new Label("Bevande");
        hBevande.getChildren().add(bevande);
        hBevande.setSpacing(10);
		
        ObservableList<BevandaOrdinata> tvBevandeObservableList = FXCollections.observableArrayList();
		tvBevandeObservableList.addAll(iOrdine.getBevande());
		tableBevande = new TableView<BevandaOrdinata>();		
		tableBevande.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableBevande.setMaxWidth(750);
		tableBevande.setMaxHeight(215);
		tableBevande.setItems(tvBevandeObservableList);
        TableColumn<BevandaOrdinata, IBevanda> colNomeBevanda = new TableColumn<BevandaOrdinata, IBevanda>("Nome");
        colNomeBevanda.setCellValueFactory(new PropertyValueFactory<>("bevanda"));
        TableColumn<BevandaOrdinata, Integer> colQuantitaBevanda = new TableColumn<BevandaOrdinata, Integer>("Quantit�");
        colQuantitaBevanda.setCellValueFactory(new PropertyValueFactory<>("quantita"));  
        TableColumn colRimuoviBevanda = new TableColumn("Rimuovi");
        colRimuoviBevanda.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<BevandaOrdinata, String>, TableCell<BevandaOrdinata, String>> cellFactoryRimuoviBevanda = new Callback<TableColumn<BevandaOrdinata, String>, TableCell<BevandaOrdinata, String>>() {
           
			@Override
            public TableCell call(final TableColumn<BevandaOrdinata, String> paramRimuoviBevanda) {
                final TableCell<BevandaOrdinata, String> cellRimuoviBevanda = new TableCell<BevandaOrdinata, String>() {

                    final Button btnRimuoviBevanda = new Button("                 ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnRimuoviBevanda.setOnAction(event -> {
                        		controllerO.rimuoviBevanda(iOrdine.getNomeTavolo(), iOrdine.getDataOra(), getTableView().getItems().get(getIndex()).getBevanda());
                        		tableBevande.getItems().clear();
                        		tvBevandeObservableList.clear();
                        		tvBevandeObservableList.addAll(iOrdine.getBevande());
                        		tableBevande.setItems(tvBevandeObservableList);
                        	
                        	});
                            setGraphic(btnRimuoviBevanda);
                            setText(null);
                        }
                    }
                };
                return cellRimuoviBevanda;
            }
        };
        colRimuoviBevanda.setCellFactory(cellFactoryRimuoviBevanda);
        
        TableColumn colModificaBevanda = new TableColumn("Modifica");
        colModificaBevanda.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<BevandaOrdinata, String>, TableCell<BevandaOrdinata, String>> cellFactoryModificaBevanda = new Callback<TableColumn<BevandaOrdinata, String>, TableCell<BevandaOrdinata, String>>() {
           
			@Override
            public TableCell call(final TableColumn<BevandaOrdinata, String> paramModificaBevanda) {
                final TableCell<BevandaOrdinata, String> cellModificaBevanda = new TableCell<BevandaOrdinata, String>() {

                    final Button btnModificaBevanda = new Button("                 ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnModificaBevanda.setOnAction(event -> {
                        		ClientCameriereApp.setBevandaOrdinataDaVisualizzare(getTableView().getItems().get(getIndex()));
                        		ModificaBevandaPane modificaB = new ModificaBevandaPane(controllerO);
                    			Scene nextSceneMB = new Scene(modificaB, 750, 660, Color.BEIGE);
                    			ClientCameriereApp.getStage().setScene(nextSceneMB);
                            });
                            setGraphic(btnModificaBevanda);
                            setText(null);
                        }
                    }
                };
                return cellModificaBevanda;
            }
        };
        colModificaBevanda.setCellFactory(cellFactoryModificaBevanda);
        tableBevande.getColumns().addAll(colNomeBevanda, colQuantitaBevanda, colRimuoviBevanda,  colModificaBevanda);
        hBevande.getChildren().add(tableBevande);
        hBevande.setSpacing(10);
        
		aggiungiBevanda = new Button("AGGIUNGI BEVANDA");
		aggiungiBevanda.setOnAction(event -> {
			AggiungiBevandaPane aggiungiB = new AggiungiBevandaPane(controllerO);
			Scene nextSceneAB = new Scene(aggiungiB, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(nextSceneAB);
         });
		hBevande.getChildren().add(aggiungiBevanda);
		hBevande.setSpacing(10);
		onlyPane.getChildren().add(hBevande);
		onlyPane.setSpacing(10);
		
		hPietanze = new HBox();
		hPietanze.setAlignment(Pos.CENTER);
		hPietanze.setSpacing(10);
        
        Label pietanze = new Label("Pietanze");
        hPietanze.getChildren().add(pietanze);
        hPietanze.setSpacing(10);
		
        ObservableList<PietanzaOrdinata> tvPietanzeObservableList = FXCollections.observableArrayList();
		tvPietanzeObservableList.addAll(iOrdine.getPietanze());
		tablePietanze = new TableView<PietanzaOrdinata>();		
		tablePietanze.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablePietanze.setMaxWidth(600);
		tablePietanze.setMaxHeight(215);
		tablePietanze.setItems(tvPietanzeObservableList);
        TableColumn<PietanzaOrdinata, IPietanza> colNomePietanza = new TableColumn<PietanzaOrdinata, IPietanza>("Nome");
        colNomePietanza.setCellValueFactory(new PropertyValueFactory<>("pietanza"));
        TableColumn<PietanzaOrdinata, Integer> colQuantitaPietanza = new TableColumn<PietanzaOrdinata, Integer>("Quantit�");
        colQuantitaPietanza.setCellValueFactory(new PropertyValueFactory<>("quantita"));  
      /*  TableColumn<Pietanza, CategoriaPietanza> colCategoriaPietanza = new TableColumn<Pietanza, CategoriaPietanza>("Categoria");
        colCategoriaPietanza.setCellValueFactory(new PropertyValueFactory<>("categoriaPietanza"));    */
        TableColumn<PietanzaOrdinata, OrdineConsegna> colOrdineConsegna = new TableColumn<PietanzaOrdinata, OrdineConsegna>("Ordine"); 
        colOrdineConsegna.setCellValueFactory(new PropertyValueFactory<>("ordineConsegna"));  
        TableColumn<PietanzaOrdinata, String> colNote = new TableColumn<PietanzaOrdinata, String>("Note");
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));  
        TableColumn<PietanzaOrdinata, Boolean> colConsegnato = new TableColumn<PietanzaOrdinata, Boolean>("Consegnato");
        colConsegnato.setCellValueFactory(new PropertyValueFactory<>("consegnato"));  
        TableColumn colRimuoviPietanza = new TableColumn("Rimuovi");
        colRimuoviPietanza.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<PietanzaOrdinata, String>, TableCell<PietanzaOrdinata, String>> cellFactoryRimuoviPietanza = new Callback<TableColumn<PietanzaOrdinata, String>, TableCell<PietanzaOrdinata, String>>() {
           
			@Override
            public TableCell call(final TableColumn<PietanzaOrdinata, String> paramRimuoviPietanza) {
                final TableCell<PietanzaOrdinata, String> cellRimuoviPietanza = new TableCell<PietanzaOrdinata, String>() {

                    final Button btnRimuoviPietanza = new Button("                 ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnRimuoviPietanza.setOnAction(event -> {
                        		controllerO.rimuoviPietanza(iOrdine.getNomeTavolo(), iOrdine.getDataOra(), getTableView().getItems().get(getIndex()).getPietanza(), getTableView().getItems().get(getIndex()).getOrdineConsegna());
                        		tablePietanze.getItems().clear();
                        		tvPietanzeObservableList.clear();
                        		tvPietanzeObservableList.addAll(iOrdine.getPietanze());
                        		tablePietanze.setItems(tvPietanzeObservableList);
                        	});
                            setGraphic(btnRimuoviPietanza);
                            setText(null);
                        }
                    }
                };
                return cellRimuoviPietanza;
            }
        };
        colRimuoviPietanza.setCellFactory(cellFactoryRimuoviPietanza);
        
        TableColumn colModificaPietanza = new TableColumn("Modifica");
        colModificaPietanza.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		Callback<TableColumn<PietanzaOrdinata, String>, TableCell<PietanzaOrdinata, String>> cellFactoryModificaPietanza = new Callback<TableColumn<PietanzaOrdinata, String>, TableCell<PietanzaOrdinata, String>>() {
           
			@Override
            public TableCell call(final TableColumn<PietanzaOrdinata, String> paramModificaPietanza) {
                final TableCell<PietanzaOrdinata, String> cellModificaPietanza = new TableCell<PietanzaOrdinata, String>() {

                    final Button btnModificaPietanza = new Button("                 ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnModificaPietanza.setOnAction(event -> {
                        		ClientCameriereApp.setPietanzaOrdinataDaVisualizzare(getTableView().getItems().get(getIndex()));
                        		ModificaPietanzaPane modificaP = new ModificaPietanzaPane(controllerO);
                    			Scene nextSceneMP = new Scene(modificaP, 750, 660, Color.BEIGE);
                    			ClientCameriereApp.getStage().setScene(nextSceneMP);
                            });
                            setGraphic(btnModificaPietanza);
                            setText(null);
                        }
                    }
                };
                return cellModificaPietanza;
            }
        };
        colModificaPietanza.setCellFactory(cellFactoryModificaPietanza);
        tablePietanze.getColumns().addAll(colNomePietanza, colQuantitaPietanza, colOrdineConsegna, colNote, colConsegnato, colRimuoviPietanza, colModificaPietanza);
        hPietanze.getChildren().add(tablePietanze);
        hPietanze.setSpacing(10);
        onlyPane.getChildren().add(hPietanze);
		onlyPane.setSpacing(10);
        
		aggiungiPietanza = new Button("AGGIUNGI PIETANZA");
		aggiungiPietanza.setOnAction(event -> {
			AggiungiPietanzaPane aggiungiP = new AggiungiPietanzaPane(controllerO);
			Scene nextSceneAP = new Scene(aggiungiP, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(nextSceneAP);
         });
        onlyPane.getChildren().add(aggiungiPietanza);
		onlyPane.setSpacing(10);
		this.setTop(onlyPane);
		
		chiudi = new Button("CHIUDI");
		chiudi.setAlignment(Pos.BOTTOM_RIGHT);
		chiudi.setOnAction(event -> {
			HomeClientCameriere home = new HomeClientCameriere(controllerO);
			Scene oldScene = new Scene(home, 750, 660, Color.BEIGE);
			ClientCameriereApp.getStage().setScene(oldScene);
         });
		this.setRight(chiudi);
	}
}
