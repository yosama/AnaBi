
package anabi.controllers;

import anabi.models.Affiliation;
import anabi.models.Author;
import anabi.models.Document;
import anabi.models.Record;
import anabi.services.AffiliationServices;
import anabi.services.AuthorServices;
import anabi.services.DocumentServices;
import anabi.services.FundingServices;
import anabi.services.JournalServices;
import anabi.services.PublisherServices;
import anabi.utilities.InitServices;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class
 *
 * @author yosamac
 */
public class MainControllerView implements Initializable {


	@FXML
	private StackPane paneContext;
	@FXML
	private ListView<String> lvDocuments;
	@FXML
	private ListView <String> lvAffiliations;
	@FXML
	private ListView<String> lvAuthors;
	@FXML
	private TextField tfCountAuthors;
	@FXML
	private TextField tfCountDocuments;
	@FXML
	private Label lbDocuments;
	@FXML
	private Label lbAffiliations;
	@FXML
	private TextField tfCountAffiliations;
	@FXML
	private Label lbCountJournals;
	@FXML
	private Label lbCountPublishers;
	@FXML
	private Label lbCountFundings;
	@FXML
	private Button btnOpen;


	private ObservableList<String> olDocuments = FXCollections.observableArrayList();
	private ObservableList<String> olAuthors;
	private ObservableList<String> olAffiliations;

	private DocumentServices documentServi;
	private AuthorServices authorServi;
	private AffiliationServices affiliationServi;
	private JournalServices journalServi;
	private PublisherServices publisherServi;
	private FundingServices fundingServi;


	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tfCountAffiliations.getStyleClass().add("textfield");
		tfCountDocuments.getStyleClass().add("textfield");
		tfCountAuthors.getStyleClass().add("textfield");
		

		updateListDocument();
		updateListAffiliation();
		updateListAuthor();

	}

	public void updateListDocument() {

		documentServi = InitServices.getInstances().getDocumentServi();
		olDocuments.addAll(documentServi.getDocumentListName());

		tfCountDocuments.setText(String.valueOf(documentServi.countDocuments()));
		lvDocuments.setItems(olDocuments);
		lvDocuments.getSelectionModel().selectedItemProperty().addListener(lvDocumentListener);

	}

	private final ChangeListener<String> lvDocumentListener = new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

			documentSelected(newValue);
		}
	};


	private void documentSelected(String newValueSelect) {

		Document document = null;
		document = documentServi.findDocumentByTitle(newValueSelect);

		if ( document != null ){

			lbDocuments.setText("ID:");
			lbAffiliations.setText("Instituciones:");
			tfCountDocuments.setText(document.getCodDocumentUt());
			List <Integer> listCodAffiliations = document.getAuthorList();
			List<Integer> listCodAuthors = document.getAuthorList();
			tfCountAffiliations.setText(String.valueOf(listCodAffiliations.size()));
			tfCountAuthors.setText(String.valueOf(listCodAuthors.size()));

			// Filled affiliations list
			olAffiliations.clear();
			List <Affiliation> listAffiliations = affiliationServi.getListAffiliation(listCodAffiliations);
			List<String> listNameAffiliation = affiliationServi.getListName(listAffiliations);
			olAffiliations.addAll(listNameAffiliation);

			// Filled authors list
			olAuthors.clear();
			List<Author> listAuthors = authorServi.getAuthorList(listCodAuthors);
			List<String> listNameAuthors = authorServi.getNamesList(listAuthors);
			olAuthors.addAll(listNameAuthors);
		}
	}


	public void updateListAuthor() {

		authorServi = InitServices.getInstances().getAuthorServices();
		olAuthors = FXCollections.observableList(authorServi.getNamesAllAuthorsList());

		tfCountAuthors.setText(String.valueOf(authorServi.countAuthors()));
		lvAuthors.setItems(olAuthors);
	}

	public void updateListAffiliation() {

		affiliationServi = InitServices.getInstances().getAffiliationServi();
		olAffiliations = FXCollections.observableList(affiliationServi.getListNameAffiliation());

		tfCountAffiliations.setText(String.valueOf(affiliationServi.countAffiliations()));

		lvAffiliations.setItems(olAffiliations);
		lvAffiliations.getSelectionModel().selectedItemProperty().addListener(lvAffiliationListener);
	} 

	private final ChangeListener<String> lvAffiliationListener = new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

			affiliationSelected(newValue);
		}
	};


	private void affiliationSelected(String newValueSelect) {

		Affiliation affiliation = null;
		List <Document> listDocuments = new ArrayList<Document>();
		List<Record> listRecords;
		List<Integer> listCodAuthors = new ArrayList<Integer>();

		affiliation = affiliationServi.findByName(newValueSelect);

		if ( affiliation != null ){
			lbAffiliations.setText("Institucion:");
			lbDocuments.setText("Documentos:");
			tfCountDocuments.setText(String.valueOf(affiliation.getListRecord().size()));
			tfCountAffiliations.setText(newValueSelect);
			tfCountAuthors.setText(String.valueOf(affiliation.getListCodAuthor().size()));

			// Filled author list
			olAuthors.clear();
			listCodAuthors = affiliation.getListCodAuthor();
			List<Author> listAuthors = authorServi.getAuthorList(listCodAuthors);
			olAuthors.addAll(authorServi.getNamesList(listAuthors));
			
			//Filled document list
			olDocuments.clear();
			listRecords = affiliation.getListRecord();
			

			for (Record objRecord : listRecords){
				Document document = documentServi.findDocumentByRecord(objRecord);
				listDocuments.add(document);
			}

			for ( Document objDocument : listDocuments ){
				olDocuments.add(objDocument.getTitleTi());
			}

			olAffiliations.sorted();


		}
	}



}
