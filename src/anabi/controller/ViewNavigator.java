package anabi.controller;

import java.io.IOException;

import anabi.services.InitServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Clase que controla las vistas a mostrar.
 *
 * Los metodos son estaticos para facilitar el acceso desde cualquier sitio.
 */
public class ViewNavigator extends Pane {

	/**
	 * Constantes que guardan el camino de las vistas.
	 */
	public static String mainViewFile = "../views/MainView.fxml";
	public static final String indexViewFile = "../views/IndexView.fxml";
	public static final String emptyViewFile = "../views/EmptyView.fxml";
	public static final String authorViewFile = "../views/AuthorView.fxml";
	public static final String affiliationViewFile = "../views/AffiliationView.fxml";
	public static final String documentViewFile = "../views/DocumentView.fxml";
	public static final String journalViewFile = "../views/JournalView.fxml";
	public static final String aboutViewFile = "../views/AboutView.fxml";
	public static final String indiActivityViewFile = "../views/IndiActivityView.fxml";
	public static final String indiCitedViewFile = "../views/IndiCitedView.fxml";
	public static final String indiTematicViewFile = "../views/IndiTematicView.fxml";
	public static final String indiColaborationViewFile = "../views/IndiColaborationView.fxml";
	public static final String anabiMainView = "../views/ResumenView.fxml";



	// El controlador de la aplicacion principal
	static MainViewController mainController;

	/**
	 * Almacena la vista para usarla cuando se vaya acceder a ella
	 *
	 * @param mainController Vista principal de la aplicacion.
	 */
	public static void setMainController(MainViewController mainController) {
		ViewNavigator.mainController = mainController;
	}

	/**
	 * Carga la vista especificada dentro de un vistaHolder de la aplicacion
	 * principal.
	 *
	 * @param fxml the fxml file to be loaded.
	 */
	public static void loadView(String fxml) {
		try {
			mainController.setView((Node) FXMLLoader.load(ViewNavigator.class.getResource(fxml)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MainViewController getController() {
		return mainController;
	}
	
}
