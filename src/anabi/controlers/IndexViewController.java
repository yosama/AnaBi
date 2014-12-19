package anabi.controlers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import anabi.main.AnabiMain;

/**
 * FXML Controller class
 *
 * @author ysamon
 */
public class IndexViewController implements Initializable {

    @FXML
    private StackPane paneContext;
    @FXML
    private Button btnSelectFile;
    @FXML
    private Text txNameMyApplication;

    private AnabiMain mainApp;
    private MainViewController controllerMain;

    /**
     * Inicializa el controlador de la ventana
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);

        txNameMyApplication.setX(30.0);
        txNameMyApplication.setY(50.0);
        txNameMyApplication.setCache(true);
        txNameMyApplication.setFill(Color.web("0x3b596d"));
        txNameMyApplication.setFont(Font.font(null, FontWeight.BOLD, 30));
        txNameMyApplication.setEffect(reflection);

    }

    /**
     * LLama a la applicacion principal, hace referencia a ella misma
     *
     * @param mainApp
     */
    public void setMainApp(AnabiMain mainApp) {
        
        this.mainApp = mainApp;

    }

    @FXML
    public void showSelectFile(ActionEvent event) {
    	
        mainApp = new AnabiMain();
        controllerMain = new MainViewController();
        
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Txt files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
       // ViewNavigator.loadView(ViewNavigator.emptyViewFile);
        controllerMain.setPrimaryStage(mainApp);

        controllerMain.validateFile(file);

    }

}
