package anabi.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Document;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class DocumentServices {


	private Record keyRecord;
	private Document document;
	private List<Document> listDocument = new ArrayList<Document>();
	private InitServices iniServices;
	private AuthorServices authorServi;
	private JournalServices journalServi;
	private FundingServices fundingServi;
	private AffiliationServices affiliationServi;
	private ConnectionDB db;


	public DocumentServices(){
		iniServices = InitServices.getInstances();
		db = iniServices.getDB();

	}


	/**
	 * Crea lista de documentos.
	 *
	 * @param record
	 */
	public void setListDocument(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String categoryDocument = "";
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

		categoryDocument = record.get("PT");
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
		
		iniServices = InitServices.getInstances();
		journalServi = iniServices.getJournalServi();
		fundingServi = iniServices.getFundingServi();
		authorServi = iniServices.getAuthorServices();
		affiliationServi = iniServices.getAffiliationServi();

		Integer codJournal = journalServi.getCodJournal(keyRecord);
		Integer codFunding = fundingServi.getCodFunding(keyRecord);
		//Integer codTypeDocument = 
		List<Integer> codAuthorList = authorServi.getAuthorList(keyRecord);
		List<Integer> listCodAffiliation = affiliationServi.getListAffiliation(keyRecord);
		
		
		
//		addDocument(keyRecord.getCodDocument(), titleTI, languageLA,
//					abstractAB, volumenVL, yearPY, datePD,
//					titleSourceDI, indexKeywords, authorKeywords,
//					citedNR, citedZ9, citedTC, countPage,beginPage,endPage,
//					documentTypeDT,codC codJournal,codFunding
//					//Integer codTypeDocument, Integer codCategoryDocument, Integer codJournal, Integer codOrgFinancia
//					
//				);
		
		
		document = new Document(keyRecord.getCodDocument(), titleTI, languageLA,
								abstractAB, volumenVL, yearPY, datePD,
								titleSourceDI, indexKeywords, authorKeywords, 
								citedNR, citedZ9, citedTC, 
								countPage, documentTypeDT, keyRecord );
		

		listDocument.add(document);

	}

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

	public Integer countDocuments(){

		return listDocument.size();
	}

	public Document getDocument(Record keyrecord){

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

	public Document findByRecord (Integer idRecord){

		return listDocument.get(listDocument.size() - idRecord);
	}

	public Document findByIdWos (String wosUT){
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

	public Document findByTitle (String title){
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

	public List<Document> getListAllDocuments(){
		return listDocument;
	}

	public List<String> getListNameDocument(){
		List<String> listResult = new ArrayList<String>();

		for(Document objDocument : listDocument){
			listResult.add(objDocument.getTitleTi());
		}
		return listResult;
	}


	public void addDocument(String codArticleUT, String titleTI, String languageLA, String abstractAB,
			String volumenVL, String yearPublicationPY, String datePublicationPD, String titleSourceDI,
			String indexkewordsID, String authKewordsDE, String citedCountNR, String citedTotalZ9, 
			String citedReferenceTC, String pageCountPG, String pageBeginBP, String pageEndEP, 
			Integer codTypeDocument, Integer codCategoryDocument, Integer codJournal, Integer codOrgFinancia){

		String sql = "INSERT INTO document values("+codArticleUT+","+titleTI+","+languageLA+","+abstractAB+","+
				volumenVL+","+yearPublicationPY+","+datePublicationPD+","+
				titleSourceDI+","+indexkewordsID+","+authKewordsDE+","+
				citedCountNR+","+citedTotalZ9+","+citedReferenceTC+","+
				pageCountPG+","+pageBeginBP+","+pageEndEP+","+
				codTypeDocument+","+codCategoryDocument+","+codJournal+","+codOrgFinancia+
				")";

		db.runSql(sql);


	}

	public void deleteDocument(String idDocument){

		String sql = "DELETE FROM document WHERE cod_document_ut="+idDocument+")";
		db.runSql(sql);
	}


	public void deleteAllDocument(){
		
		String sql = "DELETE FROM document";
		db.runSql(sql);
	}


	public Document findByCod(String idDocument){

		idDocument = idDocument.trim();
		String sql = "SELECT * FROM document WHERE cod_document_ut="+idDocument;
		ResultSet rs = db.runSql(sql);
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

	public List<Document> getDocumentList(){
		List<Document> resultList = new ArrayList<Document>();
		String sql = "SELECT * FROM document";
		ResultSet rs = db.runSql(sql);
		  
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
