
package anabi.controllers;

import anabi.models.AuthorCategoryDocument;
import anabi.models.CategoryDocument;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;




import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author yosamac
 */
public class IndiActivityController implements Initializable {

   
    ViewNavigator controllerView;
    private ObservableList<CategoryDocument> listIndicatorCitable;
    private ObservableList<AuthorCategoryDocument> listAuthorDocCitable;
    private ObservableList<String> listIndiActivity;
//    private ObservableList<Document> listDocument;

    @FXML
    private ListView<String> lvIndiActivity;

    @FXML
    private HBox hbCategDoc;

    @FXML
    private HBox hbAuthorProm;

    // Tabla de categoria de documentos
    @FXML
    private TableView<CategoryDocument> tvCitable;
    @FXML
    private TableColumn tcYear;
    @FXML
    private TableColumn tcCitableCount;
    @FXML
    private TableColumn tcCitablePercentage;
    @FXML
    private TableColumn tcNoCitableCount;
    @FXML
    private TableColumn tcNoCitablePercentage;
    @FXML
    private TableColumn tcTotalProduction;

    // Tabla de Promedio de autores
    @FXML
    private TableView<AuthorCategoryDocument> tvAuthorProm;
    @FXML
    private TableColumn tcYearProm ;
    @FXML
    private  TableColumn tcAuthorCit;
    @FXML
    private  TableColumn tcDocCit;
    @FXML
    private  TableColumn tcPromCit ;
    @FXML
    private  TableColumn tcAuthorNoCit ;
    @FXML
    private  TableColumn tcDocNoCit ;
    @FXML
    private  TableColumn tcPromNoCit ;
    @FXML
    private  TableColumn tcTotAuthor ;
    @FXML
    private  TableColumn tcTotDoc;
    @FXML
    private  TableColumn tcTotProm;

    // Graficos
    @FXML
    private RadioButton rbChPie;
    @FXML
    private RadioButton rbChBar;
    @FXML
    private PieChart pchTotalProdution;
    @FXML
    private BarChart<String, Number> bchTotalProduction;
    @FXML
    private CategoryAxis xAxis;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

           

            // TABLA CATEGORY DOCUMENT
            tcYear.setCellValueFactory(new PropertyValueFactory<CategoryDocument, String>("year"));

            // Columnas citables :
            tcCitableCount.setCellValueFactory(new PropertyValueFactory<CategoryDocument, Integer>("countCitable"));
            tcCitablePercentage.setCellValueFactory(new PropertyValueFactory<CategoryDocument, Integer>("percentCitable"));

            // Columnas no citable
            tcNoCitableCount.setCellValueFactory(new PropertyValueFactory<CategoryDocument, Integer>("countNoCitable"));
            tcNoCitablePercentage.setCellValueFactory(new PropertyValueFactory<CategoryDocument, Integer>("percentNoCitable"));

            tcTotalProduction.setCellValueFactory(new PropertyValueFactory<CategoryDocument, String>("totalProduction"));

            // Tabla promedio de autores.
            tcYearProm.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, String>("year"));

            // Columnas citables :
            tcAuthorCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("countAuthorDocCitable"));
            tcDocCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("countCitable"));
            tcPromCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("percentAuthorXDocCitable"));

            // Columnas no citable
            tcAuthorNoCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("countAuthorDocNoCitable"));
            tcDocNoCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("countNoCitable"));
            tcPromNoCit.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("percentAuthorXDocNoCitable"));

            //
            tcTotAuthor.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("totalAuthor"));
            tcTotDoc.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("totalDocument"));
            tcTotProm.setCellValueFactory(new PropertyValueFactory<AuthorCategoryDocument, Integer>("totalPercent"));

            updateListIndicator();
        } catch (Exception e) {
            //Dialogs.showErrorDialog(controllerView.getController().getPrimaryStage(), "Ha un ocurrido un error intentando conectar con la base de datos.\n Intente conectar en unos minutos.", "Error", "Error de conexion");
        	Dialogs.create()
        			.owner(controllerView.getController().getPrimaryStage())
        			.title("Error de conexion")
        			.message("Ha un ocurrido un error intentando conectar con la base de datos.\n Intente conectar en unos minutos.")
        			.showError();
            ViewNavigator.loadView(ViewNavigator.indexViewFile);
        }

    }

    public void updateListIndicator() {

       

        listIndiActivity = FXCollections.observableArrayList(
                "Producción cientifica por años",
                "Cantidad y promedio de autores por documentos"
//                "Documentos con mayor numero de autores"
        );

        lvIndiActivity.setItems(listIndiActivity);
        lvIndiActivity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvIndiActivity.getSelectionModel().selectedItemProperty().addListener(lvIndiActivityListener);

    }

    private final ChangeListener<String> lvIndiActivityListener = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            IndiActivitySelected(newValue);
        }
    };

    private void IndiActivitySelected(String newValueSelect) {

        if (newValueSelect.equals("Producción cientifica por años")) {
            hbCategDoc.setVisible(true);
            hbAuthorProm.setVisible(false);
            hbCategDoc.setAlignment(Pos.CENTER);
            getCategoryDocument();

        } else if (newValueSelect.equals("Cantidad y promedio de autores por documentos")) {
            hbCategDoc.setVisible(false);
            hbAuthorProm.setVisible(true);

            hbAuthorProm.setAlignment(Pos.TOP_CENTER);
            getAuthorDocument();

        } else if (newValueSelect.equals("Documentos con mayor numero de autores")) {

        }

    }

    /**
     * Muestra los documentos por categoria
     */
    public void getCategoryDocument() {

        listIndicatorCitable = FXCollections.observableArrayList();
//        ObservableList<PieChart.Data> pieChartData = null;

        String queryDocumentCitable = "SELECT year_publication_py, \n"
                + "    COUNT(CASE \n"
                + "            WHEN cod_c_document = 1 then cod_document_ut\n"
                + "            WHEN cod_c_document = 2 then cod_document_ut\n"
                + "            ELSE NULL END) AS CITABLES,\n"
                + "    COUNT(CASE \n"
                + "            WHEN cod_c_document != 1 \n"
                + "            THEN cod_document_ut ELSE NULL END) AS NoCitables,\n"
                + "     COUNT(*) AS TOTAL\n"
                + "FROM document\n"
                + "GROUP BY year_publication_py;       ";

       // List<CategoryDocument> result = em.createNativeQuery(queryDocumentCitable).getResultList();

        Iterator it = null;
        CategoryDocument dataIndicatorCitable;

        int citableCount = 0;
        int noCitableCount = 0;

        while (it.hasNext()) {

            dataIndicatorCitable = new CategoryDocument();
            Object[] currentData = (Object[]) it.next();

            // Documentos citables
            dataIndicatorCitable.setYear(currentData[0].toString());
            dataIndicatorCitable.setCountCitable(Integer.parseInt(currentData[1].toString()));

            citableCount = Integer.parseInt(currentData[1].toString());
            int total = Integer.parseInt(currentData[3].toString());
            int percentCitable = (citableCount * 100) / total;
            dataIndicatorCitable.setPercentCitable(percentCitable);

            //bchTotalProduction = barChart(dataIndicatorCitable.getYear(), "Produccion Cientifica", "Categoria", "Cantidad de documentos", "Citables", citableCount);
            // Documentos no citables
            dataIndicatorCitable.setCountNoCitable(Integer.parseInt(currentData[2].toString()));
            noCitableCount = Integer.parseInt(currentData[2].toString());
            int percentNoCitable = (noCitableCount * 100) / total;
            dataIndicatorCitable.setPercentNoCitable(percentNoCitable);

            //Total 
            dataIndicatorCitable.setTotalProduction(total);

            listIndicatorCitable.add(dataIndicatorCitable);

            System.out.println("anio: " + dataIndicatorCitable.getYear() + "\n cantidad: " + currentData[2].toString());
        }

        tvCitable.setItems(listIndicatorCitable);

    }

    /**
     * Muestra el cuadro de promedios de autores
     */
    public void getAuthorDocument() {

        listAuthorDocCitable = FXCollections.observableArrayList();
//        ObservableList<PieChart.Data> pieChartData = null;

        String queryAuthorDocCitable = "SELECT DISTINCT d.year_publication_py Anio, COUNT(cod_author) \"Total de autores\", \n"
                + "(SELECT count(da1.cod_author) FROM document_author da1,document \n"
                + "WHERE da1.cod_document_ut = document.cod_document_ut\n"
                + "AND document.year_publication_py = d.year_publication_py \n"
                + "AND (document.cod_c_document = 1 ) \n"
                + "GROUP BY year_publication_py) \"Autores Doc Citables\",\n"
                + "\n"
                + "(SELECT count(da1.cod_author) FROM document_author da1,document \n"
                + "WHERE da1.cod_document_ut = document.cod_document_ut\n"
                + "AND document.year_publication_py = d.year_publication_py \n"
                + "AND (document.cod_c_document != 1 ) \n"
                + "GROUP BY year_publication_py) \"Autores Doc No Citables\",\n"
                + "\n"
                + "(Select count(*) FROM document \n"
                + "WHERE d.year_publication_py = document.year_publication_py  \n"
                + "GROUP BY year_publication_py) \"Total Documentos\",\n"
                + "\n"
                + "(Select count(*) FROM document \n"
                + "WHERE d.year_publication_py = document.year_publication_py AND (document.cod_c_document = 1 OR document.cod_c_document = 2) \n"
                + "GROUP BY year_publication_py) \"documentos Citables\",\n"
                + "\n"
                + "(Select count(*) FROM document \n"
                + "WHERE d.year_publication_py = document.year_publication_py AND (document.cod_c_document != 1 ) \n"
                + "GROUP BY year_publication_py) \"Documentos No Citables\"\n"
                + "\n"
                + "FROM document_author da, document d \n"
                + "WHERE  da.cod_document_ut = d.cod_document_ut \n"
                + "GROUP BY d.year_publication_py;";

     //   List<CategoryDocument> result = em.createNativeQuery(queryAuthorDocCitable).getResultList();

        Iterator it = null;
        AuthorCategoryDocument dataAuthorDocCitable;

        int citableCount = 0;
        int noCitableCount = 0;

        while (it.hasNext()) {

            dataAuthorDocCitable = new AuthorCategoryDocument();
            Object[] currentData = (Object[]) it.next();

            // Documentos citables
            dataAuthorDocCitable.setYear(currentData[0].toString());
            int totalAuthorCitable = Integer.parseInt(currentData[2].toString());
            dataAuthorDocCitable.setCountAuthorDocCitable(totalAuthorCitable);
            citableCount = Integer.parseInt(currentData[5].toString());
            dataAuthorDocCitable.setCountCitable(citableCount);

            int percentCitable = (citableCount * 100) / totalAuthorCitable;
            dataAuthorDocCitable.setPercentAuthorDocCitable(percentCitable);

            // Documentos no citables
            int totalAuthorNoCitable = 0;
            int percentNoCitable = 0;

            if ((currentData[3] != null) || (currentData[6] != null)) {

                totalAuthorNoCitable = Integer.parseInt(currentData[3].toString());
                noCitableCount = Integer.parseInt(currentData[6].toString());
                dataAuthorDocCitable.setCountAuthorDocNoCitable(totalAuthorNoCitable);
                dataAuthorDocCitable.setCountNoCitable(noCitableCount);

                percentNoCitable = (noCitableCount * 100) / totalAuthorNoCitable;

                dataAuthorDocCitable.setPercentAuthorDocNoCitable(percentNoCitable);

            } else {
                dataAuthorDocCitable.setCountAuthorDocNoCitable(totalAuthorNoCitable);
                dataAuthorDocCitable.setCountNoCitable(noCitableCount);
                dataAuthorDocCitable.setPercentAuthorDocNoCitable(percentNoCitable);
            }

            //Total 
            int totalAuthor = Integer.parseInt(currentData[1].toString());
            int totalDocument = Integer.parseInt(currentData[4].toString());
            int totalPercent = (totalDocument * 100) / totalAuthor;

            dataAuthorDocCitable.setTotalAuthor(totalAuthor);
            dataAuthorDocCitable.setTotalDocument(totalDocument);
            dataAuthorDocCitable.setTotalPercent(totalPercent);

            listAuthorDocCitable.add(dataAuthorDocCitable);

            System.out.println("anio: " + dataAuthorDocCitable.getYear() + "\n cantidad: " + currentData[2].toString());
        }

        tvAuthorProm.setItems(listAuthorDocCitable);

    }

}
