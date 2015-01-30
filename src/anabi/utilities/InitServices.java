package anabi.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import anabi.services.AffiliationServices;
import anabi.services.AuthorServices;
import anabi.services.DocumentServices;
import anabi.services.FundingServices;
import anabi.services.JournalServices;
import anabi.services.PublisherServices;
import anabi.services.DocumentTypeServices;
import anabi.services.PublicationTypeServices;


public class InitServices {

	private  AuthorServices authorServi;
	private  AffiliationServices affiliationServi;
	private  FundingServices fundingServi;
	private  JournalServices journalServi;
	private  PublisherServices publisherServi;
	private  DocumentServices documentServi;
	private DocumentTypeServices typeDocumentServi;
	private PublicationTypeServices typePublicationServi;

	private ConnectionDB db;
	private  static InitServices instances = null;

	protected  InitServices() {	
	}

	public static InitServices getInstances(){

		if (instances == null){
			instances = new InitServices();
		}
		return instances;
	}

	public void startServices(){
		authorServi = new AuthorServices();
		affiliationServi = new AffiliationServices();
		fundingServi = new FundingServices();
		journalServi = new JournalServices();
		publisherServi = new PublisherServices();
		documentServi = new DocumentServices();
		typeDocumentServi = new DocumentTypeServices();
		typePublicationServi = new PublicationTypeServices();

	}

	public AuthorServices getAuthorServices() {
		return authorServi;
	}

	public AffiliationServices getAffiliationServi() {
		return affiliationServi;
	}


	public FundingServices getFundingServi() {
		return fundingServi;
	}

	public JournalServices getJournalServi() {
		return journalServi;
	}

	public PublisherServices getPublisherServi() {
		return publisherServi;
	}
	public DocumentServices getDocumentServi() {
		return documentServi;
	}


	public void setAuthorServi(AuthorServices authorServi) {
		this.authorServi = authorServi;
	}

	public void setAffiliationServi(AffiliationServices affiliationServi) {
		this.affiliationServi = affiliationServi;
	}

	public void setFundingServi(FundingServices fundingServi) {
		this.fundingServi = fundingServi;
	}

	public void setJournalServi(JournalServices journalServi) {
		this.journalServi = journalServi;
	}

	public void setPublisherServi(PublisherServices publisherServi) {
		this.publisherServi = publisherServi;
	}

	public void setDocumentServi(DocumentServices documentServi) {
		this.documentServi = documentServi;
	}

	public DocumentTypeServices getTypeDocumentServi() {
		return typeDocumentServi;
	}

	public void setTypeDocumentServi(DocumentTypeServices typeDocumentServi) {
		this.typeDocumentServi = typeDocumentServi;
	}

	public PublicationTypeServices getTypePublicationServi() {
		return typePublicationServi;
	}

	public void setTypePublicationServi(PublicationTypeServices typePublicationServi) {
		this.typePublicationServi = typePublicationServi;
	}

	public void startConnection(){

		Properties configFile = new Properties();
		String url = "";
		String database = "";
		String user = "";
		String password = "";
		FileInputStream input = null;
		
		try {
			input = new FileInputStream("src/anabi/utilities/config.properties");
			configFile.load(input);
			url = configFile.getProperty("url");
			database = configFile.getProperty("database");
			user = configFile.getProperty("user");
			password = configFile.getProperty("password");		

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		db = new ConnectionDB(url + database, user, password);

	}
	
	public ConnectionDB getDB(){
		return db;
	}
	
	
	public void deleteDB(){
		//documentServi.deleteAllDocument();
		journalServi.deleteAllJournal();
		publisherServi.deleteAllPublisher();
		fundingServi.deleteAll();
		authorServi.deleteAllAuthor();
		
	}
	
	public boolean containQuotes(String text){
		boolean result  = false;
		if (text.contains("'")){
			result = true;
			System.out.println(text);
		}
		return result;
	}
	
	
}
