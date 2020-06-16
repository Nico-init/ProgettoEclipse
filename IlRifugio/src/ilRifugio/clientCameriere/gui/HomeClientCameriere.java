package ilRifugio.clientCameriere.gui;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.util.Date;

public class HomeClientCameriere extends BorderPane {

		@SuppressWarnings("unused")
		private IControllerOrdine controllerO;
		private Button aggiungi, chiudi;
		private TableView<IOrdine> table;
		private VBox onlyPane;
		@SuppressWarnings("unused")
		public IOrdine ordineDaVisualizzare;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public HomeClientCameriere(IControllerOrdine controllerO) {
			this.controllerO = controllerO;
			
			onlyPane = new VBox();
			onlyPane.setAlignment(Pos.CENTER);
			onlyPane.setSpacing(10);
		
			Label titolo = new Label("Sistema di Gestione Cameriere");
			onlyPane.getChildren().add(titolo);
			onlyPane.setSpacing(10);
			
			aggiungi = new Button("AGGIUNGI\n  ORDINE");
			aggiungi.setOnAction(event -> {
				AggiungiOrdinePane aggiungi = new AggiungiOrdinePane(controllerO);
				 Scene nextScene = new Scene(aggiungi, 750, 660, Color.BEIGE);
				 ClientCameriereApp.getStage().setScene(nextScene);
             });
			onlyPane.getChildren().add(aggiungi);
			onlyPane.setSpacing(10);
			
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
			TableColumn colVisualizza = new TableColumn("Visualizza");
			colVisualizza.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
	        Callback<TableColumn<IOrdine, String>, TableCell<IOrdine, String>> cellFactory = new Callback<TableColumn<IOrdine, String>, TableCell<IOrdine, String>>() {
	           
				@Override
	            public TableCell call(final TableColumn<IOrdine, String> param) {
	                final TableCell<IOrdine, String> cell = new TableCell<IOrdine, String>() {

	                    final Button btn = new Button("                                                        ");

	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                            btn.setOnAction(event -> {
	                            	ClientCameriereApp.setIOrdineDaVisualizzare(getTableView().getItems().get(getIndex()));
	                            	OrdinePaneClientCameriere ordineP = new OrdinePaneClientCameriere(controllerO);
	               				 	Scene newScene = new Scene(ordineP, 750, 660, Color.BEIGE);
	               				 	ClientCameriereApp.getStage().setScene(newScene);
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
				LoginClientCameriere login = new LoginClientCameriere(controllerO);
				 Scene oldScene = new Scene(login, 750, 660, Color.BEIGE);
				 ClientCameriereApp.getStage().setScene(oldScene);
             });
			this.setRight(chiudi);
		}
}
