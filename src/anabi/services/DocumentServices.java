package anabi.services;

import java.nio.charset.CodingErrorAction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Author;
import anabi.models.Document;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class DocumentServices {


	private Record keyRecord;
	private Document document;
	private List<Document> listDocument = new ArrayList<Document>();
	private InitServices iniServices;
	//	private AuthorServices authorServi;
	//	private JournalServices journalServi;
	//	private FundingServices fundingServi;
	//	private AffiliationServices affiliationServi;

	private ConnectionDB connDB;
	private String  sql;

	public DocumentServices(){
		iniServices = iniServices.getInstances();

	}


	/**
	 * Crea lista de documentos.
	 *
	 * @param record
	 */
	public void setListDocument(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String publicationType = "";
		String titleTI = "";
		String languageLA = "";
		String documentTypeDT = "";
		String abstractAB = "";
		String volumenVL = "";
		String yearPY = "";
		String datePD = "";
		String titleSourceDI = "";
		String indexKeywords = "";
		String authorKeywords = "";
		String citedNR = "";
		String citedZ9 = "";
		String citedTC = "";
		String countPage = "";
		String beginPage = "";
		String endPage = "";

		publicationType = record.get("PT");
		titleTI = record.get("TI");
		languageLA = record.get("LA");
		documentTypeDT = record.get("DT");
		abstractAB = record.get("AB");
		volumenVL = record.get("VL");
		yearPY = record.get("PY");
		datePD = record.get("PD");
		titleSourceDI = record.get("DI");
		indexKeywords = record.get("ID");
		authorKeywords = record.get("DE");
		citedNR = record.get("NR");
		citedZ9 = record.get("Z9");
		citedTC = record.get("TC");
		countPage = record.get("PG");
		beginPage = record.get("BP");
		endPage = record.get("EP");

		if (!(validateField(beginPage))){
			beginPage = "SPI"; // SPI = Sin pagina inicial 
		}

		if (!(validateField(endPage))){
			endPage = "SPF"; // SPF = Sin pagina final
		}


		addDocument(keyRecord.getCodDocument(),publicationType, titleTI, languageLA,documentTypeDT,
				abstractAB, volumenVL, yearPY, datePD,
				titleSourceDI, indexKeywords, authorKeywords,
				citedNR, citedZ9, citedTC, countPage,beginPage,endPage);


		document = new Document(keyRecord.getCodDocument(), titleTI, languageLA,
				abstractAB, volumenVL, yearPY, datePD,
				titleSourceDI, indexKeywords, authorKeywords, 
				citedNR, citedZ9, citedTC, 
				countPage, documentTypeDT, keyRecord );

		listDocument.add(document);

	}


	// Validate the fields beginPage and endPage 
	public boolean validateField (String field){

		try {
			if ( field.isEmpty() ){
				return false;
			} else{
				return true;
			}
		} catch( NullPointerException npe){
			return false;
		}
	}


	// Count the documents of the local list
	public Integer countDocuments(){

		return listDocument.size();
	}


	public Document findDocumentByRecord(Record keyrecord){

		Document  result = new Document();
		Integer idRow = 0;
		String idDocument = "";


		for (Document objDocument : listDocument ){

			idRow = objDocument.getRecord().getIDRecord();
			idDocument = objDocument.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				result = objDocument;
				//System.out.println("Documento  encontrado");
			} else {
				//System.out.println("Documento no encontrado");
			}
		}
		return result;
	} 


	// 
	public Document findDocumentByRecord (Integer aRow){

		return listDocument.get(listDocument.size() - aRow);
	}


	// Find a document by the WoS code  
	public Document findDocumentByIdWos (String wosUT){
		boolean encontrado = false;
		Document result = null;
		for ( int i= 0; i < listDocument.size() && encontrado == false; i++){

			result = listDocument.get(i);
			if (result.getCodDocumentUt().equals(wosUT)){
				encontrado = true;
			}
		}
		return result;
	}


	// Find a document by  its title of the local list
	public Document findDocumentByTitle (String title){
		boolean finded = false;
		Document result = null;
		title = title.trim();

		for ( int i= 0; i < listDocument.size() && finded == false; i++){

			result = listDocument.get(i);
			if ( result.getTitleTi().trim().equals(title) ){
				finded = true;
			}
		}
		return result;
	}


	// Return  all documents of the local list
	public List<Document> getDocumentsListAll(){
		return listDocument;
	}


	// Return all names documents of the local list
	public List<String> getDocumentListName(){
		List<String> listResult = new ArrayList<String>();

		for(Document objDocument : listDocument){
			listResult.add(objDocument.getTitleTi());
		}
		return listResult;
	}


	// Add a document to the database 
	public void addDocument(String codArticleUT, String aPublicationType, String titleTI, String languageLA, String aDocumentType,
			String abstractAB, String volumenVL, String yearPublicationPY, String datePublicationPD, 
			String titleSourceDI, String indexkewordsID, String authKewordsDE, 
			String citedCountNR, String citedTotalZ9, String citedReferenceTC, 
			String pageCountPG, String pageBeginBP, String pageEndEP) {


		iniServices = InitServices.getInstances();
		JournalServices journalServi = iniServices.getJournalServi();
		FundingServices fundingServi = iniServices.getFundingServi();
		AffiliationServices affiliationServi = iniServices.getAffiliationServi();

		// Get codJournal for this document
		Integer codJournal = journalServi.getCodJournal(keyRecord);
		// Get codFunding for this document
		Integer codFunding = fundingServi.getCodFunding(keyRecord);

		String data = (titleTI+"@NEXT@"+abstractAB+"@NEXT@"+titleSourceDI+"@NEXT@"+indexkewordsID+"@NEXT@"+authKewordsDE);

		//		System.out.println("TITLEEEEE:  "+splitData[0]);
		//		System.out.println("ABSSS:  "+splitData[1]);
		//		System.out.println("TITLESDI:  "+splitData[2]);

		if ( data.contains(String.valueOf('"')) ){
			System.out.println(data);
			data  = data.replace(String.valueOf('"'), "'");
			String [] splitData = data.split("@NEXT@");
			titleTI = splitData[0];
			abstractAB = splitData[1];
			titleSourceDI = splitData[2];
			indexkewordsID = splitData[3];
			authKewordsDE = splitData[4];

			//			System.out.println("TITLEEEEE:  "+splitData[0]);
			//			System.out.println("ABSSS:  "+splitData[1]);
			//			System.out.println("TITLESDI:  "+splitData[2]);

		} 

		sql = "";
		sql = "INSERT INTO document VALUES (\""+codArticleUT+"\",\""+aPublicationType+"\",\""+titleTI+"\",\""+languageLA+"\",\""+aDocumentType+"\",\""+abstractAB+"\",\""+
				volumenVL+"\",\""+yearPublicationPY+"\",\""+datePublicationPD+"\",\""+
				titleSourceDI+"\",\""+indexkewordsID+"\",\""+authKewordsDE+"\",\""+
				citedCountNR+"\","+citedTotalZ9+","+citedReferenceTC+",\""+
				pageCountPG+"\",\""+pageBeginBP+"\",\""+pageEndEP+"\","
				+codJournal+","+codFunding+")";

		System.out.println(sql);

		connDB = iniServices.getDB();
		connDB.runSql(sql);

		addDocumentAndAuthor();
	}


	// Add a occurrence of Document and Author 
	public void addDocumentAndAuthor() {

		AuthorServices authorServi = iniServices.getAuthorServices();
		sql = "";

		// Get list authors for this document
		List<Integer> listCodAuthor = authorServi.getCodAuthorList(keyRecord);

		//connDB = iniServices.getDB();
		for ( Integer idAuthor : listCodAuthor){

			sql = "INSERT INTO document_author VALUES (\""+keyRecord.getCodDocument()+"\","+idAuthor+")";
			connDB.runSql(sql);
		}

	}


	// Delete a document specific of the database
	public void deleteDocument(String idDocument){

		sql ="";
		sql = "DELETE FROM document WHERE cod_document_ut="+idDocument+")";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}


	// Delete all documents of the database
	public void deleteAllDocument(){
		sql ="";
		sql = "DELETE FROM document";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
		deleteAllDocumentAuthor();
	}

	// Delete all documents and author of the database
		public void deleteAllDocumentAuthor(){
			sql ="";
			sql = "DELETE FROM document_author";
			connDB.runSql(sql);
		}

	// Find a document by its codDocument on  the database
	public Document findDocumentByCod(String idDocument){

		idDocument = idDocument.trim();
		sql ="";
		sql = "SELECT * FROM document WHERE cod_document_ut="+idDocument;
		connDB = iniServices.getDB();
		ResultSet rs = connDB.runSql(sql);
		Document document = new Document();

		if (rs != null){
			try {
				while (rs.next()) {
					document.setCodDocumentUt(rs.getString("cod_document_ut"));
					document.setTitleTi(rs.getString("title_ti"));
					document.setLanguageTi(rs.getString("language_la"));
					document.setAbstractAb(rs.getString("abstract_ab"));
					document.setVolumenVl(rs.getString("volumen_vl"));
					document.setYearPublicationPy(rs.getString("year_publication_py"));
					document.setDatePublicationPd(rs.getString("date_publication_pd"));
					document.setTitleSourceDi(rs.getString("title_source_di"));
					document.setIndexKewordsId(rs.getString("index_kewords_id"));
					document.setAuthorKewordsDe(rs.getString("author_kewords_de"));
					document.setCitedCountNr(rs.getString("cited_count_nr"));
					document.setCitedTotalZ9(rs.getString("cited_total_z9"));
					document.setCitedReferenceTc(rs.getString("cited_reference_tc"));
					document.setPageCountPg(rs.getString("page_count_pg"));
					document.setPageBp(rs.getString("page_bp"));
					document.setPageEp(rs.getString("page_ep"));
					document.setCodCDocument(rs.getInt("cod_document_type"));
					document.setCodFunding(rs.getInt("cod_publication_type"));
					document.setCodJournal(rs.getInt("cod_journal"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return document;
	}


	// Return all documents of the database
	public List<Document> getDocumentList(){

		List<Document> resultList = new ArrayList<Document>();
		sql ="";
		sql = "SELECT * FROM document";
		connDB = iniServices.getDB();
		ResultSet rs = connDB.runSql(sql);

		if (rs != null){
			try {
				while (rs.next()) {
					Document document = new Document();
					document.setCodDocumentUt(rs.getString("cod_document_ut"));
					document.setTitleTi(rs.getString("title_ti"));
					document.setLanguageTi(rs.getString("language_la"));
					document.setAbstractAb(rs.getString("abstract_ab"));
					document.setVolumenVl(rs.getString("volumen_vl"));
					document.setYearPublicationPy(rs.getString("year_publication_py"));
					document.setDatePublicationPd(rs.getString("date_publication_pd"));
					document.setTitleSourceDi(rs.getString("title_source_di"));
					document.setIndexKewordsId(rs.getString("index_kewords_id"));
					document.setAuthorKewordsDe(rs.getString("author_kewords_de"));
					document.setCitedCountNr(rs.getString("cited_count_nr"));
					document.setCitedTotalZ9(rs.getString("cited_total_z9"));
					document.setCitedReferenceTc(rs.getString("cited_reference_tc"));
					document.setPageCountPg(rs.getString("page_count_pg"));
					document.setPageBp(rs.getString("page_bp"));
					document.setPageEp(rs.getString("page_ep"));
					document.setCodCDocument(rs.getInt("cod_document_type"));
					document.setCodFunding(rs.getInt("cod_publication_type"));
					document.setCodJournal(rs.getInt("cod_journal"));
					resultList.add(document);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

}
