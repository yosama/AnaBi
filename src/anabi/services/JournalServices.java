package anabi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.model.Journal;
import anabi.model.Record;

public class JournalServices {

	private Record keyRecord;
	private Journal journal;
	private List<Journal> listJournal = new ArrayList<Journal>();

	public JournalServices(){}
	
	/**
	 * Carga una revista
	 *
	 * @param record
	 */
	public void setListJournal(HashMap<String, String> record, Record key) {
		
		this.keyRecord = key;

		String nameJournalSO = record.get("SO");
		String issnSN = record.get("SN");
		String categoryWC = record.get("WC");
		String researchSC = record.get("SC");
		String issueIS = record.get("IS");
		String sourceAbbJ9 = record.get("J9");
		String sourceAbbJI = record.get("JI");

		journal = new Journal (nameJournalSO, issnSN, categoryWC, researchSC, issueIS, sourceAbbJ9, sourceAbbJI, keyRecord);

		listJournal.add(journal);

	}


	public List<Journal> getListJournal (){
		return listJournal;
	}


	public Journal getJournal (Record keyrecord){

		Journal result = null;
		Integer idRow = 0;
		String idDocument = "";

		for (Journal objJournal : listJournal ){

			idRow = objJournal.getRecord().getIDRecord();
			idDocument = objJournal.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				result = objJournal;
				System.out.println("Revista encontrada");
			} else {
				//System.out.println("Revista no encontrada");
			}
		}
		return result;
	}

	public Integer countJournals(){
		
		return listJournal.size();
	}
}
