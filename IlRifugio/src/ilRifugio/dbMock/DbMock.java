package ilRifugio.dbMock;

import java.rmi.RemoteException;

import ilRifugio.interfacce.controller.IControllerAccount;
import ilRifugio.interfacce.controller.IControllerMenu;
import ilRifugio.serverRistorante.dominio.CategoriaPietanza;

public class DbMock {
	
	public static void popolaMenu(IControllerMenu controllerMenu) throws RemoteException {
		controllerMenu.inserisciCoperto("adulto", 2.5);
		controllerMenu.inserisciCoperto("bambino", 1.5);
		
		controllerMenu.aggiungiBevanda("CocaCola Lattina", 3.5);
		controllerMenu.aggiungiBevanda("Fanta Lattina", 3.5);
		controllerMenu.aggiungiBevanda("Sprite Lattina", 3.5);
		controllerMenu.aggiungiBevanda("Pepsi Lattina", 3.5);
		controllerMenu.aggiungiBevanda("Birra Bionda Lattina", 3);
		controllerMenu.aggiungiBevanda("Birra Rossa Lattina", 3);
		controllerMenu.aggiungiBevanda("Birra Bionda 0.5L", 5);
		controllerMenu.aggiungiBevanda("Birra Rossa 0.5L", 5.5);
		controllerMenu.aggiungiBevanda("Calice di vino rosso", 2.5);
		controllerMenu.aggiungiBevanda("Bottiglia di vino rosso", 8);
		
		controllerMenu.aggiungiPietanza("Tris di affettati", 7, CategoriaPietanza.ANTIPASTO);
		controllerMenu.aggiungiPietanza("Salumi e formaggi", 8, CategoriaPietanza.ANTIPASTO);
		controllerMenu.aggiungiPietanza("Antipasto di pesce", 12, CategoriaPietanza.ANTIPASTO);
		
		controllerMenu.aggiungiPietanza("Lasagne alla Bolognese", 10, CategoriaPietanza.PRIMO);
		controllerMenu.aggiungiPietanza("Tortelloni ricotta e spinaci", 12, CategoriaPietanza.PRIMO);
		controllerMenu.aggiungiPietanza("Risotto allo speck", 12, CategoriaPietanza.PRIMO);
		controllerMenu.aggiungiPietanza("Tagliatelle al ragù", 11, CategoriaPietanza.PRIMO);
		controllerMenu.aggiungiPietanza("Spaghetti alla carbonara", 11.5, CategoriaPietanza.PRIMO);
		controllerMenu.aggiungiPietanza("Spaghetti al pesto", 9, CategoriaPietanza.PRIMO);
		
		controllerMenu.aggiungiPietanza("Cotoletta alla milanese", 8, CategoriaPietanza.SECONDO);
		controllerMenu.aggiungiPietanza("Branzino alla griglia", 15, CategoriaPietanza.SECONDO);
		controllerMenu.aggiungiPietanza("Patate al forno", 5, CategoriaPietanza.SECONDO);
		controllerMenu.aggiungiPietanza("Calamari fritti", 10, CategoriaPietanza.SECONDO);
		
		controllerMenu.aggiungiPietanza("Tiramisu", 4, CategoriaPietanza.DOLCE);
		controllerMenu.aggiungiPietanza("Meringata", 4, CategoriaPietanza.DOLCE);
		controllerMenu.aggiungiPietanza("Coppa gelato", 4, CategoriaPietanza.DOLCE);
	}

	public static void popolaAccount(IControllerAccount controllerAccount) throws RemoteException {
		controllerAccount.aggiungi("niccolo", "nr_rifugio", "prova", "ristoratore");
		controllerAccount.aggiungi("daniele", "dc_rifugio", "prova", "cameriere");
		controllerAccount.aggiungi("ruben", "re_rifugio", "prova", "cameriere");
	}

}
