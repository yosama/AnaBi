package anabi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.model.Document;
import anabi.model.Record;

public class DocumentServices {


	private Record keyRecord;
	private Document document;
	private List<Document> listDocument = new ArrayList<Document>();
	private InitServices iniServices;
	private AuthorServices authorServi;
	private JournalServices journalServi;
	private FundingServices fundingServi;
	private AffiliationServices affiliationServi;


	public DocumentServices(){}


	/**
	 * Crea lista de documentos.
	 *
	 * @param record
	 */
	public void setListDocument(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String documentTypeDT = "";
		String titleTI = "";
		String languageLA = "";
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

		documentTypeDT = record.get("DT");
		titleTI = record.get("TI");
		languageLA = record.get("LA");
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

		document = new Document(keyRecord.getCodDocument(), titleTI, languageLA,
				abstractAB, volumenVL, yearPY, datePD,
				titleSourceDI, indexKeywords, authorKeywords, citedNR, 
				Integer.parseInt(citedZ9), Integer.parseInt(citedTC), 
				countPage, documentTypeDT, keyRecord );



		if ( validateField(beginPage) == true ){
			document.setPageBp(beginPage);
		} else { 
			document.setPageBp("SPI"); // SPI = Sin pagina inicial 
		}

		if (validateField(endPage) == true){
			document.setPageEp(endPage);
		} else { 
			document.setPageEp("SPF"); // SPF = Sin pagina final
		}

		iniServices = InitServices.getInstances();
		journalServi = iniServices.getJournalServi();
		fundingServi = iniServices.getFundingServi();
		authorServi = iniServices.getAuthorServices();
		affiliationServi = iniServices.getAffiliationServi();

		document.setJournal(journalServi.getJournal(keyRecord));
		document.setFunding(fundingServi.getFunding(keyRecord));
		document.setAuthorList(authorServi.getAuthorList(keyRecord));
		document.setAffiliationList(affiliationServi.getListAffiliation(keyRecord));


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


	


}
