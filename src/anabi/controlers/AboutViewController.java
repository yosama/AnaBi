
package anabi.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author ysamon
 */
public class AboutViewController implements Initializable {

   

    @FXML
    private AnchorPane paneAbout;

    @FXML
    private Text txNameMyApp;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
	public void initialize(URL url, ResourceBundle rb) {

        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        
        txNameMyApp.setX(30.0);
        txNameMyApp.setY(50.0);
        txNameMyApp.setCache(true);
        txNameMyApp.setFill(Color.web("0x3b596d"));
        txNameMyApp.setFont(Font.font(null, FontWeight.BOLD, 30));
        txNameMyApp.setEffect(reflection);

        

    }

}
