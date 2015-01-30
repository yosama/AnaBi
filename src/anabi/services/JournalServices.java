package anabi.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Journal;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class JournalServices {

	private Record keyRecord;
	private Journal journal;
	private Integer codJournal;
	private PublisherServices  publisherServices;
	private InitServices iniServices ;
	private ConnectionDB connDB;
	private String sql;

	private List<Journal> listJournal;

	public JournalServices(){
		iniServices  = iniServices.getInstances();

		listJournal = new ArrayList<Journal>();
		codJournal = 0;
	}

	/**
	 * Carga una revista
	 *
	 * @param record
	 */
	public void setListJournal(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String issueIS ="";

		String nameJournalSO = record.get("SO");
		String issnSN = record.get("SN");
		String categoryWC = record.get("WC");
		String researchSC = record.get("SC");
		issueIS = record.get("IS");
		String sourceAbbJ9 = record.get("J9");
		String sourceAbbJI = record.get("JI");
		
		codJournal +=1;
		addJournal(codJournal,nameJournalSO, issnSN, categoryWC, researchSC, issueIS, sourceAbbJ9, sourceAbbJI);
		
		journal = new Journal (codJournal,nameJournalSO, issnSN, categoryWC, researchSC, issueIS, sourceAbbJ9, sourceAbbJI, keyRecord);
		listJournal.add(journal);

	}


	public List<Journal> getListJournal (){
		return listJournal;
	}


	public Integer getCodJournal (Record keyrecord){

		Integer resultCodJournal = null;
		Integer idRow = 0;
		String idDocument = "";

		for (Journal objJournal : listJournal ){

			idRow = objJournal.getRecord().getIDRecord();
			idDocument = objJournal.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				resultCodJournal = objJournal.getCodJournal();
				System.out.println("Revista encontrada");
			} else {
				//System.out.println("Revista no encontrada");
			}
		}
		return resultCodJournal;
	}

	public Integer countJournals(){

		return listJournal.size();
	}



	public void addJournal(Integer idJournal, String nameSO,String issnSO, String webScienceWC, String researchAreaSC,
			String issueIS, String sourceAbbreJ9, String sourceAbbreJI){

		publisherServices = iniServices.getPublisherServi();
		Integer codPublisher = publisherServices.getCodPublisher();

		sql = "";
		sql = "INSERT INTO journal VALUES ("+idJournal+",\""+nameSO+"\",\""+issnSO+"\",\""+webScienceWC+"\",\""+researchAreaSC+"\",\""+issueIS+"\",\""+sourceAbbreJ9+"\",\""+sourceAbbreJI+"\","+codPublisher+")";
		connDB = iniServices.getDB();	
		//System.out.println(sql);
		connDB.runSql(sql);
	}


	public void deleteAllJournal (){
		sql = "";
		sql = "DELETE FROM journal";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}

	public void deleteJournal(Integer idJournal){

		sql = "";
		sql = "DELETE FROM journal WHERE cod_journal="+idJournal;
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}



	public Journal findByName (String nameJournal){

		journal = null;
		nameJournal = nameJournal.trim();
		sql = "";
		sql = "SELECT * FROM journal WHERE name_so='"+nameJournal+"'";
		connDB = iniServices.getDB();		
		ResultSet rs = connDB.runSql(sql);

		try {
			while( rs.next()){
				journal = new Journal();
				journal.setCodJournal(rs.getInt("cod_journal"));
				journal.setNameSo(rs.getString("name_so"));
				journal.setIssnSo(rs.getString("issn_sn"));
				journal.setWebSciencewc(rs.getString("webScience_wc"));
				journal.setResearchAreasc(rs.getString("research_area_sc"));
				journal.setIssueIs(rs.getString("issue_is"));
				journal.setSourceAbbrej9(rs.getString("source_abbre_j9"));
				journal.setSourceAbbreji(rs.getString("source_abbre_ji"));
				journal.setCodPublisher(rs.getInt("cod_publisher"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return journal;
	}

}
