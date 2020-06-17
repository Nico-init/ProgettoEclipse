package ilRifugio.clientRistoratore.gui;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerLog;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.interfacce.controller.IControllerOrdine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class AccountPane extends BorderPane {

	@SuppressWarnings("unused")
	private IControllerOrdine controllerO;
	@SuppressWarnings("unused")
	private IControllerAccount controllerA;
	@SuppressWarnings("unused")
	private IControllerMenu controllerM;
	@SuppressWarnings("unused")
	private IControllerLog controllerL;
	private Button aggiungi, chiudi;
	private TextField tfNome, tfUsername, tfPassword;
	private ComboBox<RuoloAccount> cbTipo;
	private TableView<Account> tableAccount;
	private VBox onlyPane;
	private HBox hNome, hTipo, hUsername, hPassword;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AccountPane(IControllerOrdine controllerO, IControllerAccount controllerA, IControllerMenu controllerM, IControllerLog controllerL) {
		this.controllerO = controllerO;
		this.controllerA = controllerA;
		this.controllerM = controllerM;
		this.controllerL = controllerL;
		
		onlyPane = new VBox();
		onlyPane.setAlignment(Pos.CENTER);
		onlyPane.setSpacing(10);
	
		Label titolo = new Label("Account");
		onlyPane.getChildren().add(titolo);
		onlyPane.setSpacing(10); 

		ObservableList<Account> tvAccountObservableList = FXCollections.observableArrayList();
		try {
			tvAccountObservableList.addAll(stringToAccountList(controllerA.elenca()));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tableAccount = new TableView<Account>();		
		tableAccount.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableAccount.setMaxWidth(600);
		tableAccount.setMaxHeight(215);
		tableAccount.setItems(tvAccountObservableList);
        TableColumn<Account, String> colNome = new TableColumn<Account, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Account, RuoloAccount> colTipo = new TableColumn<Account, RuoloAccount>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("ruolo"));  
        TableColumn<Account, String> colUsername = new TableColumn<Account, String>("Username");
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn colRimuoviAccount = new TableColumn("Rimuovi");
        colRimuoviAccount.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Account, String>, TableCell<Account, String>> cellFactoryRimuoviAccount = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
           
			@Override
            public TableCell call(final TableColumn<Account, String> paramRimuoviAccount) {
                final TableCell<Account, String> cellRimuoviAccount = new TableCell<Account, String>() {

                    final Button btnRimuoviAccount = new Button("                             ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnRimuoviAccount.setOnAction(event -> {
                        		try {
									controllerA.rimuovi(getTableView().getItems().get(getIndex()).getNome());
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                        		tableAccount.getItems().clear();
                        		tvAccountObservableList.clear();
                        		try {
									tvAccountObservableList.addAll(stringToAccountList(controllerA.elenca()));
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                        		tableAccount.setItems(tvAccountObservableList);
                        	
                        	});
                            setGraphic(btnRimuoviAccount);
                            setText(null);
                        }
                    }
                };
                return cellRimuoviAccount;
            }
        };
        colRimuoviAccount.setCellFactory(cellFactoryRimuoviAccount);
        
        TableColumn colModificaAccount = new TableColumn("Modifica");
        colModificaAccount.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Account, String>, TableCell<Account, String>> cellFactoryModificaAccount = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
           
			@Override
            public TableCell call(final TableColumn<Account, String> paramModificaAccount) {
                final TableCell<Account, String> cellModificaAccount = new TableCell<Account, String>() {

                    final Button btnModificaAccount = new Button("                             ");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	btnModificaAccount.setOnAction(event -> {
                        		ClientRistoratoreApp.setAccountDaVisualizzare(getTableView().getItems().get(getIndex()));
                        		ModificaAccountPane modificaA = new ModificaAccountPane(controllerO, controllerA, controllerM, controllerL);
                    			Scene nextSceneMA = new Scene(modificaA, 750, 660, Color.BEIGE);
                    			ClientRistoratoreApp.getStage().setScene(nextSceneMA);
                            });
                            setGraphic(btnModificaAccount);
                            setText(null);
                        }
                    }
                };
                return cellModificaAccount;
            }
        };
        colModificaAccount.setCellFactory(cellFactoryModificaAccount);
        tableAccount.getColumns().addAll(colNome, colTipo, colUsername, colRimuoviAccount, colModificaAccount);
        onlyPane.getChildren().add(tableAccount);
		onlyPane.setSpacing(150);
	
		
		hNome = new HBox();
		hNome.setAlignment(Pos.CENTER);
	
		Label nome = new Label("Nome                            ");
		nome.setAlignment(Pos.CENTER_LEFT);
		hNome.getChildren().add(nome);
		
		tfNome = new TextField();
		tfNome.setText("");
		tfNome.setPrefWidth(200);
		tfNome.setAlignment(Pos.CENTER_RIGHT);
		hNome.getChildren().add(tfNome);
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
		
		tfPassword = new TextField();
		tfPassword.setText("");
		tfPassword.setPrefWidth(200);
		tfPassword.setAlignment(Pos.CENTER_RIGHT);
		hPassword.getChildren().add(tfPassword);
		hPassword.setSpacing(20);
		onlyPane.getChildren().add(hPassword);
		onlyPane.setSpacing(50);
		
		aggiungi = new Button("AGGIUNGI ACCOUNT");
		aggiungi.setAlignment(Pos.CENTER);
		aggiungi.setOnAction(event -> {
			if(tfNome.getText().isEmpty() || tfNome.getText().equals(null)) {
				alert("Errore", "Inserimento non corretto", "Nome errato o non vuoto");
				return;
			}
			else if(cbTipo.getValue() == null) {
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
				String sNome = tfNome.getText().trim();
				String sTipo = cbTipo.getValue().toString();
				String sUsername = tfUsername.getText().trim();
				String sPassword = tfPassword.getText().trim();
				try {
					controllerA.aggiungi(sNome, sUsername, sPassword, sTipo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tableAccount.getItems().clear();
				tvAccountObservableList.clear();
				try {
					System.out.println("Accounts: " + controllerA.elenca());
					tvAccountObservableList.addAll(stringToAccountList(controllerA.elenca()));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tableAccount.setItems(tvAccountObservableList);
				tfNome.setText("");
				cbTipo.setValue(null);
				tfUsername.setText("");
				tfPassword.setText("");
			}
		});
		onlyPane.getChildren().add(aggiungi);
		onlyPane.setSpacing(30);
		this.setTop(onlyPane);
		
		chiudi = new Button("CHIUDI");
		chiudi.setAlignment(Pos.BOTTOM_RIGHT);
		chiudi.setOnAction(event -> {
			//HomeClientRistoratore ordine = new HomeClientRistoratore(null);
			HomeClientRistoratore ordine = new HomeClientRistoratore(controllerO, controllerA, controllerM, controllerL);
			Scene oldScene = new Scene(ordine, 750, 660, Color.BEIGE);
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
	
	private Collection<Account> stringToAccountList(String accounts) {
		List<Account> listaAccount = new LinkedList<>();
		String[] accountSingoli = accounts.split(":");
		String[] account;
		Account nuovoAccount;
		for (String a : accountSingoli) {
			account = a.split("&");
			nuovoAccount = new Account(account[0], account[1], RuoloAccount.valueOf(account[2]));
			listaAccount.add(nuovoAccount);
		}		
		return listaAccount;
	}
}
