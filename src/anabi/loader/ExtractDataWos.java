
package anabi.loader;


import anabi.models.Record;
import anabi.services.AffiliationServices;
import anabi.services.AuthorServices;
import anabi.services.DocumentServices;
import anabi.services.FundingServices;
import anabi.services.JournalServices;
import anabi.services.PublisherServices;
import anabi.services.DocumentTypeServices;
import anabi.services.PublicationTypeServices;
import anabi.utilities.InitServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author yosamac
 */
public class ExtractDataWos {


	private File fileTxt;
	private List<Object> listRecords;
	private Record keyRecord;
	private HashMap<String, String> hashRecord;


	private AuthorServices authorServi;
	private AffiliationServices affiliationServi;
	private FundingServices fundingServi;
	private PublisherServices  publisherServi;
	private JournalServices journalServi;
	private DocumentServices documentServi;
	private DocumentTypeServices typeDocumentServi;
	private PublicationTypeServices typePublicationServi;



	public ExtractDataWos(File file) {

		fileTxt = file;
	}

	public boolean readFile() {
		boolean result = false;
		String line;
		String head;
		String headInsert = "";
		String headValue = "";

		HashMap<String, String> record = new HashMap<>();
		listRecords = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileTxt));

			while ((line = br.readLine()) != null) {
				if (line.length() > 1) {
					result = true;
					head = line.substring(0, 2);
					if (head.equals("ER")) {
						record.put(headInsert, headValue);
						listRecords.add(record);
						record = new HashMap<>();
					} else if (head.equals("  ")) {
						headValue += "\n" + line.substring(3);
					} else if (!head.equals("EF")) {
						if (!headInsert.isEmpty()) {
							record.put(headInsert, headValue);
						}

						headInsert = head;
						headValue = line.substring(3);
					}
				}
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("Fichero no extiste.");
			result = false;
		} catch (IOException ioe) {
			System.out.println("Fichero incompatible.");
			result = false;
		}
		return result;
	}


	public List<Object> getListRecords(){
		return listRecords;
	}


	/**
	 * Prepara los record para ser insertados insertados
	 */
	public void loadRecords() {

		int idRecord = 0;
		String idDocument = "";

		InitServices iniServices = InitServices.getInstances();

		iniServices.startServices();
		iniServices.startConnection();
		
		authorServi = iniServices.getAuthorServices();
		affiliationServi = iniServices.getAffiliationServi();
		fundingServi = iniServices.getFundingServi();
		journalServi = iniServices.getJournalServi();
		publisherServi = iniServices.getPublisherServi();
		documentServi = iniServices.getDocumentServi();
		
		for (Object listRecord : listRecords) {

			hashRecord =  (HashMap<String, String>) listRecord;
			idRecord += 1;
			idDocument = hashRecord.get("UT");

			keyRecord = new Record (idRecord,idDocument);

			System.out.println("========================");
			System.out.println("Registro: "+keyRecord.getIDRecord()+ ". ID Documento: "+keyRecord.getCodDocument());
			System.out.println("========================");

			System.out.println("Inserting Author");
			authorServi.setListAuthor(hashRecord, keyRecord);

			System.out.println("Inserting Affiliation");
			affiliationServi.setListAffiliation(hashRecord, keyRecord);

			System.out.println("Inserting Funding");
			fundingServi.setListFunding(hashRecord, keyRecord);

			System.out.println("Inserting Publisher");
			publisherServi.setListPublisher(hashRecord, keyRecord);
			
			System.out.println("Inserting Journal");
			journalServi.setListJournal(hashRecord, keyRecord);

			System.out.println("Inserting Document");
			documentServi.setListDocument(hashRecord, keyRecord);
			
		}

		System.out.println("=========================================");

		System.out.println("CANTIDAD TOTAL AUTORES: " + authorServi.countAuthors());
		System.out.println("CANTIDAD TOTAL AFILIACIONS: " +affiliationServi.countAffiliations() );
		System.out.println("CANTIDAD TOTAL DOCUMENTOS: " + documentServi.countDocuments());
		System.out.println("CANTIDAD TOTAL REVISTA: " + journalServi.countJournals());
		System.out.println("CANTIDAD TOTAL EDITORIALES: " + publisherServi.countPublishers());
		System.out.println("CANTIDAD TOTAL FINANCIERAS: " + fundingServi.countFundings());
		System.out.println("=========================================");

//		int row = 5;
//		try {
//			System.out.println("DOCUMENTO: " + documentServi.findDocumentByRecord(row).getCodDocumentUt());
//			System.out.println("CANTIDAD AUTORES: "+documentServi.findDocumentByRecord(row).getAuthorList().size());
//			System.out.println("CANTIDAD AFILIACION : "+ documentServi.findDocumentByRecord(row).getListCodAffiliation().size());	
//			System.out.println("REVISTA: "+ documentServi.findDocumentByRecord(row).getCodJournal());	
//			System.out.println("FINANCIERA: "+ documentServi.findDocumentByRecord(row).getCodFunding());
//
//		}catch(NullPointerException npe){
//System.out.println("Exeception "+ npe.getMessage());
//			System.out.println("Documento: " + documentServi.findDocumentByRecord(row).getCodDocumentUt()+
//					" \nCantidad autores: "+ authorServi.getCodAuthorList(documentServi.findDocumentByRecord(row).getRecord()).size()+
//					" \n Cantidad Afiliacion : "+ documentServi.findDocumentByRecord(row).getListCodAffiliation().size()+
//					" \nRevista : "+ documentServi.findDocumentByRecord(row).getCodJournal()+
//					" \nOrganizacion Financiera: + fundingServi.getJournal(documentServi.getDocument(row).getRecord()).getNameFu() ");
//
//		}

	}


}
