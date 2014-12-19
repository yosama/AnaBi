package anabi.controlers;

import anabi.loader.ExtractDataWos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import anabi.main.*;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author yosamac
 */
public class MainViewController implements Initializable {

	private AnabiMain primaryStage;
	private ExtractDataWos extractData;
	private Stage stagePopup;

	//     @FXML
	//    private StackPane paneContext;
	@FXML
	private AnchorPane paneMain;
	@FXML
	private StackPane paneContext;
	@FXML
	private Menu opMenuInfomation;
	@FXML
	private Menu opMenuReport;
	@FXML
	private Label lbTitle;
	@FXML
	private Text txNameMyApp;

	public void setView(Node node) {
		paneContext.getChildren().setAll(node);

		paneContext.autosize();

		paneContext.setAlignment(paneContext, Pos.CENTER);

		animationPane(paneContext);
	}

	public void setPrimaryStage(AnabiMain primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stage getPrimaryStage() {
		return this.primaryStage.getPrimaryStage();
	}

	/**
	 * Mostrando un OpenDialogo para cargar el fichero
	 *
	 * @param event
	 */
	@FXML
	public void showOpenFile(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Seleccionar archivo");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Txt files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(primaryStage.getPrimaryStage());

		ViewNavigator.loadView(ViewNavigator.indexViewFile);

		validateFile(file);

	}

	// Valida el fichero entrado
	@SuppressWarnings("deprecation")
	public void validateFile(File fileWoS) {

		if (fileWoS != null) {

			try {

				BufferedReader br = new BufferedReader(new FileReader(fileWoS));
				String line = br.readLine();
				// String firstLetters = line.subSequence(0, 3).toString().trim();

				if (line.contains("Thomson Reuters Web of Science")) {

					ExtractDataWos dataExtractor = new ExtractDataWos(fileWoS);

					if ( dataExtractor.readFile() ) {

						dataExtractor.loadRecords();

						Dialogs.create()
						.owner(primaryStage.getPrimaryStage())
						.title("Progreso")
						.message("Proceso completado")
						.showInformation();
						ViewNavigator.loadView(ViewNavigator.anabiMainView);

					} else {
						System.out.println("Error cargando los datos");

						Dialogs.create()
						.owner(primaryStage.getPrimaryStage())
						.title("Error")
						.masthead("Error cargando de datos")
						.message("Ha un ocurrido un error intentando cargar los datos")
						.showError();

						System.out.println("ventana principal" + primaryStage.toString());
					}
				} else {
					Dialogs.create()
					.owner(primaryStage.getPrimaryStage())
					.title("Error de archivo")
					.message("Archivo no valido")
					.showError();
				}

			} catch (Exception e) {

				Dialogs.create()
				.owner(primaryStage.getPrimaryStage())
				.title("Error conexion")
				.message("Ha un ocurrido un error intentando conectar con la base de datos")
				.showError();
				e.printStackTrace();

			}
		}
	}


	/**
	 * Muestra la ventana de Autores
	 */
	public void showAuthorsView() {

		ViewNavigator.loadView(ViewNavigator.authorViewFile);

	}

	public void showAffiliationsView() {
		ViewNavigator.loadView(ViewNavigator.affiliationViewFile);

	}

	public void showDocumentView() {
		ViewNavigator.loadView(ViewNavigator.documentViewFile);

	}

	public void showJournalView() {
		ViewNavigator.loadView(ViewNavigator.journalViewFile);

	}

	public void showIndiActivityView() {
		ViewNavigator.loadView(ViewNavigator.indiActivityViewFile);

	}

	public void showAboutView() {
		ViewNavigator.loadView(ViewNavigator.aboutViewFile);
	}

	public void animationPane(Pane contextPane) {
		FadeTransition fadeObject = new FadeTransition(Duration.seconds(3), contextPane);
		fadeObject.setFromValue(0);
		fadeObject.setToValue(1);
		fadeObject.play();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
