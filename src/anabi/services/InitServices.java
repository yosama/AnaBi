package anabi.services;

public class InitServices {
	
	private  AuthorServices authorServi;
	private  AffiliationServices affiliationServi;
	private  FundingServices fundingServi;
	private  JournalServices journalServi;
	private  PublisherServices publisherServi;
	private  DocumentServices documentServi;
	
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
}
