package ilRifugio.clientRistoratore.gui;

import java.util.Date;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import ilRifugio.interfacce.dominio.IOrdine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class HomeClientRistoratore extends BorderPane {
	
	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private Button account, log, menu, chiudi;
	private TableView<IOrdine> table;
	private VBox onlyPane;
	private HBox hBottoni;
	@SuppressWarnings("unused")
	public IOrdine ordineDaVisualizzare;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HomeClientRistoratore(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
	
		Label titolo = new Label("Sistema di Gestione Ristoratore");
		onlyPane.getChildren().add(titolo);
		onlyPane.setSpacing(10);
		
		hBottoni = new HBox();
		hBottoni.setAlignment(Pos.CENTER);
		hBottoni.setSpacing(50);
		
		account = new Button(" ACCOUNT");
		account.setPrefHeight(50);
		account.setPrefWidth(80);
		account.setAlignment(Pos.CENTER_LEFT);
		account.setOnAction(event -> {
			 AccountPane accountP = new AccountPane(controllerO, controllerA, controllerM, controllerL);
			 Scene nextScene = new Scene(accountP, 750, 660, Color.BEIGE);
			 ClientRistoratoreApp.getStage().setScene(nextScene);
         });
		hBottoni.getChildren().add(account);
		hBottoni.setSpacing(50);
		
		log = new Button("  LOG  ");
		log.setPrefHeight(50);
		log.setPrefWidth(80);
		log.setAlignment(Pos.CENTER);
		log.setOnAction(event -> {
			LogPane logP = new LogPane(controllerO, controllerA, controllerM, controllerL);
			 Scene nextScene = new Scene(logP, 750, 660, Color.BEIGE);
			 ClientRistoratoreApp.getStage().setScene(nextScene);
         });
		hBottoni.getChildren().add(log);
		hBottoni.setSpacing(50);
		
		menu = new Button("MENU    ");
		menu.setPrefHeight(50);
		menu.setPrefWidth(80);
		menu.setAlignment(Pos.CENTER_RIGHT);
		menu.setOnAction(event -> {
			MenuPane menuP = new MenuPane(controllerO, controllerA, controllerM, controllerL);
			 Scene nextScene = new Scene(menuP, 750, 660, Color.BEIGE);
			 ClientRistoratoreApp.getStage().setScene(nextScene);
         });
		hBottoni.getChildren().add(menu);
		hBottoni.setSpacing(50);
		onlyPane.getChildren().add(hBottoni);
		onlyPane.setSpacing(50);
		
		ObservableList<IOrdine> tvObservableList = FXCollections.observableArrayList();
		tvObservableList.addAll(controllerO.elencaOrdini());
		table = new TableView<IOrdine>();		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxWidth(600);
        table.setItems(tvObservableList);
        TableColumn<IOrdine, String> colTavolo = new TableColumn<IOrdine, String>("Tavolo");
        colTavolo.setCellValueFactory(new PropertyValueFactory<>("nomeTavolo"));
        TableColumn<IOrdine, Date> colDataOra = new TableColumn<IOrdine, Date>("DataOra");
        colDataOra.setCellValueFactory(new PropertyValueFactory<>("dataOra"));  
		TableColumn colVisualizza = new TableColumn("Dettaglio Ordine");
		colVisualizza.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<IOrdine, String>, TableCell<IOrdine, String>> cellFactory = new Callback<TableColumn<IOrdine, String>, TableCell<IOrdine, String>>() {
           
			@Override
            public TableCell call(final TableColumn<IOrdine, String> param) {
                final TableCell<IOrdine, String> cell = new TableCell<IOrdine, String>() {

                    final Button btn = new Button("                                                     ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                            	ClientRistoratoreApp.setIOrdineDaVisualizzare(getTableView().getItems().get(getIndex()));
                            	OrdinePaneClientRistoratore ordineP = new OrdinePaneClientRistoratore(controllerO, controllerA, controllerM, controllerL);
               				 	Scene newScene = new Scene(ordineP, 750, 660, Color.BEIGE);
               				 	ClientRistoratoreApp.getStage().setScene(newScene);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        colVisualizza.setCellFactory(cellFactory);
        table.getColumns().addAll(colTavolo, colDataOra, colVisualizza);
        onlyPane.getChildren().add(table);
        onlyPane.setSpacing(60);
        this.setTop(onlyPane);
        
		chiudi = new Button("CHIUDI");
		chiudi.setAlignment(Pos.BOTTOM_RIGHT);
		chiudi.setOnAction(event -> {
			LoginClientRistoratore login = new LoginClientRistoratore(controllerO, controllerA, controllerM, controllerL);
			 Scene oldScene = new Scene(login, 750, 660, Color.BEIGE);
			 ClientRistoratoreApp.getStage().setScene(oldScene);
         });
		this.setRight(chiudi);
	}
	
	public IOrdine getIOrdineDaVisualizzare() {
		return ordineDaVisualizzare;
	}
}

