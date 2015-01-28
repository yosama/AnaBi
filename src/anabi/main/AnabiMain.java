
package anabi.main;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import anabi.controllers.*;
import anabi.utilities.ConnectionDB;

/**
 *
 * @author Yosamac
 */
public class AnabiMain extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        
        this.primaryStage = stage;
        this.primaryStage.setTitle("AnaBi");
        //stage.setResizable(false);
        
        stage.setScene(createScene(loadMainPane()));

        this.primaryStage.show();
        

    }

    public Pane loadMainPane() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        ViewNavigator.mainViewFile
                )
        );

        MainViewController mainController = loader.getController();
        mainController.setPrimaryStage(this);
        IndexViewController indexViewController = new IndexViewController();
        indexViewController.setMainApp(this);
     
        
        ViewNavigator.setMainController(mainController);
        ViewNavigator.loadView(ViewNavigator.indexViewFile);
        

        return mainPane;
    }

    /**
     * Crea la escena principal.
     *
     * @param mainPane
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("../views/style.css").toExternalForm());
        return scene;
    }

    /**
     * Devuelve la ventana principal
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    

}
